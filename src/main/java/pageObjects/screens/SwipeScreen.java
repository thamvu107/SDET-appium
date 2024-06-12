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

    private WebElement swipeScreenElement() {
        return driver.findElement(swipeScreenLoc);
    }

    private WebElement carouselContainerElement() {
        return driver.findElement(carouselContainerLoc);
    }

    private WebElement swipeTitleEl() {
        return driver.findElement(swipeTitleLoc);
    }


    public SwipeScreen verifySwipeScreenTitle(String expectTitle) {
        boolean isScreenTitleCorrect = isTitleCorrect(swipeTitleEl(), expectTitle);
        Assert.assertTrue(isScreenTitleCorrect, "The title does not match");
        return this;
    }

    public boolean isCarouselDisplayed() {
        return mobileInteractions.isElementPresent(carouselContainerLoc);
    }

    public SwipeScreen swipeLeftCarouselMultiTime(int swipeTime) {

        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerElement());
        for (int swipeCounter = 0; swipeCounter < swipeTime; swipeCounter++) {
            swipeHorizontal.swipeLeft();
        }

        return this;
    }
}
