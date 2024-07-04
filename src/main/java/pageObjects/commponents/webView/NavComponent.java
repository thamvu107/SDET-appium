package pageObjects.commponents.webView;

import customAnnotations.selectors.ComponentXpathSelector;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.SwipeScreen;
import pageObjects.screens.login.LoginScreen;
import pageObjects.screens.webView.WebHomeScreen;
import utils.ElementUtils;
import utils.InteractionUtils;
import utils.PlatformUtil;

import static io.appium.java_client.AppiumBy.accessibilityId;


@ComponentXpathSelector(value = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View")
public class NavComponent {
    private final AppiumDriver driver;
    private final static By homeNavLoc = accessibilityId("Home");
    private final static By webViewNavLoc = accessibilityId("Webview");
    private final static By loginNavLoc = accessibilityId("Login");
    private final static By formsNavLoc = accessibilityId("Forms");
    private final static By swipeNavLoc = accessibilityId("Swipe");

    private final PlatformType currentPlatform;
    //
    private final ElementUtils elementUtils;

    private final InteractionUtils mobileInteractions;


    public NavComponent(AppiumDriver driver) {

        this.driver = driver;
        this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
        this.elementUtils = new ElementUtils(this.driver);
        this.mobileInteractions = new InteractionUtils(this.driver);
        ensureBottomNavLoaded(homeNavLoc);

    }

    protected void ensureBottomNavLoaded(By locator) {
        // Waits for the element to be visible and throws an exception if it is not
        WebElement element = elementUtils.waitForElementToBeVisible(locator);

        if (element == null) {
            throw new IllegalStateException("Bottom nav is not loaded");
        }
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

    public WebHomeScreen clickOnWebViewNav() {
        webViewNavEl().click();

        return new WebHomeScreen(driver);
    }
}
