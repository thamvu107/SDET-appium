package api_learning;

import driverFactory.Driver;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import utils.MobileInteractions;

import java.util.Map;

import static io.appium.java_client.AppiumBy.*;

public class SwipeVertically {
    public static void main(String[] args) {

        AppiumDriver driver;

//        driver = DriverFactory.getMobileDriver(MobilePlatform.IOS);
        driver = Driver.getLocalServerDriver(MobileFactory.getSmallEmulator());

        try {
            By formsBtnLoc = accessibilityId("Forms");
            By activeBtnLoc = accessibilityId("button-Active");
            By androidFormComponent = androidUIAutomator("new UiSelector(). textContains(\"Form components\")");
            By iosFormComponent = iOSNsPredicateString("name == \"Form components\" AND label == \"Form components\" AND value == \"Form components\"");

            Map<Platform, By> formComponentLocatorMap = Map.of(
                    Platform.ANDROID, androidFormComponent,
                    Platform.IOS, iosFormComponent
            );

            // Navigate to [Forms] screen
            driver.findElement(formsBtnLoc).click();

            // Make sure we are on the target screen before swiping up/down/left/right/any direction
            MobileInteractions interactions = new MobileInteractions(driver);
            By formComponentLoc = interactions.getLocatorIsMappedCurrentPlatform(formComponentLocatorMap);
            interactions.waitVisibilityOfElementLocated(formComponentLoc);

            MobileInteractions mobileInteractions = new MobileInteractions(driver);
            int screenWidth = mobileInteractions.getScreenWith();
            int screenHeight = mobileInteractions.getScreenHeight();
            int x = screenWidth / 2;
            int startY = (int) (screenHeight * 0.80);
            int endY = (int) (screenHeight * 0.20);

            Point startPoint = new Point(x, startY);
            Point endPoint = new Point(x, endY);

            interactions.swipeVertical(startPoint, endPoint);

            // Interact with element on the screen
            driver.findElement(activeBtnLoc).click();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // DEBUG PURPOSE ONLY
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {
        }

        driver.quit();
    }


}
