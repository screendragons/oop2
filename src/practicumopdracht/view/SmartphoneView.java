package practicumopdracht.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.model.Smartphone;

/**
 * Functionality:
 * s
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneView {
    // labels
    private Label labelBrandName;
    private Label labelSerie;
    private Label labelReleaseDate;

    // textfields
    private TextArea textFieldBrandName;
    private TextField textFieldSerie;

    // datepicker
    private DatePicker releaseDate;

    private Button buttonSave;
    private Button buttonNew;
    private Button buttonDelete;
    private Button buttonSwitch;

    // TODO vragen
    private ListView<Smartphone> listView;

    private GridPane gridPane;
    private BorderPane borderPane;
    private VBox vBox;

    public SmartphoneView() {
        initializeRoot();
    }

    private void initializeRoot() {
        // labels
        labelBrandName = new Label("Merk naam");
        labelSerie = new Label("Serie naam");
        labelReleaseDate = new Label("Datum uitgave");

        textFieldSerie = new TextField();
        textFieldBrandName = new TextArea();

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

        // Constructs a new Insets instance with four different offsets
        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setGridLinesVisible(false);
        gridPane.add(labelBrandName, 0, 0);
        gridPane.add(textFieldBrandName, 1, 0);

        gridPane.add(labelSerie, 0, 1);
        gridPane.add(textFieldSerie, 1, 1);

        gridPane.add(labelReleaseDate, 0, 2);
        gridPane.add(releaseDate, 1, 2);

        // buttons
        gridPane.add(buttonSave, 1, 5);
        gridPane.add(buttonNew, 1, 7);
        gridPane.add(buttonDelete, 2, 7);
        gridPane.add(buttonSwitch, 3, 7);

        HBox hBoxButtons = new HBox();
        hBoxButtons.setPadding(new Insets(10, 10, 10, 100));
        hBoxButtons.setSpacing(20); // distance between buttons
        hBoxButtons.getChildren().addAll(buttonSave, buttonNew, buttonDelete, buttonSwitch);

        vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(gridPane, hBoxButtons, listView);
        borderPane = new BorderPane();
        borderPane.setCenter(vBox);
    }

    public BorderPane getRoot() {
        return borderPane;
    }

    public TextArea getTextFieldBrandName() {
        return textFieldBrandName;
    }

    public TextField getTextFieldSerie() {
        return textFieldSerie;
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

    public Button getButtonSwitch() {
        return buttonSwitch;
    }

    public ListView<Smartphone> getListView() {
        return listView;
    }
}
