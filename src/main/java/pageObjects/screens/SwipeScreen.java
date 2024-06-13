package pageObjects.screens;

import enums.SwipeHorizontalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.gestures.swipe.horizontal.SwipeHorizontal;

import java.util.List;

public class SwipeScreen extends BaseScreen {

    private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");
    private final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
    private final By swipeTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");
    private final By fullCardDisplayedLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");

    private final By targetCardTitleLoc = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");

    public SwipeScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement carouselContainerEl() {

        return elementUtils.waitForElementToBeVisible(carouselContainerLoc);
    }

    private WebElement getCurrentCardEl() {

        List<WebElement> elements = elementUtils.waitForElementsToBeVisible(carouselContainerLoc, fullCardDisplayedLoc);
        if (elements.isEmpty()) {
            Assert.fail("Card should be present");
        }
        return elements.get(0);
    }

    private WebElement getCardTitleElement(WebElement currentCardEl) {
        return currentCardEl.findElement(targetCardTitleLoc);
    }

    public SwipeScreen verifySwipeScreenDisplayed() {

        Assert.assertTrue(elementUtils.isElementDisplayed(swipeScreenLoc));
        return this;
    }

    public SwipeScreen verifySwipeScreenTitle(String expectTitle) {
        Assert.assertTrue(elementUtils.waitForTextPresentInElement(swipeTitleLoc, expectTitle));
        return this;
    }

    public void verifyCarouselDisplayed() {
        Assert.assertTrue(elementUtils.isElementDisplayed(carouselContainerEl()), "The carousel is not displayed");
    }

    public void verifyTheTargetCardIsFound(String targetTitle, int maxSwipeTime) {
        boolean isFoundTargetCard = swipeLeftToTargetCard(targetTitle, maxSwipeTime);
        Assert.assertTrue(isFoundTargetCard, "The target card is not found");
    }

    public boolean swipeLeftToTargetCard(String targetTitle, int maxSwipeTime) {
        return swipeToTargetCard(SwipeHorizontalDirection.LEFT, targetTitle, maxSwipeTime);
    }

    public SwipeScreen swipeRightToTargetCard(String targetTitle, int maxSwipeTime) {
        boolean isFoundTargetCard = swipeToTargetCard(SwipeHorizontalDirection.RIGHT, targetTitle, maxSwipeTime);
        Assert.assertTrue(isFoundTargetCard, "The target card is not found");
        return this;
    }

    public SwipeScreen swipeLeft(int times) {
        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        swipeHorizontal.swipeLeft(times);
        return this;
    }


    public SwipeScreen swipeRight(int times) {
        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        swipeHorizontal.swipeRight(times);
        return this;
    }

    private boolean swipeToTargetCard(SwipeHorizontalDirection direction, String targetTitle, int maxSwipeTime) {

        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        WebElement currentCardEl;
        WebElement currentCardTitleEl;
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            swipeToHorizontalDirection(swipeHorizontal, direction);
            currentCardEl = getCurrentCardEl();
            currentCardTitleEl = getCardTitleElement(currentCardEl);
            if (elementUtils.isTextDisplayedCorrect(currentCardTitleEl, targetTitle)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private void swipeToHorizontalDirection(SwipeHorizontal swipeHorizontal, SwipeHorizontalDirection direction) {
        if (direction == SwipeHorizontalDirection.LEFT) {
            swipeHorizontal.swipeLeft();
        } else {
            swipeHorizontal.swipeRight();
        }
    }
}
