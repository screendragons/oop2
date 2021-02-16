package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Specification;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SpecificationView extends View {
    private Label labelInch;
    private Label labelHeight;
    private Label labelWidth;
    private Label labelThickness;
    private Label labelFingerprintSensor;
    private Label labelOperatingSystem;

    private TextField textFieldInch;
    private TextField textFieldHeight;
    private TextField textFieldWidth;
    private TextField textFieldThickness;
    private CheckBox checkBoxFingerprintSensor;
    private ComboBox<String> comboBoxOperatingSystem;

    private Button buttonSave;
    private Button buttonNew;
    private Button buttonDelete;
    private Button buttonSwitch;

    private ListView<Specification> listView;

    private BorderPane borderPane;
    private GridPane gridPane;
    private VBox vBox;

    private Parent root;

    public SpecificationView() {
        initializeRoot();
    }

    private void initializeRoot() {
        labelInch = new Label("Aantal inch");
        labelHeight = new Label("Lengte smartphone");
        labelWidth = new Label("Lengte smartphone");
        labelThickness = new Label("Dikte smartphone");
        labelFingerprintSensor = new Label("Vingerprint");
        labelOperatingSystem = new Label("Besturingssysteem");

        textFieldInch = new TextField();
        textFieldHeight = new TextField();
        textFieldWidth = new TextField();
        textFieldThickness = new TextField();

        checkBoxFingerprintSensor = new CheckBox();
        comboBoxOperatingSystem = new ComboBox<>();

        buttonSave = new Button("Opslaan");
        buttonNew = new Button("Nieuw");
        buttonDelete = new Button("Verwijderen");
        buttonSwitch = new Button("Switchen naar master");

        listView = new ListView<>();
        gridPane = new GridPane();

        comboBoxOperatingSystem = new ComboBox<>();
        comboBoxOperatingSystem.getItems().addAll(
                "Android",
                "iOS"
        );
        comboBoxOperatingSystem.setPromptText("Wat is het besturingssysteem?");

        // sets a gap vertically
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Constructs a new Insets instance with four different offsets
        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setGridLinesVisible(false);
        gridPane.add(labelInch, 0, 0);
        gridPane.add(textFieldInch, 1, 0);

        gridPane.add(labelHeight, 0, 1);
        gridPane.add(textFieldHeight, 1, 1);

        gridPane.add(labelWidth, 0, 2);
        gridPane.add(textFieldWidth, 1, 2);

        gridPane.add(labelThickness, 0, 3);
        gridPane.add(textFieldThickness, 1, 3);

        gridPane.add(labelFingerprintSensor, 0, 4);
        gridPane.add(checkBoxFingerprintSensor, 1, 4);

        gridPane.add(labelOperatingSystem, 0, 5);
        gridPane.add(comboBoxOperatingSystem, 1, 5);

        // buttons
        gridPane.add(buttonSave, 1, 7);
        gridPane.add(buttonNew, 2, 7);
        gridPane.add(buttonDelete, 3, 7);
        gridPane.add(buttonSwitch, 4, 7);

        HBox hBoxButtons = new HBox();
        hBoxButtons.setPadding(new Insets(10, 10, 10, 100));
        hBoxButtons.setSpacing(20); // distance between buttons
        hBoxButtons.getChildren().addAll(buttonSave, buttonNew, buttonDelete, buttonSwitch);

        vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(gridPane, hBoxButtons, listView);
        borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        root = vBox;
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public Label getLabelInch() {
        return labelInch;
    }

    public Label getLabelHeight() {
        return labelHeight;
    }

    public Label getLabelWidth() {
        return labelWidth;
    }

    public Label getLabelThickness() {
        return labelThickness;
    }

    public Label getLabelFingerprintSensor() {
        return labelFingerprintSensor;
    }

    public Label getLabelOperatingSystem() {
        return labelOperatingSystem;
    }

    public TextField getTextFieldInch() {
        return textFieldInch;
    }

    public TextField getTextFieldHeight() {
        return textFieldHeight;
    }

    public TextField getTextFieldWidth() {
        return textFieldWidth;
    }

    public TextField getTextFieldThickness() {
        return textFieldThickness;
    }

    public CheckBox getCheckBoxFingerprintSensor() {
        return checkBoxFingerprintSensor;
    }

    public ComboBox<String> getComboBoxOperatingSystem() {
        return comboBoxOperatingSystem;
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

    public ListView<Specification> getListView() {
        return listView;
    }
}
