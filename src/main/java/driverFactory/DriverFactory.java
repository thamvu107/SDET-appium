package driverFactory;

import constants.WaitConstants;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import utils.WaitUtils;

import java.net.URL;


public class DriverFactory {
    private final URL serverURL;
    private final Capabilities caps;
    AppiumDriver driver;

    public DriverFactory(URL serverURL, Capabilities caps) {
        this.serverURL = serverURL;
        this.caps = caps;
    }

    public AppiumDriver getDriver() {

        if (driver == null) {
            driver = createDriver();
        }

        return driver;
    }

    private AppiumDriver createDriver() {
        AppiumDriver driver;
        try {

            Platform platform = getCurrentPlatformFromCapabilities();

            switch (platform) {
                case ANDROID:
                    driver = new AndroidDriver(serverURL, caps);
                    break;
                case IOS:
                    driver = new IOSDriver(serverURL, caps);
                    break;
                default:
                    throw new PlatformNotSupportException("Platform " + caps.getPlatformName() + " is not supported");
            }

            WaitUtils wait = new WaitUtils(driver);
            wait.setImplicitWait(WaitConstants.SHORT_IMPLICIT_WAIT);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create driver: " + e.getMessage(), e);
        }

        return driver;
    }

    private Platform getCurrentPlatformFromCapabilities() {
        String platformName = CapabilityHelpers.getCapability(caps, "platformName", String.class);
        return Platform.valueOf(platformName.toUpperCase());
    }
}
