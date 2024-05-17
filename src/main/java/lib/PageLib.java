package lib;

import io.appium.java_client.AppiumDriver;
import models.screens.alert.AlertScreen;
import models.screens.alert.SignInAlertScreen;
import models.screens.login.LoginScreen;
import models.screens.login.SignInScreen;
import models.screens.login.SignUpScreen;

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
