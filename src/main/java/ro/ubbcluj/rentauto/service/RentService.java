package ro.ubbcluj.rentauto.service;

import ro.ubbcluj.rentauto.model.Rent;

import java.util.List;

public interface RentService {

    Rent add(Rent rent);

    Double getIncome(Long carId);

    List<Rent> getAll();

    void delete(Rent rent);
}
