package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static sample.CommentReader.commentDeleter;

public class Main extends Application {

    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage= primaryStage;
        primaryStage.setTitle("Plag Catcher");
        primaryStage.setScene(new Scene(sceneCreator(), 300, 275));
        primaryStage.show();
    }

    public Group sceneCreator()
    {
        Group primaryScene= new Group();


        Button exitButton= new Button("exit");
        exitButton.setTranslateX(80);
        exitButton.setTranslateY(20);
        exitButton.setMinSize(50, 30);
        exitButton.setOnAction(e->{
            primaryStage.close();
        });
        primaryScene.getChildren().addAll(exitButton);


        Button startButton= new Button("start");
        startButton.setTranslateX(20);
        startButton.setTranslateY(20);
        startButton.setMinSize(50, 30);
        startButton.setOnAction(e->{
            try {
                commentDeleter();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        primaryScene.getChildren().addAll(startButton);

        return primaryScene;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
