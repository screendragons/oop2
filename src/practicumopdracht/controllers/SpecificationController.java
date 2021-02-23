package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public SpecificationController() {
        specificationView = new SpecificationView();
        // koppel een actie
        specificationView.getButtonSave().setOnAction(event -> saveSpecification());
//        specificationView.getButtonNew().setOnAction(event -> newSpecification());
    }

    private void saveSpecification() {
        // TODO vragen
        String inchString = specificationView.getTextFieldInch().getText();
        double inch;
        inch = Double.parseDouble(inchString);

        String heightString = specificationView.getTextFieldHeight().getText();
        double height;
        height = Double.parseDouble(heightString);

        String widthString = specificationView.getTextFieldWidth().getText();
        double width;
        width = Double.parseDouble(widthString);

        String thicknessString = specificationView.getTextFieldThickness().getText();
        double thickness;
        thickness = Double.parseDouble(thicknessString);

        boolean fingerprintSensor = specificationView.getCheckBoxFingerprintSensor().isSelected();
        String operatingSystem = specificationView.getComboBoxOperatingSystem().getValue();

        String notes = specificationView.getTextAreaNotes().getText();

        specifications.add(new Specification(inch, height, width, thickness, fingerprintSensor, operatingSystem, notes));
        showSpecification();
    }

    private void showSpecification() {
        ObservableList<Specification> specList = FXCollections.observableArrayList(specifications);
        specificationView.getListView().setItems(specList);
    }

    @Override
    public View getView() {
        return specificationView;
    }
}
