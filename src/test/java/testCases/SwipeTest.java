package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.screens.SwipeScreen;

import static constants.SwipeScreenConstants.SWIPE_MULTI_TIME;
import static constants.SwipeScreenConstants.SWIPE_SCREEN_TITLE;


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
    public void swipe5Time() {
        swipeScreen.swipeLeftCarouselMultiTime(SWIPE_MULTI_TIME);
    }

}
