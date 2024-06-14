package pageObjects.screens;

import enums.SwipeHorizontalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import utils.gestures.swipe.horizontal.SwipeHorizontal;

import java.util.List;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;

public class SwipeScreen extends BaseScreen {

    private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");
    private final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
    private final By swipeScreenTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");
    private final By currentCardLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");
    //    private final By currentCardTitleLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");
//    private final By firstCardLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"FULLY OPEN SOURCE\")");
    private final By firstCardLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\"__CAROUSEL_ITEM_0_READY__\")");
    private final By lastCardLoc = AppiumBy.accessibilityId("card");

    private final By currentCardTitleLoc = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");

    public SwipeScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement carouselContainerEl() {

        return elementUtils.waitForElementToBeVisible(carouselContainerLoc, LONG_EXPLICIT_WAIT);
    }

    private WebElement currentCardEl() {
        return elementUtils.waitForFindingElement(currentCardLoc, SHORT_EXPLICIT_WAIT);
    }

    private WebElement currentCardTitleEl() {
        return getCurrentCardTitleEl(currentCardEl());
    }

    private WebElement getCurrentCardTitleEl(WebElement currentCardEl) {
        return currentCardEl.findElement(currentCardTitleLoc);
    }

    private WebElement lastCardEl() {
        return elementUtils.waitForFindingElement(lastCardLoc);
    }

    public SwipeScreen verifySwipeScreenDisplayed() {

        Assert.assertTrue(elementUtils.isElementDisplayed(swipeScreenLoc));
        return this;
    }

    public SwipeScreen verifySwipeScreenTitle(String expectTitle) {
        Assert.assertTrue(elementUtils.waitForTextPresentInElement(swipeScreenTitleLoc, expectTitle));
        return this;
    }

    public SwipeScreen verifyCarouselDisplayed() {
        Assert.assertTrue(elementUtils.isElementDisplayed(carouselContainerEl()), "The carousel is not displayed");
        return this;
    }

    public void verifySwipeLeftToCardTitle(String targetTitle, int maxSwipeTime) {
        boolean isFoundTargetCard = swipeLeftToCard(targetTitle, maxSwipeTime);
        Assert.assertTrue(isFoundTargetCard, "The target card is not found");
    }

    public void verifySwipeRightToCardTitle(String targetTitle, int maxSwipeTime) {
        boolean isFoundTargetCard = swipeRightToCard(targetTitle, maxSwipeTime);
        Assert.assertTrue(isFoundTargetCard, "The target card is not found");
    }

    public boolean swipeLeftToCard(String targetTitle, int maxSwipeTime) {
        return swipeToElement(SwipeHorizontalDirection.LEFT, targetTitle, maxSwipeTime);
    }

    public boolean swipeRightToCard(String targetTitle, int maxSwipeTime) {
        return swipeToElement(SwipeHorizontalDirection.RIGHT, targetTitle, maxSwipeTime);
    }

    public boolean swipeLeftToElement(By targetLoc, int maxSwipeTime) {
        return swipeToElement(SwipeHorizontalDirection.LEFT, targetLoc, maxSwipeTime);
    }

    public boolean swipeRightToElement(By cardLoc, int maxSwipeTime) {
        return swipeToElement(SwipeHorizontalDirection.RIGHT, cardLoc, maxSwipeTime);
    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, String targetTitle, int maxSwipeTime) {

        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        WebElement currentCardEl;
        WebElement currentCardTitleEl;
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
            currentCardEl = getCardEl(currentCardLoc);
            currentCardTitleEl = getCurrentCardTitleEl(currentCardEl);
            if (elementUtils.isTextDisplayedCorrect(currentCardTitleEl, targetTitle)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private WebElement getCardEl(By locator) {

        List<WebElement> elements = elementUtils.waitForElementsToBeVisible(carouselContainerLoc, locator);
        if (elements.isEmpty()) {
            Assert.fail("Card should be present");
        }
        return elements.get(0);
    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, By targetLoc, int maxSwipeTime) {

        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
            if (elementUtils.isElementDisplayed(targetLoc, 1)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private void horizontalSwipe(SwipeHorizontal swipeHorizontal, SwipeHorizontalDirection direction) {
        switch (direction) {
            case LEFT:
                swipeHorizontal.swipeLeft();
                break;
            case RIGHT:
                swipeHorizontal.swipeRight();
                break;
            default:
                throw new RuntimeException("Unsupported direction");
        }
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

    private boolean isFirstCard(long timeInMillis) {
        return elementUtils.isElementDisplayed(firstCardLoc, timeInMillis);
    }

    public SwipeScreen goToTheFirstCard(int maxSwipeTime) {
        boolean isTheFirstCard = isFirstCard(SHORT_EXPLICIT_WAIT) || swipeRightToElement(firstCardLoc, maxSwipeTime);
        Assert.assertTrue(isTheFirstCard);
        return this;
    }

    public void verifyFirsCard(String title, String description) {
        elementUtils.isElementPresentText(currentCardTitleEl(), title, SHORT_EXPLICIT_WAIT);
    }

    public SwipeScreen goToLastCard(int maxSwipeTime, @Optional long timeInMillis) {

        boolean isLastItem = timeInMillis == 0 ? isLastCard(LONG_EXPLICIT_WAIT) : isLastCard(SHORT_EXPLICIT_WAIT);

        if (!isLastItem) {
            isLastItem = swipeLeftToElement(lastCardLoc, maxSwipeTime);
        }
        System.out.println("isLastItem " + isLastItem);

        Assert.assertTrue(isLastItem);
        return this;
    }

    private boolean isLastCard(long timeInMillis) {
        System.out.println(lastCardLoc);
        boolean isLastCard = elementUtils.isElementDisplayed(lastCardLoc, timeInMillis);
        System.out.println("isLastCard " + isLastCard);
        return isLastCard;
    }

    public void verifyLastCard(String title, String description) {
        String actualText = currentCardTitleEl().getText();
        System.out.println(actualText);
        Assert.assertTrue(elementUtils.isElementPresentText(currentCardTitleEl(), title, SHORT_EXPLICIT_WAIT));
    }
}
