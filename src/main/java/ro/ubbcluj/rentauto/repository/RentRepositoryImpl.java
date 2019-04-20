package ro.ubbcluj.rentauto.repository;

import ro.ubbcluj.rentauto.model.Rent;

import java.util.*;

public class RentRepositoryImpl implements Repository<Rent, Long> {

    private static Map<Long, Rent> RENTS = new HashMap<>();


    @Override
    public Rent save(Rent rent) {
        return RENTS.put(rent.getId(), rent);
    }

    @Override
    public Optional<Rent> findById(Long rentId) {
        return Optional.ofNullable(RENTS.get(rentId));
    }

    @Override
    public List<Rent> findAll() {
        return new ArrayList<>(RENTS.values());
    }
}
