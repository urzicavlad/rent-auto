package ro.ubbcluj.webclient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "ro.ubbcluj.webclient")
public class Application {

    public static void main(String[] args) throws Exception {
        new AnnotationConfigApplicationContext(Application.class)
                .getBean(UndertowServer.class)
                .start();
    }

}

