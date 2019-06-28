package ro.ubbcluj.service.repository;

import ro.ubbcluj.service.model.Car;
import ro.ubbcluj.service.model.Rent;
import ro.ubbcluj.service.util.JsonSerializer;
import ro.ubbcluj.service.util.TempFileWriter;

import java.io.File;
import java.util.*;

public class RentRepositoryImpl implements Repository<Rent, Long> {

    private static Map<Long, Rent> RENTS = new HashMap<>();
    private static final File FILE;
    private static String RESOURCE_NAME = "/json-files/rents.json";

    static {
        final var writer = new TempFileWriter<>(Rent[].class);
        FILE = writer.write(() -> RentRepositoryImpl.class.getResourceAsStream(RESOURCE_NAME));
        writer.populate(FILE, rent -> RENTS.put(rent.getId(), rent));
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
    public void remove(Rent rent) {
        RENTS.remove(rent.getId());
        refresh(new ArrayList<>(RENTS.values()));
    }

    private void refresh(List<Rent> rents) {
        new JsonSerializer<Rent>().writeData(rents, FILE);
    }

}
