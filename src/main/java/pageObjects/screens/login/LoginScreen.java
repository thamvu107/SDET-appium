package pageObjects.screens.login;

import enums.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.screens.BaseScreen;
import pageObjects.screens.alert.SignInAlertScreen;
import pageObjects.screens.alert.SignUpAlertScreen;

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
    protected final By signInTabLoc = accessibilityId("button-login-container"); // android + ios
    protected final By signUpTabLoc = accessibilityId("button-sign-up-container"); // android + ios
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
        verifyScreenLoaded(loginScreenLoc);
    }

    protected By invalidEmailLabelLoc = elementUtils.getLocator(invalidEmailLabelLocatorMap);
    protected By invalidPasswordLabelLoc = elementUtils.getLocator(invalidPasswordLabelLocatorMap);

    protected WebElement loginScreenElement() {

        return driver.findElement(loginScreenLoc);
    }

    protected WebElement loginTabElement() {

        return elementUtils.waitForElementTobeClickable(loginTabLoc);
    }

    protected WebElement signInTabEl() {

        return elementUtils.waitForElementTobeClickable(signInTabLoc);
    }

    protected WebElement signUpTabEl() {

        return elementUtils.waitForElementTobeClickable(signUpTabLoc);
    }

    protected WebElement emailFieldEl() {

        return elementUtils.waitForElementTobeClickable(emailInputLoc);
    }

    protected WebElement passwordFieldEl() {

        return elementUtils.waitForElementTobeClickable(passwordInputLoc);
    }

    protected WebElement invalidEmailLabelEl() {

        return elementUtils.waitForElementTobeClickable(invalidEmailLabelLoc);
    }

    protected WebElement invalidPasswordLabelEl() {

        return elementUtils.waitForFindingElement(invalidPasswordLabelLoc);
    }

    public LoginScreen openLoginScreen() {

        bottomNavComponent.clickOnLoginNav();

        return this;
    }

    public SignInScreen openSignInForm() {

        signInTabEl().click();

        return new SignInScreen(driver);
    }


    public SignUpScreen openSingUpForm() {

        signUpTabEl().click();

        return new SignUpScreen(driver);
    }

    public LoginScreen inputEmail(String email) {
        elementUtils.inputText(emailFieldEl(), email);

        return this;
    }

    public LoginScreen inputPassword(String password) {
        elementUtils.inputText(passwordFieldEl(), password);

        return this;
    }

    public SignUpAlertScreen switchToSignUpAlert() {

        return new SignUpAlertScreen(driver);
    }


    public SignInAlertScreen switchToSignInAlert() {

        return new SignInAlertScreen(driver);
    }

    public String getInvalidEmailMessage() {

        return invalidEmailLabelEl().getText();
    }

    public String getInvalidPasswordMessage() {

        return invalidPasswordLabelEl().getText();
    }
}
