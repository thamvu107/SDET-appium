package learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import enums.SwipeHorizontalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;
import utils.ElementUtils;
import utils.gestures.swipe.horizontal.SwipeHorizontal;

import java.util.List;

import static constants.SwipeScreenConstants.SWIPE_SCREEN_TITLE;
import static devices.MobileFactory.getEmulator;

public class SwipeHorizontally {
    public static AppiumDriver driver;
    public static ElementUtils elementUtils;
    private static final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
    private static final By currentCardLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");
    private static final By firstCardLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\"__CAROUSEL_ITEM_0_READY__\")");
    private static final By lastCardLoc = AppiumBy.accessibilityId("card");
    private static final By targetCardTitleLoc = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");

    public static void main(String[] args) {


        DriverProvider driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        elementUtils = new ElementUtils(driver);
        try {
            HomeScreen homeScreen = new HomeScreen(driver);
            homeScreen.verifyAppLaunched();

            SwipeScreen swipeScreen = new SwipeScreen(driver);
            swipeScreen.goToSwipeScreen().verifySwipeScreenDisplayed().verifySwipeScreenTitle(SWIPE_SCREEN_TITLE).verifyCarouselDisplayed();
            swipeScreen.verifySwipeLeftToCardTitle("SUPPORT VIDEOS", 4);
            boolean isFirstCard = isFirstCard();
            Assert.assertTrue(isFirstCard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }

    }

    // Method to extract the numeric part from the element name
    private static int extractNumericPart(String element) {
        // Assuming the numeric part is at the end of the string
        String numericPart = element.substring(element.lastIndexOf('_') + 1, element.lastIndexOf('_') + 2);
        return Integer.parseInt(numericPart);
    }

    public static boolean swipeLeftToTargetCard(String targetTitle, int maxSwipeTime) {
        return swipeToTargetCard(SwipeHorizontalDirection.LEFT, targetTitle, maxSwipeTime);
    }

    public static boolean swipeLeftToTargetCard(By targetElement, int maxSwipeTime) {
        return swipeToTargetCard(SwipeHorizontalDirection.LEFT, targetElement, maxSwipeTime);
    }

    public static void swipeRightToTargetCard(String targetTitle, int maxSwipeTime) {
        boolean isFoundTargetCard = swipeToTargetCard(SwipeHorizontalDirection.RIGHT, targetTitle, maxSwipeTime);
        Assert.assertTrue(isFoundTargetCard, "The target card is not found");
    }

    public static boolean swipeRightToTargetCard(By targetElement, int maxSwipeTime) {
        return swipeToTargetCard(SwipeHorizontalDirection.RIGHT, targetElement, maxSwipeTime);
    }

    private static void swipeToHorizontalDirection(SwipeHorizontal swipeHorizontal, SwipeHorizontalDirection direction) {
        if (direction == SwipeHorizontalDirection.LEFT) {
            swipeHorizontal.swipeLeft();
        } else {
            swipeHorizontal.swipeRight();
        }
    }

    private static WebElement carouselContainerEl() {

        return elementUtils.waitForElementToBeVisible(carouselContainerLoc);
    }

    private static WebElement getCardTitleElement(WebElement currentCardEl) {
        return currentCardEl.findElement(targetCardTitleLoc);
    }

    private static WebElement getCardEl(By locator) {

        List<WebElement> elements = elementUtils.waitForElementsToBeVisible(carouselContainerLoc, locator);

        if (elements.isEmpty()) {
            Assert.fail("Card should be present");
        }
        return elements.get(0);
    }

    private static WebElement isLastCardEl(By locator) {

        List<WebElement> elements = elementUtils.waitForElementsToBeVisible(carouselContainerLoc, locator);
        if (elements.isEmpty()) {
            Assert.fail("Card should be present");
        }
        return elements.get(0);
    }


    private static boolean swipeToTargetCard(SwipeHorizontalDirection direction, By targetElementLoc, int maxSwipeTime) {

        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            swipeToHorizontalDirection(swipeHorizontal, direction);
            if (elementUtils.isElementPresent(targetElementLoc)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    private static boolean swipeToTargetCard(SwipeHorizontalDirection direction, String targetTitle, int maxSwipeTime) {

        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
        WebElement currentCardEl;
        WebElement currentCardTitleEl;
        boolean isTargetFound = false;

        for (int swipeCounter = 0; swipeCounter < maxSwipeTime; swipeCounter++) {
            swipeToHorizontalDirection(swipeHorizontal, direction);
            currentCardEl = getCardEl(currentCardLoc);
            currentCardTitleEl = getCardTitleElement(currentCardEl);
            if (elementUtils.isTextDisplayedCorrect(currentCardTitleEl, targetTitle)) {
                isTargetFound = true;
                break;
            }
        }
        return isTargetFound;
    }

    public static boolean isFirstCard() {
        boolean isFirstCard = false;
        boolean isElementPresent = elementUtils.isElementPresent(firstCardLoc);
        if (isElementPresent) {
            isFirstCard = true;
        } else {
            boolean isFoundTarget = swipeRightToTargetCard(firstCardLoc, 5);
            if (isFoundTarget) {
                isFirstCard = true;
            }
        }
        System.out.println(isFirstCard);

        return isFirstCard;
    }
}
