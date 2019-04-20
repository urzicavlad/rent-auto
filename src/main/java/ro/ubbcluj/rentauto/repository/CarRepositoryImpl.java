package ro.ubbcluj.rentauto.repository;

import ro.ubbcluj.rentauto.model.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CarRepositoryImpl implements Repository<Car, Long> {

    private static Map<Integer, Car> CARS = new HashMap<>();


    @Override
    public Car save(Car object) {
        return null;
    }

    @Override
    public Optional<Car> findById(Long aLong) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

}
