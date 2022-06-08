package com.example.rutgerscafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 This is the MainApplication class that runs the MainController class.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class MainApplication extends Application {
    @Override
    /**
     * This sets the stage and scene for the GUI.
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Rutgers Cafe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This launches the GUI / program.
     * @param args Commandline arguments
     */
    public static void main(String[] args) {
        launch();
    }
}