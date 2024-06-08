package Utils.propertyReader;

public class PropertyHelper {

    private final ConfigFileReaderHelper propertyReader;

    public PropertyHelper(String filePath) {
        System.out.println(filePath);
        this.propertyReader = new ConfigFileReaderHelper(filePath);
    }

    public String getStringProperty(String propertyName) {

        return propertyReader.getProperty(propertyName);
    }

    public int getIntProperty(String propertyName) {
        String propertyValue = propertyReader.getProperty(propertyName);
        try {
            return Integer.parseInt(propertyValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid number format for property '%s': %s", propertyName, propertyValue), e);
        }
    }

    public long getLongProperty(String propertyName) {
        String propertyValue = propertyReader.getProperty(propertyName);
        try {
            return Integer.parseInt(propertyValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid number format for property '%s': %s", propertyName, propertyValue), e);
        }
    }

    public boolean getBooleanProperty(String propertyName) {
        String propertyValue = propertyReader.getProperty(propertyName);
        if ("true".equalsIgnoreCase(propertyValue) || "false".equalsIgnoreCase(propertyValue)) {
            return Boolean.parseBoolean(propertyValue);
        } else {
            throw new IllegalArgumentException(String.format("Invalid boolean format for property '%s': %s", propertyName, propertyValue));
        }
    }
}
