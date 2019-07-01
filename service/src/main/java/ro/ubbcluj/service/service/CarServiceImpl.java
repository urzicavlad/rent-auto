package ro.ubbcluj.service.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.common.model.Car;
import ro.ubbcluj.inmemory.AbstractRepository;
import ro.ubbcluj.service.exception.CarNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final AbstractRepository<Car, Long> carRepository;

    public CarServiceImpl(AbstractRepository<Car, Long> carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Long getKilometers(Long carId) {
        return carRepository.findById(carId)
                .map(Car::getKilometers)
                .orElseThrow(() -> new CarNotFoundException("Car was not found!"));
    }

    @Override
    public List<Car> getAllCarsByRentTime() {
        return carRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Car::getRentTimes).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Car getById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car was not found!"));
    }

    @Override
    public void delete(Car car) {
        carRepository.remove(car);
    }
}
