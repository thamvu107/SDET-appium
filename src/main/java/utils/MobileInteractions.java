package utils;

import constants.WaitConstants;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class MobileInteractions {
    private final AppiumDriver driver;
    private final WebDriverWait wait;
    FluentWait<AppiumDriver> fluentWait;
    //    private final String currentPlatform;
    private final Platform currentPlatform;

    public MobileInteractions(AppiumDriver driver) {
        this.driver = driver;
        this.currentPlatform = new PlatformUtil().getCurrentPlatform(this.driver);

        WaitUtils waitUtils = new WaitUtils(driver);
        this.wait = waitUtils.explicitWait();
        fluentWait = waitUtils.fluentWait(WaitConstants.SHORT_FLUENT_WAIT, WaitConstants.POLLING_EVERY);
    }

    public void waitElementPressAble(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementLocatedVisibility(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitVisibilityOfElementLocated(By locator) {

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Assert.assertNotNull(element, "Element is not visible after waiting");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitElementVisibility(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementInvisibility(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

    }


    public void waitInVisibilityOfElementLocated(By Locator) {

        try {
            boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
            Assert.assertTrue(element, "Wait until invisibility of element located");
        } catch (TimeoutException | StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitElementLocatedAndFindElement(By locator) {

        this.waitVisibilityOfElementLocated(locator);

        return driver.findElement(locator);
    }

    public <T> void waitUntil(ExpectedCondition<T> conditionToBe) {
        try {
            wait.until(conditionToBe);
        } catch (Exception e) {
            throw e;
        }
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

    public boolean isElementPresent(By locator) {

        return !driver.findElements(locator).isEmpty();
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

    public WebElement findElement(Map<driverFactory.Platform, By> locatorMap) {

//        By locator = locatorMap.get(driverFactory.Platform.valueOf(currentPlatform));
        By locator = locatorMap.get(currentPlatform);

        return driver.findElement(locator);
    }

    public By getLocatorIsMappedCurrentPlatform(Map<driverFactory.Platform, By> locatorMap) {

        Require.nonNull("Locator", locatorMap);

//        return locatorMap.get(Platform.valueOf(currentPlatform));
        return locatorMap.get(currentPlatform);
    }

    public boolean isTextDisplayedCorrect(WebElement element, String title) {
//        waitElementVisibility(element);
        return element.isDisplayed() && element.getText().equalsIgnoreCase(title);
    }

    public Dimension getScreenSize() {
        return driver.manage().window().getSize();
    }

    public int getScreenWith() {
        return this.getScreenSize().getWidth();
    }

    public int getScreenHeight() {
        return this.getScreenSize().getHeight();

    }

    public void swipeVertical(Point start, Point end) {

        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, viewport(), start.x, start.y))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, ofMillis(WaitConstants.SHORT_PAUSE)))
                .addAction(pointerInput.createPointerMove(ofMillis(WaitConstants.MOVE), viewport(), end.x, end.y))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public void openNotificationPanel() {

        manageNotificationPanel(true);
    }

    public void closeNotificationPanel() {

        manageNotificationPanel(false);
    }

    public void manageNotificationPanel(boolean display) {

        MobileInteractions mobileInteractions = new MobileInteractions(driver);
        int screenWidth = mobileInteractions.getScreenWith();
        int screenHeight = mobileInteractions.getScreenHeight();

        int x = screenWidth / 2;
        int topY = 0;
        int bottomY = screenHeight / 2;

        Point startPoint = display ? new Point(x, topY) : new Point(x, bottomY);
        Point endPoint = display ? new Point(x, bottomY) : new Point(x, topY);

        mobileInteractions.swipeVertical(startPoint, endPoint);
    }


}
