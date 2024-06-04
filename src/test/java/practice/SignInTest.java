package practice;

import constants.SignInConstants;
import dataProvider.signIn.LoginCredData;
import entity.LoginCred;
import models.screens.login.LoginScreen;
import models.screens.login.SignInScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class)
    public void loginInWithCorrectCredential(LoginCred loginCred) {

        signInScreen
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton()
                .switchToSignInAlert()
                .verifyAlertPresent(SignInConstants.SIGN_IN_DIALOG_TITLE, SignInConstants.SIGN_IN_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }

    @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectCredentials(LoginCred loginCred) {
        signInScreen
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton()
                .verifyInvalidEmailMessage(SignInConstants.INVALID_EMAIL_MESSAGE)
                .verifyInvalidPasswordMessage(SignInConstants.INVALID_PASSWORD_MESSAGE);
    }

    @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectEmail(LoginCred loginCred) {
        signInScreen
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton()
                .verifyInvalidEmailMessage(SignInConstants.INVALID_EMAIL_MESSAGE);
    }


    @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectPassword(LoginCred loginCred) {
        signInScreen
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton()
                .verifyInvalidPasswordMessage(SignInConstants.INVALID_PASSWORD_MESSAGE);

    }

}
