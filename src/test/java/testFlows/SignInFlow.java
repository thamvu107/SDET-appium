package testFlows;

import entity.LoginCred;
import io.appium.java_client.AppiumDriver;
import pageObjects.screens.login.SignInScreen;

public class SignInFlow extends LoginFlow {
    private final SignInScreen signInScreen;

    public SignInFlow(AppiumDriver driver) {
        super(driver);
        signInScreen = new SignInScreen(driver);
    }

    public void openSignInTab() {
        signInScreen
                .clickOnSingInTab()
                .verifySignInFormDisplayed();
    }

    public SignInScreen loginWithCred(LoginCred loginCred) {
        return signInScreen
                .inputEmail(loginCred.getEmail())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton();
    }
}
