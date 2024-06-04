package driverFactory;

public enum Platform {
    ANDROID,
    IOS;

    public static Platform fromString(String platformName) {
        try {
            return Platform.valueOf(platformName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown platform: " + platformName, e);
        }
    }
}
