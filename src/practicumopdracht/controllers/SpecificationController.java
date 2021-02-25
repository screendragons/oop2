package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Smartphone;
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
    private ArrayList<Specification> specifications = new ArrayList<>();
    private ObservableList<Specification> specificationObservableList;

    public SpecificationController() {
        specificationView = new SpecificationView();
        // koppel een actie
        specificationView.getButtonSave().setOnAction(event -> saveSpecification());
        specificationView.getButtonSwitch().setOnAction(event -> switchToSmartphone());

//        specificationView.getButtonNew().setOnAction(event -> newSpecification());
        specificationObservableList = FXCollections.observableArrayList();
    }

    private void saveSpecification() {
        // validation
        StringBuilder errorStringBuilder = new StringBuilder();

        // inch
        String inchString = specificationView.getTextFieldInch().getText().trim();
        double inch = 0;

        // height
        String heightString = specificationView.getTextFieldHeight().getText().trim();
        double height = 0;

        // width
        String widthString = specificationView.getTextFieldWidth().getText().trim();
        double width = 0;

        // thickness
        String thicknessString = specificationView.getTextFieldThickness().getText().trim();
        double thickness = 0;

        // finger print sensor
        boolean fingerprintSensor = specificationView.getCheckBoxFingerprintSensor().isSelected();

        String operatingSystem = specificationView.getComboBoxOperatingSystem().getValue();

        String note = specificationView.getTextAreaNote().getText();

        specifications.add(new Specification(inch, height, width, thickness, fingerprintSensor, operatingSystem, note));

        // inch
        // TODO fix save doubles
        if(inchString.isEmpty()) {
            errorStringBuilder.append("- Amount inch is required\n");
            specificationView.getTextFieldInch().setStyle("-fx-border-color: #ff0000");
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
        if(heightString.isEmpty()) {
            errorStringBuilder.append("- Amount height is required\n");
            specificationView.getTextFieldHeight().setStyle("-fx-border-color: #ff0000");
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
        if(widthString.isEmpty()) {
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

        if(thicknessString.isEmpty()) {
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

        if(operatingSystem == null) {
            errorStringBuilder.append("- Amount operating system is required\n");
            specificationView.getComboBoxOperatingSystem().setStyle("-fx-border-color: #ff0000");
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
                specificationView.getCheckBoxFingerprintSensor().setText("");
                specificationView.getComboBoxOperatingSystem().setValue("");
                specificationView.getTextAreaNote().setText("");

            } catch (Exception ex) {
                System.out.println("Oh nee!");
            }

            Specification specification = new Specification(
                    inch, height, width, thickness, fingerprintSensor, operatingSystem, note
            );

            specificationObservableList.add(new Specification(
                    inch, height, width, thickness, fingerprintSensor, operatingSystem, note
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(specification.toString());
            alert.showAndWait();
        }

        showSpecification();
    }

    private void showSpecification() {
        ObservableList<Specification> specList = FXCollections.observableArrayList(specifications);
        specificationView.getListView().setItems(specList);
    }

    public void switchToSmartphone() {
        MainApplication.switchController(new SmartphoneController());
    }

    @Override
    public View getView() {
        return specificationView;
    }
}
