package pageObjects.screens;

import constants.WaitConstants;
import driverFactory.Platform;
import helpers.LocatorMapperHelper;
import helpers.MobileInteractionHelper;
import helpers.PlatformHelper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.commponents.BottomNavComponent;

import static java.time.Duration.ofMillis;

public class BaseScreen {

    protected final AppiumDriver driver;
    protected WebDriverWait wait;
    protected LocatorMapperHelper locatorMapperHelper;
    protected BottomNavComponent bottomNavComponent;

    protected MobileInteractionHelper mobileInteractionHelper;
    //    protected static String currentPlatform;
    protected Platform currentPlatform;


    protected BaseScreen(final AppiumDriver driver) {

        this.driver = driver;
        this.currentPlatform = new PlatformHelper().getCurrentPlatform(this.driver);
        this.wait = new WebDriverWait(this.driver, ofMillis(WaitConstants.LONG_EXPLICIT_WAIT));
        this.bottomNavComponent = new BottomNavComponent(this.driver);
        this.mobileInteractionHelper = new MobileInteractionHelper(driver);

    }
}
