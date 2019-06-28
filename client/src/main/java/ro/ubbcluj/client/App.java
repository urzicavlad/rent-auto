package ro.ubbcluj.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fx/home/home.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        final var scene = new Scene(homeLoader.load(), 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
