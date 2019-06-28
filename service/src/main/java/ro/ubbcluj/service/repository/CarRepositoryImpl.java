package ro.ubbcluj.service.repository;

import ro.ubbcluj.service.model.Car;
import ro.ubbcluj.service.util.JsonSerializer;
import ro.ubbcluj.service.util.TempFileWriter;

import java.io.File;
import java.util.*;

public class CarRepositoryImpl implements Repository<Car, Long> {

    private static final Map<Long, Car> CARS = new HashMap<>();
    private static final File FILE;
    private static final String RESOURCE_NAME = "/json-files/cars.json";

    static {
        final var writer = new TempFileWriter<>(Car[].class);
        FILE = writer.write(() -> CarRepositoryImpl.class.getResourceAsStream(RESOURCE_NAME));
        writer.populate(FILE, car -> CARS.put(car.getId(), car));
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
        new JsonSerializer<Car>().writeData(cars, FILE);
    }
}
