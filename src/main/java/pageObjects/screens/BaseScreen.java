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
    protected Platform currentPlatform;
    protected AssertUtils assertUtils;


    public BaseScreen(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        this.driver = driver;
        this.currentPlatform = new PlatformUtil().getCurrentPlatform(this.driver);
        this.elementUtils = new ElementUtils(driver);
        this.interactionUtils = new InteractionUtils(driver);
        this.assertUtils = new AssertUtils(driver);
        this.bottomNavComponent = new BottomNavComponent(this.driver);

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

}
