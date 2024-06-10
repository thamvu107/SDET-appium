package testCases;

import org.testng.Assert;
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
                .verifySwipeScreenTitle(SWIPE_SCREEN_TITLE);
    }

    @Test
    public void seeCarouselTest() {

        Assert.assertTrue(swipeScreen.isCarouselDisplayed(), "The carousel is not displayed");
    }

    @Test
    public void swipe5Time() {
        swipeScreen.swipeMultiTime(SWIPE_MULTI_TIME);
    }

}
