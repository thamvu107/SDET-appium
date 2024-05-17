package practice;

import models.screens.login.LoginScreen;
import models.screens.login.SignUpScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static constants.Screens.SignUpConstants.*;

public class SignUpTest extends BaseTest {

    private SignUpScreen signUpScreen;

    @BeforeClass
    public void beforeClass() {
        new LoginScreen(driver)
                .clickOnLoginNav()
                .verifyLoginScreenDisplayed();
    }

    @BeforeMethod
    public void beforeMethod() {

        signUpScreen = new SignUpScreen(driver)
                .clickOnSingUpTab()
                .verifySignUpFormDisplayed();
    }

    @Test
    public void signUpWithCorrectCredentials() {
        signUpScreen
                .inputEmail(SIGN_UP_EMAIL)
                .inputPassword(SIGN_UP_PASSWORD)
                .inputRepeatPassword(SIGN_UP_PASSWORD)
                .clickOnSignUpButton()
                .switchToSignUpAlert()
                .verifyAlertPresent(SIGN_UP_DIALOG_TITLE, SIGN_UP_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }

    @Test
    void signUpWithInvalidRepeatPassWord() {
        signUpScreen
                .inputEmail(SIGN_UP_EMAIL)
                .inputPassword(SIGN_UP_PASSWORD)
                .inputRepeatPassword(INCORRECT_REPEAT_PASSWORD)
                .clickOnSignUpButton()
                .verifyInvalidRepeatPasswordMessage(INCORRECT_REPEAT_PASSWORD_MESSAGE);
    }
}
