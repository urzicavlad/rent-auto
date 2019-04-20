package ro.ubbcluj.rentauto.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.model.Rent;
import ro.ubbcluj.rentauto.repository.Repository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceImplTest {

    @InjectMocks
    private RentServiceImpl rentService;

    @Mock
    private Repository<Rent, Long> repository;

    @Mock
    private CarService carService;


    @Test
    public void add() {
        final var rent = new Rent();
        rent.setId(1L);
        rent.setKilometers(100);

        var car = new Car();
        car.setKilometers(100L);
        car.setRentTimes(3);
        when(carService.getById(anyLong())).thenReturn(car);
        when(repository.save(Mockito.any(Rent.class))).thenReturn(rent);
        assertNotNull(rentService.add(rent));
        verify(repository, times(1)).save(any(Rent.class));
    }

    @Test
    public void getIncome() {
        var car = new Car();
        car.setId(1L);
        car.setPricePerDay(10.00);

        var rent_1 = new Rent();
        rent_1.setCarId(1L);
        rent_1.setDays(3);
        var rent_2 = new Rent();
        rent_2.setCarId(1L);
        rent_2.setDays(2);
        var rent_3 = new Rent();
        rent_3.setCarId(1L);
        rent_3.setDays(3);


        when(carService.getById(anyLong())).thenReturn(car);
        when(repository.findAll()).thenReturn(List.of(rent_1, rent_2, rent_3));
        final var income = rentService.getIncome(1L);
        assertEquals(Double.valueOf(80), income);
        verify(carService, times(1)).getById(anyLong());
        verify(repository, times(1)).findAll();
    }
}