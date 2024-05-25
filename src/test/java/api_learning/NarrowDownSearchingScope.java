package api_learning;

import driverFactory.Platform;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import practice.BaseTest;

import java.util.List;
import java.util.Map;

import static io.appium.java_client.AppiumBy.*;

public class NarrowDownSearchingScope extends BaseTest {

    @Test
    public void narrowDownSearchingScope() {
        final String NOTIFICATION_TITLE = "Appium Settings";
        final String NOTIFICATION_MESSAGE = "Keep this service running, so Appium for Android can properly interact with several system APIs";
        boolean notificationFound = false;

        try {
            By formsBtnLoc = AppiumBy.accessibilityId("Forms");
            mobileInteractions.waitVisibilityOfElementLocated(formsBtnLoc);
            driver.findElement(formsBtnLoc).click();

            Map<Platform, By> formComponentLocatorMap = Map.of(
                    Platform.ANDROID, androidUIAutomator("new UiSelector(). textContains(\"Form components\")"),
                    Platform.IOS, iOSNsPredicateString("name == \"Form components\" AND label == \"Form components\" AND value == \"Form components\"")
            );
            By formComponentLoc = mobileInteractions.getLocatorIsMappedCurrentPlatform(formComponentLocatorMap);
            mobileInteractions.waitVisibilityOfElementLocated(formComponentLoc);

            mobileInteractions.openNotificationPanel();

            // TODO: Simulator notification
            Map<Platform, By> notificationLocatorMap = Map.of(
                    Platform.ANDROID, id("com.android.systemui:id/expanded"),
                    Platform.IOS, id("com.android.systemui:id/expanded")
            );
            By notificationLoc = mobileInteractions.getLocatorIsMappedCurrentPlatform(notificationLocatorMap);
            mobileInteractions.waitVisibilityOfElementLocated(notificationLoc);


            // TODO: handle app_name_text
//            By notificationTitleLoc = AppiumBy.id("android:id/app_name_text");

            Map<Platform, By> notificationTitleLocatorMap = Map.of(
                    Platform.ANDROID, id("android:id/title"),
                    Platform.IOS, id("android:id/title")
            );
            By notificationTitleLoc = mobileInteractions.getLocatorIsMappedCurrentPlatform(notificationTitleLocatorMap);
            mobileInteractions.waitVisibilityOfElementLocated(notificationTitleLoc);


            Map<Platform, By> notificationMessageLocatorMap = Map.of(
                    Platform.ANDROID, id("android:id/big_text"),
                    Platform.IOS, id("android:id/big_text")
            );
            By notificationMessageLoc = mobileInteractions.getLocatorIsMappedCurrentPlatform(notificationMessageLocatorMap);
            mobileInteractions.waitVisibilityOfElementLocated(notificationMessageLoc);


            List<WebElement> notificationEleList = driver.findElements(notificationLoc);

            Assert.assertFalse(notificationEleList.isEmpty(), "No notifications found");
            System.out.println("1");

            for (WebElement notificationEle : notificationEleList) {

                // Narrow down searching scope
                WebElement notificationTitleEle = notificationEle.findElement(notificationTitleLoc);
                WebElement notificationMessageEle = notificationEle.findElement(notificationMessageLoc);
                System.out.println("a");
                if (mobileInteractions.isTextDisplayedCorrect(notificationTitleEle, NOTIFICATION_TITLE) && mobileInteractions.isTextDisplayedCorrect(notificationMessageEle, NOTIFICATION_MESSAGE)) {
                    notificationFound = true;
                    break;
                }
            }
            Assert.assertTrue(notificationFound);

        } catch (Exception e) {
            Assert.fail("No notifications found " + e.getMessage());
            e.printStackTrace();

        } finally {
            mobileInteractions.closeNotificationPanel();
        }
    }

}
