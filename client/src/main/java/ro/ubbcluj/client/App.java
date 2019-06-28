package ro.ubbcluj.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ro.ubbcluj.service.repository.CarRepositoryImpl;
import ro.ubbcluj.service.service.CarService;
import ro.ubbcluj.service.service.CarServiceImpl;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fx/home/home.fxml"));
        Parent root = homeLoader.load();
        stage.setTitle("Rent manager");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        stage.setTitle("JavaFX on JDK 11");

//        CarService carService = new CarServiceImpl(new CarRepositoryImpl());

//        Label label = new Label("The Cool Logic Output is: "+ "carService.getAllCarsByRentTime()");
//
//        Scene scene = new Scene(label, 400, 200);
//
//        stage.setScene(scene);
//        stage.show();

    }
}
