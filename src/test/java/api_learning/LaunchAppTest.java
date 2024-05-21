package api_learning;

import driverFactory.Driver;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;

public class LaunchAppTest {
    public static void main(String[] args) {
        AppiumDriver driver;

//      driver = DriverFactory.getMobileDriver(MobilePlatform.ANDROID);
        driver = Driver.getRemoteServerDriver(MobileFactory.getAndroidPhysicalMobile());

        driver.quit();
    }
}
