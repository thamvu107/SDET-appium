package testCases;

import base.BaseTest;
import dataProvider.signUp.SignUpCredData;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.SignUpCred;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import testFlows.SignUpFlow;
import utils.AlertHelper;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.*;
import static devices.MobileFactory.getEmulator;

public class SignUpTest extends BaseTest {

    private SignUpFlow signUpFlow;
    private AlertHelper alertHelper;


    @BeforeClass
    public void beforeClass() {
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        homeScreen = new HomeScreen(driver);
        homeScreen.verifyAppLaunched();

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
