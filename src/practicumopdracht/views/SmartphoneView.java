package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    // textfields
    private TextField textFieldBrandName;

    // datepicker
    private DatePicker releaseDate;

    // buttons
    private Button buttonSave;
    private Button buttonNew;
    private Button buttonDelete;
    private Button buttonSwitch;

    // combobox
    private ComboBox<String> comboBoxSerie;

    // TODO vragen
    private ListView<Smartphone> listView;

    private GridPane gridPane;
    private BorderPane borderPane;
    private VBox vBox;
    private HBox hBoxButtons;

    private Parent root;

    public SmartphoneView() {
        initializeRoot();
    }

    private void initializeRoot() {
        // labels
        labelSmartphoneName = new Label("Smartphone naam");
        labelSerie = new Label("Serie naam");
        labelReleaseDate = new Label("Datum uitgave");

        comboBoxSerie = new ComboBox<>();
        textFieldBrandName = new TextField();

        releaseDate = new DatePicker();

        // buttons
        buttonSave = new Button("Opslaan");
        buttonNew = new Button("Nieuw");
        buttonDelete = new Button("Verwijderen");
        buttonSwitch = new Button("Switchen naar details");

        listView = new ListView<>();
        gridPane = new GridPane();

        // sets a gap vertically
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        comboBoxSerie = new ComboBox<>();
        comboBoxSerie.getItems().addAll(
                "OnePlus Nord series",
                "Samsung S series",
                "Samsung A series",
                "Samsung J series",
                "Samsung M series"
                );
        comboBoxSerie.setPromptText("series");

        // Constructs a new Insets instance with four different offsets
        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setGridLinesVisible(false);
        gridPane.add(labelSmartphoneName, 0, 0);
        gridPane.add(textFieldBrandName, 1, 0);

        gridPane.add(labelSerie, 0, 1);
        gridPane.add(comboBoxSerie, 1, 1);

        gridPane.add(labelReleaseDate, 0, 2);
        gridPane.add(releaseDate, 1, 2);

        // buttons
        gridPane.add(buttonSave, 1, 5);
        gridPane.add(buttonNew, 1, 7);
        gridPane.add(buttonDelete, 2, 7);
        gridPane.add(buttonSwitch, 3, 7);

        hBoxButtons = new HBox();
        hBoxButtons.setPadding(new Insets(10, 10, 10, 100));
        hBoxButtons.setSpacing(20); // distance between buttons
        hBoxButtons.getChildren().addAll(buttonSave, buttonNew, buttonDelete, buttonSwitch);

        vBox = new VBox();

        root = vBox;

        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(gridPane, hBoxButtons, listView);
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public TextField getTextFieldBrandName() {
        return textFieldBrandName;
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

    public Button getButtonDelete() {
        return buttonDelete;
    }

    // TODO make this button working
    public Button getButtonSwitch() {
        return buttonSwitch;
    }

    public ListView<Smartphone> getListView() {
        return listView;
    }
}
