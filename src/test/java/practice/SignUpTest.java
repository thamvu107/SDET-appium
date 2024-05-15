package practice;

import driverFactory.Driver;
import driverFactory.capabilities.IOSCapabilities;
import models.screens.login.SignUpScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static constants.Screens.SignUpConstants.*;

public class SignUpTest extends BaseTest {

    private SignUpScreen signUpScreen;

    @BeforeClass
    public void setUpSignUpPage() {
        // Android
//        driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

//        iOS
        driver = Driver.createDriver(Driver.getServerUrl(), IOSCapabilities.getCaps());

        signUpScreen = new SignUpScreen(driver)
                .clickOnLoginNav()
                .verifyLoginScreenDisplayed()
                .clickOnSingUpTab()
                .verifySignUpFormDisplayed();
    }

    @Test
    public void signUpWithCorrectCredentials() {
        signUpScreen
                .inputEmail(SIGN_UP_EMAIL)
                .inputPassword(SIGN_UP_PASSWORD)
                .inputRepeatPassword(SIGN_UP_PASSWORD)
                .clickOnSignUpButton()
                .switchToSignUpAlert()
                .verifyAlertPresent()
                .verifyAlertTitle(SIGN_UP_DIALOG_TITLE)
                .verifyAlertMessage(SIGN_UP_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }
}
