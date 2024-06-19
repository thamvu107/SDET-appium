package testCases.webView;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.commponents.BottomNavComponent;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.WebViewScreen;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static devices.MobileFactory.getEmulator;

public class WebViewTest extends BaseTest {
    private WebViewScreen webViewScreen;

    @BeforeClass
    public void setupSwipeTestClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());

            driver = driverProvider.getLocalServerDriver(caps);

            bottomNavComponent = new BottomNavComponent(driver);
            homeScreen = new HomeScreen(driver);
            homeScreen.verifyAppLaunched();

        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        webViewScreen = bottomNavComponent.switchToNativeContext(LONG_EXPLICIT_WAIT)
                .clickOnWebViewNav()
                .verifyWebViewScreen()
                .switchToWebViewContext();
    }

    @Test
    public void closeAnnouncementBar() {

        webViewScreen.clickOnAnnouncementCloseButton()
                .invisibleAnnouncementBar();

    }


    @Test
    public void getMenuItems() {
        webViewScreen.verifyMenuItemList();
    }

}
