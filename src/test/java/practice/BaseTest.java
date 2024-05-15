package practice;

import constants.WaitConstant;
import driverFactory.Driver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public abstract class BaseTest {

    protected static AppiumDriver driver;
    protected static WebDriverWait wait;
    protected static FluentWait<AppiumDriver> fluentWait;
//    protected static FluentWait<AppiumDriver> wait;

    @BeforeClass
    public void setUpAppium() {

        wait = new WebDriverWait(driver, Duration.ofMillis(WaitConstant.SHORT_EXPLICIT_WAIT));

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(7000))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver(driver);
//        Driver.stopServer();
    }

}
