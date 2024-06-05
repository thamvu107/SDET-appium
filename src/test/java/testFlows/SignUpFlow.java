package testFlows;

import entity.SignUpCred;
import io.appium.java_client.AppiumDriver;
import pageObjects.screens.login.SignUpScreen;

public class SignUpFlow extends LoginFlow {
    private final SignUpScreen signUpScreen;

    public SignUpFlow(AppiumDriver driver) {
        super(driver);
        this.signUpScreen = new SignUpScreen(driver);
    }

    public void openSignUpTab() {
        signUpScreen
                .clickOnSingUpTab()
                .verifySignUpFormDisplayed();
    }

    public SignUpScreen signUpWithCred(SignUpCred signUpCred) {
        return signUpScreen
                .inputEmail(signUpCred.getEmail())
                .inputPassword(signUpCred.getPassword())
                .inputRepeatPassword(signUpCred.getRepeatPassword())
                .clickOnSignUpButton();
    }


}
