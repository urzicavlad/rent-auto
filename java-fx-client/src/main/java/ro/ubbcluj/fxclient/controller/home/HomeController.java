package ro.ubbcluj.fxclient.controller.home;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import ro.ubbcluj.fxclient.controller.common.AlertController;

import java.io.IOException;


@Controller
public class HomeController {

    @FXML
    private Button btnCars;
    @FXML
    private Button btnRents;
    @FXML
    private Button btnExit;

    private final ApplicationContext applicationContext;

    public HomeController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void goToCarsOnClick() {
        goTo("/fx/car/car.fxml");
    }

    public void goToRentsOnClick() {
        goTo("/fx/rent/rent.fxml");
    }

    public void exitOnClick() {
        Platform.exit();
    }

    private void goTo(String path) {
        try {
            initScene(path);
        } catch (IOException e) {
            AlertController.showError("Error occurred!", e.getCause().getMessage(), e.getMessage());
            System.out.println("Exception occurred: {}" + e.getMessage());

        }
    }

    private void initScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        final var stage = createStage(path, fxmlLoader);
        stage.showAndWait();
    }


    private Stage createStage(String path, FXMLLoader fxmlLoader) throws IOException {
        fxmlLoader.setLocation(getClass().getResource(path));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
