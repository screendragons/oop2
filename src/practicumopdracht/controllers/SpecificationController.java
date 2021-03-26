package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.DetailComparator;
import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;
import practicumopdracht.views.SpecificationView;
import practicumopdracht.views.View;

import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SpecificationController extends Controller {
    private SpecificationView specificationView;
    private DetailComparator detailComparator;

    public SpecificationController(Smartphone smartphone) {
        specificationView = new SpecificationView();

        // link multiple actions to the save button
        specificationView.getButtonSave().setOnAction(event ->
        {
            if (validation(specificationView)) {
                // save specification
                save(specificationView);
            }
            MainApplication.getSpecificationDAO().save();
        });

        // switch to master view
        specificationView.getButtonSwitch().setOnAction(event -> switchToSmartphone());

        // edit button
        specificationView.getButtonEdit().setOnAction(event -> edit());

        // delete button
        specificationView.getButtonDelete().setOnAction(event -> delete());

        specificationView.getBtnSortAscName().setOnAction(event -> sortAscName());

        specificationView.getBtnSortDescName().setOnAction(event -> sortDescName());

        // master
        ObservableList masterData = FXCollections.observableArrayList(MainApplication.getSmartphoneDAO().getAll());
        specificationView.getComboBoxMaster().setItems(masterData);
        specificationView.getComboBoxMaster().setValue(smartphone);

        // list in specifications
        ObservableList detailList = FXCollections.observableArrayList(MainApplication.getSpecificationDAO().getAllFor(smartphone));
        specificationView.getListView().setItems(detailList);
    }

    private void sortAscName() {
        if(this.specificationView.getBtnSortAscName().isSelected()) {
            ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
            detailComparator = new DetailComparator(false);
            FXCollections.sort(specificationObservableList, detailComparator);
            specificationView.getListView().setItems(specificationObservableList);
        }
    }

    private void sortDescName() {
        if(this.specificationView.getBtnSortDescName().isSelected()) {
            ObservableList<Specification> specificationObservableList = specificationView.getListView().getItems();
            detailComparator = new DetailComparator(true);
            FXCollections.sort(specificationObservableList, detailComparator);
            specificationView.getListView().setItems(specificationObservableList);
        }
    }

    private void save(SpecificationView detailView) {
        // inch
        double inchField = 0;
        // height
        double heightField = 0;
        // width
        double widthField = 0;
        // thickness
        double thicknessField = 0;

        try {
            // inch
            inchField = Double.parseDouble(detailView.getTextFieldInch().getText().trim());
            // height
            heightField = Double.parseDouble(detailView.getTextFieldHeight().getText().trim());
            // width
            widthField = Double.parseDouble(detailView.getTextFieldWidth().getText().trim());
            // thickness
            thicknessField = Double.parseDouble(detailView.getTextFieldThickness().getText().trim());
        } catch (Exception e) {

        }

        // finger print sensor
        boolean fingerprintSensor = detailView.getCheckBoxFingerprintSensor().isSelected();

        Object operatingSystem = detailView.getComboBoxOperatingSystem().getValue();

        String noteField = detailView.getTextAreaNote().getText();

        Smartphone master = specificationView.getComboBoxMaster().getSelectionModel().getSelectedItem();

        MainApplication.getSpecificationDAO().addOrUpdate(new Specification(
                inchField, heightField, widthField, thicknessField, fingerprintSensor,
                (String) operatingSystem, noteField, master
        ));

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

    public void edit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("You clicked on the edit button!");
        alert.showAndWait();
    }

    public void delete() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You clicked on the delete button!");
        alert.showAndWait();
    }

    public void switchToSmartphone() {
        MainApplication.switchController(new SmartphoneController());
    }

    @Override
    public View getView() {
        return specificationView;
    }
}


