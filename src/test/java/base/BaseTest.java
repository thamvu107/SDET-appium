package base;

import driverFactory.DriverProvider;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pageObjects.screens.HomeScreen;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected DriverProvider driverProvider;
    protected HomeScreen homeScreen;
    protected PlatformType currentPlatform;

    @BeforeSuite
    public void beforeTest() {
        //LaunchingEmulatorUtil.launchEmulator("Pixel_5_API_33_1");
//        LaunchingEmulatorUtil.launchEmulator("Pixel_4_API_33");
    }


    @AfterClass
    public void afterClass() {
        driverProvider.quitDriver(driver);
    }

    @AfterSuite
    public void afterSuite() {

//        LaunchingEmulatorUtil.killEmulator();
    }

}
