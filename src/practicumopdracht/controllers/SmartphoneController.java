package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Smartphone;
import practicumopdracht.views.SmartphoneView;
import practicumopdracht.views.View;

import java.time.LocalDate;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneController extends Controller {
    // geen controller in de view. Maar de view in de controller
    // koppel de view aan de controller
    private SmartphoneView smartphoneView;
    private ObservableList<Smartphone> smartphoneObservableList;

    public SmartphoneController() {
        smartphoneView = new SmartphoneView();

        // link multiple actions to the save button
        smartphoneView.getButtonSave().setOnAction(event -> validationSmart(smartphoneView));

        // switch to detail view
        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications());

        // edit button
        smartphoneView.getButtonEdit().setOnAction(event -> editSmart());

        // delete button
        smartphoneView.getButtonDelete().setOnAction(event -> deleteSmart());

        smartphoneObservableList = FXCollections.observableArrayList();
    }

    private boolean validationSmart(SmartphoneView smartphoneView) {

        StringBuilder errorStringBuilder = new StringBuilder();

        // TODO fix unselected red borders
        // smartphone name
        String smartphoneName = smartphoneView.getTextFieldSmartphoneName().getText().trim();

        if(smartphoneName.equals("")) {
            errorStringBuilder.append("- Enter the brand name of the smartphone \n");
            smartphoneView.getTextFieldSmartphoneName().setStyle("-fx-border-color: #ff0000");
        }

        // serie
        String serie = smartphoneView.getComboBoxSerie().getValue();

        if(serie == null) {
            errorStringBuilder.append("- Serie is unknown\n");
            smartphoneView.getComboBoxSerie().setStyle("-fx-border-color: #ff0000");
        }

        // release date
        LocalDate releaseDate = smartphoneView.getReleaseDate().getValue();

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
            return false;
        }

        try {
            //reset fields
            smartphoneView.getTextFieldSmartphoneName().setText("");
            smartphoneView.getComboBoxSerie().setPromptText("series");
            smartphoneView.getReleaseDate().setValue(null);

        } catch (Exception ex) {
            System.out.println("Oh nee!");
        }

        Smartphone smartphone = new Smartphone(
                smartphoneName, serie, releaseDate
        );

        smartphoneObservableList.add(smartphone);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(smartphone.toString());
        alert.showAndWait();

        showSmartphone();

        return true;
    }

    private void showSmartphone() {
        ObservableList<Smartphone> smartList = FXCollections.observableArrayList(smartphoneObservableList);
        smartphoneView.getListView().setItems(smartList);
    }

    private void editSmart() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("You clicked on the edit button!");
        alert.showAndWait();
    }

    private void deleteSmart() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You clicked on the delete button!");
        alert.showAndWait();
    }

    public void switchToSpecifications() {
        MainApplication.switchController(new SpecificationController());
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
