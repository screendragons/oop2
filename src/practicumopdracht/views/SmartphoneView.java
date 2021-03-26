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

    // listview
    private ListView<Smartphone> listView;

    private GridPane gridPaneSmart;
    private VBox vBoxSmart;
    private HBox menuButtonsSmart;
    private HBox menuFileSort;
    private BorderPane borderPane;

    private BorderPane menuFileBorderPane;
    private BorderPane menuSortBorderPane;

    // menu file
    private Menu menuFile;
    private MenuItem menuItemSave;
    private MenuItem menuItemLoad;
    private MenuItem menuItemExit;
    private MenuBar menuBarFile;

    // menu file
    private Menu menuSort;
    private MenuItem menuItemAsc;
    private MenuItem menuItemDesc;
    private MenuBar menuBarSort;

    private Parent root;

    public SmartphoneView() {
        initializeRoot();
    }

    private void initializeRoot() {
        // menu file
        menuFile = new Menu("File");
        menuSort = new Menu("Sort");

        // menu items
        // file
        menuItemSave = new MenuItem("Save");
        menuItemLoad = new MenuItem("Load");
        menuItemExit = new MenuItem("Exit");

        //sort
        menuItemAsc = new MenuItem("Sort ascending (A-Z)");
        menuItemDesc = new MenuItem("Sort descending (Z-A)");

        // menubar
        menuBarFile = new MenuBar();
        menuBarSort = new MenuBar();

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

        // add items to the menu file
        menuFile.getItems().add(menuItemSave);
        menuFile.getItems().add(menuItemLoad);
        menuFile.getItems().add(menuItemExit);

        // add items to the menu sort
        menuSort.getItems().add(menuItemAsc);
        menuSort.getItems().add(menuItemDesc);

        // add the menu to the menubar file
        menuBarFile.getMenus().add(menuFile);

        // add the menu to the menubar sort
        menuBarSort.getMenus().add(menuSort);

        // constructs a new Insets instance with four different offsets
        // top right bottom left
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

        // menu file
        menuFileBorderPane = new BorderPane(menuBarFile);

        // menu sort
        menuSortBorderPane = new BorderPane(menuBarSort);

        menuFileSort = new HBox();
//        menuButtonsSmart.setPadding(new Insets(10, 10, 10, 10));
        menuFileSort.getChildren().addAll(menuFileBorderPane, menuSortBorderPane);

        vBoxSmart.setPadding(new Insets(10, 10, 10, 10));
        vBoxSmart.getChildren().addAll(menuFileSort , gridPaneSmart, menuButtonsSmart, listView);

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

    public MenuItem getMenuItemAsc() {
        return menuItemAsc;
    }

    public MenuItem getMenuItemDesc() {
        return menuItemDesc;
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
