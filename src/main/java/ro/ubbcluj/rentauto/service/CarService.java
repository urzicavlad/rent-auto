package ro.ubbcluj.rentauto.service;

import ro.ubbcluj.rentauto.model.Car;

import java.util.List;

public interface CarService {

    Car add(Car car);

    Long getKilometers(Long carId);

    List<Car> getAllCarsByRentTime();
}
