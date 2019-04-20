package ro.ubbcluj.rentauto.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.service.CarServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CarRepositoryImpl implements Repository<Car, Long> {

    private static Map<Long, Car> CARS = new HashMap<>();

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final Car[] cars = objectMapper.readValue(new File("json-files/cars.json"), Car[].class);
            Arrays.asList(cars).forEach(car -> CARS.put(car.getId(), car));
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        CarServiceImpl carService = new CarServiceImpl(new CarRepositoryImpl());
        final var allCarsByRentTime = carService.getAllCarsByRentTime();
        System.out.println(allCarsByRentTime);
    }
}
