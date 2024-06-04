package utils;

import constants.WaitConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WaitUtils {

    private final AppiumDriver driver;

    public WaitUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public void setImplicitWait(long timeoutMillis) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeoutMillis));
    }

    public WebDriverWait shortExplicitWait() {
        return new WebDriverWait(driver, Duration.ofMillis(WaitConstants.SHORT_EXPLICIT_WAIT));
    }

    public WebDriverWait explicitWait() {
        return new WebDriverWait(driver, Duration.ofMillis(WaitConstants.EXPLICIT_WAIT));
    }

    public WebDriverWait longExplicitWait() {
        return new WebDriverWait(driver, Duration.ofMillis(WaitConstants.LONG_EXPLICIT_WAIT));
    }

    public FluentWait<AppiumDriver> fluentWait(long timeout, long polling) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }

}
