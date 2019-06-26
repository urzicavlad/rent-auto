package ro.ubbcluj.rentauto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fx/home/home.fxml"));
        Parent root = homeLoader.load();
        stage.setTitle("Rent manager");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
