package ro.ubbcluj.webclient;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletException;


@Configuration
@ComponentScan(basePackages = "ro.ubbcluj.webclient")
public class Application {

    public static void main(String[] args) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    }


}
