package driver;

import driver.capabilities.AndroidDesiredCapabilities;
import driver.capabilities.IOSCapabilities;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.WaitConstant.SHORT_IMPLICIT_WAIT;
import static java.time.Duration.ofMillis;

/**
 * Singleton driver factory
 */
public class DriverFactory {

    public static AppiumDriver getDriver(Platforms platformType) {

        AppiumDriver appiumDriver = null;

        if (platformType == null) {
            throw new IllegalArgumentException("Platform must not be null");
        }

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to construct the Appium server URL", e);
        }

        if (appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server URL");
        }

        switch (platformType) {
            case ANDROID:
//                appiumDriver = new AndroidDriver(appiumServer, AndroidCapabilities.setCapabilities());
                appiumDriver = new AndroidDriver(appiumServer, AndroidDesiredCapabilities.setCapabilities());
                break;
            case IOS:
                appiumDriver = new IOSDriver(appiumServer, IOSCapabilities.setCapabilities());
                break;
            default:
                throw new PlatformNotSupportException("Platform is not supported");
        }

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        waitDriverSession(appiumDriver);

        return appiumDriver;
    }

    public static AppiumDriver getMobileDriver(String platform) {

        AppiumDriver appiumDriver = null;

        if (platform == null) {
            throw new IllegalArgumentException("Platform must not be null");
        }

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to construct the Appium server URL", e);
        }

        if (appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server URL");
        }


        switch (platform.toLowerCase()) {
            case "android":
//                appiumDriver = new AndroidDriver(appiumServer, AndroidCapabilities.setCapabilities());
                appiumDriver = new AndroidDriver(appiumServer, AndroidDesiredCapabilities.setCapabilities());
                break;
            case "ios":
                appiumDriver = new IOSDriver(appiumServer, IOSCapabilities.setCapabilities());
                break;
            default:
                throw new PlatformNotSupportException("Platform is not supported");
        }

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        waitDriverSession(appiumDriver);

        return appiumDriver;
    }

    public static void waitDriverSession(AppiumDriver driver) {
        driver.manage()
                .timeouts()
                .implicitlyWait(ofMillis(SHORT_IMPLICIT_WAIT));
    }
}
