package ro.ubbcluj.rentauto.repository;

import org.junit.Test;

public class RentRepositoryImplTest {

    @Test
    public void save() {
        RentRepositoryImpl rentRepository = new RentRepositoryImpl();
        final var all = rentRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }
}