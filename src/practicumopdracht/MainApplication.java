package practicumopdracht;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

        HBox rootHBox = new HBox();
        rootHBox.setAlignment(Pos.CENTER);
        rootHBox.setStyle("-fx-background-color: grey");

        TextField textField = new TextField();

        StackPane stackPane = new StackPane();

        VBox vBox1 = new VBox();

        // iets onder elkaar stapelen
        HBox userNameBox = new HBox();

        Label userNameLabel = new Label("Username:");
        TextField userNameTextField = new TextField();

        userNameBox.getChildren().addAll(userNameLabel, userNameTextField);

        // centereren
        HBox passwordBox = new HBox();

        Label passwordLabel = new Label("Password");
        TextField passwordTextField = new PasswordField();

        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        Button button = new Button("Login");

        rootHBox.getChildren().addAll(userNameBox, passwordBox, button);

        Scene scene = new Scene(rootHBox);

        stage.setScene(scene);

        stage.show();
    }
}
