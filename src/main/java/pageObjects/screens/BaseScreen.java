package pageObjects.screens;

import constants.WaitConstants;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.commponents.BottomNavComponent;
import pageObjects.screens.login.LoginScreen;
import utils.LocatorMapperUtils;
import utils.PlatformUtil;
import utils.gestures.MobileInteractions;

import static java.time.Duration.ofMillis;

public class BaseScreen {

    protected AppiumDriver driver;
    protected WebDriverWait wait;
    protected LocatorMapperUtils locatorMapperHelper;
    protected BottomNavComponent bottomNavComponent;

    protected MobileInteractions mobileInteractions;
    //    protected static String currentPlatform;
    protected Platform currentPlatform;


    public BaseScreen(AppiumDriver driver) {

        this.driver = driver;
        this.currentPlatform = new PlatformUtil().getCurrentPlatform(this.driver);
        this.wait = new WebDriverWait(this.driver, ofMillis(WaitConstants.LONG_EXPLICIT_WAIT));
        this.bottomNavComponent = new BottomNavComponent(this.driver);
        this.mobileInteractions = new MobileInteractions(driver);

    }


    public LoginScreen goToLoginScreen() {

        return bottomNavComponent.clickOnLoginNav();
    }

//    public FormsScreen goToFormsScreen() {
//
//        return bottomNavComponent.clickOnFormsNav();
//
//    }

    public SwipeScreen goToSwipeScreen() {
        return bottomNavComponent.clickOnSwipeNav();
    }

    protected boolean isTitleCorrect(WebElement element, String expectedTitle) {
        return mobileInteractions.isTextDisplayedCorrect(element, expectedTitle);
    }
}
