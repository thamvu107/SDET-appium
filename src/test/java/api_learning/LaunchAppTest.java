package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobilePlatform;

public class LaunchAppTest {
    public static void main(String[] args) {

//        AppiumDriver appiumDriver = DriverFactory.createDriver(AndroidCapabilities.getCaps());
        AppiumDriver appiumDriver = DriverFactory.getMobileDriver(MobilePlatform.ANDROID);

        appiumDriver.quit();
    }
}
