package testCases.authen;

import base.BaseTest;
import customAnnotations.author.Author;
import dataProvider.signUp.SignUpCredData;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.authen.SignUpCred;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.alert.SignUpAlertScreen;
import pageObjects.screens.login.LoginScreen;
import pageObjects.screens.login.SignUpScreen;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.*;
import static devices.MobileFactory.getSimulator;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignUpTest extends BaseTest {

    private LoginScreen loginScreen;
    private SignUpScreen signUpScreen;
    private SignUpAlertScreen alertScreen;


    @BeforeClass
    public void beforeClass() {
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getSimulator());
//        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        putMDC(caps);
        loginScreen = new HomeScreen(driver).openLoginScreen();
    }

    @BeforeMethod
    public void beforeMethod() {
        signUpScreen = loginScreen.openSingUpForm();
    }

    @AfterMethod
    public void afterMethod() {
        if (alertScreen != null) {
            alertScreen.acceptAlert();
            alertScreen = null;
        }
    }

    @Author(THAM_VU)
    @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithCorrectCredentials(SignUpCred signupCred) {
        alertScreen = signUpScreen.signUpAsValidCred(signupCred);

        Assert.assertTrue(alertScreen.isAlertPresent(), "Alert is not present");
        Assert.assertEquals(alertScreen.getAlertTitle(), SIGN_UP_SUCCESS_TITLE, "Alert title is not correct");
        Assert.assertEquals(alertScreen.getAlertMessage(), SIGN_UP_SUCCESS_MESSAGE, "Alert message is not correct");
    }

    @Author(THAM_VU)
    @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class)
    public void signUpWithInvalidUser(SignUpCred signUpCred) {

        signUpScreen.signUpAsInvalidCred(signUpCred);

        Assert.assertEquals(signUpScreen.getInvalidEmailMessage(), INVALID_EMAIL_MESSAGE, "Invalid email message is not correct");
        Assert.assertEquals(signUpScreen.getInvalidPasswordMessage(), INVALID_PASSWORD_MESSAGE, "Invalid password message is not correct");
        Assert.assertEquals(signUpScreen.getInvalidRepeatPasswordMessage(), INCORRECT_REPEAT_PASSWORD_MESSAGE, "Invalid repeat password message is not correct");
    }

    @Author(THAM_VU)
    @Test(dataProvider = "signUpCredInvalidRepeatPassword", dataProviderClass = SignUpCredData.class)
    public void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {

        signUpScreen.signUpAsInvalidCred(signUpCred);
        Assert.assertEquals(signUpScreen.getInvalidRepeatPasswordMessage(), INCORRECT_REPEAT_PASSWORD_MESSAGE, "Invalid repeat password message is not correct");
    }
}
