package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverFactory {
    public  static AppiumDriver getDriver(Platform platform) {

        // DesiredCaps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UUID_OPTION, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server URL");
        }

        AppiumDriver appiumDriver = null;
        switch (platform){
            case ANDROID:
                appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver(appiumServer, desiredCapabilities);
                break;
            default:
                throw new RuntimeException("Platform not supported");
        }

        return appiumDriver;
    }
}
