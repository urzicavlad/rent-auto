package ro.ubbcluj.service.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.common.model.Rent;
import ro.ubbcluj.inmemory.AbstractRepository;

import java.util.List;


@Service
public class RentServiceImpl implements RentService {

    private final AbstractRepository<Rent, Long> rentRepository;
    private final CarService carService;

    public RentServiceImpl(AbstractRepository<Rent, Long> rentRepository, CarService carService) {
        this.rentRepository = rentRepository;
        this.carService = carService;
    }

    @Override
    public Rent add(Rent rent) {
        final var car = carService.getById(rent.getCarId());
        car.setKilometers(car.getKilometers() + rent.getKilometers());
        car.setRentTimes(car.getRentTimes() + 1);
        carService.add(car);
        return rentRepository.save(rent);
    }

    @Override
    public Double getIncome(Long carId) {
        final var car = carService.getById(carId);
        final var rentDays = rentRepository.findAll()
                .stream()
                .filter(rent -> rent.getCarId().equals(car.getId()))
                .mapToInt(Rent::getDays)
                .sum();
        return car.getPricePerDay() * (double) rentDays;
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public void delete(Rent rent) {
        rentRepository.remove(rent);
    }
}
