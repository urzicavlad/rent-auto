package ro.ubbcluj.webclient;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.RedirectHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.core.DeploymentImpl;
import io.undertow.servlet.core.DeploymentManagerImpl;
import io.undertow.servlet.core.ServletContainerImpl;
import io.undertow.servlet.spec.ServletContextImpl;
import io.undertow.servlet.util.ImmediateInstanceHandle;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Component
public class UndertowServer implements InitializingBean, DisposableBean {


    private String webAppName = "rent-auto";

    private int port = 8080;

    private Undertow server;

    private DeploymentManager manager;

    private DeploymentInfo deploymentInfo;

    private AnnotationConfigWebApplicationContext root;


    private ServletContext getServletContext(DeploymentInfo deploymentInfo) {
        final var servletContainer = new ServletContainerImpl();
        manager = new DeploymentManagerImpl(deploymentInfo, servletContainer);
        final var deployment = new DeploymentImpl(manager, deploymentInfo, servletContainer);
        return new ServletContextImpl(servletContainer, deployment);
    }


    private ImmediateInstanceHandle getDispatcherServlet() {
        return new ImmediateInstanceHandle<>(
                new DispatcherServlet(root)
        );
    }

    private ServletInfo getServletInfo() {
        return new ServletInfo("DispatcherServlet", DispatcherServlet.class,
                this::getDispatcherServlet);
    }


    private void register() {
        root = new AnnotationConfigWebApplicationContext();
        root.register(Application.class);
        root.refresh();
        deploymentInfo.addServlet(getServletInfo().addMapping("/*"));
        root.setServletContext(getServletContext(deploymentInfo));
    }

    public void start() throws ServletException {
        register();
        manager.deploy();
        PathHandler pathHandler = constructPathHandler(manager.start());
        server = getServer(pathHandler);
        server.start();
    }

    public void afterPropertiesSet() {
        deploymentInfo = Servlets.deployment()
                .setClassLoader(UndertowServer.class.getClassLoader())
                .setContextPath(webAppName)
                .setDeploymentName(webAppName + "-war");
    }


    private PathHandler constructPathHandler(HttpHandler httpHandler) {
        RedirectHandler defaultHandler = Handlers.redirect("/" + webAppName);
        PathHandler pathHandler = Handlers.path(defaultHandler);
        pathHandler.addPrefixPath("/" + webAppName, httpHandler);
        return pathHandler;
    }

    private Undertow getServer(PathHandler pathHandler) {
        return Undertow.builder()
                .addHttpListener(port, "localhost")
                .setHandler(pathHandler)
                .build();
    }

    @Override
    public void destroy() throws Exception {
        server.stop();
        manager.stop();
        manager.undeploy();
    }

}
