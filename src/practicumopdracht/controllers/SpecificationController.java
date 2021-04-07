package practicumopdracht.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.DetailComparator;
import practicumopdracht.data.SpecificationDAO;
import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;
import practicumopdracht.views.SpecificationView;
import practicumopdracht.views.View;

/**
 * Functionality: Controller of the detail
 *
 * @author Chi Yu Yeung
 */
public class SpecificationController extends Controller {
    private final ButtonType YES = new ButtonType("Yes", ButtonBar.ButtonData.YES);
    private final ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.NO);
    private SpecificationDAO specificationDAO;
    private SpecificationView specificationView;
    private Specification selectedSpecification;
    private DetailComparator detailComparator;
    private Smartphone selectedSmartphone;
    private Smartphone master;

    /**
     * Constructor to define all the attributes that are used and with the functions for the buttons
     */
    public SpecificationController(Smartphone smartphone) {
        specificationDAO = MainApplication.getSpecificationDAO();

        specificationView = new SpecificationView();

        selectedSmartphone = smartphone;

        // master combobox
        ObservableList masterData = FXCollections.observableArrayList(MainApplication.getSmartphoneDAO().getAll());
        specificationView.getComboBoxMaster().setItems(masterData);
        specificationView.getComboBoxMaster().setValue(smartphone);
        // to switch to another master item in the combobox with the details
        specificationView.getComboBoxMaster().getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldSmartphone, newSmartphone) -> {
                    selectedSmartphone = newSmartphone;
                    ObservableList detailList = FXCollections.observableArrayList(
                            MainApplication.getSpecificationDAO().getAllFor(selectedSmartphone)
                    );
                    specificationView.getListView().setItems(detailList);
                    // to clear the fields if you select an other smartphone
                    resetFields();
                })
        );

        // list in specifications
        ObservableList detailList = FXCollections.observableArrayList(MainApplication.getSpecificationDAO().getAllFor(smartphone));
        specificationView.getListView().setItems(detailList);

        // link validation to the save button
        // for the "normal" DAO's
        specificationView.getButtonSave().setOnAction(event -> validationSaveBtn());

        // new button
        specificationView.getButtonNew().setOnAction(event -> newSpec());

        // edit button
        specificationView.getButtonEdit().setOnAction(event -> edit(selectedSpecification));

        // delete button
        specificationView.getButtonDelete().setOnAction(event -> delete());

        // switch to master view
        specificationView.getButtonSwitch().setOnAction(event -> switchToSmartphone());

        // radio buttons sort
        specificationView.getBtnSortAscTypeOne().setOnAction(event -> sortAscTypeOne());

        specificationView.getBtnSortDescTypeOne().setOnAction(event -> sortDescTypeOne());

        specificationView.getBtnSortAscTypeTwo().setOnAction(event -> sortAscTypeTwo());

        specificationView.getBtnSortDescTypeTwo().setOnAction(event -> sortDescTypeTwo());

        // replaces the old value with the new by editing
        // the listview
        specificationView.getListView().getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldSpecification, newSpecification) -> {
                    if (newSpecification == null || oldSpecification == newSpecification) {
                        return;
                    }

                    enableNewButton();
                    enableDeleteButton();
                    enableEditButton();
                    edit(oldSpecification);

                    specificationDAO.getAll();
                });

        enableSaveButton();
        disableNewButton();
        disableEditButton();
        disableDeleteButton();
    }

    /**
     * Function that combines the validation and save funtion
     */
    private void validationSaveBtn() {
        if (validation()) {
            // save specification
            save();
        }
    }

    /**
     * Save function
     */
    private void save() {
        // get the selected item of the master combobox
        master = specificationView.getComboBoxMaster().getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to save this data?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The data is saved");
            succes.show();
            // inch
            double inch = 0;
            // height
            double height = 0;
            // width
            double width = 0;
            // thickness
            double thickness = 0;

            try {
                // inch
                inch = Double.parseDouble(specificationView.getTextFieldInch().getText().trim());
                // height
                height = Double.parseDouble(specificationView.getTextFieldHeight().getText().trim());
                // width
                width = Double.parseDouble(specificationView.getTextFieldWidth().getText().trim());
                // thickness
                thickness = Double.parseDouble(specificationView.getTextFieldThickness().getText().trim());
            } catch (Exception e) {

            }

            // finger print sensor
            boolean fingerprintSensor = specificationView.getCheckBoxFingerprintSensor().isSelected();

            // operating system
            Object operatingSystem = specificationView.getComboBoxOperatingSystem().getValue();

            // note
            String note = specificationView.getTextAreaNote().getText();

            // if the selected smartphone is equal to null, add or update
            if (selectedSpecification == null) {
                Specification specification = new Specification(
                        master,
                        inch,
                        height,
                        width,
                        thickness,
                        fingerprintSensor,
                        operatingSystem,
                        note
                );
                MainApplication.getSpecificationDAO().addOrUpdate(specification);

            }
            // else update the existing specification
            else {
                selectedSpecification.setInch(inch);
                selectedSpecification.setHeight(height);
                selectedSpecification.setWidth(width);
                selectedSpecification.setThickness(thickness);
                selectedSpecification.setFingerprintSensor(fingerprintSensor);
                selectedSpecification.setNote(note);
                MainApplication.getSpecificationDAO().addOrUpdate(selectedSpecification);
            }
            show();
            resetFields();
        }

        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The data is not saved");
            fail.show();
        }

        show();
    }

    /**
     * The validation for when something wants to be saved
     *
     * @return
     */
    private boolean validation() {
        StringBuilder errorStringBuilder = new StringBuilder();

        String inchString = specificationView.getTextFieldInch().getText().trim();
        double inch = 0;

        // inch
        // if the textfield is empty, then show an alert
        if (inchString.isEmpty()) {
            errorStringBuilder.append("- Amount inch is required\n");
            specificationView.getTextFieldInch().setStyle("-fx-border-color: #ff0000");
        } else {
            try {
                inch = Double.parseDouble(inchString);

                if (inch < 1) {
                    errorStringBuilder.append("- Amount inch is too small and can't be smaller than 1\n");
                }
            } catch (Exception ex) {
                errorStringBuilder.append("- Amount inch is not a valid number\n");
            }
        }

        // height
        String heightString = specificationView.getTextFieldHeight().getText().trim();
        double height = 0;

        // if the textfield is empty, then show an alert
        if (heightString.isEmpty()) {
            errorStringBuilder.append("- Amount height is required\n");
            specificationView.getTextFieldHeight().setStyle("-fx-border-color: #ff0000");
        } else {
            try {
                height = Double.parseDouble(heightString);
                if (height < 0.1) {
                    errorStringBuilder.append("- Amount height is too small and can't be smaller than 0.1\n");
                }
            } catch (Exception ex) {
                errorStringBuilder.append("- Amount height is not a valid number\n");
            }
        }

        // width
        String widthString = specificationView.getTextFieldWidth().getText().trim();
        double width = 0;

        // if the textfield is empty, then show an alert
        if (widthString.isEmpty()) {
            errorStringBuilder.append("- Amount width is required\n");
            specificationView.getTextFieldWidth().setStyle("-fx-border-color: #ff0000");
        } else {
            try {
                width = Double.parseDouble(widthString);
                if (width < 1) {
                    errorStringBuilder.append("- Amount width is too small and can't be smaller than 0.1\n");
                }
            } catch (Exception ex) {
                errorStringBuilder.append("- Amount width is not a valid number\n");
            }
        }

        // thickness
        String thicknessString = specificationView.getTextFieldThickness().getText().trim();
        double thickness = 0;

        // if the textfield is empty, then show an alert
        if (thicknessString.isEmpty()) {
            errorStringBuilder.append("- Amount thickness is required\n");
            specificationView.getTextFieldThickness().setStyle("-fx-border-color: #ff0000");
        } else {
            try {
                thickness = Double.parseDouble(thicknessString);
                if (thickness < 0.1) {
                    errorStringBuilder.append("- Amount thickness is too small and can't be smaller than 0.1\n");
                }
            } catch (Exception ex) {
                errorStringBuilder.append("- Amount thickness is not a valid number\n");
            }
        }

        // operating system
        String operatingSystem = specificationView.getComboBoxOperatingSystem().getValue();

        if (operatingSystem == null) {
            errorStringBuilder.append("- Amount operating system is required\n");
            specificationView.getComboBoxOperatingSystem().setStyle("-fx-border-color: #ff0000");
        }

        // check if there is an error
        if (errorStringBuilder.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(errorStringBuilder.toString());
            // blocks the performance of the code
            alert.showAndWait();
            return false;

            // else show information
        } else {
            boolean fingerprintSensor = specificationView.getCheckBoxFingerprintSensor().isSelected();
            String noteField = specificationView.getTextAreaNote().getText();

            master = specificationView.getComboBoxMaster().getSelectionModel().getSelectedItem();

            Specification specification = new Specification(
                    master, inch, height, width, thickness, fingerprintSensor, operatingSystem, noteField
            );

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(specification.toString());
            alert.showAndWait();

            return true;
        }
    }

    /**
     * reset the fields
     */
    private void resetFields() {
        specificationView.getTextFieldInch().setText("");
        specificationView.getTextFieldHeight().setText("");
        specificationView.getTextFieldWidth().setText("");
        specificationView.getTextFieldThickness().setText("");
        specificationView.getCheckBoxFingerprintSensor().setSelected(false);
        specificationView.getComboBoxOperatingSystem().getSelectionModel().clearSelection();
        specificationView.getTextAreaNote().setText("");
        // set the selected specification to default again
        selectedSpecification = null;
    }

    /**
     * Show the information in an obersableList by using the getAll function
     */
    private void show() {
        ObservableList<Specification> specList = FXCollections.observableArrayList(MainApplication.getSpecificationDAO().getAllFor(selectedSmartphone));
        specificationView.getListView().setItems(specList);
    }

    private void newSpec() {
        Alert newSpec = new Alert(Alert.AlertType.INFORMATION, "Do you want to create a new specification?", YES, NO);
        newSpec.showAndWait();

        if (newSpec.getResult() == YES) {
            Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "You can now create a new specification");
            succes.show();
        }

        if (newSpec.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "Nothing will be created.");
            fail.show();
        }

        resetFields();
    }

    /**
     * Edit the specification
     *
     * @param newSpecification
     */
    private void edit(Specification newSpecification) {
        selectedSpecification = specificationView.getListView().getSelectionModel().getSelectedItem();

        // using setters because you are editing something
        if (selectedSpecification != null) {
            try {
                double inch = selectedSpecification.getInch();
                specificationView.getTextFieldInch().setText(String.valueOf(inch));
            } catch (Exception e) {

            }
            try {
                double height = selectedSpecification.getHeight();
                specificationView.getTextFieldHeight().setText(String.valueOf(height));
            } catch (Exception e) {

            }

            try {
                double width = selectedSpecification.getWidth();
                specificationView.getTextFieldWidth().setText(String.valueOf(width));
            } catch (Exception e) {

            }

            try {
                double thickness = selectedSpecification.getThickness();
                specificationView.getTextFieldThickness().setText(String.valueOf(thickness));
            } catch (Exception e) {

            }

            // set finger print
            specificationView.getCheckBoxFingerprintSensor().setSelected(selectedSpecification.isFingerprintSensor());

            // set the operating system
            specificationView.getComboBoxOperatingSystem().getSelectionModel().select(
                    (String) selectedSpecification.getOperatingSystem()
            );

            specificationView.getTextAreaNote().setText(selectedSpecification.getNote());
        }
    }

    /**
     * Delete the created specification
     */
    public void delete() {
        selectedSpecification = specificationView.getListView().getSelectionModel().getSelectedItem();

        // unselect item when the application is started, preventing from deleting something by accident
        if (selectedSpecification == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to delete this item?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            // delete the specification
            specificationDAO.remove(selectedSpecification);
            Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The data is deleted");
            succes.show();
        }

        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The data is not deleted");
            fail.show();
        }

        show();
        resetFields();
    }

    /**
     * Switch to the second screen
     */
    public void switchToSmartphone() {
        MainApplication.switchController(new SmartphoneController());
    }

    /**
     * Sort ascending by inch
     */
    private void sortAscTypeOne() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(false);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }

    /**
     * Sort descending by inch
     */
    private void sortDescTypeOne() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(true);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }

    /**
     * Sort ascending by inch
     * But if two or more are the same amount of inches, get the height to ascend on
     */
    private void sortAscTypeTwo() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(false);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }

    /**
     * Sort descending by inch
     * But if two or more are the same amount of inches, get the height to descend on
     */
    private void sortDescTypeTwo() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(true);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }


    /**
     * Enable and disable the buttons
     */
    private void enableSaveButton() {
        specificationView.getButtonSave().setDisable(false);
    }

    private void disableSaveButton() {
        specificationView.getButtonSave().setDisable(true);
    }

    private void enableNewButton() {
        specificationView.getButtonNew().setDisable(false);
    }

    private void disableNewButton() {
        specificationView.getButtonNew().setDisable(true);
    }

    private void enableEditButton() {
        specificationView.getButtonEdit().setDisable(false);
    }

    private void disableEditButton() {
        specificationView.getButtonEdit().setDisable(true);
    }

    private void enableDeleteButton() {
        specificationView.getButtonDelete().setDisable(false);
    }

    private void disableDeleteButton() {
        specificationView.getButtonDelete().setDisable(true);
    }

    public void enableSwitchButton() {
        specificationView.getButtonSwitch().setDisable(false);
    }

    public void disableSwitchButton() {
        specificationView.getButtonSwitch().setDisable(true);
    }

    /**
     * Get the view of the detail
     *
     * @return
     */
    @Override
    public View getView() {
        return specificationView;
    }
}


