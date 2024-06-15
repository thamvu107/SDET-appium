package testCases;

import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.SwipeScreen;

import static constants.SwipeScreenConstants.*;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;

public class SwipeTest extends BaseTest {
    protected SwipeScreen swipeScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        try {
            swipeScreen = homeScreen.goToSwipeScreen();
            //swipeScreen = new SwipeScreen(driver);
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
        swipeScreen.verifySwipeLeftToCardTitle(SWIPE_LEFT_TARGET_CARD_TITLE, MAX_SWIPE_TIMES);
    }

    @Test
    public void swipeRightToTargetCard() {
        swipeScreen.swipeLeft(MAX_SWIPE_TIMES)
                .verifySwipeRightToCardTitle(SWIPE_RIGHT_TARGET_CARD_TITLE, MAX_SWIPE_TIMES);
    }

    @Test
    public void swipeToFirstCard() {
        System.out.println("swipeToFirstCard ----------------");
        swipeScreen.goToTheFirstCard(MAX_SWIPE_TIMES, SHORT_EXPLICIT_WAIT)
                .verifyCardContent(FIRST_CARD_TITLE, FIRST_CARD_DESCRIPTION);
    }

    @Test
    public void swipeToLastCard() {
        swipeScreen.goToLastCard(MAX_SWIPE_TIMES, SHORT_EXPLICIT_WAIT)
                .verifyCardContent(LAST_CARD_TITLE, LAST_CARD_DESCRIPTION);
    }
}