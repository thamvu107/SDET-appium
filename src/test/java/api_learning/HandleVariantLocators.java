package api_learning;

import driver.Platforms;
import driverFactory.Driver;
import driverFactory.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LocatorMapper;

import java.util.Map;

public class HandleVariantLocators {
    private static final Map<Platforms, By> navloginBtnLocMap = Map.of(
            Platforms.ANDROID, AppiumBy.accessibilityId("Login"),
            Platforms.IOS, AppiumBy.accessibilityId("try-to-have-difference-here")
    );

    private static final Map<Platform, By> navloginButtonLocatorMap = Map.of(
            Platform.ANDROID, AppiumBy.accessibilityId("Login"),
            Platform.IOS, AppiumBy.accessibilityId("Login")
    );
    private static final By emailFieldLoc = AppiumBy.accessibilityId("input-email");
    private static final By passwordLoc = AppiumBy.accessibilityId("input-password");
    private static final By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");

    public static void main(String[] args) {

        AppiumDriver driver = Driver.getLocalServerDriver(MobileFactory.getIOSsMobile());

        try {
            LocatorMapper elementHandler = new LocatorMapper(driver);
            WebElement navLoginBtnEle = elementHandler.findElement(navloginButtonLocatorMap);
            navLoginBtnEle.click();

            WebElement emailFieldEle = driver.findElement(emailFieldLoc);
            emailFieldEle.clear();
            emailFieldEle.sendKeys("teo@sth.com");

            // Input password
            WebElement passwordEle = driver.findElement(passwordLoc);
            passwordEle.sendKeys("12345678");

            // Click on Login Btn
            WebElement loginBtnEle = driver.findElement(loginBtnLoc);
            loginBtnEle.click();

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
