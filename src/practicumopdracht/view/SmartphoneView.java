package practicumopdracht.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import practicumopdracht.model.Smartphone;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SmartphoneView {
    // labels
    private Label labelBrandName;
    private Label labelSerie;
    private Label labelReleaseDate;

    // textfields
    private TextField textFieldBrandName;
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

    public SmartphoneView() {
        initializeRoot();
    }

    private void initializeRoot() {
        labelBrandName = new Label("Merk naam");
        labelSerie = new Label("Serie naam");
        labelReleaseDate = new Label("Datum uitgave");

        textFieldSerie = new TextField();
        textFieldBrandName = new TextField();

        releaseDate = new DatePicker();

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

        gridPane.add(labelSerie, 0,1);
        gridPane.add(textFieldSerie, 1,1);

        gridPane.add(labelReleaseDate, 0, 2);
        gridPane.add(releaseDate, 1,2);

        gridPane.add(buttonSave, 1, 5);
        gridPane.add(buttonNew, 1, 7);
        gridPane.add(buttonDelete, 2, 7);
        gridPane.add(buttonSwitch, 3, 7);
    }

    public BorderPane getRoot() {
        return borderPane;
    }

    public TextField getTextFieldBrandName() {
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
