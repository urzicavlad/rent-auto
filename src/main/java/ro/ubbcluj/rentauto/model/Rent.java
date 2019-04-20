package ro.ubbcluj.rentauto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rent {

    private Long id;
    private Long carId;
    private Integer days;
    private Integer kilometers;
}
