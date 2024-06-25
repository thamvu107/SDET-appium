package pageObjects.screens.webView;

import io.appium.java_client.AppiumDriver;

public class WebHomeScreen extends WebBasedScreen {
    private final AppiumDriver driver;

    public WebHomeScreen(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
//        verifyScreenLoaded();
    }

}
