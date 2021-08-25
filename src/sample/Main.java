package sample;

import javafx.scene.layout.StackPane;
import process.*;

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
import process.Process;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static sample.CommentReader.fileChooser;
import static process.FileHash.*;
//main class
public class Main extends Application {

    public static Stage primaryStage;
    public static Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage= primaryStage;
        primaryStage.setTitle("Plagarism Catcher");
        mainScene= new Scene(sceneCreator(), 300, 300);
        primaryStage.setScene(mainScene);

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

        Button flushButton= new Button("flush");
        flushButton.setTranslateX(140);
        flushButton.setTranslateY(20);
        flushButton.setMinSize(50, 30);
        flushButton.setOnAction(event -> {
            File outputFolder= new File("G:\\Java Projects\\AntiPlagiarism\\src\\Files\\outputFiles");
            File hashFolder= new File("G:\\Java Projects\\AntiPlagiarism\\src\\Files\\OutputHash");
            File[] outputFiles= outputFolder.listFiles();
            File[] hashFiles= hashFolder.listFiles();

            for(File it : outputFiles)
                it.delete();

            for(File it : hashFiles)
                it.delete();
        });

        Button exitButton= new Button("exit");
        exitButton.setTranslateX(80);
        exitButton.setTranslateY(20);
        exitButton.setMinSize(50, 30);
        exitButton.setOnAction(e->{
            primaryStage.close();
        });


        Button startButton= new Button("start");
        startButton.setTranslateX(20);
        startButton.setTranslateY(20);
        startButton.setMinSize(50, 30);
        startButton.setOnAction(e->{
            try {
                fileChooser();
                activate();

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        });
        primaryScene.getChildren().addAll(startButton, exitButton, flushButton);

        return primaryScene;
    }

    private void activate() throws IOException {

        Process process= new Process();
        process.HashFolder();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
