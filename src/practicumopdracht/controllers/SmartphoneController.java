package practicumopdracht.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.MasterComparator;
import practicumopdracht.data.SmartphoneDAO;
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
    private final ButtonType YES = new ButtonType("Yes", ButtonBar.ButtonData.YES);
    private final ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.NO);
    private SmartphoneDAO smartphoneDAO;
    private SmartphoneView smartphoneView;
    private Smartphone selectedSmartphone;
    private MasterComparator masterComparator;

    public SmartphoneController() {
        smartphoneDAO = MainApplication.getSmartphoneDAO();

        smartphoneView = new SmartphoneView();

        // menu items
        // for the text, object, binairy and fake DAO's
        smartphoneView.getMenuItemSave().setOnAction(event -> saveToDAO());

        smartphoneView.getMenuItemLoad().setOnAction(event -> loadFromDAO());

        smartphoneView.getMenuItemExit().setOnAction(event -> exit());

        smartphoneView.getMenuItemAsc().setOnAction(event -> sortAsc());

        smartphoneView.getMenuItemDesc().setOnAction(event -> sortDesc());

        // link validation to the save button
        // for the "normal" DAO's
        smartphoneView.getButtonSave().setOnAction(event -> validationSaveBtn());

        // new button
        smartphoneView.getButtonNew().setOnAction(event -> newPhone());

        // edit button
        smartphoneView.getButtonEdit().setOnAction(event -> edit(selectedSmartphone));

        // delete button
        smartphoneView.getButtonDelete().setOnAction(event -> delete());

        // switch to detail view
        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications());

        // replaces the old value with the new by editing
        smartphoneView.getListView().getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldSmartphone, newSmartphone) -> {
                    if (newSmartphone == null || oldSmartphone == newSmartphone) {
                        return;
                    }

                    enableNewButton();
                    enableDeleteButton();
                    enableSwitchButton();
                    enableEditButton();
                    edit(oldSmartphone);
                });

        enableSaveButton();
        disableNewButton();
        disableEditButton();
        disableDeleteButton();
        disableSwitchButton();

        show();
    }

    private void saveToDAO() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to save this data?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            boolean isSaved = smartphoneDAO.save();
            if (isSaved) {
                Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The data is saved");
                succes.show();
            }
        }
        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The data is not saved");
            fail.show();
        }
    }

    // load to DAO
    private void loadFromDAO() {
        smartphoneDAO.load();
        show();
    }

    // exit application
    private void exit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want close the application?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            Alert deciding = new Alert(Alert.AlertType.WARNING, "Are you sure you want to close the application?",
                    YES, NO);
            deciding.showAndWait();

            if (deciding.getResult() == YES) {
                Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The application will close.");
                succes.show();
                Platform.exit();
            }

            if (deciding.getResult() == NO) {
                Alert fail = new Alert(Alert.AlertType.WARNING, "The application won't close");
                fail.show();
            }
        }
        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The application won't close");
            fail.show();
        }

    }

    private void validationSaveBtn() {
        if (validation()) {
            // save specification
            save();
        }
    }

    private void save() {
        String nameField = smartphoneView.getTextFieldSmartphoneName().getText();
        Object serie = smartphoneView.getComboBoxSerie().getValue();

        int versionField = 0;

        try {
            versionField = Integer.parseInt(smartphoneView.getTextFieldVersion().getText().trim());
        } catch (Exception e) {

        }

        LocalDate releaseDate = smartphoneView.getReleaseDate().getValue();

        if (selectedSmartphone == null) {
            MainApplication.getSmartphoneDAO().addOrUpdate(new Smartphone(nameField, (String) serie, versionField,
                    releaseDate));
        }
//        if (selectedSmartphone != null) {
        //else update the existing smartphone
        else {
            Smartphone smartphoneExists = selectedSmartphone;
            selectedSmartphone.setSmartphoneName(nameField);
            selectedSmartphone.setSerie(serie);
            selectedSmartphone.setVersion(versionField);
            selectedSmartphone.setReleaseDate(releaseDate);
            MainApplication.getSmartphoneDAO().addOrUpdate(smartphoneExists);
        }

        show();
        resetFields();
    }

    private boolean validation() {
        StringBuilder errorStringBuilder = new StringBuilder();

        // smartphone name
        String smartphoneName = smartphoneView.getTextFieldSmartphoneName().getText().trim();
        smartphoneView.getTextFieldSmartphoneName().setStyle("-fx-border-color: transparent");
        if (smartphoneName.equals("")) {
            errorStringBuilder.append("- Enter the brand name of the smartphone \n");
            smartphoneView.getTextFieldSmartphoneName().setStyle("-fx-border-color: #ff0000");
        }

        // serie
        String serie = smartphoneView.getComboBoxSerie().getValue();
        smartphoneView.getComboBoxSerie().setStyle("-fx-border-color: transparent");
        if (serie == null) {
            errorStringBuilder.append("- Serie is unknown\n");
            smartphoneView.getComboBoxSerie().setStyle("-fx-border-color: #ff0000");
        }

        // version
        String versionString = smartphoneView.getTextFieldVersion().getText().trim();
        int version = 0;
        smartphoneView.getTextFieldVersion().setStyle("-fx-border-color: transparent");
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
        smartphoneView.getReleaseDate().setStyle("-fx-border-color: transparent");
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
        ObservableList smartphoneObservableList = FXCollections.observableArrayList(smartphoneDAO.getAll());
//        FXCollections.sort(smartList2, );
        smartphoneView.getListView().setItems(smartphoneObservableList);
    }

    // new button
    private void newPhone() {
        resetFields();

        String nameField = smartphoneView.getTextFieldSmartphoneName().getText();
        Object serie = smartphoneView.getComboBoxSerie().getValue();

        int versionField = 0;

        try {
            versionField = Integer.parseInt(smartphoneView.getTextFieldVersion().getText().trim());
        } catch (Exception e) {

        }

        LocalDate releaseDate = smartphoneView.getReleaseDate().getValue();

        MainApplication.getSmartphoneDAO().addOrUpdate(new Smartphone(nameField, (String) serie, versionField,
                releaseDate));
    }

    // edit button
    private void edit(Smartphone newSmartphone) {
        selectedSmartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        // using setters because you are editing something
        if (selectedSmartphone != null) {
            smartphoneView.getTextFieldSmartphoneName().setText(selectedSmartphone.getSmartphoneName());

            smartphoneView.getComboBoxSerie().setValue((String) selectedSmartphone.getSerie());

            try {
                int version = selectedSmartphone.getVersion();
                smartphoneView.getTextFieldVersion().setText(String.valueOf(version));
            } catch (Exception e) {

            }

            smartphoneView.getReleaseDate().setValue(selectedSmartphone.getReleaseDate());

            // TODO dit werkt niet
            if (smartphoneView.getButtonEdit().isPressed()) {
                System.out.println("Hij komt hier");
                disableDeleteButton();
                disableSwitchButton();
            }
        }
    }

    // delete button
    private void delete() {
        selectedSmartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        // unselect item when the application is started, preventing from deleting something by accident
        if (selectedSmartphone == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to delete this item?", YES, NO);
        alert.showAndWait();

        if (alert.getResult() == YES) {
            smartphoneDAO.remove(selectedSmartphone);
            Alert succes = new Alert(Alert.AlertType.CONFIRMATION, "The data is deleted");
            succes.show();
        }

        if (alert.getResult() == NO) {
            Alert fail = new Alert(Alert.AlertType.WARNING, "The data is not deleted");
            fail.show();
        }

        show();
        resetFields();
    }

    // switch to the second screen
    public void switchToSpecifications() {
        Smartphone smartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        MainApplication.switchController(new SpecificationController(smartphone));
    }

    private void sortAsc() {
        // TODO test this out
        ObservableList<Smartphone> smartphoneObservableList = smartphoneView.getListView().getItems();
        masterComparator = new MasterComparator(false);
        FXCollections.sort(smartphoneObservableList, masterComparator);
        smartphoneView.getListView().setItems(smartphoneObservableList);
    }

    private void sortDesc() {
        ObservableList<Smartphone> smartphoneObservableList = smartphoneView.getListView().getItems();
        masterComparator = new MasterComparator(true);
        FXCollections.sort(smartphoneObservableList, masterComparator);
        smartphoneView.getListView().setItems(smartphoneObservableList);
    }


    // enable and disable the buttons
    private void enableSaveButton() {
        smartphoneView.getButtonSave().setDisable(false);
    }

    private void disableSaveButton() {
        smartphoneView.getButtonSave().setDisable(true);
    }

    private void enableNewButton() {
        smartphoneView.getButtonNew().setDisable(false);
    }

    private void disableNewButton() {
        smartphoneView.getButtonNew().setDisable(true);
    }

    private void enableEditButton() {
        smartphoneView.getButtonEdit().setDisable(false);
    }

    private void disableEditButton() {
        smartphoneView.getButtonEdit().setDisable(true);
    }

    private void enableDeleteButton() {
        smartphoneView.getButtonDelete().setDisable(false);
    }

    private void disableDeleteButton() {
        smartphoneView.getButtonDelete().setDisable(true);
    }

    public void enableSwitchButton() {
        smartphoneView.getButtonSwitch().setDisable(false);
    }

    public void disableSwitchButton() {
        smartphoneView.getButtonSwitch().setDisable(true);
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
