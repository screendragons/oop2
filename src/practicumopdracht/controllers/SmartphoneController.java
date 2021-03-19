package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import practicumopdracht.MainApplication;
import practicumopdracht.data.DAO;
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
    private DAO<Smartphone> smartphoneDAO;
    private SmartphoneView smartphoneView;
    private ObservableList<Smartphone> smartphoneObservableList;
    private Smartphone selectedSmartphone;

    public SmartphoneController() {
        smartphoneView = new SmartphoneView();

        // menu items
        smartphoneView.getMenuItemLoad().setOnAction(event -> loadFromDAO());

//        smartphoneView.getMenuItemExit().setOnAction(event -> exit());

        smartphoneView.getMenuItemSave().setOnAction(event -> {
            if (validation(smartphoneView)) {
                // save specification
                save(smartphoneView, smartphoneObservableList);
            }
        });

        // link validation to the save button
        // TODO is deze knop nog nodig?
        smartphoneView.getButtonSave().setOnAction(event ->
        {
            if (validation(smartphoneView)) {
                // save specification
                save(smartphoneView, smartphoneObservableList);
            }
        });

        // switch to detail view
//        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications(smartphoneView.getListView().getSelectionModel().getSelectedItem()));

        // edit button
//        smartphoneView.getButtonEdit().setOnAction(event -> edit());

        // delete button
        smartphoneView.getButtonDelete().setOnAction(event -> delete());

        // Load DAO button
        // TODO is deze knop nog nodig?
        smartphoneView.getButtonLoadDAO().setOnAction(event -> loadFromDAO());

        // save DAO
        smartphoneView.getButtonSaveDAO().setOnAction(event -> MainApplication.getSmartphoneDAO().save());

        smartphoneObservableList = FXCollections.observableArrayList();

        smartphoneView.getButtonSwitch().setOnAction(event -> seeDetails());

        // TODO select the right item to get the right information
        // what is the difference between selectedIndexProperty and selectedItemProperty
        smartphoneView.getListView().getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldSmartphone, newSmartphone) -> {
                    if (newSmartphone == null || newSmartphone == oldSmartphone) {
                        return;
                    }
                });
        MainApplication.getSmartphoneDAO().load();

    }

    private void save(SmartphoneView masterView, ObservableList<Smartphone> observableList) {
        int versionField = 0;

        try {
            versionField = Integer.parseInt(masterView.getTextFieldVersion().getText().trim());
        } catch (Exception e) {

        }

        String nameField = masterView.getTextFieldSmartphoneName().getText();
        Object serie = masterView.getComboBoxSerie().getValue();
        LocalDate releaseDate = masterView.getReleaseDate().getValue();

        MainApplication.getSmartphoneDAO().addOrUpdate(new Smartphone(nameField, (String) serie, versionField, releaseDate));

        show();
        resetFields();
    }

    private boolean validation(SmartphoneView smartphoneView) {
        StringBuilder errorStringBuilder = new StringBuilder();

        // smartphone name
        String smartphoneName = smartphoneView.getTextFieldSmartphoneName().getText().trim();

        if (smartphoneName.equals("")) {
            errorStringBuilder.append("- Enter the brand name of the smartphone \n");
            smartphoneView.getTextFieldSmartphoneName().setStyle("-fx-border-color: #ff0000");
        }

        // serie
        String serie = smartphoneView.getComboBoxSerie().getValue();

        if (serie == null) {
            errorStringBuilder.append("- Serie is unknown\n");
            smartphoneView.getComboBoxSerie().setStyle("-fx-border-color: #ff0000");
        }

        // version
        String versionString = smartphoneView.getTextFieldVersion().getText().trim();
        int version = 0;

        if (versionString.isEmpty()) {
            errorStringBuilder.append("- Version is required\n");
            smartphoneView.getTextFieldVersion().setStyle("-fx-border-color: #ff0000");
        } else {
            try {
                version = Integer.parseInt(versionString);

                if (version < 1) {
                    errorStringBuilder.append("- Version is too small and can't be smaller than 1\n");
                }
            } catch (Exception ex) {
                errorStringBuilder.append("- Version is not a valid number\n");
            }
        }
        
        // release date
        LocalDate releaseDate = smartphoneView.getReleaseDate().getValue();

        if (releaseDate == null) {
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
        } else {
            Smartphone smartphone = new Smartphone(
                    smartphoneName, serie, version, releaseDate
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(smartphone.toString());
            alert.showAndWait();

            return true;
        }
    }

    private void resetFields() {
        //reset fields
        smartphoneView.getTextFieldSmartphoneName().setText("");
        smartphoneView.getComboBoxSerie().setPromptText("series");
        smartphoneView.getTextFieldVersion().setText("");
        smartphoneView.getReleaseDate().setValue(null);
    }

    private void show() {
        ObservableList<Smartphone> smartList1 = FXCollections.observableArrayList(smartphoneObservableList);

        ObservableList<Smartphone> smartList2 = FXCollections.observableArrayList(MainApplication.getSmartphoneDAO().getAll());
        smartphoneView.getListView().setItems(smartList2);

    }

    private void edit(Smartphone smartphone) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("You clicked on the edit button!");
//        alert.showAndWait();

        smartphoneView.getTextFieldSmartphoneName().setText(smartphone.getSmartphoneName());
        smartphoneView.getComboBoxSerie().setValue((String) smartphone.getSerie());
        smartphoneView.getTextFieldVersion().setText(String.valueOf(smartphone.getVersion()));
        smartphoneView.getReleaseDate().setPromptText(String.valueOf(smartphone.getReleaseDate()));
        // TODO check ^
    }

    private void delete() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You clicked on the delete button!");
        alert.showAndWait();
    }

    public void switchToSpecifications() {
        Smartphone masterID = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        System.out.println(smartphoneView.getListView().getSelectionModel().getSelectedItem());

        MainApplication.switchController(new SpecificationController(masterID));
    }

    private void loadFromDAO() {
       show();
    }

    public void enableButton() {
        smartphoneView.getButtonSwitch().setDisable(true);
    }

    private void seeDetails() {
        // TODO select item
//        MainApplication.switchController(new SmartphoneController(selectedSmartphone));
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
