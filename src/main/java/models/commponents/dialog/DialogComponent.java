package models.commponents.dialog;

import driverFactory.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.ElementLocatorMapper;

import java.util.Map;

import static io.appium.java_client.AppiumBy.*;

public class DialogComponent {

    //    private final AppiumDriver driver;
    private final ElementLocatorMapper elementMapping;

    // Android
    private final By androidDialogLocator = id("android:id/content");
    private final By androidDialogTitleLoc = id("android:id/alertTitle");
    private final By androidDialogMessageLoc = id("android:id/message");
    private final By androidDialogButtonLoc = id("android:id/button1");

    // iOS
    private final By iosDialogLocator = className("XCUIElementTypeAlert");

    private final By iosDialogTitleLoc = iOSNsPredicateString("name == \"Success\" AND label == \"Success\" AND value == \"Success\"");
    private final By iosDialogMessageLoc = accessibilityId("You are logged in!");
    private final By iosDialogButtonLoc = accessibilityId("OK");

    // Mapping
    private final Map<MobilePlatform, By> dialogLocatorMap = Map.of(
            MobilePlatform.ANDROID, androidDialogLocator,
            MobilePlatform.IOS, iosDialogLocator);
    private final Map<MobilePlatform, By> dialogTitleLocMap = Map.of(
            MobilePlatform.ANDROID, androidDialogTitleLoc,
            MobilePlatform.IOS, iosDialogTitleLoc);

    private final Map<MobilePlatform, By> dialogMessageLoc = Map.of(
            MobilePlatform.ANDROID, androidDialogMessageLoc,
            MobilePlatform.IOS, iosDialogMessageLoc);
    private final Map<MobilePlatform, By> dialogOkButtonLoc = Map.of(
            MobilePlatform.ANDROID, androidDialogButtonLoc,
            MobilePlatform.IOS, iosDialogButtonLoc);

    public DialogComponent(AppiumDriver driver) {

        elementMapping = new ElementLocatorMapper(driver);

    }

    private WebElement dialogElement() {

        return elementMapping.findElement(dialogLocatorMap);
    }

    private WebElement dialogTitleElement() {

        return elementMapping.findElement(dialogTitleLocMap);
    }

    private WebElement dialogMessageElement() {

        return elementMapping.findElement(dialogMessageLoc);
    }

    private WebElement okButtonElement() {

        return elementMapping.findElement(dialogOkButtonLoc);
    }

    public DialogComponent seeDialog(String expectedTitle, String expectedMessage) {

        verifyDialogTitle(expectedTitle);
        verifyDialogMessage(expectedMessage);

        return this;
    }

    public void verifyDialogTitle(String expectedTitle) {

        String actualMessage = dialogTitleElement().getText();
        Assert.assertEquals(actualMessage, expectedTitle);
    }

    public void verifyDialogMessage(String expectedMessage) {

        String actualMessage = dialogMessageElement().getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }


    public DialogComponent clickOnOkButton() {

        okButtonElement().click();

        return this;
    }


    public void isDisappearedDialog() {
        ExpectedCondition<Boolean> invisibility = ExpectedConditions.invisibilityOf(dialogElement());
        //Assert.assertFalse(elementMapping.isElementPresent(dialogLocatorMap));
    }

}
