package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NarrowDownSearchingScope {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(MobilePlatform.ANDROID);
        try {
            By formsBtnLoc = AppiumBy.accessibilityId("Forms");

            appiumDriver.findElement(formsBtnLoc).click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Form components\")")));

            // Swipe up before interacting
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            int startX = 50 * screenHeight / 100;
            int startY = 0;
            int endX = startX;
            int endY = 50 * screenHeight / 100;

            // Specify PointerInput as [TOUCH] with name [finger1]

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
