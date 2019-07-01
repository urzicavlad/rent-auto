package ro.ubbcluj.fxclient.controller.common;

import javafx.scene.control.Alert;

public class AlertsAndInfos {

    public static void showError(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
