package practice;

import driverFactory.Driver;
import driverFactory.capabilities.IOSCapabilities;
import io.appium.java_client.AppiumDriver;
import models.commponents.dialog.DialogComponent;
import models.screens.login.LoginScreen;
import models.screens.login.SignInScreen;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static constants.Screens.SignInConstants.*;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class SignInTest extends BaseTest {
    private final By emailInputLoc = accessibilityId("input-email");
    private final By passwordInputLoc = accessibilityId("input-password");
    private SignInScreen signInScreen;
    private AppiumDriver driver;
    private LoginScreen loginScreen;

    @BeforeClass
    public void setUpLoginPage() {
        // Android
        //driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

        // iOS
        driver = Driver.createDriver(Driver.getServerUrl(), IOSCapabilities.getCaps());

        new LoginScreen(driver)
                .clickOnLoginNav()
                .verifyLoginScreenDisplayed();
    }

    @Test(priority = 1)
    public void loginInWithCorrectCredential() {

        new SignInScreen(driver)
                .inputEmail(SIGN_IN_EMAIL)
                .inputPassword(SIGN_IN_PASSWORD)
                .clickOnLoginButton();

        new DialogComponent(driver)
                .isDisplayedDialog()
                .verifyDialogTitle(SIGN_IN_DIALOG_TITLE)
                .verifyDialogMessage(SIGN_IN_DIALOG_MESSAGE)
                .clickOnOkButton()
                .isDisappearedDialog();
    }

    @Test(priority = 2)
    public void loginInWithIncorrectCredentials() {
        new SignInScreen(driver)
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
