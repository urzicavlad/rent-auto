package ro.ubbcluj.rentauto.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.ubbcluj.rentauto.exception.CarNotFoundException;
import ro.ubbcluj.rentauto.model.Car;
import ro.ubbcluj.rentauto.repository.Repository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private Repository<Car, Long> repository;


    @Test
    public void add() {
        when(repository.save(Mockito.any(Car.class))).thenReturn(new Car());
        assertNotNull(carService.add(new Car()));
        verify(repository, times(1)).save(any(Car.class));
    }

    @Test
    public void getKilometers() {
        Car car = new Car();
        car.setKilometers(2000L);
        when(repository.findById(1L)).thenReturn(Optional.of(car));
        assertNotNull(carService.getKilometers(1L));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test(expected = CarNotFoundException.class)
    public void getKilometers_shouldThrowCarNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertNotNull(carService.getKilometers(1L));
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void getAllCarsByRentTime() {
        final var car_1 = new Car();
        car_1.setRentTimes(1);
        final var car_2 = new Car();
        car_2.setRentTimes(2);
        final var car_3 = new Car();
        car_3.setRentTimes(3);
        final var input = List.of(car_2, car_1, car_3);

        when(repository.findAll()).thenReturn(input);

        final var output = carService.getAllCarsByRentTime();

        assertNotNull(output);

        System.out.println("INPUT: " + input);
        System.out.println("OUTPUT: " + output);

        assertThat(output.toArray(), not(equalTo(input.toArray())));
        verify(repository, times(1)).findAll();
    }
}