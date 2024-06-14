package utils;

import driverFactory.Platform;
import exceptions.WaitForConditionException;
import exceptions.WaitForElementException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

public class ElementUtils {
    private final AppiumDriver driver;
    public WaitUtils waitUtils;
    private final Platform currentPlatform;


    public ElementUtils(AppiumDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(this.driver);
        this.currentPlatform = new PlatformUtil().getCurrentPlatform(this.driver);

    }

    public By getLocatorIsMappedCurrentPlatform(Map<driverFactory.Platform, By> locatorMap) {

        Require.nonNull("Locator", locatorMap);
        return locatorMap.get(currentPlatform);
    }

    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = this.waitForElementToBeVisible(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(By locator, long waitTimeInMils) {
        try {
            WebElement element = this.waitForElementToBeVisible(locator, waitTimeInMils);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> waitForElementsToBeVisible(By locator) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<WebElement> waitForElementsToBeVisible(By parent, By locator) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, locator));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<WebElement> waitForElementsToBeVisible(By parent, By locator, long timeInMills) {
        try {
            return waitUtils.createWebDriverWait(timeInMills)
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, locator));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForFindingElement(By locator) {
        try {
            return this.waitForCondition(driver -> {
                assert driver != null;
                return driver.findElement(locator);
            });
        } catch (WaitForConditionException e) {
            System.err.println("Exception occurred while waiting for element: " + e.getMessage());
            throw e;
        }
    }


    public WebElement waitForFindingElement(By locator, long timeoutInMills) {

        try {
            return this.waitForCondition(driver -> {
                assert driver != null;
                return driver.findElement(locator);
            }, timeoutInMills);
        } catch (WaitForConditionException e) {
            System.err.println("Exception occurred while waiting for element: " + e.getMessage());
            throw e;
        }
    }

    public WebElement waitForElementToBeVisible(By locator) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println();
            throw new TimeoutException(
                    String.format("Timeout while waiting for element to be visible: %s", locator), e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                    String.format("Element not found while waiting for it to be visible: %s", locator), e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException(
                    String.format("Element became stale while waiting for it to be visible: %s", locator), e);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("An unexpected error occurred while waiting for element to be visible: %s", locator), e);
        }
    }

    public WebElement waitForElementToBeVisible(By locator, long timeInMillis) {

        try {
            return waitUtils.createWebDriverWait(timeInMillis)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println();
            throw new TimeoutException(
                    String.format("Timeout after %d milliseconds waiting for element to be visible: %s", timeInMillis, locator), e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                    String.format("Element not found while waiting for it to be visible: %s", locator), e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException(
                    String.format("Element became stale while waiting for it to be visible: %s", locator), e);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("An unexpected error occurred while waiting for element to be visible: %s", locator), e);
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException(
                    String.format("Timeout while waiting for element to be visible: %s", element), e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException(
                    String.format("Element became stale while waiting for it to be visible: %s", element), e);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("An unexpected error occurred while waiting for element to be visible: %s", element), e);
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element, long timeInMillis) {
        try {
            return waitUtils.createWebDriverWait(timeInMillis)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException(
                    String.format("Timeout after %d milliseconds waiting for element to be visible: %s", timeInMillis, element), e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException(
                    String.format("Element became stale while waiting for it to be visible: %s", element), e);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("An unexpected error occurred while waiting for element to be visible: %s", element), e);
        }
    }

    public WebElement waitForElementTobeClickable(By locator) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            String errorMessage = String.format("Timeout waiting for element to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        } catch (NoSuchElementException e) {
            String errorMessage = String.format("Element not found while waiting to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        } catch (StaleElementReferenceException e) {
            String errorMessage = String.format("Stale element reference while waiting to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        } catch (ElementNotInteractableException e) {
            String errorMessage = String.format("Element not interactable while waiting to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        } catch (InvalidSelectorException e) {
            String errorMessage = String.format("Invalid selector used while waiting for element to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        } catch (WebDriverException e) {
            String errorMessage = String.format("WebDriverException while waiting for element to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = String.format("Unexpected exception while waiting for element to be clickable: %s", locator);
            throw new WaitForElementException(errorMessage, e);
        }
    }

    public void waitForElementTobeClickable(WebElement element) {

        try {
            waitUtils.explicitWait()
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            String errorMessage = String.format("Timeout waiting for element to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        } catch (NoSuchElementException e) {
            String errorMessage = String.format("Element not found while waiting to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        } catch (StaleElementReferenceException e) {
            String errorMessage = String.format("Stale element reference while waiting to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        } catch (ElementNotInteractableException e) {
            String errorMessage = String.format("Element not interactable while waiting to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        } catch (InvalidSelectorException e) {
            String errorMessage = String.format("Invalid selector used while waiting for element to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        } catch (WebDriverException e) {
            String errorMessage = String.format("WebDriverException while waiting for element to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = String.format("Unexpected exception while waiting for element to be clickable: %s", element);
            throw new WaitForElementException(errorMessage, e);
        }
    }

    public WebElement waitForElementTobeClickable(WebElement element, long timeInMillis) {
        try {
            return waitUtils.createWebDriverWait(timeInMillis)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            String errorMessage = String.format("Timeout waiting for element to be clickable with timeout %d ms: %s", timeInMillis, element);
            throw new WaitForElementException(errorMessage, e);
        } catch (NoSuchElementException e) {
            String errorMessage = String.format("Element not found while waiting to be clickable with timeout %d ms: %s", timeInMillis, element);
            throw new WaitForElementException(errorMessage, e);
        } catch (StaleElementReferenceException e) {
            String errorMessage = String.format("Stale element reference while waiting to be clickable with timeout %d ms: %s", timeInMillis, element);
            throw new WaitForElementException(errorMessage, e);
        } catch (ElementNotInteractableException e) {
            String errorMessage = String.format("Element not interactable while waiting to be clickable with timeout %d ms: %s", timeInMillis, element);
            throw new WaitForElementException(errorMessage, e);
        } catch (WebDriverException e) {
            String errorMessage = String.format("WebDriverException while waiting for element to be clickable with timeout %d ms: %s", timeInMillis, element);
            throw new WaitForElementException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = String.format("Unexpected exception while waiting for element to be clickable with timeout %d ms: %s", timeInMillis, element);
            throw new WaitForElementException(errorMessage, e);
        }
    }

    public WebElement findElement(Map<Platform, By> locatorMap) {

        By locator = locatorMap.get(currentPlatform);

        return driver.findElement(locator);
    }

    public List<WebElement> findElementsByIdPrefix(String idPrefix) {
        By locator = AppiumBy.androidUIAutomator(
                "new UiSelector().resourceIdMatches(\"^" + idPrefix + ".*\")");

        List<WebElement> elements = driver.findElements(locator);

        for (WebElement element : elements) {
            System.out.println("Found element with ID: " + element.getAttribute("resource-id"));
        }

        return elements;
    }


    public Alert waitForAlertIsPresent(WebDriver driver, long timeoutInMillis) {
        try {
            return waitUtils.createWebDriverWait(timeoutInMillis)
                    .until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            System.err.println("Alert not present within " + timeoutInMillis + " millisecond.");
            return null;
        }
    }

    public Boolean isScreenTitleDisplayedCorrect(String expectedTitle) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.titleIs(expectedTitle));

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isElementPresentText(By locator, String text) {
        try {
            return waitUtils.explicitWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

        } catch (Exception e) {
            System.out.println("\n" + locator + "\n Element Not Displayed and does not have text");
            return false;
        }
    }

    public Boolean isElementPresentText(By locator, String text, long timeoutInMillis) {
        try {
            return waitUtils.createWebDriverWait(timeoutInMillis)
                    .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isElementPresentText(WebElement element, String text) {
        try {
            return waitUtils.explicitWait()
                    .until(ExpectedConditions.textToBePresentInElement(element, text));

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isElementPresentText(WebElement element, String text, long timeoutInMillis) {
        try {
            return waitUtils.createWebDriverWait(timeoutInMillis)
                    .until(ExpectedConditions.textToBePresentInElement(element, text));

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTextDisplayedCorrect(WebElement element, String title) {
        return element.isDisplayed() && element.getText().trim().equalsIgnoreCase(title);
    }

    public boolean waitForTextPresentInElement(By locator, String text) {
        return waitUtils.explicitWait()
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    private <T> T waitForCondition(ExpectedCondition<T> condition) {
        try {
            return waitUtils.explicitWait()
                    .until(condition);
        } catch (Exception e) {
            throw new WaitForConditionException(String.format("Failed to wait for condition: %s", condition), e);
        }
    }

    private <T> T waitForCondition(ExpectedCondition<T> condition, long timeoutInMillis) {
        try {
            return waitUtils.createWebDriverWait(timeoutInMillis)
                    .until(condition);
        } catch (TimeoutException e) {
            String errorMessage = String.format("Timeout while waiting for condition: %s", condition);
            throw new WaitForConditionException(errorMessage, e);
        } catch (WebDriverException e) {
            String errorMessage = String.format("WebDriverException while waiting for condition: %s", condition);
            throw new WaitForConditionException(errorMessage, e);
        } catch (Exception e) {
            String errorMessage = String.format("Unexpected exception while waiting for condition: %s", condition);
            throw new WaitForConditionException(errorMessage, e);
        }
    }
}
