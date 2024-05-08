package models.screens.login;

import driverFactory.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import models.screens.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ElementLocatorMapper;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

// MAIN INTERACTION METHODS
public class LoginScreen extends BaseScreen {

    // Scope 01: Keep the selector
    protected final By loginScreenLoc = accessibilityId("Login-screen"); // android + ios
    protected final By loginTabLoc = accessibilityId("button-login-container"); // android + ios
    protected final By signupTabLoc = accessibilityId("button-sign-up-container"); // android + ios
    protected final By emailInputLoc = accessibilityId("input-email");
    protected final By passwordInputLoc = accessibilityId("input-password");
    // Android:
    protected final By androidInvalidEmailLabelLocator = xpath("//android.widget.TextView[@text='Please enter a valid email address']");

    // TODO: Separate android locator and ios locator to 2 files.
    protected final By androidInvalidPasswordLabelLocator = xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");
    // IOS:
    protected final By iosInvalidEmailLabelLocator = accessibilityId("Please enter a valid email address");
    protected final By iosInvalidPasswordLabelLocator = accessibilityId("Please enter at least 8 characters");
    private final ElementLocatorMapper elementMapping;
    // Mapping
    private final Map<MobilePlatform, By> invalidEmailLabelLocatorMap = Map.of(
            MobilePlatform.ANDROID, androidInvalidEmailLabelLocator,
            MobilePlatform.IOS, iosInvalidEmailLabelLocator);
    private final Map<MobilePlatform, By> invalidPasswordLabelLocatorMap = Map.of(
            MobilePlatform.ANDROID, androidInvalidPasswordLabelLocator,
            MobilePlatform.IOS, iosInvalidPasswordLabelLocator);


    // Scope 02: Constructor to POM_AdvancedConcept.md the appiumDriver
    public LoginScreen(final AppiumDriver driver) {

        super(driver);
        elementMapping = new ElementLocatorMapper(driver);
    }

    protected WebElement loginScreenElement() {

        return driver.findElement(loginScreenLoc);
    }

    protected WebElement loginTabElement() {

        return driver.findElement(loginTabLoc);
    }

    protected WebElement signupTabElement() {
        return driver.findElement(signupTabLoc);
    }

    protected WebElement emailFieldElement() {
        return driver.findElement(emailInputLoc);
    }

    protected WebElement passwordFieldElement() {

        return driver.findElement(passwordInputLoc);
    }

    protected WebElement invalidEmailLabelElement() {

        return elementMapping.findElement(invalidEmailLabelLocatorMap);
    }

    protected WebElement invalidPasswordLabelElement() {

        return elementMapping.findElement(invalidPasswordLabelLocatorMap);
    }

    public LoginScreen clickOnLoginNav() {

        bottomNavComponent().loginNav().click();

        return this;
    }

    public void displayLoginScreen() {

        Assert.assertTrue(loginScreenElement().isDisplayed());

    }

    protected SignInScreen clickOnSingInTab() {
        loginTabElement().click();

        return new SignInScreen(driver);
    }

    protected LoginScreen displaySignInForm() {

        return this;
    }

    protected LoginScreen clickOnSingUpTab() {
        signupTabElement().click();

        return this;
    }

}
