package models.commponents.dialog;

import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import models.screens.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.LocatorMapper;

import java.util.Map;

import static io.appium.java_client.AppiumBy.*;

public class DialogComponent extends BaseScreen {

    //    protected final AppiumDriver driver;
    protected final LocatorMapper elementMapping;

    // Android
    protected final By androidDialogLocator = id("android:id/content");
    protected final By androidDialogTitleLoc = id("android:id/alertTitle");
    protected final By androidDialogMessageLoc = id("android:id/message");
    protected final By androidDialogButtonLoc = id("android:id/button1");

    // iOS
    protected final By iosDialogLocator = className("XCUIElementTypeAlert");

    // TODO: Diaglog for signup
    private final By iosDialogTitleLoc = iOSNsPredicateString("name == \"Success\" AND label == \"Success\" AND value == \"Success\"");
    private final By iosDialogMessageLoc = accessibilityId("You are logged in!");
    private final By iosDialogButtonLoc = accessibilityId("OK");

    // Mapping
    private final Map<Platform, By> dialogLocatorMap = Map.of(
            Platform.ANDROID, androidDialogLocator,
            Platform.IOS, iosDialogLocator);
    private final Map<Platform, By> dialogTitleLocMap = Map.of(
            Platform.ANDROID, androidDialogTitleLoc,
            Platform.IOS, iosDialogTitleLoc);

    private final Map<Platform, By> dialogMessageLocMap = Map.of(
            Platform.ANDROID, androidDialogMessageLoc,
            Platform.IOS, iosDialogMessageLoc);
    private final Map<Platform, By> dialogOkButtonLocMap = Map.of(
            Platform.ANDROID, androidDialogButtonLoc,
            Platform.IOS, iosDialogButtonLoc);

    public DialogComponent(AppiumDriver driver) {
        super(driver);
        elementMapping = new LocatorMapper(driver);

    }

    private WebElement dialogElement() {

        return elementMapping.findElement(dialogLocatorMap);
    }

    private WebElement dialogTitleElement() {

        return elementMapping.findElement(dialogTitleLocMap);
    }

    private WebElement dialogMessageElement() {

        return elementMapping.findElement(dialogMessageLocMap);
    }

    private WebElement okButtonElement() {

        return elementMapping.findElement(dialogOkButtonLocMap);
    }

    public DialogComponent isDisplayedDialog() {

        Assert.assertTrue(elementMapping.findElement(dialogOkButtonLocMap).isDisplayed());

        return this;
    }

    public DialogComponent verifyDialogTitle(String expectedTitle) {

        String actualMessage = dialogTitleElement().getText();
        Assert.assertEquals(actualMessage, expectedTitle);

        return this;
    }

    public DialogComponent verifyDialogMessage(String expectedMessage) {

        String actualMessage = dialogMessageElement().getText();
        Assert.assertEquals(actualMessage, expectedMessage);

        return this;
    }


    public DialogComponent clickOnOkButton() {

        okButtonElement().click();


        return this;
    }


    public void isDisappearedDialog() {

    }

}
