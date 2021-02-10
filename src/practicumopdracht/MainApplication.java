package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controller.SmartphoneController;
import practicumopdracht.view.SmartphoneView;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(480);

        SmartphoneController smartphoneController = new SmartphoneController();

        stage.setScene(new Scene(smartphoneController.getSmartphoneView().getRoot()));

        stage.show();
    }
}
