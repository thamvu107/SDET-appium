package utils;

import constants.WaitConstant;
import driverFactory.Driver;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static constants.WaitConstant.POLLING_EVERY;
import static constants.WaitConstant.SHORT_FLUENT_WAIT;

public class MobileActions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;
    FluentWait<AppiumDriver> fluentWait;
    private final String currentPlatform;


    public MobileActions(AppiumDriver driver) {
        this.driver = driver;
        this.currentPlatform = Driver.getCurrentPlatform(driver);
        this.wait = new WebDriverWait(driver, Duration.ofMillis(WaitConstant.SHORT_EXPLICIT_WAIT));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(SHORT_FLUENT_WAIT))
                .pollingEvery(Duration.ofMillis(POLLING_EVERY))
                .ignoring(NoSuchElementException.class);
    }

    public void waitElementPressAble(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementLocatedVisibility(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitElementVisibility(WebElement element) {

        //TODO : custom wait
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementInvisibility(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitUntilVisibilityOfElementLocated(By Locator) {

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        Assert.assertNotNull(element, "Element is not visibility");
    }

    public void waitUntilInVisibilityOfElementLocated(By Locator) {

        boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
        Assert.assertTrue(element, "Element is visibility");
    }

    public WebElement waitElementLocatedAndFindElement(By locator) {

        this.waitElementLocatedVisibility(locator);

        return driver.findElement(locator);
    }

    public boolean isElementPresent(By locator) {

        List<WebElement> elements = driver.findElements(locator);

        return !elements.isEmpty();
    }

    public boolean isElementDisplayed(By locator) {

        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyElementDisplayed(By locator) {
        Assert.assertTrue(this.isElementDisplayed(locator));
    }

    public boolean isElementDisplayed(WebElement element) {

        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyElementDisplayed(WebElement webElement) {
        Assert.assertTrue(this.isElementDisplayed(webElement));
    }

    public boolean isAlertPresent() {
        try {
            fluentWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void assertAlertHasDisappeared(AppiumDriver driver, Duration timeoutInMilliseconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInMilliseconds);

        try {
            boolean isAlertGone = wait.until(dr -> {
                try {
                    dr.switchTo().alert();
                    return false; // Alert is still present
                } catch (NoAlertPresentException e) {
                    return true; // Alert has disappeared
                }
            });
            Assert.assertTrue(isAlertGone);
        } catch (Exception e) {
            Assert.fail("Alert did not disappear within the timeout period.");
        }
    }

    public void setText(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public WebElement findElement(Map<Platform, By> locatorMap) {

        By locator = locatorMap.get(Platform.valueOf(currentPlatform));

        return driver.findElement(locator);
    }

    public By getLocatorIsMappedCurrentPlatform(Map<Platform, By> locatorMap) {

        Require.nonNull("Locator", locatorMap);

        return locatorMap.get(Platform.valueOf(currentPlatform));
    }

    public boolean isTitleCorrect(WebElement element, String title) {
        waitElementVisibility(element);
        return element.isDisplayed() && element.getText().equalsIgnoreCase(title);
    }
}
