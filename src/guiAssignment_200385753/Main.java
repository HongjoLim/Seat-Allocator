package guiAssignment_200385753;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

    /**
     * NAME: HONGJO LIM
     * DATE: June 30, 2018
     * PURPOSE: This class is the Main entry point to the program
     * This program randomly designates seats for students
     * after entering a student's name and choosing the color of the seat
     */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Seats");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
