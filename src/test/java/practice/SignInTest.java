package practice;

import dataProvider.userData.UserCredData;
import entity.User;
import models.screens.login.LoginScreen;
import models.screens.login.SignInScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static constants.Screens.SignInConstants.*;

public class SignInTest extends BaseTest {

    private SignInScreen signInScreen;

    @BeforeClass
    public void beforeClass() {

        new LoginScreen(driver)
                .clickOnLoginNav()
                .verifyLoginScreenDisplayed();
    }


    @BeforeMethod
    public void beforeMethod() {

        signInScreen = new SignInScreen(driver)
                .clickOnSingInTab()
                .verifySignInFormDisplayed();
    }

    @Test(dataProvider = "loginCredValidData", dataProviderClass = UserCredData.class)
    public void loginInWithCorrectCredential(User loginCred) {

        signInScreen
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton()
                .switchToSignInAlert()
                .verifyAlertPresent(SIGN_IN_DIALOG_TITLE, SIGN_IN_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }

    @Test
    public void loginInWithIncorrectCredentials() {
        signInScreen
                .inputEmail(INVALID_EMAIL)
                .inputPassword(INVALID_PASSWORD)
                .clickOnLoginButton()
                .verifyInvalidEmailMessage(INVALID_EMAIL_MESSAGE)
                .verifyInvalidPasswordMessage(INVALID_PASSWORD_MESSAGE);
    }

}
