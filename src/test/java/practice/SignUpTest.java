package practice;

import driverFactory.Driver;
import driverFactory.capabilities.AndroidCapabilities;
import models.screens.login.LoginScreen;
import models.screens.login.SignUpScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static constants.Screens.SignUpConstants.*;

public class SignUpTest extends BaseTest {

    private LoginScreen loginScreen;
    private SignUpScreen signUpScreen;

    @BeforeClass
    public void setUpSignUpPage() {
        // Android
        driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

        // iOS
//        driver = Driver.createDriver(Driver.getServerUrl(), IOSCapabilities.getCaps());

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
                .switchToAlert()
                .verifyAlertPresent()
                .verifyDialogTitle(SIGN_UP_DIALOG_TITLE)
                .verifyDialogMessage(SIGN_UP_DIALOG_MESSAGE)
                .clickOnOkButton()
                .verifyAlertDisappeared();
    }
}
