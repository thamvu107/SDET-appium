package lib;

import io.appium.java_client.AppiumDriver;
import pageObjects.screens.alert.AlertScreen;
import pageObjects.screens.alert.SignInAlertScreen;
import pageObjects.screens.login.LoginScreen;
import pageObjects.screens.login.SignInScreen;
import pageObjects.screens.login.SignUpScreen;

public class PageLib {
    private final AppiumDriver driver;
    private final LoginScreen loginScreen;
    private final SignUpScreen signUpScreen;

    private final SignInScreen signInScreen;

    private final AlertScreen alertScreen;

    public PageLib(AppiumDriver driver) {

        this.driver = driver;
        loginScreen = new LoginScreen(this.driver);
        signUpScreen = new SignUpScreen(this.driver);
        signInScreen = new SignInScreen(this.driver);
        alertScreen = new SignInAlertScreen(this.driver);
    }

    public LoginScreen LoginScreen() {
        return loginScreen;
    }
}
