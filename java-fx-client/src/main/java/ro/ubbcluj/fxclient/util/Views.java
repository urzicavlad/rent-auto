package ro.ubbcluj.fxclient.util;

import java.net.URL;

public enum Views {

    HOME_VIEW(Views.class.getResource("/fx/home/home.fxml")),
    CARS_VIEW(Views.class.getResource("/fx/car/car.fxml")),
    ADD_CAR_VIEW(Views.class.getResource("/fx/car/car-add.fxml")),
    RENTS_VIEW(Views.class.getResource("/fx/rent/rent.fxml")),
    ADD_RENT_VIEW(Views.class.getResource("/fx/rent/rent-add.fxml"));

    private URL path;

    Views(URL path) {
        this.path = path;
    }

    public URL getPath() {
        return path;
    }
}
