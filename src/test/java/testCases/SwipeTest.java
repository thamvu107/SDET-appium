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

            swipeScreen = homeScreen.goToSwipeScreen();
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
//        swipeScreen.swipeRightToCard(FIRST_CARD_TITLE, MAX_SWIPE_TIMES);
        swipeScreen.goToTheFirstCard(MAX_SWIPE_TIMES, SHORT_EXPLICIT_WAIT);
//        swipeScreen.swipeRight(MAX_SWIPE_TIMES);
    }

    @Test
    public void swipeLeftToTargetCard() {
        swipeScreen.swipeLeftToCardTitle(TARGET_CARD_TITLE_SWIPE_LEFT, MAX_SWIPE_TIMES)
                .verifyCardContent(TARGET_CARD_TITLE_SWIPE_LEFT, TARGET_CARD_DESCRIPTION_SWIPE_LEFT);
    }

    @Test
    public void swipeRightToTargetCard() {
        swipeScreen.swipeLeft(MAX_SWIPE_TIMES)
                .swipeRightToCardTitle(TARGET_CARD_TITLE_SWIPE_RIGHT, MAX_SWIPE_TIMES)
                .verifyCardContent(TARGET_CARD_TITLE_SWIPE_RIGHT, TARGET_CARD_DESCRIPTION_SWIPE_RIGHT);
    }

    @Test
    public void swipeToFirstCard() {
        swipeScreen.goToTheFirstCard(MAX_SWIPE_TIMES, SHORT_EXPLICIT_WAIT)
                .verifyCardContent(FIRST_CARD_TITLE, FIRST_CARD_DESCRIPTION);
    }

    @Test
    public void swipeToLastCard() {
        swipeScreen.goToLastCard(MAX_SWIPE_TIMES, SHORT_EXPLICIT_WAIT)
                .verifyCardContent(LAST_CARD_TITLE, LAST_CARD_DESCRIPTION);
    }
}