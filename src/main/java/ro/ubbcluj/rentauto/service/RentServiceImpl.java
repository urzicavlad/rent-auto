package ro.ubbcluj.rentauto.service;

import ro.ubbcluj.rentauto.model.Rent;
import ro.ubbcluj.rentauto.repository.Repository;

public class RentServiceImpl implements RentService {

    private final Repository<Rent, Long> rentRepository;
    private final CarService carService;

    public RentServiceImpl(Repository<Rent, Long> rentRepository, CarService carService) {
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
}
