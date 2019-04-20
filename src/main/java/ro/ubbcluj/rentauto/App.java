package ro.ubbcluj.rentauto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.ubbcluj.rentauto.controller.car.CarController;
import ro.ubbcluj.rentauto.repository.CarRepositoryImpl;
import ro.ubbcluj.rentauto.service.CarService;
import ro.ubbcluj.rentauto.service.CarServiceImpl;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader carLoader = new FXMLLoader(getClass().getResource("/fx/car/car.fxml"));
        Parent root = carLoader.load();
        CarController carController = carLoader.getController();
        CarService carService = new CarServiceImpl(new CarRepositoryImpl());
        carController.setServices(carService);
        stage.setTitle("Rent manager");
        stage.setScene(new Scene(root, 650, 500));
        stage.show();
    }
}
