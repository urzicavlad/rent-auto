package ro.ubbcluj.client.controller.car;

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
import ro.ubbcluj.client.controller.Actions;
import ro.ubbcluj.client.controller.common.AlertController;
import ro.ubbcluj.service.model.Car;
import ro.ubbcluj.service.service.CarService;

import java.io.IOException;

import static java.util.Objects.nonNull;


public class CarController implements Actions {

    public TableView<Car> tableViewCars;
    public TableColumn<Car, Long> tableColumnId;
    public TableColumn<Car, String> tableColumnModel;
    public TableColumn<Car, Long> tableColumnKilometers;
    public TableColumn<Car, Double> tableColumnPricePerDay;
    public TableColumn<Car, Integer> tableColumnRentTimes;

    public Button btnAdd;
    public Button btnUndo;
    public Button btnRedo;
    public Button btnClear;
    public Button btnDelete;

    private CarService carService;

    private ObservableList<Car> cars = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            tableColumnPricePerDay.setCellFactory(
                    TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            cars.addAll(carService.getAllCarsByRentTime());
            tableViewCars.setItems(cars);
        });
    }

    @Override
    public void addOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fx/car/car-add.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
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
            AlertController.showError("Error occurred! ", e.getCause().getMessage(), e.getMessage());
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @Override
    public void deleteOnClick() {
        Car car = tableViewCars.getSelectionModel().getSelectedItem();
        if (nonNull(car)) {
            try {
                carService.delete(car);
                cars.clear();
                cars.addAll(carService.getAllCarsByRentTime());
            } catch (RuntimeException rex) {
                System.out.println("Exception occurred: {}" + rex.getMessage());
                AlertController.showError("Error occurred!", rex.getCause().getMessage(), rex.getMessage());
            }
        }
    }

    @Override
    public void undoOnClick() {

    }

    @Override
    public void redoOnClick() {

    }

    @Override
    public void clearOnClick() {

    }

    public void setServices(CarService carService) {
        this.carService = carService;
    }

}
