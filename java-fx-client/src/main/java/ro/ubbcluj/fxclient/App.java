package ro.ubbcluj.fxclient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"ro.ubbcluj.fxclient","ro.ubbcluj.service", "ro.ubbcluj.inmemory"})
public class App extends Application {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(App.class);


    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Platform.setImplicitExit(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/home/home.fxml"));
        loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        final var scene = new Scene(loader.load(), 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
