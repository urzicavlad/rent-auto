package ro.ubbcluj.service.model;

public class Rent {

    private Long id;
    private Long carId;
    private Integer days;
    private Integer kilometers;

    public Long getId() {
        return id;
    }

    public Rent setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public Rent setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Integer getDays() {
        return days;
    }

    public Rent setDays(Integer days) {
        this.days = days;
        return this;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public Rent setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", carId=" + carId +
                ", days=" + days +
                ", kilometers=" + kilometers +
                '}';
    }
}
