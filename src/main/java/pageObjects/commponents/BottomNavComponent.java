package pageObjects.commponents;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.screens.SwipeScreen;
import pageObjects.screens.login.LoginScreen;
import utils.ElementUtils;
import utils.InteractionUtils;

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

    public BottomNavComponent(AppiumDriver driver) {

        this.driver = driver;
        this.elementUtils = new ElementUtils(this.driver);
        this.mobileInteractions = new InteractionUtils(this.driver);
    }

    public WebElement homeNavEl() {

        return elementUtils.waitForFindingElement(homeNavLoc);
    }

    public WebElement webNavEl() {

        return elementUtils.waitForFindingElement(webViewNavLoc);
    }

    public WebElement loginNavEl() {

        return elementUtils.waitForFindingElement(loginNavLoc);
    }

    public WebElement formsNavEl() {

        return elementUtils.waitForFindingElement(formsNavLoc);
    }

    public WebElement swipeNavEl() {

        return elementUtils.waitForFindingElement(swipeNavLoc);
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
