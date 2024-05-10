package practice;

import driverFactory.Driver;
import driverFactory.capabilities.AndroidCapabilities;
import io.appium.java_client.AppiumDriver;
import models.commponents.dialog.DialogComponent;
import models.screens.login.LoginScreen;
import models.screens.login.SignUpScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static constants.Screens.SignUpConstants.*;

public class SignUpTest extends BaseTest {

    private AppiumDriver driver;
    private SignUpScreen signUpScreen;

    @BeforeClass
    public void setUpSignUpPage() {
        // Android
        this.driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

        // iOS
        //this.driver = Driver.createDriver(Driver.getServerUrl(), IOSCapabilities.getCaps());

        signUpScreen = new LoginScreen(driver)
                .clickOnLoginNav()
                .displayLoginScreen()
                .clickOnSingUpTab();
    }

    @Test
    public void signUpWithCorrectCredentials() {
        signUpScreen.inputEmail(SIGN_UP_EMAIL)
                .inputPassword(SIGN_UP_PASSWORD)
                .inputRepeatPassword(SIGN_UP_PASSWORD)
                .clickOnSignUpButton();


        new DialogComponent(this.driver)
                .seeDialog(SIGN_UP_DIALOG_TITLE, SIGN_UP_DIALOG_MESSAGE)
                .clickOnOkButton()
                .isDisappearedDialog();
    }
}
