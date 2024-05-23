package driverFactory;

import driverFactory.capabilities.AndroidCapabilities;
import driverFactory.capabilities.IOSCapabilities;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import mobildeDevices.Mobile;
import org.openqa.selenium.Capabilities;
import server.ServerConfig;
import utils.ServerUtils;
import utils.WaitUtils;

import java.net.URL;

import static constants.ServerConstants.*;
import static constants.WaitConstant.SHORT_IMPLICIT_WAIT;

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

        return getDriver(new ServerConfig(LOCAL_SERVER_IP, SERVER_PORT), mobile);
    }

    public static AppiumDriver getRemoteServerDriver(Mobile mobile) {

        return getDriver(new ServerConfig(REMOTE_SERVER_IP, SERVER_PORT), mobile);
    }

    public static AppiumDriver getDriver(ServerConfig server, Mobile mobile) {

        AppiumDriver driver = null;
        URL serverURL = ServerUtils.getServerURL(server);

        try {

            switch (mobile.getPlatformName()) {
                case ANDROID:
                    driver = new AndroidDriver(serverURL, AndroidCapabilities.getAndroidCaps(mobile));
                    break;
                case IOS:
                    driver = new IOSDriver(serverURL, IOSCapabilities.getIOSCaps(mobile));
                    break;
                default:
                    throw new PlatformNotSupportException("Platform " + mobile.getPlatformName() + " is not supported");
            }

            WaitUtils wait = new WaitUtils(driver);
            wait.setImplicitWait(SHORT_IMPLICIT_WAIT);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return driver;
    }

    public static String getCurrentPlatform(AppiumDriver driver) {

        Capabilities caps = driver.getCapabilities();

        return CapabilityHelpers.getCapability(caps, "platformName", String.class);
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

    public static void stopServer() {
        service.stop();
    }

}
