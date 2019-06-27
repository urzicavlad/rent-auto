package ro.ubbcluj.rentauto.controller.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ro.ubbcluj.rentauto.controller.car.CarController;
import ro.ubbcluj.rentauto.controller.common.AlertController;
import ro.ubbcluj.rentauto.controller.rent.RentController;
import ro.ubbcluj.rentauto.repository.CarRepositoryImpl;
import ro.ubbcluj.rentauto.repository.RentRepositoryImpl;
import ro.ubbcluj.rentauto.service.CarService;
import ro.ubbcluj.rentauto.service.CarServiceImpl;
import ro.ubbcluj.rentauto.service.RentService;
import ro.ubbcluj.rentauto.service.RentServiceImpl;

import java.io.IOException;

public class HomeController {

    public Button btnCars;
    public Button btnRents;
    private CarService carService;
    private RentService rentService;

    enum Type {RENT_VIEW, CARS_VIEW}

    public void goToCarsOnClick() {
        goTo("/fx/car/car.fxml", Type.CARS_VIEW);
    }

    public void goToRentsOnClick() {
        goTo("/fx/rent/rent.fxml", Type.RENT_VIEW);
    }

    private void goTo(String path, Type type) {
        carService = new CarServiceImpl(new CarRepositoryImpl());
        try {
            if (type.equals(Type.RENT_VIEW)) initRentScene(path);
            if (type.equals(Type.CARS_VIEW)) initCarScene(path);
        } catch (IOException e) {
            AlertController.showError("Error occured!", e.getCause().getMessage(), e.getMessage());
            System.out.println("Exception occurred: {}" + e.getMessage());
        }
    }

    private void initCarScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        final var stage = createStage(path, fxmlLoader);
        CarController carController = fxmlLoader.getController();
        carController.setServices(carService);
        stage.showAndWait();
    }

    private void initRentScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        final var stage = createStage(path, fxmlLoader);
        RentController rentController = fxmlLoader.getController();
        rentService = new RentServiceImpl(new RentRepositoryImpl(), carService);
        rentController.setServices(rentService);
        stage.showAndWait();
    }

    private Stage createStage(String path, FXMLLoader fxmlLoader) throws IOException {
        fxmlLoader.setLocation(getClass().getResource(path));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
