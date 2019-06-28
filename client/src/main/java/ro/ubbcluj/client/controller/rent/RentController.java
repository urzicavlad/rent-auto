package ro.ubbcluj.client.controller.rent;

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
import ro.ubbcluj.client.controller.Actions;
import ro.ubbcluj.client.controller.common.AlertController;
import ro.ubbcluj.service.model.Rent;
import ro.ubbcluj.service.service.RentService;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class RentController implements Actions {

    public TableView<Rent> tableViewRents;
    public TableColumn<Rent, Long> tableColumnId;
    public TableColumn<Rent, String> tableColumnCar;
    public TableColumn<Rent, Long> tableColumnKilometers;
    public TableColumn<Rent, Integer> tableColumnDays;

    public Button btnAdd;
    public Button btnUndo;
    public Button btnRedo;
    public Button btnClear;
    public Button btnDelete;

    private RentService rentService;

    private ObservableList<Rent> rents = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            rents.addAll(rentService.getAll());
            tableViewRents.setItems(rents);
        });
    }

    @Override
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

    @Override
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

    @Override
    public void undoOnClick() {

    }

    @Override
    public void redoOnClick() {

    }

    @Override
    public void clearOnClick() {

    }

    public void setServices(RentService rentService) {
        this.rentService = rentService;
    }

}
