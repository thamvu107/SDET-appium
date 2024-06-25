package base;

import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import pageObjects.screens.HomeScreen;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected DriverProvider driverProvider;
    protected HomeScreen homeScreen;

    @BeforeTest
    public void beforeTest() {
//        System.out.println("Before Test");
    }


    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}
