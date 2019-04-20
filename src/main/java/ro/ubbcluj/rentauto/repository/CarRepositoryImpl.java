package ro.ubbcluj.rentauto.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ro.ubbcluj.rentauto.model.Car;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
public class CarRepositoryImpl implements Repository<Car, Long> {

    private static Map<Long, Car> CARS = new HashMap<>();


    static {
        try {
            final var src = new File("src/main/resources/json-files/cars.json");
            final var cars = new ObjectMapper().readValue(src, Car[].class);
            Arrays.asList(cars).forEach(car -> CARS.put(car.getId(), car));
        } catch (IOException e) {
            log.info("Exception occurred: {}", e.getMessage());
        }
    }

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
