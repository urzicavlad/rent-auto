package ro.ubbcluj.fxclient.controller.home;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.util.Views;
import ro.ubbcluj.fxclient.controller.common.AlertsAndInfos;
import ro.ubbcluj.fxclient.util.StageBuilder;

import java.io.IOException;

import static ro.ubbcluj.fxclient.util.Views.CARS_VIEW;
import static ro.ubbcluj.fxclient.util.Views.RENTS_VIEW;


@Controller
public class HomeController {

    @FXML
    private Button btnCars;
    @FXML
    private Button btnRents;
    @FXML
    private Button btnExit;

    private final StageBuilder stageBuilder;

    public HomeController(StageBuilder stageBuilder) {
        this.stageBuilder = stageBuilder;
    }


    public void goToCarsOnClick() {
        initialize(CARS_VIEW);
    }

    public void goToRentsOnClick() {
        initialize(RENTS_VIEW);
    }

    public void exitOnClick() {
        Platform.exit();
    }

    private void initialize(Views view) {
        try {
            this.stageBuilder.withView(view)
                    .withWidth(800d)
                    .withHeight(500d)
                    .build()
                    .showAndWait();
        } catch (IOException ioe) {
            AlertsAndInfos.showError("Error occurred! ",
                    ioe.getCause().getMessage(), ioe.getMessage());
        }
    }

}
