package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Smartphone;
import practicumopdracht.views.SmartphoneView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneController extends Controller {
    // geen controller in de view. Maar de view in de controller
    // koppel de view aan de controller
    private SmartphoneView smartphoneView;
    private ArrayList<Smartphone> smartphones = new ArrayList<>();
    private ObservableList<Smartphone> smartphoneObservableList;

    public SmartphoneController() {
        smartphoneView = new SmartphoneView();
        // koppel een actie
        smartphoneView.getButtonSave().setOnAction(event -> saveSmartphone());
        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications());

        smartphoneObservableList = FXCollections.observableArrayList();
    }

    private void saveSmartphone() {
        StringBuilder errorStringBuilder = new StringBuilder();

        String smartphoneName = smartphoneView.getTextFieldSmartphoneName().getText().trim();
        String serie = smartphoneView.getComboBoxSerie().getValue();
        LocalDate releaseDate = smartphoneView.getReleaseDate().getValue();

        smartphones.add(new Smartphone(smartphoneName, serie, releaseDate));

        // TODO fix unselected red borders
        // smartphone name
        if(smartphoneName.equals("")) {
            errorStringBuilder.append("- Enter the brand name of the smartphone \n");
            smartphoneView.getTextFieldSmartphoneName().setStyle("-fx-border-color: #ff0000");
        }

        // serie
        if(serie == null) {
            errorStringBuilder.append("- Serie is unknown\n");
            smartphoneView.getComboBoxSerie().setStyle("-fx-border-color: #ff0000");
        }

        // release date
        if(releaseDate == null) {
            errorStringBuilder.append("- Release date is unknown \n");
            smartphoneView.getReleaseDate().setStyle("-fx-border-color: #ff0000");
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
                smartphoneView.getTextFieldSmartphoneName().setText("");
                smartphoneView.getComboBoxSerie().setValue("");
                smartphoneView.getReleaseDate().setValue(null);

            } catch (Exception ex) {
                System.out.println("Oh nee!");
            }

            Smartphone smartphone = new Smartphone(
                    smartphoneName, serie, releaseDate
            );

            smartphoneObservableList.add(new Smartphone(smartphoneName, serie, releaseDate));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(smartphone.toString());
            alert.showAndWait();
        }

        // TODO how to dont show list when there is missing one input
        showSmartphone();
    }

    private void showSmartphone() {
        ObservableList<Smartphone> smartList = FXCollections.observableArrayList(smartphones);
        smartphoneView.getListView().setItems(smartList);
    }

    public void switchToSpecifications() {
        MainApplication.switchController(new SpecificationController());
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
