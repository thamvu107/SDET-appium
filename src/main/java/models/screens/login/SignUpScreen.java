package models.screens.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class SignUpScreen extends LoginScreen {

    private final By repeatPasswordLocator = accessibilityId("input-repeat-password"); // android + ios
    private final By signUpButtonLocator = accessibilityId("button-SIGN UP"); // android + ios

    public SignUpScreen(AppiumDriver driver) {
        super(driver);
    }

    public SignUpScreen displaySignUpForm() {

        if (this.driver instanceof AndroidDriver) {
            // execute AndroidFlow
        } else {
            // execute IOSFlow
        }

        return this;

    }

}
