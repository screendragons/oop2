package practicumopdracht.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import practicumopdracht.model.Specification;
import practicumopdracht.view.SpecificationView;

import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SpecificationController {

    private SpecificationView specificationView;
    private ArrayList<Specification> specifications = new ArrayList<>();

    public SpecificationController() {
        specificationView = new SpecificationView();
        // koppel een actie
        specificationView.getButtonSave().setOnAction(event -> saveSpecification());
//        specificationView.getButtonNew().setOnAction(event -> newSpecification());
    }

    private void saveSpecification() {
        double inch = specificationView.getTextFieldInch().getValue();
        double height = specificationView.getTextFieldHeight().getValue();
        double width = specificationView.getTextFieldWidth().getValue();
        double thickness= specificationView.getTextFieldThickness().getValue();
        boolean fingerprintSensor = specificationView.getCheckBoxFingerprintSensor().getValue();
        String operatingSystem = specificationView.getTextAreaOperatingSystem().getText();
        specifications.add(new Specification(inch, height, width, thickness, fingerprintSensor, operatingSystem));
    }

    private void showSpecification() {
        ObservableList<Specification> ol = FXCollections.observableArrayList(specifications);
    }
    public SpecificationView getSpecificationView() {
        return specificationView;
    }
}
