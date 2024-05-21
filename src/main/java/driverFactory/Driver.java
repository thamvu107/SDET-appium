package driverFactory;

import driverFactory.capabilities.CapabilitiesManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import mobildeDevices.Mobile;
import mobildeDevices.PhysicalMobile;
import org.openqa.selenium.Capabilities;
import utils.ServerUtils;

import java.net.URL;

import static constants.ServerConstants.*;
import static constants.WaitConstant.SHORT_IMPLICIT_WAIT;
import static java.time.Duration.ofMillis;

public class Driver {

    private static AppiumDriverLocalService service;

    private Driver() {
    }


    public static void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress(LOCAL_SERVER_IP)
                .usingPort(SERVER_PORT)
                .withTimeout(SERVER_START_TIMEOUT)
                .build();
        service.start();

//        return service;
    }

    public static AppiumDriver getLocalServerDriver(Mobile mobile) {

        AppiumDriver driver = null;
        URL serverURL = ServerUtils.getLocalServerURL();
        Capabilities caps = CapabilitiesManager.getCaps(mobile);

        if (caps instanceof UiAutomator2Options) {
            driver = createAndroidDriver(serverURL, caps);

        } else if (caps instanceof XCUITestOptions) {
            driver = createIOSDriver(serverURL, caps);
        } else {
            throw new IllegalArgumentException("Unsupported capabilities type: " + caps.getClass().getName());
        }

        return driver;
    }

    public static AppiumDriver getRemoteServerDriver(PhysicalMobile mobile) {

        AppiumDriver driver = null;
        URL serverURL = ServerUtils.getRemoteServerURL();
        Capabilities caps = CapabilitiesManager.getCaps(mobile);

        if (caps instanceof UiAutomator2Options) {
            driver = createAndroidDriver(serverURL, caps);
        }

        return driver;
    }

    public static AppiumDriver createAndroidDriver(URL serverURL, Capabilities caps) {

        AppiumDriver driver = new AndroidDriver(serverURL, caps);

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        implicitWaitDriverSession(driver);

        return driver;
    }

    public static AppiumDriver createIOSDriver(URL serverURL, Capabilities caps) {

        AppiumDriver driver = new IOSDriver(serverURL, caps);

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        implicitWaitDriverSession(driver);

        return driver;
    }

    public static void implicitWaitDriverSession(AppiumDriver driver) {
        driver.manage()
                .timeouts()
                .implicitlyWait(ofMillis(SHORT_IMPLICIT_WAIT));
    }

    public static String getCurrentPlatform(AppiumDriver driver) {

        Capabilities caps = driver.getCapabilities();

        return CapabilityHelpers.getCapability(caps, "platformName", String.class);
    }

    public static void stopServer() {
        service.stop();
    }

    public static void quitDriver(AppiumDriver driver) {
        try {
            System.out.println(driver);
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearApp(String appId) {
        // Stop the app if it's running

        // Clear app data using adb shell pm clear command
        try {
            Runtime.getRuntime().exec("adb shell pm clear " + appId);
            System.out.println("Cleared data for app with package ID: " + appId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
