package api_learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;

import static devices.MobileFactory.getEmulator;

public class LaunchAppTest {
    public static void main(String[] args) {

        AppiumDriver driver;

        DriverProvider driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);

        driver.quit();
    }
}
