package models.commponents;

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

    public BottomNavComponent(AppiumDriver driver) {

        this.driver = driver;
    }

    public WebElement homeNav() {

        return driver.findElement(homeNavLoc);
    }

    public WebElement webNav() {

        return driver.findElement(webViewNavLoc);
    }

    public WebElement loginNav() {

        return driver.findElement(loginNavLoc);
    }

    public WebElement swipeNav() {

        return driver.findElement(swipeNavLoc);
    }
}
