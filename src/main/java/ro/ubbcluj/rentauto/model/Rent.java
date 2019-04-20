package ro.ubbcluj.rentauto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rent {

    private Long id;
    private Long carId;
    private Integer days;
    private Integer kilometers;
}
