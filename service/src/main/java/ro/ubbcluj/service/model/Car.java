package ro.ubbcluj.service.model;

public class Car {

    private Long id;
    private String model;
    private Long kilometers;
    private Double pricePerDay;
    private Integer rentTimes = 0;


    public Long getId() {
        return id;
    }

    public Car setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public Car setKilometers(Long kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public Car setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public Integer getRentTimes() {
        return rentTimes;
    }

    public Car setRentTimes(Integer rentTimes) {
        this.rentTimes = rentTimes;
        return this;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", kilometers=" + kilometers +
                ", pricePerDay=" + pricePerDay +
                ", rentTimes=" + rentTimes +
                '}';
    }
}
