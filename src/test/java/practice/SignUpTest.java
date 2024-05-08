package practice;

import driverFactory.Driver;
import driverFactory.capabilities.AndroidCapabilities;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.screens.login.SignInScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTest extends BaseTest {

//    private LoginScreen loginScreen;

    private SignInScreen signInScreen;

    @BeforeClass
    public void setUpLoginPage() {
//        DriverFactory.startAppiumServer();
        AppiumDriver driver = Driver.createDriver(Driver.getServerUrl(), AndroidCapabilities.getCaps());

        signInScreen = new SignInScreen(driver);
        signInScreen.clickOnLoginNav();
    }

    @Test
    public void signUpWithValidAccount() {
//        SignUpComponent signUpComponent = loginScreen.signUpComponent()
//                .inputEmail("tham.qa@gmail.com")
//                .inputPassword("MyPassword1020")
//                .clickOnSignUpBtn();

        //signInScreen.inputEmail();


        // Wait for the dialog displayed
        By dialogMsgLoc = AppiumBy.id("android:id/message");
        By dialogBtnLoc = AppiumBy.id("android:id/button1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        WebElement dialogMsgEle = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
        System.out.printf("Dialog msg: %s\n", dialogMsgEle.getText());
        driver.findElement(dialogBtnLoc).click();


    }
}
