package practice;

import driverFactory.Driver;
import driverFactory.capabilities.IOSCapabilities;
import io.appium.java_client.AppiumDriver;
import models.screens.login.SignInScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static constants.Screens.SignInConstants.*;

public class SignInTest extends BaseTest {
    private AppiumDriver driver;

    @BeforeClass
    public void setUpLoginPage() {
        // Android
        //driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

        // iOS
        driver = Driver.createDriver(Driver.getServerUrl(), IOSCapabilities.getCaps());

        new SignInScreen(driver)
                .clickOnLoginNav()
                .verifyLoginScreenDisplayed()
                .clickOnSingInTab()
                .verifySignInFormDisplayed();
    }

    @Test(priority = 1)
    public void loginInWithCorrectCredential() {

        new SignInScreen(driver)
                .inputEmail(SIGN_IN_EMAIL)
                .inputPassword(SIGN_IN_PASSWORD)
                .clickOnLoginButton()
                .switchToSignInAlert()
                .verifyAlertPresent()
                .verifyAlertTitle(SIGN_IN_DIALOG_TITLE)
                .verifyAlertMessage(SIGN_IN_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }

    @Test(priority = 2)
    public void loginInWithIncorrectCredentials() {
        new SignInScreen(driver)
                .inputEmail(INVALID_EMAIL)
                .inputPassword(INVALID_PASSWORD)
                .clickOnLoginButton()
                .verifyInvalidEmailMessage(INVALID_EMAIL_MESSAGE)
                .verifyInvalidPasswordMessage(INVALID_PASSWORD_MESSAGE);
    }

    @AfterClass
    public void tearDown() {

        Driver.quitDriver(driver);
    }

}
