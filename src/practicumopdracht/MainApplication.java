package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controller.SmartphoneController;
import practicumopdracht.controller.SpecificationController;
import practicumopdracht.view.SmartphoneView;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        SmartphoneController smartphoneController = new SmartphoneController();
        Scene smartphone = new Scene(smartphoneController.getSmartphoneView().getRoot());

        SpecificationController specificationController = new SpecificationController();
        Scene specification = new Scene(specificationController.getSpecificationView().getRoot());

        stage.setScene(smartphone);
        stage.setScene(specification);

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(480);
        stage.show();
    }
}
