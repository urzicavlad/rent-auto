package ro.ubbcluj.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class JsonSerializer<T> {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<T> readData(File file, Class<T[]> clazz) {
        try {
            return Arrays.asList(objectMapper.readValue(file, clazz));
        } catch (IOException e) {
            System.out.println("Error occurred! " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public void writeData(List<T> data, File resultFile) {
        try {
            objectMapper.writeValue(resultFile, data);
        } catch (IOException e) {
            System.out.println("Error occurred! " + e.getMessage());
        }
    }

}
