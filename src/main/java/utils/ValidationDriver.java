package utils;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationDriver {

    private static final Logger logger = LoggerFactory.getLogger(ValidationDriver.class);

    private ValidationDriver() {
        // Private constructor to prevent instantiation
    }

    public static void validateDriverNotNull(AppiumDriver driver) {
        if (driver == null) {
            logger.error("Driver must not be null.");
            throw new IllegalArgumentException("Driver must not be null.");
        }
    }

}
