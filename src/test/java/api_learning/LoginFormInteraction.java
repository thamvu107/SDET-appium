package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginFormInteraction {

    /*
     * Locator | By | from AppiumBy
     * Element | WebElement
     */
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Login Action
            // By navLoginBtnLoc = By.xpath("//android.widget.Button[@content-desc='Login']");
            By navLoginBtnLoc = AppiumBy.accessibilityId("Login");
            WebElement navLoginBtnEle = appiumDriver.findElement(navLoginBtnLoc);
            navLoginBtnEle.click();

            // Input username
            //By emailFieldLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
            By emailFieldLoc = AppiumBy.accessibilityId("input-email");
            WebElement emailFieldEle = appiumDriver.findElement(emailFieldLoc);
            emailFieldEle.sendKeys("testing.email@gmail.com");


            // Input password
            //By passwordLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");
            By passwordLoc = AppiumBy.accessibilityId("input-password");
            WebElement passwordEle = appiumDriver.findElement(passwordLoc);
            passwordEle.sendKeys("yourPassword12345");

            // Click on Login Btn
            By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
            WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
            loginBtnEle.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }

        appiumDriver.quit();
    }
}
