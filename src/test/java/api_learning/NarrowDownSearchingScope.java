package api_learning;

import driverFactory.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NarrowDownSearchingScope {
    public static void main(String[] args) {
        AppiumDriver driver;

//        driver = DriverFactory.getMobileDriver(MobilePlatform.ANDROID);
        driver = Driver.getLocalServerDriver(MobileFactory.getAndroidMobile());

        try {
            By formsBtnLoc = AppiumBy.accessibilityId("Forms");

            driver.findElement(formsBtnLoc).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Form components\")")));

            // Swipe up before interacting
            Dimension windowSize = driver.manage().window().getSize();
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
