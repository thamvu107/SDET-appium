package practice;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected static AppiumDriver driver;

    @BeforeClass
    public void setUpAppium() {

        //DriverFactory.startAppiumServer();
//        driver = DriverFactory.getDriver(MobilePlatform.ANDROID);
        // driver = DriverFactory.createDriver(AndroidCapabilities.getCaps());

        // Global Implicit Wait - applied for WHOLE appiumDriver session
        // DriverFactory.waitDriverSession(driver);
    }

    @AfterClass
    public void tearDown() {
//        DriverFactory.quitDriver(driver);
//        DriverFactory.stopServer();
        //driver.quit();
    }

}
