package driver;

import capabilities.AndroidCapabilities;
import capabilities.IOSCapabilities;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    public static AppiumDriver getDriver(String platform) {

        if (platform == null) {
            throw new IllegalArgumentException("Platform must not be null");
        }

        URL appiumServer;
        try {
            appiumServer = new URL("http://localhost:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to construct the Appium server URL", e);
        }

        AppiumDriver appiumDriver;
        switch (platform) {
            case MobilePlatform.ANDROID:
//                appiumDriver = new AndroidDriver(appiumServer, AndroidDesiredCapabilities.setCapabilities());
                appiumDriver = new AndroidDriver(appiumServer, AndroidCapabilities.setOptions());
                break;
            case MobilePlatform.IOS:
//                appiumDriver = new IOSDriver(appiumServer, IOSDesiredCapabilities.setCapabilities());
                appiumDriver = new IOSDriver(appiumServer, IOSCapabilities.setCapabilities());
                break;
            default:
                throw new PlatformNotSupportException("Platform is not supported");
        }

        // Global Implicit Wait - applied for WHOLE driver session
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));

        return appiumDriver;
    }
}
