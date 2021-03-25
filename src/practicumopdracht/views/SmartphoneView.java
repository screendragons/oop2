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
 * @author Chi Yu Yeung
 */
public class SmartphoneView extends View {
    // labels
    private Label labelSmartphoneName;
    private Label labelSerie;
    private Label labelVersion;
    private Label labelReleaseDate;

    // textfield
    private TextField textFieldSmartphoneName;
    private TextField textFieldVersion;

    // datepicker
    private DatePicker releaseDate;

    // buttons
    private Button buttonSave;
    private Button buttonNew;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonSwitch;
    private Button buttonSaveDAO;
    private Button buttonLoadDAO;

    // combobox
    private ComboBox<String> comboBoxSerie;

    private ListView<Smartphone> listView;

    private GridPane gridPaneSmart;
    private VBox vBoxSmart;
    private HBox menuButtonsSmart;
    private BorderPane borderPane;
    private BorderPane menuBorderPane;

    private Menu menu;
    private MenuItem menuItemSave;
    private MenuItem menuItemLoad;
    private MenuItem menuItemExit;
    private MenuBar menuBar;

    private Parent root;

    public SmartphoneView() {
        initializeRoot();
    }

    private void initializeRoot() {
        // menu
        menu = new Menu("File");

        // menu items
        menuItemSave = new MenuItem("Save");
        menuItemLoad = new MenuItem("Load");
        menuItemExit = new MenuItem("Exit");

        // menubar
        menuBar = new MenuBar();

        // labels
        labelSmartphoneName = new Label("Smartphone name");
        labelSerie = new Label("Serie name");
        labelVersion = new Label("Version");
        labelReleaseDate = new Label("Release date");

        // textfield
        textFieldSmartphoneName = new TextField();
        textFieldVersion = new TextField();

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
//        buttonSaveDAO = new Button("Save to DAO");
//        buttonLoadDAO = new Button("Load from DAO");

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

        // add items to the menu
        menu.getItems().add(menuItemSave);
        menu.getItems().add(menuItemLoad);
        menu.getItems().add(menuItemExit);

        // add the menu to the menubar
        menuBar.getMenus().add(menu);

        // Constructs a new Insets instance with four different offsets
        gridPaneSmart.setPadding(new Insets(10, 10, 10, 10));
        gridPaneSmart.add(labelSmartphoneName, 0, 0);
        gridPaneSmart.add(textFieldSmartphoneName, 1, 0);

        gridPaneSmart.add(labelSerie, 0, 1);
        gridPaneSmart.add(comboBoxSerie, 1, 1);

        gridPaneSmart.add(labelVersion, 0, 2);
        gridPaneSmart.add(textFieldVersion, 1,2);

        gridPaneSmart.add(labelReleaseDate, 0, 3);
        gridPaneSmart.add(releaseDate, 1, 3);

        // buttons
        gridPaneSmart.add(buttonSave, 2, 8);
        gridPaneSmart.add(buttonNew, 3, 8);
        gridPaneSmart.add(buttonEdit, 4, 8);
        gridPaneSmart.add(buttonDelete, 5, 8);
        gridPaneSmart.add(buttonSwitch, 6, 8);
//        gridPaneSmart.add(buttonSaveDAO, 7, 8);
//        gridPaneSmart.add(buttonLoadDAO, 8,8);

        // buttons added to hbox
        menuButtonsSmart = new HBox();
        menuButtonsSmart.setPadding(new Insets(10, 10, 10, 120));
        menuButtonsSmart.setSpacing(20); // distance between buttons
        menuButtonsSmart.getChildren().addAll(buttonSave, buttonNew, buttonEdit, buttonDelete, buttonSwitch);
//                buttonSaveDAO, buttonLoadDAO);

        vBoxSmart = new VBox();

        menuBorderPane = new BorderPane(menuBar);

        vBoxSmart.setPadding(new Insets(10, 10, 10, 10));
        vBoxSmart.getChildren().addAll(menuBorderPane, gridPaneSmart, menuButtonsSmart, listView);

        borderPane = new BorderPane();
        borderPane.setCenter(vBoxSmart);
        root = borderPane;
    }

    public MenuItem getMenuItemSave() {
        return menuItemSave;
    }

    public MenuItem getMenuItemLoad() {
        return menuItemLoad;
    }

    public MenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public TextField getTextFieldSmartphoneName() {
        return textFieldSmartphoneName;
    }

    public TextField getTextFieldVersion() {
        return textFieldVersion;
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

    public Button getButtonSaveDAO() {
        return buttonSaveDAO;
    }

    public Button getButtonLoadDAO() {
        return buttonLoadDAO;
    }

    public ListView<Smartphone> getListView() {
        return listView;
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
