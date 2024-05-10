package driverFactory;

import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.ServerConstants.*;
import static constants.WaitConstant.LONG_IMPLICIT_WAIT;
import static java.time.Duration.ofMillis;

public class Driver {

    private static AppiumDriverLocalService service;

    private Driver() {
    }

    public static void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress(LOCAL_SERVER_IP)
                .usingPort(LOCAL_SERVER_PORT)
                .withTimeout(SERVER_START_TIMEOUT)
                .build();
        service.start();

//        return service;
    }

    public static URL getServerUrl() {
        URL localServerUrl;
        try {
//            localServerUrl = new URL(String.format("http://%s:%d", LOCAL_SERVER_IP, LOCAL_SERVER_PORT));
            //localServerUrl = new URL("http://" + LOCAL_SERVER_IP + LOCAL_SERVER_PORT);
            localServerUrl = new URL("http://localhost:4723");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to construct the Appium server URL", e);
        }

        return localServerUrl;
    }

    public static AppiumDriver createDriver(DesiredCapabilities caps) {
        AppiumDriver driver = null;

        switch (caps.getPlatformName().toString().toLowerCase()) {
            case "android":
                driver = new AndroidDriver(getServerUrl(), caps);
                break;
            case "ios":
                driver = new IOSDriver(getServerUrl(), caps);
                break;
            default:
                throw new PlatformNotSupportException("Platform is not supported");
        }

        return driver;
    }

    public static AppiumDriver createDriver(URL serverURL, UiAutomator2Options caps) {

        AppiumDriver driver = new AndroidDriver(serverURL, caps);

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        waitDriverSession(driver);

        return driver;
    }

    public static AppiumDriver createDriver(URL serverURL, XCUITestOptions caps) {

        AppiumDriver driver = new IOSDriver(serverURL, caps);

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        waitDriverSession(driver);

        return driver;
    }

    public static void waitDriverSession(AppiumDriver driver) {
        driver.manage()
                .timeouts()
                .implicitlyWait(ofMillis(LONG_IMPLICIT_WAIT));
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
