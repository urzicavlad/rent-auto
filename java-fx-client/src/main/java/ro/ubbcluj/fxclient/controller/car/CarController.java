package ro.ubbcluj.fxclient.controller.car;

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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.controller.Actions;
import ro.ubbcluj.fxclient.controller.common.AlertController;
import ro.ubbcluj.common.model.Car;
import ro.ubbcluj.service.service.CarService;

import java.io.IOException;

import static java.util.Objects.nonNull;


@Controller
public class CarController implements Actions {

    @FXML
    private TableView<Car> tableViewCars;
    @FXML
    private TableColumn<Car, Long> tableColumnId;
    @FXML
    private TableColumn<Car, String> tableColumnModel;
    @FXML
    private TableColumn<Car, Long> tableColumnKilometers;
    @FXML
    private TableColumn<Car, Double> tableColumnPricePerDay;
    @FXML
    private TableColumn<Car, Integer> tableColumnRentTimes;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUndo;
    @FXML
    private Button btnRedo;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;

    private final CarService carService;

    private final ApplicationContext applicationContext;

    private ObservableList<Car> cars = FXCollections.observableArrayList();

    public CarController(CarService carService, ApplicationContext applicationContext) {
        this.carService = carService;
        this.applicationContext = applicationContext;
    }

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
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            Stage stage = new Stage();

            stage.setTitle("Car add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
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

}
