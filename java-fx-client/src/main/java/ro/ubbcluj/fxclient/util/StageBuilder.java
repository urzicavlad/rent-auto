package ro.ubbcluj.fxclient.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static javafx.stage.StageStyle.UNDECORATED;

@Component
public class StageBuilder {


    private final ApplicationContext applicationContext;
    private Views view;
    private Double width;
    private Double height;

    public StageBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public StageBuilder withView(Views view){
        this.view = view;
        return this;
    }

    public StageBuilder withWidth(Double width){
        this.width = width;
        return this;
    }

    public StageBuilder withHeight(Double height){
        this.height = height;
        return this;
    }

    public Stage build() throws IOException {
        Objects.requireNonNull(view);
        Objects.requireNonNull(width);
        Objects.requireNonNull(height);
        final var fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(view.getPath());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        final var scene = new Scene(fxmlLoader.load(), width, height);
        var stage = new Stage();
        stage.initStyle(UNDECORATED);
        final var x = new AtomicReference<Double>();
        final var y = new AtomicReference<Double>();
        scene.setOnMousePressed(event -> {
            x.set(event.getSceneX());
            y.set(event.getSceneY());
        });
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x.get());
            stage.setY(event.getScreenY() - y.get());
        });
        stage.setScene(scene);
        stage.setResizable(false);
        return stage;
    }
}
