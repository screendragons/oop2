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
 * Functionality:
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

    public SpecificationController(Smartphone smartphone) {
        specificationDAO = MainApplication.getSpecificationDAO();

        specificationView = new SpecificationView();

        // menu items
        // for the text, object, binairy and fake DAO's
        specificationView.getMenuItemSave().setOnAction(event -> saveToDAO());

        specificationView.getMenuItemLoad().setOnAction(event -> loadFromDAO());

        specificationView.getMenuItemExit().setOnAction(event -> exit());

        // master combobox
        ObservableList masterData = FXCollections.observableArrayList(MainApplication.getSmartphoneDAO().getAll());
        specificationView.getComboBoxMaster().setItems(masterData);
        specificationView.getComboBoxMaster().setValue(smartphone);

        // list in specifications
        ObservableList detailList = FXCollections.observableArrayList(MainApplication.getSpecificationDAO().getAllFor(smartphone));
        specificationView.getListView().setItems(detailList);

        // link validation to the save button
        // for the "normal" DAO's
        specificationView.getButtonSave().setOnAction(event -> validationSaveBtn());

        // new button
        specificationView.getButtonNew().setOnAction(event -> newSpecification());

        // edit button
        specificationView.getButtonEdit().setOnAction(event -> edit(selectedSpecification));

        // delete button
        specificationView.getButtonDelete().setOnAction(event -> delete());

        // switch to master view
        specificationView.getButtonSwitch().setOnAction(event -> switchToSmartphone());

        specificationView.getBtnSortAscTypeOne().setOnAction(event -> sortAscTypeOne());

        specificationView.getBtnSortDescTypeOne().setOnAction(event -> sortDescTypeOne());

        specificationView.getBtnSortAscTypeTwo().setOnAction(event -> sortAscTypeTwo());

        specificationView.getBtnSortDescTypeTwo().setOnAction(event -> sortDescTypeTwo());

        specificationView.getListView().getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldSpecification, newSpecification) -> {
                    if (newSpecification == null || oldSpecification == newSpecification) {
                        return;
                    }

                    enableNewButton();
                    enableDeleteButton();
                    enableSwitchButton();

                    enableEditButton();
                    edit(oldSpecification);
                });

        enableSaveButton();
        disableNewButton();
        disableEditButton();
        disableDeleteButton();
        disableSwitchButton();
    }

    private void newSpecification() {
        // TODO edit new function
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to create a new item?", YES, NO);
        alert.showAndWait();

        double inch = 0;
        try {
            inch = Double.parseDouble(specificationView.getTextFieldInch().getText().trim());
        } catch (Exception e) {

        }

        double height = 0;
        try {
            height = Double.parseDouble(specificationView.getTextFieldHeight().getText().trim());
        } catch (Exception e) {

        }

        double width = 0;
        try {
            width = Double.parseDouble(specificationView.getTextFieldWidth().getText().trim());
        } catch (Exception e) {

        }

        double thickness = 0;
        try {
            thickness = Double.parseDouble(specificationView.getTextFieldThickness().getText().trim());
        } catch (Exception e) {

        }

        // finger print sensor
        boolean fingerprintSensor = specificationView.getCheckBoxFingerprintSensor().isSelected();

        Object operatingSystem = specificationView.getComboBoxOperatingSystem().getValue();

        String note = specificationView.getTextAreaNote().getText();

        Smartphone master = specificationView.getComboBoxMaster().getSelectionModel().getSelectedItem();

        if (alert.getResult() == YES) {
            // if the selected smartphone doesn't exist create one
            if (specificationView == null) {
                MainApplication.getSpecificationDAO().addOrUpdate(new Specification(
                        inch, height, width, thickness, fingerprintSensor,
                        operatingSystem, note, master
                ));
            }
            resetFields();
            Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "You can create an item");
            succes.show();
        }

        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "Item is not created");
            fail.show();
        }

        resetFields();
    }

    private void saveToDAO() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to save this data?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            boolean isSaved = specificationDAO.save();
            if (isSaved) {
                Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The data is saved");
                succes.show();
            }
        }
        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The data is not saved");
            fail.show();
        }
    }

    private void loadFromDAO() {
        specificationDAO.load();
        show();
    }

    private void exit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want close the application?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            Alert deciding = new Alert(Alert.AlertType.WARNING, "Are you sure you want to close the application?",
                    YES, NO);
            deciding.showAndWait();

            if (deciding.getResult() == YES) {
                Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The application will close.");
                succes.show();
                Platform.exit();
            }

            if (deciding.getResult() == NO) {
                Alert fail = new Alert(Alert.AlertType.WARNING, "The application won't close");
                fail.show();
            }
        }
        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The application won't close");
            fail.show();
        }
    }

    private void validationSaveBtn() {
        if (validation(specificationView)) {
            // save specification
            save(specificationView);
        }
    }

    private void save(SpecificationView detailView) {
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
            inch = Double.parseDouble(detailView.getTextFieldInch().getText().trim());
            // height
            height = Double.parseDouble(detailView.getTextFieldHeight().getText().trim());
            // width
            width = Double.parseDouble(detailView.getTextFieldWidth().getText().trim());
            // thickness
            thickness = Double.parseDouble(detailView.getTextFieldThickness().getText().trim());
        } catch (Exception e) {

        }

        // finger print sensor
        boolean fingerprintSensor = detailView.getCheckBoxFingerprintSensor().isSelected();

        Object operatingSystem = detailView.getComboBoxOperatingSystem().getValue();

        String note = detailView.getTextAreaNote().getText();

        Smartphone master = specificationView.getComboBoxMaster().getSelectionModel().getSelectedItem();

        if (selectedSpecification == null) {
            MainApplication.getSpecificationDAO().addOrUpdate(new Specification(
                    inch, height, width, thickness, fingerprintSensor,
                    operatingSystem, note, master
            ));
        } else {
            Specification specificationExists = selectedSpecification;
            selectedSpecification.setInch(inch);
            selectedSpecification.setHeight(height);
            selectedSpecification.setWidth(width);
            selectedSpecification.setThickness(thickness);
            selectedSpecification.setFingerprintSensor(fingerprintSensor);
            selectedSpecification.setNote(note);
            MainApplication.getSpecificationDAO().addOrUpdate(specificationExists);
        }

        show();
        resetFields();
    }

    private boolean validation(SpecificationView specificationView) {
        StringBuilder errorStringBuilder = new StringBuilder();

        String inchString = specificationView.getTextFieldInch().getText().trim();
        double inch = 0;

        // inch
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

            Smartphone master = specificationView.getComboBoxMaster().getSelectionModel().getSelectedItem();

            Specification specification = new Specification(
                    inch, height, width, thickness, fingerprintSensor, operatingSystem, noteField, master
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(specification.toString());
            alert.showAndWait();

            return true;
        }
    }

    private void resetFields() {
        //reset fields
        specificationView.getTextFieldInch().setText("");
        specificationView.getTextFieldHeight().setText("");
        specificationView.getTextFieldWidth().setText("");
        specificationView.getTextFieldThickness().setText("");
        specificationView.getCheckBoxFingerprintSensor().setSelected(false);
        specificationView.getComboBoxOperatingSystem().setPromptText("What is the operatingsystem?");
        specificationView.getTextAreaNote().setText("");
    }

    private void show() {
        ObservableList<Specification> specList = FXCollections.observableArrayList(MainApplication.getSpecificationDAO().getAll());
        specificationView.getListView().setItems(specList);
    }

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

            specificationView.getCheckBoxFingerprintSensor().setSelected(selectedSpecification.isFingerprintSensor());

            specificationView.getTextAreaNote().setText(selectedSpecification.getNote());

            // TODO dit werkt niet
            if (specificationView.getButtonEdit().isPressed()) {
                System.out.println("Hij komt hier");
                disableDeleteButton();
                disableSwitchButton();
            }
        }
    }

    public void delete() {
        selectedSpecification = specificationView.getListView().getSelectionModel().getSelectedItem();

        // unselect item when the application is started, preventing from deleting something by accident
        if (selectedSpecification == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to delete this item?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
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

    public void switchToSmartphone() {
        MainApplication.switchController(new SmartphoneController());
    }

    private void sortAscTypeOne() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(false);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }

    private void sortDescTypeOne() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(true);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }

    private void sortAscTypeTwo() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(false);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }

    private void sortDescTypeTwo() {
        ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
        detailComparator = new DetailComparator(true);
        FXCollections.sort(specificationObservableList, detailComparator);
        specificationView.getListView().setItems(specificationObservableList);
    }


    // enable and disable the buttons
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

    @Override
    public View getView() {
        return specificationView;
    }
}


