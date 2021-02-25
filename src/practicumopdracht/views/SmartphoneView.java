package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import practicumopdracht.models.Smartphone;

/**
 * Functionality:
 *
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneView extends View{
    // labels
    private Label labelSmartphoneName;
    private Label labelSerie;
    private Label labelReleaseDate;

    // textfield
    private TextField textFieldSmartphoneName;

    // datepicker
    private DatePicker releaseDate;

    // buttons
    private Button buttonSave;
    private Button buttonNew;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonSwitch;

    // combobox
    private ComboBox<String> comboBoxSerie;

    // TODO vragen
    private ListView<Smartphone> listView;

    private GridPane gridPaneSmart;
    private VBox vBoxSmart;
    private HBox menuButtonsSmart;
    private BorderPane borderPane;

    private Parent root;

    public SmartphoneView() {
        initializeRoot();
    }

    private void initializeRoot() {
        // labels
        labelSmartphoneName = new Label("Smartphone name");
        labelSerie = new Label("Serie name");
        labelReleaseDate = new Label("Release date");

        // textfield
        textFieldSmartphoneName = new TextField();

        // combobox
        comboBoxSerie = new ComboBox<>();

        // datepicker
        releaseDate = new DatePicker();

        // buttons
        buttonSave = new Button("Save");
        buttonNew = new Button("New");
        buttonEdit = new Button("Edit");
        buttonDelete = new Button("Delete");
        buttonSwitch = new Button("Switch to details");

        listView = new ListView<>();
        gridPaneSmart = new GridPane();

        // sets a gap vertically
        gridPaneSmart.setVgap(10);
        gridPaneSmart.setHgap(10);

        // combobox
        comboBoxSerie = new ComboBox<>();
        comboBoxSerie.getItems().addAll(
                "OnePlus Nord series",
                "Samsung S series",
                "Samsung A series",
                "Samsung J series",
                "Samsung M series"
                );
        // default text for when there is no option selected
        comboBoxSerie.setPromptText("series");

        // Constructs a new Insets instance with four different offsets
        gridPaneSmart.setPadding(new Insets(10, 10, 10, 10));
        gridPaneSmart.add(labelSmartphoneName, 0, 0);
        gridPaneSmart.add(textFieldSmartphoneName, 1, 0);

        gridPaneSmart.add(labelSerie, 0, 1);
        gridPaneSmart.add(comboBoxSerie, 1, 1);

        gridPaneSmart.add(labelReleaseDate, 0, 2);
        gridPaneSmart.add(releaseDate, 1, 2);

        // buttons
        gridPaneSmart.add(buttonSave, 2, 8);
        gridPaneSmart.add(buttonNew, 3, 8);
        gridPaneSmart.add(buttonEdit, 4, 8);
        gridPaneSmart.add(buttonDelete, 5, 8);
        gridPaneSmart.add(buttonSwitch, 6, 8);

        // buttons added to hbox
        menuButtonsSmart = new HBox();
        menuButtonsSmart.setPadding(new Insets(10, 10, 10, 100));
        menuButtonsSmart.setSpacing(20); // distance between buttons
        menuButtonsSmart.getChildren().addAll(buttonSave, buttonNew, buttonEdit, buttonDelete, buttonSwitch);

        vBoxSmart = new VBox();

        vBoxSmart.setPadding(new Insets(10, 10, 10, 10));
        vBoxSmart.getChildren().addAll(gridPaneSmart, menuButtonsSmart, listView);

        borderPane = new BorderPane();
        borderPane.setCenter(vBoxSmart);
        root = borderPane;
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public TextField getTextFieldSmartphoneName() {
        return textFieldSmartphoneName;
    }

    public ComboBox<String> getComboBoxSerie() {
        return comboBoxSerie;
    }

    public DatePicker getReleaseDate() {
        return releaseDate;
    }

    public Button getButtonSave() {
        return buttonSave;
    }

    public Button getButtonNew() {
        return buttonNew;
    }

    public Button getButtonEdit() {
        return buttonEdit;
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    public Button getButtonSwitch() {
        return buttonSwitch;
    }

    public ListView<Smartphone> getListView() {
        return listView;
    }
}
