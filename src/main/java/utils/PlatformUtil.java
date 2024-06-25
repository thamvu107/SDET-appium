package utils;

import enums.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.Capabilities;

public class PlatformUtil {
    private AppiumDriver driver;
    public WaitUtils waitUtils;

    public PlatformUtil(AppiumDriver driver) {
        ValidationDriver.validateDriverNotNull(driver);
        this.driver = driver;
        this.waitUtils = new WaitUtils(this.driver);
    }

    public PlatformUtil() {
    }

    /**
     * Gets the current platform from the provided Appium driver.
     *
     * @return the current platform
     * @throws IllegalArgumentException if the driver is null
     */
    public Platform getCurrentPlatform() {

        Capabilities caps = driver.getCapabilities();
        String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);

        return Platform.fromString(currentPlatform);
    }

}
