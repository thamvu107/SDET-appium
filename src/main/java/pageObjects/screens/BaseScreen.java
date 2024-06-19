package pageObjects.screens;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import pageObjects.commponents.BottomNavComponent;
import pageObjects.screens.login.LoginScreen;
import utils.AssertUtils;
import utils.ElementUtils;
import utils.InteractionUtils;
import utils.PlatformUtil;

public class BaseScreen {

    protected AppiumDriver driver;
    //    protected LocatorMapperUtils locatorMapperHelper;
    protected BottomNavComponent bottomNavComponent;

    protected ElementUtils elementUtils;

    protected InteractionUtils interactionUtils;
    protected PlatformUtil platformUtil;
    protected Platform currentPlatform;
    protected AssertUtils assertUtils;


    public BaseScreen(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        this.driver = driver;
        this.platformUtil = new PlatformUtil(this.driver);
        this.currentPlatform = platformUtil.getCurrentPlatform();
        this.elementUtils = new ElementUtils(driver);
        this.interactionUtils = new InteractionUtils(driver);
        this.assertUtils = new AssertUtils(driver);
        this.bottomNavComponent = new BottomNavComponent(this.driver);

    }

    public HomeScreen goToHomeScreen() {
        return bottomNavComponent.clickOnHomeNav();
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

    public WebViewScreen gotToWebViewScreen() {
        return bottomNavComponent.clickOnWebViewNav();
    }
}
