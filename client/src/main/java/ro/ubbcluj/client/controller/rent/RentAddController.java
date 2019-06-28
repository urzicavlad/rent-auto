package ro.ubbcluj.client.controller.rent;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.ubbcluj.client.controller.common.AlertController;
import ro.ubbcluj.service.model.Rent;
import ro.ubbcluj.service.service.RentService;

public class RentAddController {


    public TextField days;
    public TextField kilometers;
    public TextField carId;
    public Button btnAdd;
    public Button btnCancel;
    public Spinner id;

    private RentService rentService;

    public void setService(RentService rentService) {
        this.rentService = rentService;
    }

    public void btnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick() {
        try {
            Rent rent = new Rent();
            rent.setId(Long.valueOf((Integer) id.getValue()));
            rent.setCarId(Long.valueOf(carId.getText()));
            rent.setDays(Integer.valueOf(days.getText()));
            rent.setKilometers(Integer.valueOf(kilometers.getText()));
            rentService.add(rent);
            btnCancelClick();
        } catch (RuntimeException rex) {
            AlertController.showError("Error occured!", this.getClass().toString(), "btnAddClick");
            System.out.println("Exception occurred: {}" + rex.getMessage());
        }
    }
}
