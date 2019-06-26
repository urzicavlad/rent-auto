package ro.ubbcluj.rentauto.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubbcluj.rentauto.model.Rent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RentRepositoryImpl implements Repository<Rent, Long> {

    private static Map<Long, Rent> RENTS = new HashMap<>();

    static {
        try {
            final var src = new File("src/main/resources/json-files/rents.json");
            final var rents = new ObjectMapper().readValue(src, Rent[].class);
            Arrays.asList(rents).forEach(rent -> RENTS.put(rent.getId(), rent));
        } catch (IOException e) {
            System.out.println("Exception occurred: {}"+ e.getMessage());
        }
    }

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

    @Override
    public void remove(Rent object) {

    }
}
