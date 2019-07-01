package ro.ubbcluj.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TempFileWriter<T> {

    private static final String DIR_NAME = "rent-auto";
    private static final String FILE_EXTENSION = ".json";
    private Class<T[]> clazz;

    public TempFileWriter(Class<T[]> clazz) {
        this.clazz = clazz;
    }

    public File write(Supplier<InputStream> resource) {
        File file = null;
        try {
            final var fileName = clazz.getSimpleName().toLowerCase();
            final var tempFile = Files.createTempFile(Files.createTempDirectory(DIR_NAME), fileName, FILE_EXTENSION);
            Files.copy(resource.get(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            file = new File(tempFile.toString());
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        return file;
    }

    public void populate(File file, Consumer<T> consumer){
        new JsonSerializer<T>()
                .readData(file,clazz)
                .forEach(consumer);
    }
}
