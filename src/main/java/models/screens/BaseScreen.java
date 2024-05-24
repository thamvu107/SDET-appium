package models.screens;

import constants.WaitConstant;
import driverFactory.Driver;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import models.commponents.BottomNavComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorMapper;
import utils.MobileInteractions;

import static java.time.Duration.ofMillis;

public class BaseScreen {

    protected final AppiumDriver driver;
    protected WebDriverWait wait;
    protected LocatorMapper locatorMapper;
    protected BottomNavComponent bottomNavComponent;

    protected MobileInteractions mobileInteractions;
    //    protected static String currentPlatform;
    protected static Platform currentPlatform;


    protected BaseScreen(final AppiumDriver driver) {

        this.driver = driver;
        currentPlatform = Driver.getCurrentPlatform(driver);
        this.wait = new WebDriverWait(this.driver, ofMillis(WaitConstant.LONG_EXPLICIT_WAIT));
        this.bottomNavComponent = new BottomNavComponent(this.driver);
        this.mobileInteractions = new MobileInteractions(driver);

    }
}
