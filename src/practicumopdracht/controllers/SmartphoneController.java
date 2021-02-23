package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;
import practicumopdracht.views.SmartphoneView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneController extends Controller{

//    private final View VIEW;

    // geen controller in de view. Maar de view in de controller
    // koppel de view aan de controller
    private SmartphoneView smartphoneView;
    private ArrayList<Smartphone> smartphones = new ArrayList<>();
    private ObservableList<Specification>specificatiesObservableList;

    public SmartphoneController() {
        smartphoneView = new SmartphoneView();
        // koppel een actie
        smartphoneView.getButtonSave().setOnAction(event -> saveSmartphone());
        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications());
        specificatiesObservableList = FXCollections.observableArrayList();
//        VIEW = smartphoneView;
    }

    private void saveSmartphone() {
        StringBuilder stringBuilder = new StringBuilder();

        String brandName = smartphoneView.getTextFieldBrandName().getText();
        String brandSerie = smartphoneView.getComboBoxSerie().getValue();
        LocalDate dateRelease = smartphoneView.getReleaseDate().getValue();
        smartphones.add(new Smartphone(brandName, brandSerie, dateRelease));

        // TODO fix red borders
        if (brandName.trim().equals("")) {
            stringBuilder.append("Enter the brand name of the smartphone \n");
            smartphoneView.getTextFieldBrandName().setStyle("-fx-border-color: #ff0000");
        } else if (brandSerie.equals("")) {
            stringBuilder.append("Serie is unknown\n");
            smartphoneView.getComboBoxSerie().setStyle("-fx-border-color: #ff0000");
        } else if (dateRelease == null) {
            stringBuilder.append("Release date is unknown \n");
        }

        showSmartphone();
    }

    private void showSmartphone() {
        ObservableList<Smartphone> smartList = FXCollections.observableArrayList(smartphones);
        smartphoneView.getListView().setItems(smartList);
    }

    public SmartphoneView getSmartphoneView() {
        return smartphoneView;
    }

    public void switchToSpecifications() {
        MainApplication.switchController(new SpecificationController());
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
