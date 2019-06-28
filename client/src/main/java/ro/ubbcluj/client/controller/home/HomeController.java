package ro.ubbcluj.client.controller.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ro.ubbcluj.client.controller.car.CarController;
import ro.ubbcluj.client.controller.common.AlertController;
import ro.ubbcluj.client.controller.rent.RentController;
import ro.ubbcluj.service.repository.CarRepositoryImpl;
import ro.ubbcluj.service.repository.RentRepositoryImpl;
import ro.ubbcluj.service.service.CarService;
import ro.ubbcluj.service.service.CarServiceImpl;
import ro.ubbcluj.service.service.RentService;
import ro.ubbcluj.service.service.RentServiceImpl;

import java.io.IOException;
import java.time.Instant;

public class HomeController {

    public Button btnCars;
    public Button btnRents;
    public Button btnExit;

    private CarService carService;

    enum Type {RENT_VIEW, CARS_VIEW}

    public void goToCarsOnClick() {
        goTo("/fx/car/car.fxml", Type.CARS_VIEW);
    }

    public void goToRentsOnClick() {
        goTo("/fx/rent/rent.fxml", Type.RENT_VIEW);
    }

    public void exitOnClick(){
        System.exit(0);
    }

    private void goTo(String path, Type type) {
        carService = new CarServiceImpl(new CarRepositoryImpl());
        try {
            if (type.equals(Type.RENT_VIEW)) initRentScene(path);
            if (type.equals(Type.CARS_VIEW)) initCarScene(path);
        } catch (IOException e) {
            AlertController.showError("Error occurred!", e.getCause().getMessage(), e.getMessage());
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
        RentService rentService = new RentServiceImpl(new RentRepositoryImpl(), carService);
        rentController.setServices(rentService);
        stage.showAndWait();
    }

    private Stage createStage(String path, FXMLLoader fxmlLoader) throws IOException {
        fxmlLoader.setLocation(getClass().getResource(path));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
