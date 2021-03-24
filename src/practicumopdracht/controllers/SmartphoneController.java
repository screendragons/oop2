package practicumopdracht.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.SmartphoneDAO;
import practicumopdracht.models.Smartphone;
import practicumopdracht.views.SmartphoneView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Comparator;

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
    private ObservableList<Smartphone> smartphoneObservableList;
    private Smartphone selectedSmartphone;

    public SmartphoneController() {
        smartphoneDAO = MainApplication.getSmartphoneDAO();

        smartphoneView = new SmartphoneView();

        // menu items
        // voor de text, object en fake DAO's
        smartphoneView.getMenuItemSave().setOnAction(event -> {
            // TODO how do you create an alert with yes and no -> confirmation -> week 5. step 6
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this application?", YES, NO);

            alert.setContentText(selectedSmartphone.toString());
            alert.showAndWait();

            if(this.selectedSmartphone == null) {
                System.err.println("Er is geen smartphone aangemaakt of geselecteerd!");
            }

            if (alert.getResult() == NO) {
                return;
            }
            //
            SmartphoneDAO smartphoneDAO = MainApplication.getSmartphoneDAO();
            smartphoneDAO.addOrUpdate(selectedSmartphone);
        });

        smartphoneView.getMenuItemLoad().setOnAction(event -> loadFromDAO());

        smartphoneView.getMenuItemExit().setOnAction(event -> exit());

        // link validation to the save button
        // voor de algemene DAO's
        // TODO get this data saved after starting and closing the application
        smartphoneView.getButtonSave().setOnAction(event ->
        {
            if (validation(smartphoneView)) {
                // save specification
                save(smartphoneView, smartphoneObservableList);
            }
            smartphoneDAO.save();
        });

        // new button
        smartphoneView.getButtonNew().setOnAction(event -> newPhone());

        // edit button
        // TODO edit function doesn't work
        smartphoneView.getButtonEdit().setOnAction(event -> edit(selectedSmartphone));

        // delete button
        smartphoneView.getButtonDelete().setOnAction(event -> delete());

        // switch to detail view
        smartphoneView.getButtonSwitch().setOnAction(event -> switchToSpecifications());

        smartphoneView.getBtnSortDescName().setOnAction(event -> sortAscName());
        // TODO sort werkt niet
        smartphoneView.getBtnSortDescName().setOnAction(event -> sortDescName());
        
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
                    edit(newSmartphone);
                    smartphoneDAO.addOrUpdate(newSmartphone);
                });

        enableSaveButton();
        disableNewButton();
        disableEditButton();
        disableDeleteButton();
        disableSwitchButton();
        show();
    }

    private void sortAscName() {
        if(this.smartphoneView.getBtnSortAscName().isSelected()) {
            // TODO hoe kan ik ervoor zorgen dat ie niet null is? (smartphoneObservableList)
            this.smartphoneObservableList.sort(new SortAscName());
        }
    }

    private void sortDescName() {
        if(this.smartphoneView.getBtnSortDescName().isSelected()) {
            // TODO hoe kan ik ervoor zorgen dat ie niet null is? (smartphoneObservableList)
            this.smartphoneObservableList.sort(new SortDescName());
        }
    }

    private void save(SmartphoneView masterView, ObservableList<Smartphone> observableList) {

        String nameField = masterView.getTextFieldSmartphoneName().getText();
        Object serie = masterView.getComboBoxSerie().getValue();

        int versionField = 0;

        try {
            versionField = Integer.parseInt(masterView.getTextFieldVersion().getText().trim());
        } catch (Exception e) {

        }

        LocalDate releaseDate = masterView.getReleaseDate().getValue();

        MainApplication.getSmartphoneDAO().addOrUpdate(new Smartphone(nameField, (String) serie, versionField, releaseDate));

        show();
        resetFields();
    }

    private boolean validation(SmartphoneView smartphoneView) {
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
        // TODO moet je hier een aparte observable list gebruiken of gebruik je steeds dezelfde (van de attributen)
        smartphoneObservableList = FXCollections.observableArrayList(smartphoneDAO.getAll());
//        FXCollections.sort(smartList2, );
        smartphoneView.getListView().setItems(smartphoneObservableList);
    }

    // new button
    private void newPhone() {
        // TODO newbutton -> pop up -> hoe moet je een ja en nee knop maken?
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Are you sure you want to create a new phone? ");
        alert.showAndWait();

        resetFields();
    }

    // edit button
    private void edit(Smartphone newSmartphone) {
        // TODO als ik edit, dan maakt hij een nieuw item aan en vervangt hij niet de oude value
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
        smartphoneDAO.addOrUpdate(selectedSmartphone);

    }

    // TODO delete function not working
    // TODO todo after save function
    // delete button
    private void delete() {
        selectedSmartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        // unselect item when the application is started, preventing from deleting something by accident
        if(selectedSmartphone == null) {
            return;
        }

        // TODO deletebutton -> pop up -> hoe moet je een ja en nee knop maken?
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Are you sure you want to delete a phone? ");
        alert.showAndWait();

        smartphoneObservableList.remove(selectedSmartphone);

        smartphoneDAO.remove(selectedSmartphone);
    }

    // switch to the second screen
    public void switchToSpecifications() {
        Smartphone smartphone = smartphoneView.getListView().getSelectionModel().getSelectedItem();

        MainApplication.switchController(new SpecificationController(smartphone));
    }

    // load to DAO
    // TODO loading not working
    private void loadFromDAO() {
        smartphoneDAO.load();
        show();
    }

    // save to the DAO
    private void saveFromDAO() {
        MainApplication.getSmartphoneDAO().save();
        MainApplication.getSpecificationDAO().save();
    }

    // exit application
    private void exit() {
        // TODO how do you make alert as a reminder that some data aren't saved -> Yes/No
        Platform.exit();
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

    // inner classes
    public class SortDescName implements Comparator<Smartphone> {

        @Override
        public int compare(Smartphone o1, Smartphone o2) {
            return o2.getSmartphoneName().compareTo(o1.getSmartphoneName());
        }
    }

    public class SortAscName implements Comparator<Smartphone> {

        @Override
        public int compare(Smartphone o1, Smartphone o2) {
            return o1.getSmartphoneName().compareTo(o2.getSmartphoneName());
        }
    }

    @Override
    public View getView() {
        return smartphoneView;
    }
}
