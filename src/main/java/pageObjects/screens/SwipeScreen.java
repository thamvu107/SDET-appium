package pageObjects.screens;

import enums.SwipeHorizontalDirection;
import enums.SwipeVerticalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.gestures.swipe.horizontal.SwipeHorizontally;
import utils.gestures.swipe.vertical.SwipeVertically;

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
    private final By scrollTargetLogoLoc = AppiumBy.accessibilityId("WebdriverIO logo");
    private final By scrollTargetTextLoc = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"You found me!!!\")");
    private final SwipeHorizontally swipeHorizontal;
    private final SwipeVertically swipeVertically;

    public SwipeScreen(AppiumDriver driver) {

        super(driver);
        swipeHorizontal = new SwipeHorizontally(driver, carouselContainerEl(), 100);
        swipeVertically = initScrollDown(driver, swipeScreenEl(), carouselContainerEl());
    }

    public WebElement swipeScreenEl() {
        return elementUtils.waitForElementToBeVisible(swipeScreenLoc);
    }


    public WebElement carouselContainerEl() {

        return elementUtils.waitForElementToBeVisible(carouselContainerLoc, LONG_EXPLICIT_WAIT);
    }

    public WebElement scrollTargetEl() {

        return elementUtils.waitForElementToBeVisible(scrollTargetLogoLoc, SHORT_EXPLICIT_WAIT);
    }

    private WebElement firstCardEl() {
        return elementUtils.waitForFindingElement(firstCardWrapperLoc, SHORT_EXPLICIT_WAIT);
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

    public SwipeScreen swipeRightToCardTitle(String targetTitle, int maxSwipes) {
        boolean isTargetCard = swipeRightToCard(targetTitle, maxSwipes);
        Assert.assertTrue(isTargetCard);

        return this;
    }

    public SwipeScreen swipeLeftToCardTitle(String targetTitle, int maxSwipes) {
        boolean isTargetCard = swipeToElement(SwipeHorizontalDirection.LEFT, targetTitle, maxSwipes);
        Assert.assertTrue(isTargetCard);

        return this;
    }

    public boolean swipeRightToCard(String targetTitle, int maxSwipes) {
        return swipeToElement(SwipeHorizontalDirection.RIGHT, targetTitle, maxSwipes);
    }

    public boolean swipeLeftToElement(By targetLoc, int maxSwipes) {
        return swipeToElement(SwipeHorizontalDirection.LEFT, targetLoc, maxSwipes);
    }

    public boolean swipeRightToElement(By cardLoc, int maxSwipes) {
        return swipeToElement(SwipeHorizontalDirection.RIGHT, cardLoc, maxSwipes);
    }


    public boolean swipeLeftToElement(WebElement targetEl, int maxSwipes) {
        return swipeToElement(SwipeHorizontalDirection.LEFT, targetEl, maxSwipes);
    }

    public boolean swipeRightToElement(WebElement targetEl, int maxSwipes) {
        return swipeToElement(SwipeHorizontalDirection.RIGHT, targetEl, maxSwipes);

    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, String targetTitle, int maxSwipes) {

        WebElement currentCardEl;
        WebElement currentCardTitleEl;
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
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

        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            Assert.fail("Card should be present");
        }
        return elements.get(0);
    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, By targetLoc, int maxSwipes) {

        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
            if (elementUtils.isElementPresent(targetLoc)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private boolean swipeToElement(SwipeHorizontalDirection direction, WebElement carouselContainerEl, int maxSwipes) {

//        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
            horizontalSwipe(swipeHorizontal, direction);
            if (elementUtils.isElementDisplayed(carouselContainerEl)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private void horizontalSwipe(SwipeHorizontally swipeHorizontal, SwipeHorizontalDirection direction) {
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

    public SwipeScreen swipeLeft(int maxSwipes) {
        SwipeHorizontally swipeHorizontal = new SwipeHorizontally(driver, carouselContainerEl());
        swipeHorizontal.swipeLeft(maxSwipes);
        return this;
    }

    public SwipeScreen swipeRight(int maxSwipes) {
        SwipeHorizontally swipeHorizontal = new SwipeHorizontally(driver, carouselContainerEl());
        swipeHorizontal.swipeRight(maxSwipes);
        return this;
    }

    public SwipeScreen goToTheFirstCard(int maxSwipes) {

        // TODO: improve get last card by the way that no left card before current card

        boolean isFirstItem = isFirstCard();
        if (!isFirstItem) {
            isFirstItem = swipeRightToElement(firstCardWrapperLoc, maxSwipes);
        }
        Assert.assertTrue(isFirstItem, "This is not first card");

        return this;
    }

    private boolean isFirstCard() {
        By currentCardWrapperLoc = getCurrentCardWrapperLoc();

        return firstCardWrapperLoc.equals(currentCardWrapperLoc);
    }

    public SwipeScreen goToLastCard(int maxSwipes) {

        // TODO: improve get last card by the way that no right card after current card

        boolean isLastItem = isLastCard();
        if (!isLastItem) {
            isLastItem = swipeLeftToElement(lastCardWrapperLoc, maxSwipes);
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

    public SwipeScreen verifyScrollWrapperIsDisplayed() {
        boolean isFoundTarget = elementUtils.isElementDisplayed(swipeScreenEl());
        Assert.assertTrue(isFoundTarget, "Scroll wrapper is not found");
        return this;
    }

    public SwipeScreen scrollToWebDriverIOLogo() {

        boolean isFoundTarget = swipeUpToElement(scrollTargetLogoLoc, 3);
        Assert.assertTrue(isFoundTarget, "WebDriverIO logo is not found");

        return this;
    }

    public SwipeScreen scrollToScreenTitle() {

        boolean isFoundTarget = swipeDownToElement(swipeScreenTitleLoc, 3);
        Assert.assertTrue(isFoundTarget, "Title is not found");

        return this;
    }

    public void verifyScrollUpTargetFound() {
        Assert.assertTrue(elementUtils.isElementDisplayed(swipeScreenEl()));
        Assert.assertTrue(elementUtils.isElementDisplayed(scrollTargetTextLoc));
    }

    public void verifyScrollDownTargetFound() {
        Assert.assertTrue(elementUtils.isElementDisplayed(swipeScreenTitleLoc));
    }

    public boolean swipeUpToElement(By targetLoc, int maxSwipes) {
        return swipeToElement(SwipeVerticalDirection.UP, targetLoc, maxSwipes);
    }

    public boolean swipeDownToElement(By cardLoc, int maxSwipes) {
        return swipeToElement(SwipeVerticalDirection.DOWN, cardLoc, maxSwipes);
    }

    private boolean swipeToElement(SwipeVerticalDirection direction, By targetLoc, int maxSwipes) {

        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
            verticalSwipe(swipeVertically, direction);
            if (elementUtils.isElementPresent(targetLoc)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private void verticalSwipe(SwipeVertically swipeVertically, SwipeVerticalDirection direction) {
        switch (direction) {
            case UP:
                swipeVertically.swipeUp();
                break;
            case DOWN:
                swipeVertically.swipeDown();
                break;
            default:
                throw new RuntimeException("Unsupported direction");
        }
    }

    private SwipeVertically initScrollDown(AppiumDriver driver, WebElement wrapper, WebElement childElement) {

        Rectangle wrapperRect = wrapper.getRect();

        int anchor = wrapperRect.getX() + wrapperRect.getWidth() / 2;
        int scrollTopY = wrapperRect.getY() + 100;
        int scrollBottomY = childElement.getLocation().getY() - 100;

        Point start = new Point(anchor, scrollTopY);
        Point end = new Point(anchor, scrollBottomY);

        return new SwipeVertically(driver, start, end, 60);
    }
}
