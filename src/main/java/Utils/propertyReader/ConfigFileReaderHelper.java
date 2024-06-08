package Utils.propertyReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigFileReaderHelper {
    private final Map<String, String> props;
    private final String fileName;

    public ConfigFileReaderHelper(String filePath) {
        this.fileName = filePath;
        System.out.println(this.fileName);
        this.props = Collections.unmodifiableMap(loadProperties());

    }

    public String getProperty(String propertyName) {

        String propertyValue = props.get(propertyName);
        if (propertyValue == null) {
            throw new IllegalArgumentException(String.format("Property '%s' not found", propertyName));
        }

        return propertyValue;
    }


    private Map<String, String> loadProperties() {
        System.out.println(fileName);

        try (InputStream inputStream = ConfigFileReaderHelper.class.getClassLoader().getResourceAsStream(fileName)
        ) {
            validateFile(inputStream);
            Properties tempProps = new Properties();
            tempProps.load(inputStream);
            return tempProps.stringPropertyNames()
                    .stream()
                    .collect(Collectors.toMap(name -> name, tempProps::getProperty));
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Failed to read property file '%s'", fileName), ex);
        }
    }

    private static void validateFile(InputStream inputStream) {
        System.out.println(inputStream);
        if (inputStream == null) {
            throw new IllegalArgumentException("Property file not found in the classpath");
        }
    }
}
