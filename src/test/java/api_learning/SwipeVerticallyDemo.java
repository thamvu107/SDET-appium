package api_learning;

import Utils.gestures.MobileInteractions;
import Utils.gestures.SwipeVertically;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

import java.util.Map;

import static devices.MobileFactory.getEmulator;
import static io.appium.java_client.AppiumBy.*;

public class SwipeVerticallyDemo {

    public static void main(String[] args) {

        AppiumDriver driver;

//        driver = DriverFactory.getMobileDriver(MobilePlatform.IOS);
//        driver = DriverFactory.getLocalServerDriver(MobileFactory.getSmallEmulator());
        DriverProvider driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);

        try {
            By formsBtnLoc = accessibilityId("Forms");
            By activeBtnLoc = accessibilityId("button-Active");
            By androidFormComponent = androidUIAutomator("new UiSelector(). textContains(\"Form components\")");
            By iosFormComponent = iOSNsPredicateString("name == \"Form components\" AND label == \"Form components\" AND value == \"Form components\"");

            Map<Platform, By> formComponentLocatorMap = Map.of(Platform.ANDROID, androidFormComponent, Platform.IOS, iosFormComponent);

            // Navigate to [Forms] screen
            driver.findElement(formsBtnLoc).click();

            // Make sure we are on the target screen before swiping up/down/left/right/any direction
            MobileInteractions interactions = new MobileInteractions(driver);
            By formComponentLoc = interactions.getLocatorIsMappedCurrentPlatform(formComponentLocatorMap);
            interactions.waitVisibilityOfElementLocated(formComponentLoc);

            SwipeVertically swipeVertically = new SwipeVertically(driver);

            swipeVertically.swipeUp();

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
