package practicumopdracht.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import practicumopdracht.model.Smartphone;
import practicumopdracht.view.SmartphoneView;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneController {

    // geen controller in de view. Maar de view in de controller
    // koppel de view aan de controller
    private SmartphoneView smartphoneView;
    private ArrayList<Smartphone> smartphones = new ArrayList<>();


    public SmartphoneController() {
        smartphoneView = new SmartphoneView();
        // koppel een actie
        smartphoneView.getButtonSave().setOnAction(event -> saveSmartphone());
    }

    private void saveSmartphone() {
        String brandName = smartphoneView.getTextFieldBrandName().getText();
        String brandSerie = smartphoneView.getTextFieldSerie().getText();
        LocalDate dateRelease = smartphoneView.getReleaseDate().getValue();
        smartphones.add(new Smartphone(brandName, brandSerie, dateRelease));
        showSmartphone();
    }

    private void showSmartphone() {
        ObservableList<Smartphone> ol = FXCollections.observableArrayList(smartphones);
        smartphoneView.getListView().setItems(ol);
    }
    public SmartphoneView getSmartphoneView() {
        return smartphoneView;
    }
}
