package driverFactory;

import driver.Platforms;
import driverFactory.capabilities.AndroidCapabilities;
import driverFactory.capabilities.IOSCapabilities;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import server.ServerConfig;

import java.net.MalformedURLException;
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

    public static URL getServerUrl(ServerConfig serverConfig) {

        URL localServerUrl;
        try {
            //localServerUrl = new URL("http://localhost:4723");
            localServerUrl = new URL(String.format("http://%s:%d", serverConfig.getServerIP(), serverConfig.getPort()));

        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to construct the Appium server URL", e);
        }

        return localServerUrl;
    }

    public static AppiumDriver createDriver(ServerConfig serverConfig, DesiredCapabilities caps) {
        AppiumDriver driver = null;

        switch (caps.getPlatformName().toString().toLowerCase()) {
            case "android":
                driver = new AndroidDriver(getServerUrl(serverConfig), caps);
                break;
            case "ios":
                driver = new IOSDriver(getServerUrl(serverConfig), caps);
                break;
            default:
                throw new PlatformNotSupportException("Platform is not supported");
        }

        return driver;
    }

    public static AppiumDriver createDriver(URL serverURL, UiAutomator2Options caps) {

        AppiumDriver driver = new AndroidDriver(serverURL, caps);

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        implicitWaitDriverSession(driver);

        return driver;
    }

    public static AppiumDriver createDriver(URL serverURL, XCUITestOptions caps) {

        AppiumDriver driver = new IOSDriver(serverURL, caps);

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        implicitWaitDriverSession(driver);

        return driver;
    }

    public static AppiumDriver getDriver(ServerConfig serverConfig, Platforms platform) {

        URL serverUrl = Driver.getServerUrl(serverConfig);

        AppiumDriver driver = null;

        switch (platform) {
            case ANDROID:
                UiAutomator2Options opts = serverConfig.getServerIP().equals(LOCAL_SERVER_IP) ? AndroidCapabilities.getLocalCaps() : AndroidCapabilities.getRemoteCaps();
                driver = Driver.createDriver(serverUrl, opts);
                break;
            case IOS:
                serverUrl = Driver.getServerUrl(new ServerConfig(LOCAL_SERVER_IP, SERVER_PORT)); // Force local URL since not yet config remote env for ios
                driver = Driver.createDriver(serverUrl, IOSCapabilities.getLocalCaps());
                break;
            default:
                throw new PlatformNotSupportException("Platform is not supported");
        }

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
