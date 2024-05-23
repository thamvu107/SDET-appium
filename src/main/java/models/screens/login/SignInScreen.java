package models.screens.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SignInScreen extends LoginScreen {


    private final By signInButtonLocator = AppiumBy.accessibilityId("button-LOGIN");

    public SignInScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement signInButtonElement() {

        return this.driver.findElement(signInButtonLocator);
    }

    public SignInScreen verifySignInFormDisplayed() {

        mobileInteractions.waitVisibilityOfElementLocated(signInButtonLocator);
        mobileInteractions.verifyElementDisplayed(emailInputLoc);
        mobileInteractions.verifyElementDisplayed(passwordInputLoc);

        return this;
    }

    public SignInScreen inputEmail(String email) {

        emailFieldElement().clear();
        emailFieldElement().sendKeys(email);

        return this;
    }

    public SignInScreen inputPassword(String password) {

        passwordFieldElement().clear();
        passwordFieldElement().sendKeys(password);

        return this;
    }

    public SignInScreen clickOnLoginButton() {

        // TODO: On smaller screens there could be a possibility that the button is not shown
        signInButtonElement().click();

        return this;
    }

    public SignInScreen verifyInvalidEmailMessage(String expectMessage) {

        String actualMessage = invalidEmailLabelElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

        return this;
    }

    public void verifyInvalidPasswordMessage(String expectMessage) {

        String actualMessage = invalidPasswordLabelElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);
    }


}
