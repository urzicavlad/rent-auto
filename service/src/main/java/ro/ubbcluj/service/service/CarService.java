package ro.ubbcluj.service.service;

import ro.ubbcluj.service.model.Car;

import java.util.List;

public interface CarService {

    Car add(Car car);

    Long getKilometers(Long carId);

    List<Car> getAllCarsByRentTime();

    Car getById(Long carId);

    void delete(Car car);
}
