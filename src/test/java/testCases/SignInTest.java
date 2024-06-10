package testCases;

import dataProvider.signIn.LoginCredData;
import entity.LoginCred;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testFlows.SignInFlow;
import utils.AlertHelper;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_DIALOG_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_DIALOG_TITLE;

public class SignInTest extends BaseTest {
    private SignInFlow signInFlow;
    private AlertHelper alertHelper;

    @BeforeClass
    public void beforeClass() {
        signInFlow = new SignInFlow(driver);
        signInFlow.gotoLoginScreen();
        alertHelper = new AlertHelper(driver);
    }

    @BeforeMethod
    public void beforeMethod() {
        signInFlow.openSignInTab();
    }

    @AfterMethod
    public void afterMethod() {
        alertHelper.closeAlertIfPresent();
    }

    @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class)
    public void loginInWithCorrectCredential(LoginCred loginCred) {
        signInFlow.loginWithCred(loginCred)
                .switchToSignInAlert()
                .verifyAlertPresent(SIGN_IN_DIALOG_TITLE, SIGN_IN_DIALOG_MESSAGE);
    }

    @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectCredentials(LoginCred loginCred) {
        signInFlow.loginWithCred(loginCred)
                .verifyInvalidEmailMessage(INVALID_EMAIL_MESSAGE)
                .verifyInvalidPasswordMessage(INVALID_PASSWORD_MESSAGE);
    }

    @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectEmail(LoginCred loginCred) {
        signInFlow.loginWithCred(loginCred)
                .verifyInvalidEmailMessage(INVALID_EMAIL_MESSAGE);
    }


    @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectPassword(LoginCred loginCred) {
        signInFlow.loginWithCred(loginCred)
                .verifyInvalidPasswordMessage(INVALID_PASSWORD_MESSAGE);
    }
}
