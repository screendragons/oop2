package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Specification;
import practicumopdracht.views.SpecificationView;
import practicumopdracht.views.View;

import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SpecificationController extends Controller{
    private SpecificationView specificationView;
//    private ArrayList<Specification> specifications = new ArrayList<>();
    private ObservableList<Specification> specificationObservableList;

    public SpecificationController() {
        specificationView = new SpecificationView();

        // link multiple actions to the save button
        specificationView.getButtonSave().setOnAction(event ->
        {
                if(validationSpec(specificationView)) {
                    // save specification
                    saveSpecification(specificationView, specificationObservableList);
                }
        });
        // switch to master view
        specificationView.getButtonSwitch().setOnAction(event -> switchToSmartphone());

        // edit button
        specificationView.getButtonEdit().setOnAction(event -> editSpecification());

        // delete button
        specificationView.getButtonDelete().setOnAction(event -> deleteSpecification());

        // observable list
        specificationObservableList = FXCollections.observableArrayList();
    }


    private void saveSpecification(SpecificationView detailView, ObservableList<Specification> observableList) {
        // inch
        double inchField = Double.parseDouble(detailView.getTextFieldInch().getText().trim());

        // height
        double heightField = Double.parseDouble(detailView.getTextFieldHeight().getText().trim());

        // width
        double widthField = Double.parseDouble(detailView.getTextFieldWidth().getText().trim());

        // thickness
        double thicknessField = Double.parseDouble(detailView.getTextFieldThickness().getText().trim());

        // finger print sensor
        boolean fingerprintSensor = detailView.getCheckBoxFingerprintSensor().isSelected();

        Object operatingSystem = detailView.getComboBoxOperatingSystem().getValue();

        String noteField = detailView.getTextAreaNote().getText();

        observableList.add(new Specification(
                inchField, heightField, widthField, thicknessField, fingerprintSensor,
                (String) operatingSystem, noteField
        ));
    }

    private boolean validationSpec(SpecificationView specificationView) {
        boolean isTrue = true;

        StringBuilder errorStringBuilder = new StringBuilder();

        String inchString = specificationView.getTextFieldInch().getText().trim();
        double inch = 0;

        // inch
        if(inchString.isEmpty()) {
            errorStringBuilder.append("- Amount inch is required\n");
            specificationView.getTextFieldInch().setStyle("-fx-border-color: #ff0000");
            isTrue = false;
        } else {
            try {
                inch = Double.parseDouble(inchString);

                if(inch < 1) {
                    errorStringBuilder.append("- Amount inch is too small and can't be smaller than 1\n");
                }
            }
            catch (Exception ex) {
                errorStringBuilder.append("- Amount inch is not a valid number\n");
            }
        }

        // height
        String heightString = specificationView.getTextFieldHeight().getText().trim();
        double height = 0;

        if(heightString.isEmpty()) {
            errorStringBuilder.append("- Amount height is required\n");
            specificationView.getTextFieldHeight().setStyle("-fx-border-color: #ff0000");
            isTrue = false;
        } else {
            try {
                height = Double.parseDouble(heightString);
                if(height < 0.1) {
                    errorStringBuilder.append("- Amount height is too small and can't be smaller than 0.1\n");
                }
            }
            catch (Exception ex) {
                errorStringBuilder.append("- Amount height is not a valid number\n");
            }
        }

        // width
        String widthString = specificationView.getTextFieldWidth().getText().trim();
        double width = 0;

        if(widthString.isEmpty()) {
            errorStringBuilder.append("- Amount width is required\n");
            specificationView.getTextFieldWidth().setStyle("-fx-border-color: #ff0000");
            isTrue = false;

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

        if(thicknessString.isEmpty()) {
            errorStringBuilder.append("- Amount thickness is required\n");
            specificationView.getTextFieldThickness().setStyle("-fx-border-color: #ff0000");
            isTrue = false;

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

        String operatingSystem = specificationView.getComboBoxOperatingSystem().getValue();

        if(operatingSystem == null) {
            errorStringBuilder.append("- Amount operating system is required\n");
            specificationView.getComboBoxOperatingSystem().setStyle("-fx-border-color: #ff0000");
            isTrue = false;
        }

        // check if there is an error
        if (errorStringBuilder.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(errorStringBuilder.toString());
            // blokkeert de uitvoering van de code
            alert.showAndWait();

            // else show information
        } else {
            try {
                //reset fields
                specificationView.getTextFieldInch().setText("");
                specificationView.getTextFieldHeight().setText("");
                specificationView.getTextFieldWidth().setText("");
                specificationView.getTextFieldThickness().setText("");
                specificationView.getCheckBoxFingerprintSensor().setSelected(false);
                specificationView.getComboBoxOperatingSystem().setPromptText("What is the operatingsystem?");
                specificationView.getTextAreaNote().setText("");

            } catch (Exception ex) {
                System.out.println("Oh nee!");
            }

            Specification specification = new Specification(
                    inch, height, width, thickness, operatingSystem
            );

            specificationObservableList.add(new Specification(inch, height, width, thickness, operatingSystem));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(specification.toString());
            alert.showAndWait();
        }

        showSpecification();
        return isTrue;
    }

    private void showSpecification() {
        ObservableList<Specification> specList = FXCollections.observableArrayList(specificationObservableList);
        specificationView.getListView().setItems(specList);
    }

    public void editSpecification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("You clicked on the edit button!");
        alert.showAndWait();
    }

    public void deleteSpecification() {
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