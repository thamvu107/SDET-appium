package testCases.swipe;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;

import static devices.MobileFactory.getEmulator;

public class VerticalSwipeTest extends BaseTest {
    private SwipeScreen swipeScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());
            driver = driverProvider.getLocalServerDriver(caps);
            homeScreen = new HomeScreen(driver);
            homeScreen.verifyAppLaunched(30_000L);

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

    @Test
    public void swipeUp() {
        swipeScreen.verifyScrollWrapperIsDisplayed()
                .scrollToWebDriverIOLogo()
                .verifyScrollUpTargetFound();
    }

    @Test
    public void swipeDown() {
        swipeScreen.verifyScrollWrapperIsDisplayed()
                .scrollToScreenTitle()
                .verifyScrollDownTargetFound();
    }
}
