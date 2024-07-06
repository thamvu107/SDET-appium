package testCases.authen;

import base.BaseTest;
import annotations.author.Author;
import dataProvider.signIn.LoginCredData;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.authen.LoginCred;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.screens.HomeScreen;
import screens.screens.alert.SignInAlertScreen;
import screens.screens.login.LoginScreen;
import screens.screens.login.SignInScreen;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

public class SignInTest extends BaseTest {
    private LoginScreen loginScreen;
    private SignInScreen signInScreens;
    private SignInAlertScreen alertScreen;

    @BeforeClass
    public void beforeClass() {
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
//        Capabilities caps = CapabilityFactory.getCaps(getSimulator());
        driver = driverProvider.getLocalServerDriver(caps);
        setLogParams(caps);
        loginScreen = new HomeScreen(driver).openLoginScreen();
    }


    @BeforeMethod
    public void beforeMethod() {
        signInScreens = loginScreen.openSignInForm();
    }

    @AfterMethod
    public void afterMethod() {
        if (alertScreen != null) {
            alertScreen.acceptAlert();
            alertScreen = null;
        }
    }

    @Author(THAM_VU)
    @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class)
    public void loginInWithCorrectCredential(LoginCred loginCred) {

        alertScreen = signInScreens.signInAsValidCred(loginCred);

        Assert.assertTrue(alertScreen.isAlertPresent(), "Alert is not present");
        Assert.assertEquals(alertScreen.getAlertTitle(), SIGN_IN_ALERT_TITLE, "Alert title is not correct");
        Assert.assertEquals(alertScreen.getAlertMessage(), SIGN_IN_ALERT_MESSAGE, "Alert title is not correct");
    }

    @Author(THAM_VU)
    @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectCredentials(LoginCred loginCred) {

        signInScreens.signInAsInvalidCred(loginCred);

        Assert.assertEquals(signInScreens.getInvalidEmailMessage(), INVALID_EMAIL_MESSAGE, "Invalid email message is not correct");
        Assert.assertEquals(signInScreens.getInvalidPasswordMessage(), INVALID_PASSWORD_MESSAGE, "Invalid password message is not correct");
    }

    @Author(THAM_VU)
    @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectEmail(LoginCred loginCred) {

        signInScreens.signInAsInvalidCred(loginCred);

        Assert.assertEquals(signInScreens.getInvalidEmailMessage(), INVALID_EMAIL_MESSAGE, "Invalid email message is not correct");
    }


    @Author(THAM_VU)
    @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class)
    public void loginInWithIncorrectPassword(LoginCred loginCred) {

        signInScreens.signInAsInvalidCred(loginCred);

        Assert.assertEquals(signInScreens.getInvalidPasswordMessage(), INVALID_PASSWORD_MESSAGE, "Invalid password message is not correct");
    }
}
