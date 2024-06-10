package testFlows;

import io.appium.java_client.AppiumDriver;
import pageObjects.screens.BaseScreen;

public class BaseFlow {

    protected final AppiumDriver driver;
    protected BaseScreen baseScreen;

    public BaseFlow(AppiumDriver driver) {

        this.driver = driver;
        this.baseScreen = new BaseScreen(this.driver);
    }

    public void gotoLoginScreen() {
        baseScreen
                .goToLoginScreen()
                .verifyLoginScreenDisplayed();
    }

}
