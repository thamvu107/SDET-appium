package pageObjects.commponents;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.screens.SwipeScreen;
import pageObjects.screens.login.LoginScreen;
import utils.gestures.MobileInteractions;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class BottomNavComponent {
    private final static By homeNavLoc = accessibilityId("Home");
    private final static By webViewNavLoc = accessibilityId("Webview");
    private final static By loginNavLoc = accessibilityId("Login");
    private final static By formsNavLoc = accessibilityId("Forms");
    private final static By swipeNavLoc = accessibilityId("Swipe");
    private final AppiumDriver driver;

    private final MobileInteractions mobileInteractions;

    public BottomNavComponent(AppiumDriver driver) {

        this.driver = driver;
        this.mobileInteractions = new MobileInteractions(this.driver);
    }

    public WebElement homeNavEl() {

        return mobileInteractions.waitElementLocatedAndFindElement(homeNavLoc);
    }

    public WebElement webNavEl() {

        return mobileInteractions.waitElementLocatedAndFindElement(webViewNavLoc);
    }

    public WebElement loginNavEl() {

        return mobileInteractions.waitElementLocatedAndFindElement(loginNavLoc);
    }

    public WebElement formsNavEl() {
        return mobileInteractions.waitElementLocatedAndFindElement(formsNavLoc);
    }

    public WebElement swipeNavEl() {

        return mobileInteractions.waitElementLocatedAndFindElement(swipeNavLoc);
    }

    public LoginScreen clickOnLoginNav() {

        loginNavEl().click();

        return new LoginScreen(driver);
    }

    public void clickOnFormsNav() {
        formsNavEl().click();

    }

    public SwipeScreen clickOnSwipeNav() {
        swipeNavEl().click();
        return new SwipeScreen(driver);
    }
}
