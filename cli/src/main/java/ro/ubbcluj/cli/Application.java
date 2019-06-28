package ro.ubbcluj.cli;

import ro.ubbcluj.service.model.Rent;

public class Application {

    public static void main(String[] args) {

        Rent rent = new Rent();
        rent.setCarId(1L);
        System.out.println(rent);
    }
}
