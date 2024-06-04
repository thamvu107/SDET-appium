package practice;

import constants.SignUpConstants;
import dataProvider.signUp.SignUpCredData;
import entity.SignUpCred;
import models.screens.login.LoginScreen;
import models.screens.login.SignUpScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithCorrectCredentials(SignUpCred signupCred) {
        signUpScreen
                .inputEmail(signupCred.getEmail())
                .inputPassword(signupCred.getPassword())
                .inputRepeatPassword(signupCred.getPassword())
                .clickOnSignUpButton()
                .switchToSignUpAlert()
                .verifyAlertPresent(SignUpConstants.SIGN_UP_DIALOG_TITLE, SignUpConstants.SIGN_UP_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }

    @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithInvalidUser(SignUpCred signUpCred) {
        signUpScreen
                .inputEmail(signUpCred.getEmail())
                .inputPassword(signUpCred.getPassword())
                .inputRepeatPassword(signUpCred.getRepeatPassword())
                .clickOnSignUpButton()
                .verifyInvalidEmailMessage(SignUpConstants.INVALID_EMAIL_MESSAGE)
                .verifyInvalidPasswordMessage(SignUpConstants.INVALID_PASSWORD_MESSAGE)
                .verifyInvalidRepeatPasswordMessage(SignUpConstants.INCORRECT_REPEAT_PASSWORD_MESSAGE);
    }

    @Test(dataProvider = "signUpCredInvalidRepeatPassword", dataProviderClass = SignUpCredData.class)
    public void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {
        signUpScreen
                .inputEmail(signUpCred.getEmail())
                .inputPassword(signUpCred.getPassword())
                .inputRepeatPassword(signUpCred.getRepeatPassword())
                .clickOnSignUpButton()
                .verifyInvalidRepeatPasswordMessage(SignUpConstants.INCORRECT_REPEAT_PASSWORD_MESSAGE);
    }
}
