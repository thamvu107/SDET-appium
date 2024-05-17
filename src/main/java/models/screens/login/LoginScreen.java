package models.screens.login;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import models.screens.BaseScreen;
import models.screens.alert.SignInAlertScreen;
import models.screens.alert.SignUpAlertScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

// MAIN INTERACTION METHODS
public class LoginScreen extends BaseScreen {
    // TODO: Separate android locator and ios locator to 2 files.

    // Scope 01: Keep the selector
    // Android:
    protected final By loginScreenLoc = accessibilityId("Login-screen"); // android + ios
    protected final By loginTabLoc = accessibilityId("button-login-container"); // android + ios
    protected final By signupTabLoc = accessibilityId("button-sign-up-container"); // android + ios
    protected final By emailInputLoc = accessibilityId("input-email");
    protected final By passwordInputLoc = accessibilityId("input-password");
    protected final By androidInvalidEmailLabelLocator = xpath("//android.widget.TextView[@text='Please enter a valid email address']");
    protected final By androidInvalidPasswordLabelLocator = xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");

    // IOS:
    protected final By iosInvalidEmailLabelLocator = accessibilityId("Please enter a valid email address");
    protected final By iosInvalidPasswordLabelLocator = accessibilityId("Please enter at least 8 characters");

    // Mapping
    private final Map<Platform, By> invalidEmailLabelLocatorMap = Map.of(
            Platform.ANDROID, androidInvalidEmailLabelLocator,
            Platform.IOS, iosInvalidEmailLabelLocator);
    private final Map<Platform, By> invalidPasswordLabelLocatorMap = Map.of(
            Platform.ANDROID, androidInvalidPasswordLabelLocator,
            Platform.IOS, iosInvalidPasswordLabelLocator);


    // Scope 02: Constructor to POM_AdvancedConcept.md the appiumDriver
    public LoginScreen(final AppiumDriver driver) {

        super(driver);
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

        return driver.findElement(mobileActions.getLocatorIsMappedCurrentPlatform(invalidEmailLabelLocatorMap));
    }

    protected WebElement invalidPasswordLabelElement() {


        return driver.findElement(mobileActions.getLocatorIsMappedCurrentPlatform(invalidPasswordLabelLocatorMap));
    }

    public LoginScreen clickOnLoginNav() {

        bottomNavComponent.clickOnLoginNav();

        return this;
    }

    public LoginScreen verifyLoginScreenDisplayed() {

        mobileActions.waitUntilVisibilityOfElementLocated(loginScreenLoc);

        return this;
    }

    public SignInScreen clickOnSingInTab() {

        loginTabElement().click();

        return new SignInScreen(driver);
    }


    public SignUpScreen clickOnSingUpTab() {

        signupTabElement().click();

        return new SignUpScreen(driver);
    }

    public SignUpAlertScreen switchToSignUpAlert() {

        return new SignUpAlertScreen(driver);
    }


    public SignInAlertScreen switchToSignInAlert() {

        return new SignInAlertScreen(driver);
    }
}
