package ro.ubbcluj.rentauto.controller.car;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.service.CarService;

import java.awt.event.ActionEvent;
import java.io.IOException;

@Getter
@Slf4j
public class CarController {

    private TableView tableViewCars;
    private TableColumn tableColumnId;
    private TableColumn tableColumnModel;
    private TableColumn tableColumnKilometers;
    private TableColumn tableColumnPricePerDay;
    private TableColumn tableColumnRentTimes;
    private Button btnDeleteCar;
    private Button btnAddCar;

    private CarService carService;

    private ObservableList<Car> cars = FXCollections.observableArrayList();


    private void initialize() {
        Platform.runLater(() -> {
            final var allCarsByRentTime = carService.getAllCarsByRentTime();
            tableViewCars.setItems(cars);
        });
    }

    public void setServices(CarService carService) {
        this.carService = carService;
    }

    public void btnCarAddClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fx/car/car-add.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();

            stage.setTitle("Car add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CarAddController controller = fxmlLoader.getController();
            controller.setService(carService);
            stage.showAndWait();
            cars.clear();
            cars.addAll(carService.getAllCarsByRentTime());

        } catch (IOException e) {
            log.info("error");
        }
    }
}
