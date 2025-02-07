package utils.propertyReader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Slf4j
public class PropertiesMap {
  private final Map<String, String> props;
  private final String configureFile;

  public PropertiesMap(String configureFile) {
    this.configureFile = configureFile;
    this.props = Collections.unmodifiableMap(loadProperties());
  }

  public String getProperty(String propertyName) {

    String propertyValue = props.get(propertyName);
    if (propertyValue == null) {
      log.atError().log(String.format("Property '%s' not found", propertyName));
      throw new IllegalArgumentException(String.format("Property '%s' not found", propertyName));
    }

    return propertyValue;
  }

  public int getIntProperty(String propertyName) {
    String propertyValue = getProperty(propertyName);
    try {
      return Integer.parseInt(propertyValue);
    } catch (NumberFormatException e) {
      log.atError().log(String.format("Invalid number format for property '%s': %s", propertyName, propertyValue));
      throw new IllegalArgumentException(String.format("Invalid number format for property '%s': %s", propertyName, propertyValue), e);
    }
  }

  public long getLongProperty(String propertyName) {
    String propertyValue = getProperty(propertyName);
    try {
      return Integer.parseInt(propertyValue);
    } catch (NumberFormatException e) {
      log.atError().log(String.format("Invalid number format for property '%s': %s", propertyName, propertyValue));
      throw new IllegalArgumentException(String.format("Invalid number format for property '%s': %s", propertyName, propertyValue), e);
    }
  }

  public boolean getBooleanProperty(String propertyName) {
    String propertyValue = getProperty(propertyName);
    if ("true".equalsIgnoreCase(propertyValue) || "false".equalsIgnoreCase(propertyValue)) {
      return Boolean.parseBoolean(propertyValue);
    } else {
      log.atError().log(String.format("Invalid boolean format for property '%s': %s", propertyName, propertyValue));
      throw new IllegalArgumentException(String.format("Invalid boolean format for property '%s': %s", propertyName, propertyValue));
    }
  }

  private Map<String, String> loadProperties() {

//    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)
    try (InputStream inputStream = PropertiesMap.class.getClassLoader().getResourceAsStream(configureFile)
    ) {
//      System.out.println("getClass()" + getClass());
      System.out.println("InputStream for file '" + configureFile + "': " + inputStream);

      validateFile(inputStream);
      Properties tempProps = new Properties();
      tempProps.load(inputStream);
      return tempProps.stringPropertyNames()
        .stream()
        .collect(Collectors.toMap(name -> name, tempProps::getProperty));
    } catch (IOException ex) {
      log.atError().log(String.format("Failed to read property file '%s'", configureFile));
      throw new RuntimeException(String.format("Failed to read property file '%s'", configureFile), ex);
    }
  }

  private static void validateFile(InputStream inputStream) {
    System.out.println("inputStream " + inputStream);
    if (inputStream == null) {
      log.atError().log("Property file not found in the classpath");
      throw new IllegalArgumentException("Property file not found in the classpath");
    }
  }
}
