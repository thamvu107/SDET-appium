package api_learning;

import driverFactory.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormInteraction {

    /*
     * Locator | By | from AppiumBy
     *
     * Element | WebElement
     */
    public static void main(String[] args) {

        AppiumDriver driver;

//      driver = DriverFactory.getDriver(MobilePlatform.ANDROID);
        driver = Driver.getLocalServerDriver(MobileFactory.getAndroidMobile());

        try {
            // Login Action
            // By navLoginBtnLoc = By.xpath("//android.widget.Button[@content-desc='Login']");
            By navLoginBtnLoc = AppiumBy.accessibilityId("Login");
            WebElement navLoginBtnEle = driver.findElement(navLoginBtnLoc);
            navLoginBtnEle.click();

            // Input username
            //By emailFieldLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
            By emailFieldLoc = AppiumBy.accessibilityId("input-email");
            WebElement emailFieldEle = driver.findElement(emailFieldLoc);
            emailFieldEle.clear();
            emailFieldEle.sendKeys("testing.email@gmail.com");


            // Input password
            //By passwordLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");
            By passwordLoc = AppiumBy.accessibilityId("input-password");
            WebElement passwordEle = driver.findElement(passwordLoc);
            passwordEle.sendKeys("yourPassword12345");

            // Click on Login Btn
            By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
            WebElement loginBtnEle = driver.findElement(loginBtnLoc);
            loginBtnEle.click();

            // Wait for the dialog displayed
            By dialogMsgLoc = AppiumBy.id("android:id/message");
            By dialogBtnLoc = AppiumBy.id("android:id/button1");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
            WebElement dialogMsgEle = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
            System.out.printf("Dialog msg: %s\n", dialogMsgEle.getText());
            driver.findElement(dialogBtnLoc).click();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }

        driver.quit();
        //Driver.clearApp(APP_PACKAGE);
    }
}
