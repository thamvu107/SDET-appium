package testFlows;

import io.appium.java_client.AppiumDriver;
import pageObjects.screens.login.LoginScreen;

public class LoginFlow extends BaseFlow {
    private final LoginScreen loginScreen;

    public LoginFlow(AppiumDriver driver) {
        super(driver);
        loginScreen = new LoginScreen(driver);
    }

    public void gotoLoginScreen() {
        loginScreen
                .clickOnLoginNav()
                .verifyLoginScreenDisplayed();
    }
}
