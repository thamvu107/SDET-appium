package base;

import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import pageObjects.screens.HomeScreen;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected DriverProvider driverProvider;
    protected HomeScreen homeScreen;


    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}
