package models.screens.alert;


import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public class SignInAlertScreen extends AlertScreen {
    private final By iosAlertTitleLoc = iOSNsPredicateString("name == \"Success\" AND label == \"Success\" AND value == \"Success\"");
    private final By iosAlertMessageLoc = accessibilityId("You are logged in!");

    public SignInAlertScreen(AppiumDriver driver) {
        super(driver);
    }

    private final Map<Platform, By> alertTitleLocatorMap = Map.of(
            Platform.ANDROID, androidAlertTitleLoc,
            Platform.IOS, iosAlertTitleLoc);

    private final Map<Platform, By> alertMessageLocatorMap = Map.of(
            Platform.ANDROID, androidAlertMessageLoc,
            Platform.IOS, iosAlertMessageLoc);

    @Override
    protected WebElement dialogTitleElement() {
        By locator = mobileActions.getLocatorIsMappedCurrentPlatform(alertTitleLocatorMap);
        mobileActions.waitUntilVisibilityOfElementLocated(locator);

        return mobileActions.findElement(alertTitleLocatorMap);
    }

    @Override
    protected WebElement dialogMessageElement() {
        return mobileActions.findElement(alertMessageLocatorMap);
    }

}
