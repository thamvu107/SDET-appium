package testCases.swipe;

import base.BaseTest;
import annotations.author.Author;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.screens.HomeScreen;
import screens.screens.SwipeScreen;

import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class VerticalSwipeTest extends BaseTest {
    private SwipeScreen swipeScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        setLogParams(caps);

        swipeScreen = new HomeScreen(driver)
                .goToSwipeScreen();
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
