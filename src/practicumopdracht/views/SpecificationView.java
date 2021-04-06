package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Smartphone;
import practicumopdracht.models.Specification;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class SpecificationView extends View {
    // labels
    private Label labelMaster;
    private Label labelInch;
    private Label labelHeight;
    private Label labelWidth;
    private Label labelThickness;
    private Label labelFingerprintSensor;
    private Label labelOperatingSystem;
    private Label labelNotes;
    private Label labelSort;

    // textfields
    private TextField textFieldInch;
    private TextField textFieldHeight;
    private TextField textFieldWidth;
    private TextField textFieldThickness;

    // radio button
    private ToggleGroup toggleGroup;
    private RadioButton btnSortAscTypeOne;
    private RadioButton btnSortDescTypeOne;
    private RadioButton btnSortAscTypeTwo;
    private RadioButton btnSortDescTypeTwo;

    // checkbox
    private CheckBox checkBoxFingerprintSensor;

    // combobox master
    private ComboBox<Smartphone> comboBoxMaster;

    // combobox OS
    private ComboBox<String> comboBoxOperatingSystem;

    // note
    private TextArea textAreaNote;

    // buttons
    private Button buttonSave;
    private Button buttonNew;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonSwitch;

    private ListView<Specification> listView;

    private GridPane gridPaneSpec;
    private VBox vBoxSpec;
    private HBox menuButtonsSpec;
    private HBox sort;
    private BorderPane borderPane;
    private BorderPane menuBorderPane;

    private Parent root;

    public SpecificationView() {
        initializeRoot();
    }

    private void initializeRoot() {
        // master
        labelMaster = new Label("Smartphone");

        // labels
        labelInch = new Label("Number of inches");
        labelHeight = new Label("Length smartphone");
        labelWidth = new Label("Width smartphone");
        labelThickness = new Label("Thickness smartphone");
        labelFingerprintSensor = new Label("Fingerprint");
        labelOperatingSystem = new Label("Operatingsystem");
        labelNotes = new Label("Description");
        labelSort = new Label("Sort");

        // textfields
        textFieldInch = new TextField();
        textFieldHeight = new TextField();
        textFieldWidth = new TextField();
        textFieldThickness = new TextField();

        // checkbox
        checkBoxFingerprintSensor = new CheckBox();

        // combobox master
        comboBoxMaster = new ComboBox<>();
        comboBoxMaster.setPrefHeight(110);

        // combobox operating system
        comboBoxOperatingSystem = new ComboBox<>();
        comboBoxOperatingSystem.getItems().addAll(
                "Android",
                "iOS"
        );
        // default text for when there is no option selected
        comboBoxOperatingSystem.setPromptText("What is the operatingsystem?");

        textAreaNote = new TextArea();
        textAreaNote.setPrefHeight(100);

        // buttons
        buttonSave = new Button("Save");
        buttonNew = new Button("New");
        buttonEdit = new Button("Edit");
        buttonDelete = new Button("Delete");
        buttonSwitch = new Button("Switch to master");

        // radiobuttons
        btnSortAscTypeOne = new RadioButton("Sort ascending #1 (A-Z)");
        btnSortDescTypeOne = new RadioButton("Sort descending #1 (Z-A)");

        btnSortAscTypeTwo = new RadioButton("Sort ascending #2 (A-Z)");
        btnSortDescTypeTwo = new RadioButton("Sort descending #2 (Z-A)");

        // sets a gap vertically
        gridPaneSpec = new GridPane();
        gridPaneSpec.setVgap(10);
        gridPaneSpec.setHgap(10);

        // Constructs a new Insets instance with four different offsets
        gridPaneSpec.setPadding(new Insets(5, 10, 10, 10));

        gridPaneSpec.add(labelMaster, 0, 0);
        gridPaneSpec.add(comboBoxMaster, 1, 0);

        gridPaneSpec.add(labelInch, 0, 1);
        gridPaneSpec.add(textFieldInch, 1, 1);

        gridPaneSpec.add(labelHeight, 0, 2);
        gridPaneSpec.add(textFieldHeight, 1, 2);

        gridPaneSpec.add(labelWidth, 0, 3);
        gridPaneSpec.add(textFieldWidth, 1, 3);

        gridPaneSpec.add(labelThickness, 0, 4);
        gridPaneSpec.add(textFieldThickness, 1, 4);

        gridPaneSpec.add(labelFingerprintSensor, 0, 5);
        gridPaneSpec.add(checkBoxFingerprintSensor, 1, 5);

        gridPaneSpec.add(labelOperatingSystem, 0, 6);
        gridPaneSpec.add(comboBoxOperatingSystem, 1, 6);

        gridPaneSpec.add(labelNotes, 0, 7);
        gridPaneSpec.add(textAreaNote, 1, 7);

        // buttons
        gridPaneSpec.add(buttonSave, 2, 9);
        gridPaneSpec.add(buttonNew, 3, 9);
        gridPaneSpec.add(buttonEdit, 4, 9);
        gridPaneSpec.add(buttonDelete, 5, 9);
        gridPaneSpec.add(buttonSwitch, 6, 9);

        // buttons added to hbox

        menuButtonsSpec = new HBox();
        menuButtonsSpec.setPadding(new Insets(10, 10, 10, 100));
        menuButtonsSpec.setSpacing(20); // distance between buttons
        menuButtonsSpec.getChildren().addAll(buttonSave, buttonNew, buttonEdit, buttonDelete, buttonSwitch);
        // radio buttons
        gridPaneSpec.add(btnSortDescTypeOne, 0, 20);

        sort = new HBox();
        sort.setPadding(new Insets(10, 10, 10, 60));
        sort.setSpacing(20);

        sort.getChildren().addAll(labelSort, btnSortAscTypeOne, btnSortDescTypeOne,
                btnSortAscTypeTwo, btnSortDescTypeTwo);

        listView = new ListView<>();
        vBoxSpec = new VBox();

        vBoxSpec.setPadding(new Insets(10, 10, 10, 10));
        vBoxSpec.getChildren().addAll(gridPaneSpec, menuButtonsSpec, listView, sort);

        borderPane = new BorderPane();
        borderPane.setCenter(vBoxSpec);
        root = borderPane;
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    public RadioButton getBtnSortAscTypeOne() {
        return btnSortAscTypeOne;
    }

    public RadioButton getBtnSortDescTypeOne() {
        return btnSortDescTypeOne;
    }

    public RadioButton getBtnSortAscTypeTwo() {
        return btnSortAscTypeTwo;
    }

    public RadioButton getBtnSortDescTypeTwo() {
        return btnSortDescTypeTwo;
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

    public ComboBox<Smartphone> getComboBoxMaster() {
        return comboBoxMaster;
    }

    public TextArea getTextAreaNote() {
        return textAreaNote;
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

    public ListView<Specification> getListView() {
        return listView;
    }
}
