package ro.ubbcluj.rentauto.repository;

import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.util.JsonSerializer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CarRepositoryImpl implements Repository<Car, Long> {

    private static final Map<Long, Car> CARS = new HashMap<>();
    private static final File FILE = new File("src/main/resources/json-files/cars.json");

    static {
        try {
            new JsonSerializer<Car>()
                    .readData(FILE, Car[].class)
                    .forEach(car -> CARS.put(car.getId(), car));
        } catch (IOException e) {
            System.out.println("Exception occurred: {}" + e.getMessage());
        }
    }

    @Override
    public Car save(Car car) {
        final var result = CARS.put(car.getId(), car);
        refresh(new ArrayList<>(CARS.values()));
        return result;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(CARS.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(CARS.values());
    }

    @Override
    public void remove(Car car) {
        CARS.remove(car.getId());
        refresh(new ArrayList<>(CARS.values()));
    }

    private void refresh(List<Car> cars) {
        try {
            JsonSerializer<Car> jsonSerializer = new JsonSerializer<>();
            jsonSerializer.writeData(cars, FILE);
        } catch (IOException e) {
            System.out.println("Error occured!" + e.getMessage());
        }
    }
}
