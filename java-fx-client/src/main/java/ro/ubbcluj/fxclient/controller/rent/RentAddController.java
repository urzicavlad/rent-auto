package ro.ubbcluj.fxclient.controller.rent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.controller.common.AlertsAndInfos;
import ro.ubbcluj.common.model.Rent;
import ro.ubbcluj.service.service.RentService;

@Controller
public class RentAddController {

    @FXML
    private TextField days;
    @FXML
    private TextField kilometers;
    @FXML
    private TextField carId;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;


    private final RentService rentService;

    public RentAddController(RentService rentService) {
        this.rentService = rentService;
    }


    public void btnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick() {
        try {
            Rent rent = new Rent();
            rent.setCarId(Long.valueOf(carId.getText()));
            rent.setDays(Integer.valueOf(days.getText()));
            rent.setKilometers(Integer.valueOf(kilometers.getText()));
            rentService.add(rent);
            btnCancelClick();
        } catch (RuntimeException rex) {
            AlertsAndInfos.showError("Error occurred!", this.getClass().toString(), "btnAddClick");
        }
    }
}
