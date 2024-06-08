package pageObjects.commponents;

import utils.gestures.MobileInteractions;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class BottomNavComponent {
    private final static By homeNavLoc = accessibilityId("Home");
    private final static By webViewNavLoc = accessibilityId("Webview");
    private final static By loginNavLoc = accessibilityId("Login");
    private final static By swipeNavLoc = accessibilityId("Swipe");
    private final AppiumDriver driver;

    private final MobileInteractions mobileInteraction;

    public BottomNavComponent(AppiumDriver driver) {

        this.driver = driver;
        this.mobileInteraction = new MobileInteractions(this.driver);
    }

    public WebElement homeNavEl() {

        return mobileInteraction.waitElementLocatedAndFindElement(homeNavLoc);
    }

    public WebElement webNavEl() {

        return mobileInteraction.waitElementLocatedAndFindElement(webViewNavLoc);
    }

    public WebElement loginNavEl() {

        return mobileInteraction.waitElementLocatedAndFindElement(loginNavLoc);
    }

    public WebElement swipeNavEl() {

        return mobileInteraction.waitElementLocatedAndFindElement(swipeNavLoc);
    }

    public void clickOnLoginNav() {

        loginNavEl().click();
    }
}
