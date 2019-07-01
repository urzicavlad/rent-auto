package ro.ubbcluj.fxclient.controller.rent;

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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.controller.Actions;
import ro.ubbcluj.fxclient.controller.common.AlertController;
import ro.ubbcluj.common.model.Rent;
import ro.ubbcluj.service.service.RentService;

import java.io.IOException;

import static java.util.Objects.nonNull;

@Controller
public class RentController implements Actions {

    @FXML
    private TableView<Rent> tableViewRents;
    @FXML
    private TableColumn<Rent, Long> tableColumnId;
    @FXML
    private TableColumn<Rent, String> tableColumnCar;
    @FXML
    private TableColumn<Rent, Long> tableColumnKilometers;
    @FXML
    private TableColumn<Rent, Integer> tableColumnDays;

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

    private final RentService rentService;

    private final ApplicationContext applicationContext;

    private ObservableList<Rent> rents = FXCollections.observableArrayList();

    public RentController(RentService rentService, ApplicationContext applicationContext) {
        this.rentService = rentService;
        this.applicationContext = applicationContext;
    }

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
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();

            stage.setTitle("Rent add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
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

}
