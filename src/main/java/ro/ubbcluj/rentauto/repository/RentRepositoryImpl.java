package ro.ubbcluj.rentauto.repository;

import ro.ubbcluj.rentauto.model.Rent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RentRepositoryImpl implements Repository<Rent, Long> {

    private static Map<Integer, Rent> RENTS = new HashMap<>();


    @Override
    public Rent save(Rent object) {
        return null;
    }

    @Override
    public Optional<Rent> findById(Long aLong) {
        return null;
    }

    @Override
    public List<Rent> findAll() {
        return null;
    }
}
