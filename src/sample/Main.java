package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static sample.CommentReader.fileChooser;
//main class
public class Main extends Application {

    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage= primaryStage;
        primaryStage.setTitle("Plagarism Catcher");
        primaryStage.setScene(new Scene(sceneCreator(), 300, 275));

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    primaryStage.close();
                    ke.consume(); // <-- stops passing the event to next node
                }
            }
        });

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
                fileChooser();
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
