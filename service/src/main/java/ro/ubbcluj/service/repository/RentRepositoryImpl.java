package ro.ubbcluj.service.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.ubbcluj.service.model.Rent;
import ro.ubbcluj.service.util.JsonSerializer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RentRepositoryImpl implements Repository<Rent, Long> {

    private static Map<Long, Rent> RENTS = new HashMap<>();
    private static final File FILE = new File("C:\\Users\\cristian.urzica\\Desktop\\examen-practic\\service\\src\\main\\resources\\json-files\\rents.json");


    static {
        try {
            final var rents = new ObjectMapper().readValue(FILE, Rent[].class);
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
    public void remove(Rent rent) {
        RENTS.remove(rent.getId());
        refresh(new ArrayList<>(RENTS.values()));
    }
    private void refresh(List<Rent> rents) {
        try {
            JsonSerializer<Rent> jsonSerializer = new JsonSerializer<>();
            jsonSerializer.writeData(rents, FILE);
        } catch (IOException e) {
            System.out.println("Error occured!" + e.getMessage());
        }
    }

}
