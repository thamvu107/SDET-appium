package testCases;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static devices.MobileFactory.getEmulator;

public abstract class BaseTest {

    protected AppiumDriver driver;
    DriverProvider driverProvider;

    @BeforeClass
    public void setUpAppium() {

//        this.driver = DriverFactory.getLocalServerDriver(MobileFactory.getEmulator());
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
    }

    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}
