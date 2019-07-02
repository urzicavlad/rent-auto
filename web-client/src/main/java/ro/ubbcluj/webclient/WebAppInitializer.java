package ro.ubbcluj.webclient;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class WebAppInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(Application.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DefaultController.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

//        AnnotationConfigWebApplicationContext context = getContext();
//
//
//        ServletRegistration.Dynamic servlet =
//                servletContext.addServlet("dispatcher", new DispatcherServlet(context));
//        servlet.setAsyncSupported(true);
//
//        DeploymentInfo servletBuilder = Servlets.deployment()
//                .setClassLoader(Application.class.getClassLoader())
//                .setContextPath("/myapp")
//                .setDeploymentName("test.war")
//                .addServlet((ServletInfo) servlet);
//
//        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
//        manager.deploy();
//
//        Undertow server = Undertow.builder()
//                .addHttpListener(8080, "localhost")
//                .build();
//        server.start();

    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("ro.ubbcluj.webclient");
        context.register(Application.class);
        return context;
    }
}