package pageObjects.screens.alert;


import enums.Platform;
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
        verifyScreenLoaded(elementUtils.getLocator(alertTitleLocatorMap));
    }

    private final Map<Platform, By> alertTitleLocatorMap = Map.of(
            Platform.ANDROID, androidAlertTitleLoc,
            Platform.IOS, iosAlertTitleLoc);

    private final Map<Platform, By> alertMessageLocatorMap = Map.of(
            Platform.ANDROID, androidAlertMessageLoc,
            Platform.IOS, iosAlertMessageLoc);

    @Override
    protected WebElement alertTitleEl() {

        By locator = elementUtils.getLocator(alertTitleLocatorMap);
        return elementUtils.waitForFindingElement(locator);
    }

    @Override
    protected WebElement alertMessageEl() {
        return elementUtils.findElement(alertMessageLocatorMap);
    }

}
