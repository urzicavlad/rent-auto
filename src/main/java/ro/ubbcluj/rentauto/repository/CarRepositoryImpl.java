package ro.ubbcluj.rentauto.repository;

import ro.ubbcluj.rentauto.model.Car;

import java.util.*;

public class CarRepositoryImpl implements Repository<Car, Long> {

    private static Map<Long, Car> CARS = new HashMap<>();

    @Override
    public Car save(Car car) {
        return CARS.put(car.getId(), car);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(CARS.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(CARS.values());
    }
}
