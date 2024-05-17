package models.screens.login;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import models.screens.alert.AlertScreen;
import models.screens.alert.SignUpAlertScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

public class SignUpScreen extends LoginScreen {
//    private AppiumDriver driver;

    private final By repeatPasswordLocator = accessibilityId("input-repeat-password");
    private final By signUpButtonLocator = accessibilityId("button-SIGN UP");
    private final By androidInvalidRepeatPasswordLocator = xpath("//android.widget.TextView[@text=\"Please enter the same password\"]");
    private final By iosInvalidRepeatPasswordLocator = accessibilityId("Please enter the same password");

    public SignUpScreen(AppiumDriver driver) {
        super(driver);
    }

    private final Map<Platform, By> invalidRepeatPasswordLocatorMap = Map.of(
            Platform.ANDROID, androidInvalidRepeatPasswordLocator,
            Platform.IOS, iosInvalidRepeatPasswordLocator
    );

    private WebElement repeatPasswordElement() {

        return driver.findElement(repeatPasswordLocator);
    }

    private WebElement signUpButtonElement() {

        return driver.findElement(signUpButtonLocator);
    }

    private WebElement invalidRepeatPasswordElement() {

        By locator = mobileActions.getLocatorIsMappedCurrentPlatform(invalidRepeatPasswordLocatorMap);

        return mobileActions.waitElementLocatedAndFindElement(locator);
    }

    public SignUpScreen verifySignUpFormDisplayed() {

        mobileActions.waitUntilVisibilityOfElementLocated(signUpButtonLocator);
        mobileActions.verifyElementDisplayed(emailInputLoc);
        mobileActions.verifyElementDisplayed(passwordInputLoc);
        mobileActions.verifyElementDisplayed(repeatPasswordLocator);

        return this;
    }

    public SignUpScreen inputEmail(String email) {

        mobileActions.setText(emailFieldElement(), email);

        return this;
    }

    public SignUpScreen inputPassword(String password) {

        mobileActions.setText(passwordFieldElement(), password);

        return this;
    }

    public SignUpScreen inputRepeatPassword(String repeatPassword) {

        mobileActions.setText(repeatPasswordElement(), repeatPassword);

        return this;
    }

    public SignUpScreen clickOnSignUpButton() {

        // TODO: On smaller screens there could be a possibility that the button is not shown
        signUpButtonElement().click();

        return this;
    }

    public void verifyInvalidRepeatPasswordMessage(String expectMessage) {

        String actualMessage = invalidRepeatPasswordElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

    }

    public AlertScreen SwitchToAlert() {
        return new SignUpAlertScreen(driver);
    }

}
