package utils;

import io.appium.java_client.AppiumDriver;

public class ValidationDriver {
    private ValidationDriver() {
        // Private constructor to prevent instantiation
    }

    public static void validateDriverNotNull(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver must not be null.");
        }
    }

}
