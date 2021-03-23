package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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
        smartphoneDAO = MainApplication.getSmartphoneDAO();

        smartphoneView = new SmartphoneView();

        // menu items
        smartphoneView.getMenuItemLoad().setOnAction(event -> loadFromDAO());

//        smartphoneView.getMenuItemExit().setOnAction(event -> exit());

        // voor de text, object en fake DAO's
        smartphoneView.getMenuItemSave().setOnAction(event -> {
            // DAO
            saveFromDAO();
        });

        // link validation to the save button
        // voor de algemene DAO's
        smartphoneView.getButtonSave().setOnAction(event ->
        {
            if (validation(smartphoneView)) {
                // save specification
                save(smartphoneView, smartphoneObservableList);
            }
            smartphoneDAO.save();
        });

        smartphoneView.getButtonNew().setOnAction(event -> newPhone());

        // edit button
        // TODO edit function doesn't work
        smartphoneView.getButtonEdit().setOnAction(event -> editCheck(selectedSmartphone));

        // delete button
        smartphoneView.getButtonDelete().setOnAction(event -> delete());

        // switch to detail view
        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications());

        smartphoneObservableList = FXCollections.observableArrayList();

        // TODO select the right item (master) to get the right information (detail)
        // what is the difference between selectedIndexProperty and selectedItemProperty
        // replaces the old value with the new by editing
        smartphoneView.getListView().getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldSmartphone, newSmartphone) -> {
                    if (newSmartphone == null || oldSmartphone == newSmartphone) {
                        return;
                    }
                    editCheck(newSmartphone);
                });
        show();
    }

    private void save(SmartphoneView masterView, ObservableList<Smartphone> observableList) {

        String nameField = masterView.getTextFieldSmartphoneName().getText();
        Object serie = masterView.getComboBoxSerie().getValue();

        int versionField = 0;

        try {
            versionField = Integer.parseInt(masterView.getTextFieldVersion().getText().trim());
        } catch (Exception e) {

        }

        // TODO local date doesn't work
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
        ObservableList<Smartphone> smartList = FXCollections.observableArrayList(MainApplication.getSmartphoneDAO().getAll());
//        FXCollections.sort(smartList2, );
        smartphoneView.getListView().setItems(smartList);

    }

    private void newPhone() {
        // TODO verder gaan met de pop up, if yes then this if no then this
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Are you sure you want to create a new phone? ");
        alert.showAndWait();

        resetFields();
    }

    private void editCheck(Smartphone newSmartphone) {
        selectedSmartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        if(selectedSmartphone != null) {

            smartphoneView.getTextFieldSmartphoneName().setText(selectedSmartphone.getSmartphoneName());

            smartphoneView.getComboBoxSerie().setValue((String) selectedSmartphone.getSerie());

            try {
                int versionString = selectedSmartphone.getVersion();
                smartphoneView.getTextFieldVersion().setText(String.valueOf(Integer.parseInt(String.valueOf(versionString))));

            } catch (Exception e) {

            }

            smartphoneView.getReleaseDate().setValue(selectedSmartphone.getReleaseDate());
        }
    }

    // TODO delete function not working
    // TODO todo after save function
    private void delete() {
        selectedSmartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        // unselect item when the application is started, preventing from deleting something by accident
        if(selectedSmartphone == null) {
            return;
        }

        smartphoneObservableList.remove(selectedSmartphone);

        smartphoneDAO.remove(selectedSmartphone);
    }

    public void switchToSpecifications() {
        Smartphone smartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        MainApplication.switchController(new SpecificationController(smartphone));
    }

    private void loadFromDAO() {
        MainApplication.getSmartphoneDAO().load();
//        smartphoneDAO.load();
        show();
    }

    private void saveFromDAO() {
        MainApplication.getSmartphoneDAO().save();
    }

    public void enableButton() {
        smartphoneView.getButtonSwitch().setDisable(true);
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
