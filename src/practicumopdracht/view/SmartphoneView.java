package practicumopdracht.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

    private GridPane gridPane;

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
        buttonSwitch = new Button("Schakelen");

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

    public GridPane getGridPane() {
        return gridPane;
    }
}
