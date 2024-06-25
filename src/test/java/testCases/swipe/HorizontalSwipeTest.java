package testCases.swipe;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;

import static constants.SwipeScreenConstants.*;
import static devices.MobileFactory.getEmulator;

public class HorizontalSwipeTest extends BaseTest {
    private SwipeScreen swipeScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());
            driver = driverProvider.getLocalServerDriver(caps);

            swipeScreen = new HomeScreen(driver).goToSwipeScreen();
        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        swipeScreen.goToSwipeScreen();
    }

    @AfterMethod
    public void afterMethod() {
        Assert.assertTrue(swipeScreen.goToTheFirstCard(MAX_SWIPES));
    }

    @Test
    public void swipeLeftToTargetCard() {
        boolean isFoundTarget = swipeScreen.swipeLeftToCardTitle(TARGET_CARD_TITLE_SWIPE_LEFT, MAX_SWIPES);

        Assert.assertTrue(isFoundTarget, "This is not target card");
    }

    @Test
    public void swipeRightToTargetCard() {
        boolean isFoundTarget = swipeScreen.swipeLeft(MAX_SWIPES)
                .swipeRightToCardTitle(TARGET_CARD_TITLE_SWIPE_RIGHT, MAX_SWIPES);

        Assert.assertTrue(isFoundTarget, "This is not target card");
    }

    @Test
    public void swipeToFirstCard() {
        boolean isFoundTarget = swipeScreen.goToTheFirstCard(MAX_SWIPES);
        Assert.assertTrue(isFoundTarget, "This is not first card");
    }

    @Test
    public void swipeToLastCard() {
        boolean isFoundTarget = swipeScreen.goToLastCard(MAX_SWIPES);
        Assert.assertTrue(isFoundTarget, "This is not last card");
    }
}