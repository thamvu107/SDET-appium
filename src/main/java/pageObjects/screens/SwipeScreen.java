package pageObjects.screens;

import enums.SwipeHorizontalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.gestures.swipe.horizontal.SwipeHorizontal;

import java.util.List;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;

public class SwipeScreen extends BaseScreen {

    private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");
    private final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
    private final By swipeScreenTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");

    //    private final By currentCardLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");
    private final By currentCardLoc = AppiumBy.accessibilityId("card");

    private final By firstCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_0_READY__\")");

    //private final By firstCardLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"FULLY OPEN SOURCE\")");
    //NOTE: The requested id selector does not have a package name prefix.
    //private final By firstCardWrapperLoc = AppiumBy.id("__CAROUSEL_ITEM_0_READY__");
    private final By lastCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_5_READY__\")");
    private final By currentCardTitleLoc = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");
    private final By currentCardDescriptionLoc = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[2]");
    SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());

    public SwipeScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement carouselContainerEl() {

        return elementUtils.waitForElementToBeVisible(carouselContainerLoc, LONG_EXPLICIT_WAIT);
    }

    private WebElement firstCardEl() {
        return elementUtils.waitForFindingElement(firstCardWrapperLoc, SHORT_EXPLICIT_WAIT);
    }

    private WebElement firstCardTitleEl() {
        return firstCardEl().findElement(firstCardWrapperLoc);
    }


    private WebElement currentCardEl() {
        try {
            return elementUtils.waitForFindingElement(currentCardLoc, SHORT_EXPLICIT_WAIT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private WebElement currentCardTitleEl() {
        return currentCardEl().findElement(currentCardTitleLoc);
    }

    private WebElement currentCardDescriptionEl() {
        return currentCardEl().findElement(currentCardDescriptionLoc);
    }


    private WebElement lastCardEl() {
        return elementUtils.waitForFindingElement(lastCardWrapperLoc);
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

    public SwipeScreen swipeRightToCardTitle(String targetTitle, int maxSwipeTime) {
        boolean isTargetCard = swipeRightToCard(targetTitle, maxSwipeTime);
        Assert.assertTrue(isTargetCard);

        return this;
    }

    public SwipeScreen swipeLeftToCardTitle(String targetTitle, int maxSwipeTime) {
        boolean isTargetCard = swipeToElement(SwipeHorizontalDirection.LEFT, targetTitle, maxSwipeTime);
        Assert.assertTrue(isTargetCard);

        return this;
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


    public boolean swipeLeftToElement(WebElement targetEl, int maxSwipeTime) {
        return swipeToElement(SwipeHorizontalDirection.LEFT, targetEl, maxSwipeTime);
    }

    public boolean swipeRightToElement(WebElement targetEl, int maxSwipeTime) {
        return swipeToElement(SwipeHorizontalDirection.RIGHT, targetEl, maxSwipeTime);

    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, String targetTitle, int maxSwipeTime) {

//        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        WebElement currentCardEl;
        WebElement currentCardTitleEl;
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
            currentCardEl = getCurrentCardEl(currentCardLoc);
            currentCardTitleEl = currentCardEl.findElement(currentCardTitleLoc);
            if (elementUtils.isTextDisplayedCorrect(currentCardTitleEl, targetTitle)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private WebElement getCurrentCardEl(By locator) {

        List<WebElement> elements = elementUtils.waitForElementsToBeVisible(carouselContainerLoc, locator);
        if (elements.isEmpty()) {
            Assert.fail("Card should be present");
        }
        return elements.get(0);
    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, By targetLoc, int maxSwipeTime) {

//        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
//            if (elementUtils.isElementDisplayed(targetLoc, 1000)) {
            if (elementUtils.isElementPresent(targetLoc)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, WebElement carouselContainerEl, int maxSwipeTime) {

//        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
            if (elementUtils.isElementDisplayed(carouselContainerEl)) {
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

    public SwipeScreen goToTheFirstCard(int maxSwipeTime, long timeInMillis) {

        boolean isFirstItem = isFirstCard();
        if (!isFirstItem) {
            isFirstItem = swipeRightToElement(firstCardWrapperLoc, maxSwipeTime);
        }
        Assert.assertTrue(isFirstItem, "This is not first card");

        return this;
    }

    private boolean isFirstCard() {
        By currentCardWrapperLoc = getCurrentCardWrapperLoc();

        return firstCardWrapperLoc.equals(currentCardWrapperLoc);
    }

    public SwipeScreen goToLastCard(int maxSwipeTime, long timeInMillis) {

        boolean isLastItem = isLastCard();
        if (!isLastItem) {
            isLastItem = swipeLeftToElement(lastCardWrapperLoc, maxSwipeTime);
        }
        Assert.assertTrue(isLastItem, "This is not last card");

        return this;
    }

    private boolean isLastCard() {
        By currentCardWrapperLoc = getCurrentCardWrapperLoc();

        return lastCardWrapperLoc.equals(currentCardWrapperLoc);
    }


    private By getCurrentCardWrapperLoc() {

        WebElement currentCardWrapperEl = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]/.."));
        String resourceId = currentCardWrapperEl.getAttribute("resourceId");

        return AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")");
    }

    public void verifyCardContent(String expectedTitle, String expectedDescription) {

        verifyCardTitle(expectedTitle);
        verifyCardDescription(expectedDescription);
    }

    private void verifyCardTitle(String expectedTitle) {
        String actualTitle = currentCardTitleEl().getText();
        Assert.assertEquals(actualTitle, expectedTitle, "Title is not correct");
    }

    private void verifyCardDescription(String expectedDescription) {
        String actualDescription = currentCardDescriptionEl().getText();
        Assert.assertEquals(actualDescription, expectedDescription, "Description is not correct");
    }
}
