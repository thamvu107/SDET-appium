package utils.expectedConditions;

import driverFactory.Platform;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.PlatformUtil;
import utils.ValidationDriver;

public class isMultipleContexts implements ExpectedCondition<Boolean> {
    private final AppiumDriver driver;

    public isMultipleContexts(AppiumDriver driver) {
        ValidationDriver.validateDriverNotNull(driver);
        this.driver = driver;
    }

    public Boolean apply(WebDriver input) {
        Platform currentPlatform = new PlatformUtil(driver).getCurrentPlatform();
        switch (currentPlatform) {
            case ANDROID:
                return ((AndroidDriver) driver).getContextHandles().size() > 1;
            case IOS:
                return ((IOSDriver) driver).getContextHandles().size() > 1;
            default:
                throw new PlatformNotSupportException("Platform not supported: " + currentPlatform);
        }
    }

    public String toString() {
        return "More than one context";
    }
}
