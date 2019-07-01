package ro.ubbcluj.fxclient.controller.car;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.common.model.Car;
import ro.ubbcluj.fxclient.controller.DefaultActions;
import ro.ubbcluj.fxclient.controller.common.AlertsAndInfos;
import ro.ubbcluj.fxclient.util.StageBuilder;
import ro.ubbcluj.service.service.CarService;

import java.io.IOException;

import static java.util.Objects.nonNull;
import static ro.ubbcluj.fxclient.util.Views.ADD_CAR_VIEW;


@Controller
public class CarController implements DefaultActions {

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
    @FXML
    private Button btnCancel;


    private final CarService carService;

    private final StageBuilder stageBuilder;

    private ObservableList<Car> cars = FXCollections.observableArrayList();

    public CarController(CarService carService, StageBuilder stageBuilder) {
        this.carService = carService;
        this.stageBuilder = stageBuilder;
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
            this.stageBuilder.withView(ADD_CAR_VIEW)
                    .withWidth(500d)
                    .withHeight(300d)
                    .build()
                    .showAndWait();
            cars.clear();
            cars.addAll(carService.getAllCarsByRentTime());
        } catch (IOException e) {
            AlertsAndInfos.showError("Error occurred! ", e.getCause().getMessage(), e.getMessage());
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
                AlertsAndInfos.showError("Error occurred!", rex.getCause().getMessage(), rex.getMessage());
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

    @Override
    public void cancel() {
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }
}
