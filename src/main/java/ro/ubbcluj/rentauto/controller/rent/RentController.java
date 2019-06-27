package ro.ubbcluj.rentauto.controller.rent;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ro.ubbcluj.rentauto.controller.car.CarAddController;
import ro.ubbcluj.rentauto.controller.common.AlertController;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.model.Rent;
import ro.ubbcluj.rentauto.service.RentService;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class RentController {

    public TableView<Rent> tableViewRents;
    public TableColumn<String, Long> tableColumnId;
    public TableColumn<String, String> tableColumnCar;
    public TableColumn<String, Long> tableColumnKilometers;
    public TableColumn<String, Integer> tableColumnDays;

    public Button btnDeleteRent;
    public Button btnAddRent;

    private RentService rentService;

    public void setServices(RentService rentService) {
        this.rentService = rentService;
    }

    private ObservableList<Rent> rents = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            rents.addAll(rentService.getAll());
            tableViewRents.setItems(rents);
        });
    }

    public void addOnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fx/rent/rent-add.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();

            stage.setTitle("Rent add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            RentAddController controller = fxmlLoader.getController();
            controller.setService(rentService);
            stage.showAndWait();
            rents.clear();
            rents.addAll(rentService.getAll());
        } catch (IOException e) {
            AlertController.showError("Error occured!", e.getCause().getMessage(), e.getMessage());
            System.out.println("Exception occurred: {}" + e.getMessage());
        }
    }

    public void deleteOnClick() {
        Rent rent = tableViewRents.getSelectionModel().getSelectedItem();
        if (nonNull(rent)) {
            try {
                rentService.delete(rent);
                rents.clear();
                rents.addAll(rentService.getAll());
            } catch (RuntimeException rex) {
                System.out.println("Exception occurred: {}" + rex.getMessage());
                AlertController.showError("Error occured!", rex.getCause().getMessage(), rex.getMessage());
            }
        }
    }


}
