package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.SmartphoneController;
import practicumopdracht.data.*;

public class MainApplication extends Application {
    private final String TITLE = "Smartphone -> specificatie";
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;
    private static Stage stage;
    private static SmartphoneDAO smartphoneDAO;
    private static SpecificationDAO specificationDAO;

    @Override
    public void start(Stage stage) {
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        // switch easily between the DAO's
        // master
        smartphoneDAO = new TextSmartphoneDAO();
//        smartphoneDAO = new FakeSmartphoneDAO();
//        smartphoneDAO = new BinairySmartphoneDAO();

        // detail
        specificationDAO = new TextSpecificationDAO();
//        specificationDAO = new FakeSpecificationDAO();
//        specificationDAO = new ObjectSpecificationDAO();

        MainApplication.stage = stage;

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s - %s", Main.studentNaam, TITLE));
        stage.getIcons().add(new Image("file:phone.png"));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        switchController(new SmartphoneController());
    }

    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }

    public static SmartphoneDAO getSmartphoneDAO() {
        return smartphoneDAO;
    }

    public static SpecificationDAO getSpecificationDAO() {
        return specificationDAO;
    }
}
