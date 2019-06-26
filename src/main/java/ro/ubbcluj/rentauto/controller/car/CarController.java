package ro.ubbcluj.rentauto.controller.car;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import ro.ubbcluj.rentauto.controller.common.AlertController;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.service.CarService;

import java.io.IOException;

import static java.util.Objects.nonNull;


public class CarController {


    public TableView<Car> tableViewCars;
    public TableColumn<String, Long> tableColumnId;
    public TableColumn<String, String> tableColumnModel;
    public TableColumn<String, Long> tableColumnKilometers;
    public TableColumn<String, Double> tableColumnPricePerDay;
    public TableColumn<String, Integer> tableColumnRentTimes;
    public Button btnDeleteCar;
    public Button btnAddCar;

    private CarService carService;
    private ObservableList<Car> cars = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            tableColumnPricePerDay.setCellFactory(
                    TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            cars.addAll(carService.getAllCarsByRentTime());
            tableViewCars.setItems(cars);
        });
    }

    public void setServices(CarService carService) {
        this.carService = carService;
    }

    public void addOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fx/car/car-add.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
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
            AlertController.showError("Error occured!", e.getCause().getMessage(), e.getMessage());
            System.out.println("Exception occurred: {}" + e.getMessage());
        }
    }

    public void deleteOnClick() {
        Car car = tableViewCars.getSelectionModel().getSelectedItem();
        if (nonNull(car)) {
            try {
                carService.delete(car);
                cars.clear();
                cars.addAll(carService.getAllCarsByRentTime());
            } catch (RuntimeException rex) {
                System.out.println("Exception occurred: {}" + rex.getMessage());
                AlertController.showError("Error occured!", rex.getCause().getMessage(), rex.getMessage());
            }
        }
    }
}
