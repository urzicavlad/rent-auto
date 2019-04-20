package ro.ubbcluj.rentauto.service;

import ro.ubbcluj.rentauto.model.Rent;

public interface RentService {

    Rent add(Rent rent);

    Long getIncome(Long carId);
}
