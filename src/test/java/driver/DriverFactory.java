package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

    private static DesiredCapabilities setAndroidCapabilities() {
        // DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.UDID_OPTION, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");
        return capabilities;
    }

    private static DesiredCapabilities setIOSCapabilities() {
        // DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME_OPTION, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.UDID_OPTION, "");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");
        return capabilities;
    }

    public static AppiumDriver getDriver(Platform platform) {

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server URL");
        }

        AppiumDriver appiumDriver = null;
        switch (platform) {
            case ANDROID:
                DesiredCapabilities androidCapabilities = setAndroidCapabilities();
                appiumDriver = new AndroidDriver(appiumServer, androidCapabilities);
                break;
            case IOS:
                DesiredCapabilities setIOSCapabilities = setIOSCapabilities();
                appiumDriver = new IOSDriver(appiumServer, setIOSCapabilities);
                break;
            default:
                throw new RuntimeException("Platform not supported");
        }

        // Global Implicit Wait - applied for WHOLE driver session
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));

        return appiumDriver;
    }
}
