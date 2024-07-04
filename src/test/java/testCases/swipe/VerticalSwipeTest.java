package testCases.swipe;

import base.BaseTest;
import customAnnotations.author.Author;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;

import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

public class VerticalSwipeTest extends BaseTest {
    private SwipeScreen swipeScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());
            driver = driverProvider.getLocalServerDriver(caps);
            putMDC(caps);

            swipeScreen = new HomeScreen(driver)
                    .goToSwipeScreen();
        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @Author(THAM_VU)
    @Test
    public void swipeUpAndDown() {
        boolean isFoundSwipeUpTarget = swipeScreen.scrollToWebDriverIOLogo();
        Assert.assertTrue(isFoundSwipeUpTarget, "Can't find WebDriverIO logo");

        boolean isFoundSwipeDownTarget = swipeScreen.scrollToScreenTitle();
        Assert.assertTrue(isFoundSwipeDownTarget, "Can't find screen title");
    }
}
