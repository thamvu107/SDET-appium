package base;

import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import pageObjects.screens.HomeScreen;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected DriverProvider driverProvider;
    protected HomeScreen homeScreen;

    @BeforeSuite
    public void beforeTest() {
    }


    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}
