package utils;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Objects;

public class DataObjectBuilderUtil {
    private static final Gson gson = new Gson();

    public static <T> T buildDataObject(Path relativeFilePath, Class<T> dataType) {

        Objects.requireNonNull(relativeFilePath, "The relative file path must not be null");
        Objects.requireNonNull(dataType, "The testData type must not be null");

        Path absoluteFilePath = new PathUtil(relativeFilePath).getAbsolutePath();

        try (Reader reader = Files.newBufferedReader(absoluteFilePath)) {
            return gson.fromJson(reader, dataType);
        } catch (NoSuchFileException e) {
            throw new RuntimeException(String.format("[ERR] Could not find the file: %s", absoluteFilePath), e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(String.format("[ERR] JSON syntax error in file: %s", absoluteFilePath), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("[ERR] IO error reading file: %s", absoluteFilePath), e);
        }
    }

}
