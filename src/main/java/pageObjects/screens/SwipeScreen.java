package pageObjects.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.gestures.swipe.horizontal.SwipeHorizontal;

public class SwipeScreen extends BaseScreen {

    private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");
    private final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
    private final By swipeTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");


    public SwipeScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement carouselContainerEl() {

        return elementUtils.waitForElementToBeVisible(carouselContainerLoc);
    }

    private WebElement swipeTitleEl() {
        return driver.findElement(swipeTitleLoc);
    }

    public SwipeScreen verifySwipeScreenDisplayed() {

        Assert.assertTrue(elementUtils.isElementDisplayed(swipeScreenLoc));
        return this;
    }

    public SwipeScreen verifySwipeScreenTitle(String expectTitle) {
        Assert.assertTrue(elementUtils.isTextDisplayedCorrect(swipeTitleEl(), expectTitle));
        return this;
    }

    public SwipeScreen verifyCarouselDisplayed() {
        Assert.assertTrue(elementUtils.isElementDisplayed(carouselContainerEl()), "The carousel is not displayed");
        return this;
    }

    public SwipeScreen swipeLeftCarouselMultiTime(int swipeTime) {
        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        for (int swipeCounter = 0; swipeCounter < swipeTime; swipeCounter++) {
            swipeHorizontal.swipeLeft();
        }
        return this;
    }
}
