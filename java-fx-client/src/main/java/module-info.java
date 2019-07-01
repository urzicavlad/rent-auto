module rent.auto.javafx.client {

    requires javafx.controls;
    requires javafx.fxml;

    requires rent.auto.service;
    requires rent.auto.common;

    requires spring.aop;
    requires spring.jcl;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.expression;

    opens ro.ubbcluj.fxclient to javafx.graphics, spring.core;
    opens ro.ubbcluj.fxclient.controller to spring.core;
    opens ro.ubbcluj.fxclient.controller.home to javafx.fxml, spring.core;
    opens ro.ubbcluj.fxclient.controller.car to javafx.fxml, spring.core;
    opens ro.ubbcluj.fxclient.controller.rent to javafx.fxml, spring.core;

    exports ro.ubbcluj.fxclient;
    exports ro.ubbcluj.fxclient.controller.home;
    exports ro.ubbcluj.fxclient.controller.car;
    exports ro.ubbcluj.fxclient.controller.rent;
    exports ro.ubbcluj.fxclient.controller.common;

}