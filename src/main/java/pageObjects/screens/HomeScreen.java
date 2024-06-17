package pageObjects.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;

// INTRODUCING MAIN INTERACTION METHODS
public class HomeScreen extends BaseScreen {

    private final By homeScreenLoc = AppiumBy.accessibilityId("Home-screen");

    public HomeScreen(final AppiumDriver driver) {

        super(driver);
    }

    private WebElement homeScreenEl() {
        return elementUtils.waitForElementToBeVisible(homeScreenLoc, LONG_EXPLICIT_WAIT);
    }

    public boolean verifyAppLaunched() {
        return elementUtils.isElementDisplayed(homeScreenEl());
    }

    private WebElement homeScreenEl(long durationInMillis) {
        return elementUtils.waitForElementToBeVisible(homeScreenLoc, durationInMillis);
    }

    public boolean verifyAppLaunched(long durationInMillis) {
        return elementUtils.isElementDisplayed(homeScreenEl(durationInMillis));
    }

}
