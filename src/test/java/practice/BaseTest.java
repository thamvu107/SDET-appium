package practice;

import driverFactory.Driver;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected static AppiumDriver driver;

    @BeforeClass
    public void setUpAppium() {
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver(driver);
//        Driver.stopServer();
    }

}
