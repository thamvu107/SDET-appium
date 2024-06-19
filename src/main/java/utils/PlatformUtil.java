package utils;

import driverFactory.Platform;
import enums.Contexts;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import utils.expectedConditions.MyExpectedConditions;

import java.util.Set;

public class PlatformUtil {
    private AppiumDriver driver;
    public WaitUtils waitUtils;

    public PlatformUtil(AppiumDriver driver) {
        ValidationDriver.validateDriverNotNull(driver);
        this.driver = driver;
        this.waitUtils = new WaitUtils(this.driver);
    }

    public PlatformUtil() {
    }

    /**
     * Gets the current platform from the provided Appium driver.
     *
     * @return the current platform
     * @throws IllegalArgumentException if the driver is null
     */
    public Platform getCurrentPlatform() {

        Capabilities caps = driver.getCapabilities();
        String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);

        return Platform.fromString(currentPlatform);
    }

    public Boolean hasMultiContexts(AppiumDriver driver) {
        try {
            return waitUtils.explicitWait()
                    .until(MyExpectedConditions.hasMultipleContexts(driver));
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean hasMultiContexts(AppiumDriver driver, long timeoutInSeconds) {
        try {
            return waitUtils.createWebDriverWait(timeoutInSeconds)
                    .until(MyExpectedConditions.hasMultipleContexts(driver));
        } catch (Exception e) {
            return false;
        }
    }

    public Set<String> retrieveAvailableContexts(Platform platform) {
        return switch (platform) {
            case ANDROID -> ((AndroidDriver) driver).getContextHandles();
            case IOS -> ((IOSDriver) driver).getContextHandles();
            default -> throw new PlatformNotSupportException("Platform not supported: " + platform);
        };
    }


    private WebDriver switchContextByPlatform(Platform platform, String contextName) {
        return switch (platform) {
            case ANDROID -> ((AndroidDriver) driver).context(contextName);
            case IOS -> ((IOSDriver) driver).context(contextName);
            default -> throw new PlatformNotSupportException("Platform not supported: " + platform);
        };
    }
    

    private WebDriver switchContext(Platform currentPlatform, Contexts context) {
        Set<String> availableContexts = retrieveAvailableContexts(currentPlatform);
        String contextName = context.getContextName();

        if (availableContexts.contains(contextName)) {
            return switchContextByPlatform(currentPlatform, contextName);
        } else {
            throw new IllegalArgumentException("Context not found: " + contextName);
        }
    }

    public WebDriver switchToNativeContext(Platform currentPlatform, long timeoutInSeconds) {
        return switchToContext(currentPlatform, Contexts.NATIVE, timeoutInSeconds);
    }

    public WebDriver switchToWebViewContext(Platform currentPlatform, long timeoutInSeconds) {
        return switchToContext(currentPlatform, Contexts.WEBVIEW, timeoutInSeconds);
    }

    private WebDriver switchToContext(Platform currentPlatform, Contexts context, long timeoutInSeconds) {
        if (hasMultiContexts(driver, timeoutInSeconds)) {
            return switchContext(currentPlatform, context);
        } else {
            return driver;
        }
    }

}
