package api_learning;

import driverFactory.Driver;
import driverFactory.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import utils.MobileInteractions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public class NarrowDownSearchingScope {
    public static void main(String[] args) {
        final String NOTIFICATION_TITLE = "Appium Settings";
        final String NOTIFICATION_MESSAGE = "Keep this service running, so Appium for Android can properly interact with several system APIs";

        AppiumDriver driver;

//        driver = DriverFactory.getMobileDriver(MobilePlatform.ANDROID);
        driver = Driver.getLocalServerDriver(MobileFactory.getEmulator());

        try {
            By formsBtnLoc = AppiumBy.accessibilityId("Forms");
            MobileInteractions interactions = new MobileInteractions(driver);
            interactions.waitVisibilityOfElementLocated(formsBtnLoc);
            driver.findElement(formsBtnLoc).click();

            // TODO: Simulator notification
            Map<Platform, By> formComponentLocatorMap = Map.of(
                    Platform.ANDROID, androidUIAutomator("new UiSelector(). textContains(\"Form components\")"),
                    Platform.IOS, iOSNsPredicateString("name == \"Form components\" AND label == \"Form components\" AND value == \"Form components\"")
            );

            By formComponentLoc = interactions.getLocatorIsMappedCurrentPlatform(formComponentLocatorMap);
            interactions.waitVisibilityOfElementLocated(formComponentLoc);

            MobileInteractions mobileInteractions = new MobileInteractions(driver);

            // way 1:
            mobileInteractions.openNotificationPanel();

            By notificationLoc = AppiumBy.id("com.android.systemui:id/expanded");
//            By notificationLoc = AppiumBy.androidUIAutomator(" new UiSelector().resourceId(\"com.android.systemui:id/expanded\")");

            // TODO: handle app_name_text
//            By notificationTitleLoc = AppiumBy.id("android:id/app_name_text");
//            By notificationTitleLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/app_name_text\")");
            By notificationTitleLoc = AppiumBy.id("android:id/title");
            By notificationMessageLoc = AppiumBy.id("android:id/big_text");

            List<WebElement> notificationEleList = driver.findElements(notificationLoc);

            if (notificationEleList.isEmpty()) {
                Assert.fail("No notifications found");
            }

            boolean notificationFound = false;
            for (WebElement notificationEle : notificationEleList) {

                // Narrow down searching scope
                WebElement notificationTitleEle = notificationEle.findElement(notificationTitleLoc);
                WebElement notificationMessageEle = notificationEle.findElement(notificationMessageLoc);
                String notificationTitleText = notificationTitleEle.getText();
                String notificationMessageText = notificationMessageEle.getText();
                if (notificationTitleText.equalsIgnoreCase(NOTIFICATION_TITLE) && notificationMessageText.equalsIgnoreCase(NOTIFICATION_MESSAGE)) {
                    notificationFound = true;
                    break;
                }
            }
            Assert.assertTrue(notificationFound);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void openNotifications(AppiumDriver appiumDriver) {
        // Swipe up before interacting
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Construct coordinators
        int startX = 50 * screenWidth / 100;
        int startY = 0;
        int endX = startX;
        int endY = 50 * screenHeight / 100;
        System.out.printf("%d %d %d %d ", startX, startY, endX, endY);

        // Specify PointerInput as [TOUCH] with name [finger1]
        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        // Specify sequence
        Sequence sequence = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, Duration.ofMillis(250)))
                .addAction(pointerInput.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.viewport(), endX, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Ask appium server to perform the sequence
        appiumDriver.perform(Collections.singletonList(sequence));
    }

}
