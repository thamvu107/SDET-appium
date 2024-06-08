package Utils;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.Capabilities;

public class PlatformUtil {
    public Platform getCurrentPlatform(AppiumDriver driver) {

        Capabilities caps = driver.getCapabilities();
        String platformName = CapabilityHelpers.getCapability(caps, "platformName", String.class);

        return Platform.fromString(platformName);
    }
}
