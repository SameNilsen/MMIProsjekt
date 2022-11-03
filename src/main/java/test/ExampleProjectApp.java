package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExampleProjectApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("App.fxml"))));
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        primaryStage.setMaximized(true);
        // primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    //  Feil score avhengig av hvor langt unna knapp

}
