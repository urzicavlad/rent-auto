package ro.ubbcluj.fxclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static javafx.stage.StageStyle.UNDECORATED;
import static ro.ubbcluj.fxclient.util.Views.HOME_VIEW;

@Configuration
@ComponentScan(basePackages = {"ro.ubbcluj.fxclient","ro.ubbcluj.service", "ro.ubbcluj.inmemory"})
public class FxApplication extends Application {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(FxApplication.class);


    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HOME_VIEW.getPath());
        loader.setControllerFactory(APPLICATION_CONTEXT::getBean);
        primaryStage.initStyle(UNDECORATED);
        final var scene = new Scene(loader.load(), 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
