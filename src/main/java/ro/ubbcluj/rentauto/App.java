package ro.ubbcluj.rentauto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ro.ubbcluj.rentauto.model.Rent;

public class App extends Application {

    public static void main(String[] args) throws JsonProcessingException {
//        launch();

        Rent car = new Rent();
        car.setKilometers(100);
        car.setDays(3);
        car.setId(1l);
        car.setCarId(1l);
        ObjectMapper objectMapper = new ObjectMapper();
        final var s = objectMapper.writeValueAsString(car);
        System.out.println(s);

    }

    @Override
    public void start(Stage stage) {
        Label l = new Label("Hello, world");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
