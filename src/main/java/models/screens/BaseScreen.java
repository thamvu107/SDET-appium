package models.screens;

import constants.WaitConstants;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import models.commponents.BottomNavComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorMapper;
import utils.MobileInteractions;
import utils.PlatformUtil;

import static java.time.Duration.ofMillis;

public class BaseScreen {

    protected final AppiumDriver driver;
    protected WebDriverWait wait;
    protected LocatorMapper locatorMapper;
    protected BottomNavComponent bottomNavComponent;

    protected MobileInteractions mobileInteractions;
    //    protected static String currentPlatform;
    protected Platform currentPlatform;


    protected BaseScreen(final AppiumDriver driver) {

        this.driver = driver;
        this.currentPlatform = new PlatformUtil().getCurrentPlatform(this.driver);
        this.wait = new WebDriverWait(this.driver, ofMillis(WaitConstants.LONG_EXPLICIT_WAIT));
        this.bottomNavComponent = new BottomNavComponent(this.driver);
        this.mobileInteractions = new MobileInteractions(driver);

    }
}
