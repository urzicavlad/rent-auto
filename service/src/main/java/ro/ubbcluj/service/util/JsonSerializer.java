package ro.ubbcluj.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class JsonSerializer<T> {

    public List<T> readData(File file, Class<T[]> clazz) throws IOException {
        return Arrays.asList(new ObjectMapper().readValue(file, clazz));
    }

    public void writeData(List<T> data, File resultFile) throws IOException {
        new ObjectMapper().writeValue(resultFile, data);
    }

}
