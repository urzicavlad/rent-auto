package ro.ubbcluj.fxclient.controller.car;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.controller.common.AlertsAndInfos;
import ro.ubbcluj.common.model.Car;
import ro.ubbcluj.service.service.CarService;

@Controller
public class CarAddController {

    @FXML
    private TextField model;
    @FXML
    private TextField kilometers;
    @FXML
    private TextField pricePerDay;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;

    private final CarService carService;

    public CarAddController(CarService carService) {
        this.carService = carService;
    }


    public void btnCancelClick() {
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick() {
        try {
            Car car = new Car();
            car.setModel(this.model.getText());
            car.setPricePerDay(Double.valueOf(this.pricePerDay.getText()));
            car.setKilometers(Long.valueOf(this.kilometers.getText()));
            carService.add(car);
            btnCancelClick();
        } catch (RuntimeException rex) {
            AlertsAndInfos.showError("Error occurred! ", this.getClass().toString(), "btnAddClick"); }
    }
}
