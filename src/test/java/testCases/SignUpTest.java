package testCases;

import dataProvider.signUp.SignUpCredData;
import entity.SignUpCred;
import helpers.AlertHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testFlows.SignUpFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpConstants.*;

public class SignUpTest extends BaseTest {

    private SignUpFlow signUpFlow;
    private AlertHelper alertHelper;


    @BeforeClass
    public void beforeClass() {
        signUpFlow = new SignUpFlow(driver);
        signUpFlow.gotoLoginScreen();
        alertHelper = new AlertHelper(driver);
    }

    @BeforeMethod
    public void beforeMethod() {
        signUpFlow.openSignUpTab();
    }

    @AfterMethod
    public void afterMethod() {
        alertHelper.closeAlertIfPresent();

    }

    @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithCorrectCredentials(SignUpCred signupCred) {
        signUpFlow.signUpWithCred(signupCred)
                .switchToSignUpAlert()
                .verifyAlertPresent(SIGN_UP_DIALOG_TITLE, SIGN_UP_DIALOG_MESSAGE);
    }

    @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithInvalidUser(SignUpCred signUpCred) {
        signUpFlow.signUpWithCred(signUpCred)
                .verifyInvalidEmailMessage(INVALID_EMAIL_MESSAGE)
                .verifyInvalidPasswordMessage(INVALID_PASSWORD_MESSAGE)
                .verifyInvalidRepeatPasswordMessage(INCORRECT_REPEAT_PASSWORD_MESSAGE);
    }

    @Test(dataProvider = "signUpCredInvalidRepeatPassword", dataProviderClass = SignUpCredData.class)
    public void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {
        signUpFlow.signUpWithCred(signUpCred)
                .verifyInvalidRepeatPasswordMessage(INCORRECT_REPEAT_PASSWORD_MESSAGE);
    }
}
