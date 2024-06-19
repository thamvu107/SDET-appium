package driverFactory;

import lombok.Getter;

public enum Platform {
    ANDROID("android"),
    IOS("iOs");

    @Getter
    private final String platformName;

    Platform(String platformName) {
        this.platformName = platformName;
    }

    public static Platform fromString(String platformName) {
        try {
            return Platform.valueOf(platformName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown platform: " + platformName, e);
        }
    }
}
