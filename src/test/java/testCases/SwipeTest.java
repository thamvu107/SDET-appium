package testCases;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;

import static constants.SwipeScreenConstants.*;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;
import static devices.MobileFactory.getEmulator;

public class SwipeTest extends BaseTest {
    protected SwipeScreen swipeScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());
            driver = driverProvider.getLocalServerDriver(caps);
            homeScreen = new HomeScreen(driver);
            homeScreen.verifyAppLaunched();
            swipeScreen = new SwipeScreen(driver);
        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        swipeScreen.goToSwipeScreen()
                .verifyCarouselDisplayed();
    }

    @AfterMethod
    public void afterMethod() {
        swipeScreen.swipeRightToCard(FIRST_CARD_TITLE, MAX_SWIPE_TIMES);
//        swipeScreen.goToTheFirstCard(MAX_SWIPE_TIMES);
//        swipeScreen.swipeRight(MAX_SWIPE_TIMES);
    }

    @Test
    public void swipeLeftToTargetCard() {
        swipeScreen.verifySwipeLeftToCardTitle(SWIPE_LEFT_TARGET_CARD_TITLE, MAX_SWIPE_TIMES);
    }

    @Test
    public void swipeRightToTargetCard() {
        swipeScreen.swipeLeft(MAX_SWIPE_TIMES)
                .verifySwipeRightToCardTitle(SWIPE_RIGHT_TARGET_CARD_TITLE, MAX_SWIPE_TIMES);
    }

    @Test
    public void swipeToFirstCard() {
        swipeScreen.goToTheFirstCard(MAX_SWIPE_TIMES)
                .verifyFirsCard(FIRST_CARD_TITLE, FIRST_CARD_DESCRIPTION);
    }

    @Test
    public void swipeToLastCard() {
        swipeScreen.goToLastCard(MAX_SWIPE_TIMES, SHORT_EXPLICIT_WAIT)
                .verifyLastCard(LAST_CARD_TITLE, "");
    }
}