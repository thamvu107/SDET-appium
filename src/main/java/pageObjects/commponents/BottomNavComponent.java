package pageObjects.commponents;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;
import pageObjects.screens.WebViewScreen;
import pageObjects.screens.login.LoginScreen;
import utils.ElementUtils;
import utils.InteractionUtils;
import utils.PlatformUtil;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class BottomNavComponent {
    private final static By homeNavLoc = accessibilityId("Home");
    private final static By webViewNavLoc = accessibilityId("Webview");
    private final static By loginNavLoc = accessibilityId("Login");
    private final static By formsNavLoc = accessibilityId("Forms");
    private final static By swipeNavLoc = accessibilityId("Swipe");
    private final AppiumDriver driver;
    private final ElementUtils elementUtils;

    private final InteractionUtils mobileInteractions;

    private final PlatformUtil platformUtil;

    public BottomNavComponent(AppiumDriver driver) {

        this.driver = driver;
        this.elementUtils = new ElementUtils(this.driver);
        this.mobileInteractions = new InteractionUtils(this.driver);
        this.platformUtil = new PlatformUtil(this.driver);
    }

    public BottomNavComponent switchToNativeContext(long timeout) {
        platformUtil.switchToNativeContext(platformUtil.getCurrentPlatform(), timeout);

        return this;
    }

    public BottomNavComponent switchToWebViewContext(long timeout) {
        platformUtil.switchToWebViewContext(platformUtil.getCurrentPlatform(), timeout);

        return this;
    }

    public WebElement homeNavEl() {

        return elementUtils.waitForFindingElement(homeNavLoc);
    }

    private WebElement homeNavEl(long durationInMillis) {
        return elementUtils.waitForElementTobeClickable(homeNavLoc, durationInMillis);
    }

    public WebElement webNavEl() {

        return elementUtils.waitForElementTobeClickable(webViewNavLoc);
    }

    public WebElement loginNavEl() {

        return elementUtils.waitForElementTobeClickable(loginNavLoc);
    }

    public WebElement formsNavEl() {

        return elementUtils.waitForElementTobeClickable(formsNavLoc);
    }

    public WebElement swipeNavEl() {

        return elementUtils.waitForElementTobeClickable(swipeNavLoc);
    }

    public WebElement webViewNavEl() {

        return elementUtils.waitForElementTobeClickable(webViewNavLoc);
    }

    public LoginScreen clickOnLoginNav() {
        loginNavEl().click();

        return new LoginScreen(driver);
    }

    public HomeScreen clickOnHomeNav() {
        homeNavEl().click();

        return new HomeScreen(driver);
    }

    public SwipeScreen clickOnSwipeNav() {
        swipeNavEl().click();

        return new SwipeScreen(driver);
    }

    public WebViewScreen clickOnWebViewNav() {
        webViewNavEl().click();

        return new WebViewScreen(driver);
    }

}
