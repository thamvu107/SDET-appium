package models.screens.alert;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public class SignUpAlertScreen extends AlertScreen {

    private final By iosAlertTitleLoc = iOSNsPredicateString("name == \"Signed Up!\" AND label == \"Signed Up!\" AND value == \"Signed Up!\"");
    private final By iosAlertMessageLoc = accessibilityId("You successfully signed up!");

    public SignUpAlertScreen(AppiumDriver driver) {
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

        By locator = mobileInteractions.getLocatorIsMappedCurrentPlatform(alertTitleLocatorMap);
        mobileInteractions.waitVisibilityOfElementLocated(locator);

        return mobileInteractions.findElement(alertTitleLocatorMap);
    }

    @Override
    protected WebElement dialogMessageElement() {

        return mobileInteractions.findElement(alertMessageLocatorMap);
    }

}
