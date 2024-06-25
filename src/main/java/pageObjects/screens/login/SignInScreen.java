package pageObjects.screens.login;

import entity.authen.LoginCred;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.screens.alert.SignInAlertScreen;

public class SignInScreen extends LoginScreen {


    private final By signInButtonLocator = AppiumBy.accessibilityId("button-LOGIN");

    public SignInScreen(AppiumDriver driver) {
        super(driver);
        verifyScreenLoaded(signInButtonLocator);
    }


    public SignInScreen verifySignInFormDisplayed() {

        elementUtils.waitForFindingElement(signInButtonLocator);
        elementUtils.waitForFindingElement(emailInputLoc);
        elementUtils.waitForFindingElement(passwordInputLoc);

        return this;
    }

    private SignInScreen submitSignIn() {

        WebElement signInBtnEl = elementUtils.waitForFindingElement(signInButtonLocator);
        signInBtnEl.click();

        return this;
    }

    public SignInAlertScreen submitSignInSuccess() {
        submitSignIn();

        return new SignInAlertScreen(driver);
    }

    public SignInScreen inputSignInCredentials(LoginCred loginCred) {

        this.inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword());

        return this;
    }

    public SignInAlertScreen signInAsValidCred(LoginCred loginCred) {
        return this.inputSignInCredentials(loginCred)
                .submitSignInSuccess();
    }


    public SignInScreen signInAsInvalidCred(LoginCred loginCred) {
        return this.inputSignInCredentials(loginCred)
                .submitSignIn();
    }

}
