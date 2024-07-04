package pageObjects.screens;

import context.ContextSwitching;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.commponents.BottomNavComponent;
import pageObjects.screens.login.LoginScreen;
import pageObjects.screens.webView.WebHomeScreen;
import utils.ElementUtils;
import utils.InteractionUtils;
import utils.PlatformUtil;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;

public abstract class BaseScreen {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AppiumDriver driver;
    //    protected LocatorMapperUtils locatorMapperHelper;
    protected ContextSwitching contextSwitching;


    @Getter
    protected BottomNavComponent bottomNavComponent;

    protected ElementUtils elementUtils;

    protected InteractionUtils interactionUtils;
    protected PlatformType currentPlatform;
    //protected AssertUtils assertUtils;


//    private final By screenWrapper = By.id("com.wdiodemoapp:id/action_bar_root");

    public BaseScreen(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        this.driver = driver;
        this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
        this.contextSwitching = new ContextSwitching(this.driver);
        this.elementUtils = new ElementUtils(driver);
        this.interactionUtils = new InteractionUtils(driver);
        // this.assertUtils = new AssertUtils(driver);
        this.bottomNavComponent = new BottomNavComponent(this.driver);
    }

    public String getCurrentContext() {
        return contextSwitching.getCurrentContext(driver);

    }

    public WebDriver switchToNativeContext() {
        return contextSwitching.switchToNativeContext(LONG_EXPLICIT_WAIT);
    }

    public WebDriver switchToWebViewContext() {
        return contextSwitching.switchToWebViewContext(SHORT_EXPLICIT_WAIT);

    }


    public HomeScreen openHomeScreen() {
        return bottomNavComponent.clickOnHomeNav();
    }


    public LoginScreen openLoginScreen() {

        return bottomNavComponent.clickOnLoginNav();
    }

    public SwipeScreen goToSwipeScreen() {
        return bottomNavComponent.clickOnSwipeNav();
    }

    public WebHomeScreen openWebViewScreen() {

        return bottomNavComponent.clickOnWebViewNav();
    }

    protected void verifyScreenLoaded(By locator) {
        // Waits for the element to be visible and throws an exception if it is not
        WebElement element = elementUtils.waitForElementToBeVisible(locator);
        logger.info("Page is loaded. Element found: " + locator.toString());

        if (element == null) {
            logger.info("Page is not loaded. Element not found: " + locator);
            throw new NoSuchElementException("Page is not loaded. Element not found: " + locator);
        }
    }
}
