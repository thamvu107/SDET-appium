package models.screens.login;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class SignUpScreen extends LoginScreen {

    private final By repeatPasswordLocator = accessibilityId("input-repeat-password"); // android + ios
    private final By signUpButtonLocator = accessibilityId("button-SIGN UP"); // android + ios

    public SignUpScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement repeatPasswordElement() {

        return driver.findElement(repeatPasswordLocator);
    }

    private WebElement signUpButtonElement() {

        return driver.findElement(signUpButtonLocator);
    }


    public SignUpScreen inputEmail(String email) {

        emailFieldElement().clear();
        emailFieldElement().sendKeys(email);

        return this;
    }

    public SignUpScreen inputPassword(String password) {

        passwordFieldElement().clear();
        passwordFieldElement().sendKeys(password);

        return this;
    }

    public SignUpScreen inputRepeatPassword(String repeatPassword) {
        repeatPasswordElement().clear();
        repeatPasswordElement().sendKeys(repeatPassword);

        return this;
    }

    public SignUpScreen clickOnSignUpButton() {

        // TODO: On smaller screens there could be a possibility that the button is not shown
        signUpButtonElement().click();

        return this;
    }

}
