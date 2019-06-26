package ro.ubbcluj.rentauto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Car {

    private Long id;
    private String model;
    private Long kilometers;
    private Double pricePerDay;
    private Integer rentTimes = 0;
}
