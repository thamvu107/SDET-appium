package pageObjects.screens.alert;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public class FormComponentAlertScreen extends AlertScreen {
    private final By iosAlertTitleLoc = iOSNsPredicateString("name == \"This button is\" AND label == \"This button is\" AND value == \"This button is\"");
    private final By iosAlertMessageLoc = accessibilityId("This button is active");

    protected FormComponentAlertScreen(AppiumDriver driver) {
        super(driver);
    }

    private final Map<Platform, By> dialogTitleLocMap = Map.of(
            Platform.ANDROID, androidAlertTitleLoc,
            Platform.IOS, iosAlertTitleLoc);

    private final Map<Platform, By> dialogMessageLocMap = Map.of(
            Platform.ANDROID, androidAlertMessageLoc,
            Platform.IOS, iosAlertMessageLoc);


    @Override
    protected WebElement dialogTitleElement() {
        return null;
    }

    @Override
    protected WebElement dialogMessageElement() {
        return null;
    }

    @Override
    public AlertScreen verifyAlertTitle(String expectedTitle) {
        return null;
    }
}
