package ro.ubbcluj.service.service;

import ro.ubbcluj.common.model.Rent;

import java.util.List;

public interface RentService {

    Rent add(Rent rent);

    Double getIncome(Long carId);

    List<Rent> getAll();

    void delete(Rent rent);
}
