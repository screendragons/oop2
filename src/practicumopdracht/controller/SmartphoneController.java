package practicumopdracht.controller;

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

    public static SmartphoneView getSmartphoneView;
    // geen controller in de view. Maar de view in de controller
    // koppel de view aan de controller
    private SmartphoneView smartphoneView;
    private ArrayList<Smartphone> smartphoneArrayList = new ArrayList<>();


    public SmartphoneController() {
        smartphoneView = new SmartphoneView();
        // koppel een actie
        smartphoneView.getButtonSave().setOnAction(event -> saveSmartphone());
    }

    private void saveSmartphone() {
        String brandName = smartphoneView.getTextFieldBrandName().getText();
        String brandSerie = smartphoneView.getTextFieldSerie().getText();
        LocalDate dateRelease = smartphoneView.getReleaseDate().getValue();
    }

    public SmartphoneView getSmartphoneView() {
        return smartphoneView;
    }
}
