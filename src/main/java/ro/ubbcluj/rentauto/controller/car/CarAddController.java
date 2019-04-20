package ro.ubbcluj.rentauto.controller.car;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.service.CarService;

import java.awt.event.ActionEvent;

@Slf4j
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

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick(ActionEvent actionEvent) {

        try {
            Car car = new Car();
            car.setId((Long) id.getValue());
            car.setModel(this.model.getText());
            car.setPricePerDay(Double.valueOf(this.pricePerDay.getText()));
            car.setKilometers(Long.valueOf(this.kilometers.getText()));
            carService.add(car);

            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            log.info("problems!");
        }
    }

}
