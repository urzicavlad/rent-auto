package ro.ubbcluj.client.controller.car;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.ubbcluj.client.controller.common.AlertController;
import ro.ubbcluj.service.model.Car;
import ro.ubbcluj.service.service.CarService;

public class CarAddController {

    public TextField model;
    public TextField kilometers;
    public TextField pricePerDay;
    public Button btnAdd;
    public Button btnCancel;
    public Spinner id;

    private CarService carService;

    public void setService(CarService carService) {
        this.carService = carService;
    }

    public void btnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick() {
        try {
            Car car = new Car();
            car.setModel(this.model.getText());
            car.setPricePerDay(Double.valueOf(this.pricePerDay.getText()));
            car.setKilometers(Long.valueOf(this.kilometers.getText()));
            car.setId(Long.valueOf((Integer) id.getValue()));
            carService.add(car);
            btnCancelClick();
        } catch (RuntimeException rex) {
            AlertController.showError("Error occured!", this.getClass().toString(), "btnAddClick");
            System.out.println("Exception occurred: {}" + rex.getMessage());
        }
    }
}
