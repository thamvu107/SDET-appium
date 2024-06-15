package testCases;

import base.BaseTest;
import dataProvider.signIn.LoginCredData;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.LoginCred;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import testFlows.SignInFlow;
import utils.AlertHelper;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_DIALOG_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_DIALOG_TITLE;
import static devices.MobileFactory.getEmulator;

public class SignInTest extends BaseTest {
    private SignInFlow signInFlow;
    private AlertHelper alertHelper;

    @BeforeClass
    public void beforeClass() {
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        homeScreen = new HomeScreen(driver);
        homeScreen.verifyAppLaunched();

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
