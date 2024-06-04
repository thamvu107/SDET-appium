package models.screens.alert;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import models.screens.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

import static io.appium.java_client.AppiumBy.*;

public abstract class AlertScreen extends BaseScreen {

    // TODO: Separate locators by platform file.

    // Android
    protected final By androidAlertLoc = id("android:id/content");
    protected final By androidAlertTitleLoc = id("android:id/alertTitle");
    protected final By androidAlertMessageLoc = id("android:id/message");
    protected final By androidAlertButtonLoc = id("android:id/button1");

    //iOS
    protected final By iosAlertLocator = className("XCUIElementTypeAlert");
    protected final By iosAlertButtonLoc = accessibilityId("OK");

    protected AlertScreen(AppiumDriver driver) {

        super(driver);
    }

    protected final Map<Platform, By> alertLocatorMap = Map.of(
            Platform.ANDROID, androidAlertLoc,
            Platform.IOS, iosAlertLocator);

    protected final Map<Platform, By> alertOkButtonLocatorMap = Map.of(
            Platform.ANDROID, androidAlertButtonLoc,
            Platform.IOS, iosAlertButtonLoc);


    protected WebElement dialogElement() {

        return mobileInteractionHelper.findElement(alertLocatorMap);
    }

    protected abstract WebElement dialogTitleElement();

    protected abstract WebElement dialogMessageElement();

    protected WebElement okButtonElement() {

        return mobileInteractionHelper.findElement(alertOkButtonLocatorMap);
    }

    public AlertScreen verifyAlertPresent(String expectedTitle, String expectedMessage) {

        Assert.assertTrue(mobileInteractionHelper.isAlertPresent());
        this.verifyAlertTitle(expectedTitle);
        this.verifyAlertMessage(expectedMessage);

        return this;
    }

    public AlertScreen verifyAlertTitle(String expectedTitle) {

        String actualMessage = dialogTitleElement().getText();
        Assert.assertEquals(actualMessage, expectedTitle);

        return this;
    }

    public AlertScreen verifyAlertMessage(String expectedMessage) {

        String actualMessage = dialogMessageElement().getText();
        Assert.assertEquals(actualMessage, expectedMessage);

        return this;
    }

    public AlertScreen clickOnOkButton() {

        okButtonElement().click();

        return this;
    }

    public void verifyAlertDisappeared() {

        mobileInteractionHelper.assertAlertHasDisappeared(driver, Duration.ofMillis(500));
    }

}
