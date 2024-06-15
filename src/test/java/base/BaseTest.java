package base;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import pageObjects.screens.HomeScreen;

import static devices.MobileFactory.getEmulator;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected DriverProvider driverProvider;
    protected HomeScreen homeScreen;

    @BeforeTest
    public void setup() {
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        homeScreen = new HomeScreen(driver);
        homeScreen.verifyAppLaunched();
    }

    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}
