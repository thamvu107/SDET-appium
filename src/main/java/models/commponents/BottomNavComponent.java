package models.commponents;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.MobileActions;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class BottomNavComponent {
    private final static By homeNavLoc = accessibilityId("Home");
    private final static By webViewNavLoc = accessibilityId("Webview");
    private final static By loginNavLoc = accessibilityId("Login");
    private final static By swipeNavLoc = accessibilityId("Swipe");
    private final AppiumDriver driver;

    private final MobileActions mobileActions;

    public BottomNavComponent(AppiumDriver driver) {

        this.driver = driver;
        this.mobileActions = new MobileActions(this.driver);
    }

    public WebElement homeNavEl() {

        return mobileActions.waitElementLocatedAndFindElement(homeNavLoc);
    }

    public WebElement webNavEl() {

        return mobileActions.waitElementLocatedAndFindElement(webViewNavLoc);
    }

    public WebElement loginNavEl() {

        return mobileActions.waitElementLocatedAndFindElement(loginNavLoc);
    }

    public WebElement swipeNavEl() {

        return mobileActions.waitElementLocatedAndFindElement(swipeNavLoc);
    }

    public void clickOnLoginNav() {

        loginNavEl().click();
    }
}
