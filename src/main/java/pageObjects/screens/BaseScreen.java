package pageObjects.screens;

import Utils.LocatorMapperUtils;
import Utils.PlatformUtil;
import Utils.gestures.MobileInteractions;
import constants.WaitConstants;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.commponents.BottomNavComponent;

import static java.time.Duration.ofMillis;

public class BaseScreen {

    protected final AppiumDriver driver;
    protected WebDriverWait wait;
    protected LocatorMapperUtils locatorMapperHelper;
    protected BottomNavComponent bottomNavComponent;

    protected MobileInteractions mobileInteractionHelper;
    //    protected static String currentPlatform;
    protected Platform currentPlatform;


    protected BaseScreen(final AppiumDriver driver) {

        this.driver = driver;
        this.currentPlatform = new PlatformUtil().getCurrentPlatform(this.driver);
        this.wait = new WebDriverWait(this.driver, ofMillis(WaitConstants.LONG_EXPLICIT_WAIT));
        this.bottomNavComponent = new BottomNavComponent(this.driver);
        this.mobileInteractionHelper = new MobileInteractions(driver);

    }
}
