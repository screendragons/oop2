package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.SmartphoneController;
import practicumopdracht.controllers.SpecificationController;

public class MainApplication extends Application {
    private final String TITLE = "Smartphone - specificatie";
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }
        this.stage = stage;

        SmartphoneController smartphoneController = new SmartphoneController();
        Scene smartphone = new Scene(smartphoneController.getView().getRoot());

        // TODO switchen naar specification view error
//        SpecificationController specificationController = new SpecificationController();
//        Scene specification = new Scene(specificationController.getView().getRoot());

        stage.setScene(smartphone);

//        stage.setScene(specification);

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam, TITLE));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.show();
    }

    public static void switchController(Controller controller){
        stage.setScene(new Scene(controller.getView().getRoot()));
//        stage.show();
    }
}
