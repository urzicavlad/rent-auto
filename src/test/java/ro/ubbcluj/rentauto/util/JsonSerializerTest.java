package ro.ubbcluj.rentauto.util;

import org.junit.Test;
import ro.ubbcluj.rentauto.model.Car;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JsonSerializerTest {

    @Test
    public void readData() throws IOException {
        JsonSerializer<Car> carJsonSerializer = new JsonSerializer<>();
        final var cars = carJsonSerializer.readData(
                new File("src/createStage/resources/json-files/cars.json"), Car[].class);
        assertNotNull(cars);
    }

    @Test
    public void writeData() throws IOException {
        JsonSerializer<Car> carJsonSerializer = new JsonSerializer<>();
        final var read = carJsonSerializer.readData(
                new File("src/createStage/resources/json-files/cars.json"), Car[].class);
        final var resultFile = new File("src/createStage/resources/json-files/createStage.json");
        carJsonSerializer.writeData(read, resultFile);
        final var fileIsEmpty = Files.readString(Path.of(resultFile.getPath())).isEmpty();
        assertFalse(fileIsEmpty);
        Files.delete(Path.of(resultFile.getPath()));
    }
}