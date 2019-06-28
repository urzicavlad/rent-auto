module rent.auto.client {

    requires javafx.controls;
    requires rent.auto.service;
    requires javafx.fxml;

    opens ro.ubbcluj.client to javafx.graphics;

    exports ro.ubbcluj.client.controller.home;
    exports ro.ubbcluj.client.controller.car;
    exports ro.ubbcluj.client.controller.rent;
    exports ro.ubbcluj.client.controller.common;

}