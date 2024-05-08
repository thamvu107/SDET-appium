package practice;

import driverFactory.Driver;
import driverFactory.capabilities.AndroidCapabilities;
import io.appium.java_client.AppiumDriver;
import models.commponents.dialog.DialogComponent;
import models.screens.login.LoginScreen;
import models.screens.login.SignInScreen;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverUtils;

import static constants.Screens.SignInConstants.*;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class SignInTest extends BaseTest {
    private final By emailInputLoc = accessibilityId("input-email");
    private final By passwordInputLoc = accessibilityId("input-password");
    private SignInScreen signInScreen;
    private AppiumDriver driver;
    //    private LoginScreen loginScreen;

    @BeforeClass
    public void setUpLoginPage() {
        // Android
        DriverUtils.driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

        // iOS
        //DriverUtils.driver = Driver.createDriver(Driver.getServerUrl(), IOSCapabilities.getCaps());
        this.driver = DriverUtils.driver;

        new LoginScreen(driver)
                .clickOnLoginNav()
                .displayLoginScreen();
    }

    @Test(priority = 1)
    public void loginInWithCorrectCredential() {

        new SignInScreen(this.driver)
                .inputEmail(SIGN_IN_EMAIL)
                .inputPassword(SIGN_IN_PASSWORD)
                .clickOnLoginButton();

        new DialogComponent(this.driver)
                .seeDialog(SUCCESS_TITLE, SUCCESS_MESSAGE)
                .clickOnOkButton()
                .isDisappearedDialog();
    }

    @Test(priority = 2)
    public void loginInWithIncorrectCredentials() {
        new SignInScreen(this.driver)
                .inputEmail(INVALID_EMAIL)
                .inputPassword(INVALID_PASSWORD)
                .clickOnLoginButton()
                .seeInvalidEmailMessage(INVALID_EMAIL_MESSAGE)
                .seeInvalidPasswordMessage(INVALID_PASSWORD_MESSAGE);
    }

    @AfterClass
    public void tearDown() {

        Driver.quitDriver(driver);
    }

}
