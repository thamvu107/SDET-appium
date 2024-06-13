package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.screens.SwipeScreen;

import static constants.SwipeScreenConstants.*;


public class SwipeTest extends BaseTest {
    private SwipeScreen swipeScreen;

    @BeforeClass
    public void beforeMethod() {
        swipeScreen = new SwipeScreen(driver);
        swipeScreen.goToSwipeScreen()
                .verifySwipeScreenDisplayed()
                .verifySwipeScreenTitle(SWIPE_SCREEN_TITLE)
                .verifyCarouselDisplayed();
    }

    @Test
    public void swipeLeftMultiTimes() {
        swipeScreen.swipeLeft(SWIPE_MULTI_TIME);
    }

    @Test
    public void swipeRightMultiTimes() {

        swipeScreen.swipeRight(SWIPE_MULTI_TIME);
    }

    @Test
    public void swipeLeftToTargetCard() {
        swipeScreen.verifyTheTargetCardIsFound(SWIPE_LEFT_TARGET_CARD_TITLE, MAX_SWIPE_TIMES);
    }


    @Test
    public void swipeRightToTargetCard() {
        swipeScreen.swipeLeftToTargetCard(SWIPE_RIGHT_TARGET_CARD_TITLE, MAX_SWIPE_TIMES);
    }
}
