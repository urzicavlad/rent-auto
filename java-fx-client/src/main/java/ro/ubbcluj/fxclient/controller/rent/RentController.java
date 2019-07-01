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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.controller.DefaultActions;
import ro.ubbcluj.fxclient.controller.common.AlertsAndInfos;
import ro.ubbcluj.common.model.Rent;
import ro.ubbcluj.fxclient.util.StageBuilder;
import ro.ubbcluj.service.service.RentService;

import java.io.IOException;

import static java.util.Objects.nonNull;
import static ro.ubbcluj.fxclient.util.Views.ADD_CAR_VIEW;

@Controller
public class RentController implements DefaultActions {

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
    @FXML
    private Button btnCancel;

    private final RentService rentService;

    private ObservableList<Rent> rents = FXCollections.observableArrayList();

    private final StageBuilder stageBuilder;

    public RentController(RentService rentService, StageBuilder stageBuilder) {
        this.rentService = rentService;
        this.stageBuilder = stageBuilder;
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
            this.stageBuilder.withView(ADD_CAR_VIEW)
                    .withWidth(500d)
                    .withHeight(300d)
                    .build()
                    .showAndWait();
            rents.clear();
            rents.addAll(rentService.getAll());
        } catch (IOException e) {
            AlertsAndInfos.showError("Error occurred!", e.getCause().getMessage(), e.getMessage());
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
                AlertsAndInfos.showError("Error occured!", rex.getCause().getMessage(), rex.getMessage());
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
