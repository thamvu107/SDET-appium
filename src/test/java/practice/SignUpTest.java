package practice;

import dataProvider.userData.SignUpCredData;
import entity.SignUpCred;
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

    @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithCorrectCredentials(SignUpCred signupCred) {
        signUpScreen
                .inputEmail(signupCred.getEmail())
                .inputPassword(signupCred.getPassword())
                .inputRepeatPassword(signupCred.getPassword())
                .clickOnSignUpButton()
                .switchToSignUpAlert()
                .verifyAlertPresent(SIGN_UP_DIALOG_TITLE, SIGN_UP_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }

    @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class)
    void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {
        signUpScreen
                .inputEmail(signUpCred.getEmail())
                .inputPassword(signUpCred.getPassword())
                .inputRepeatPassword(signUpCred.getRepeatPassword())
                .clickOnSignUpButton()
                .verifyInvalidRepeatPasswordMessage(INCORRECT_REPEAT_PASSWORD_MESSAGE);
    }
}
