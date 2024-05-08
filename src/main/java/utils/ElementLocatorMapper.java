package utils;

import driverFactory.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Require;

import java.util.List;
import java.util.Map;

public class ElementLocatorMapper {
    private final AppiumDriver driver;

    // TODO: try the way handle Element mapper.

    public ElementLocatorMapper(AppiumDriver driver) {
        this.driver = driver;
    }

//    public WebElement findElement(Map<Platforms, By> locatorMap) {
//        By elementLocator = locatorMap.get(Platforms.valueOf(getCurrentPlatform()));
//
//        return this.driver.findElement(elementLocator);
//    }
//
//    public List<WebElement> findElements(Map<Platforms, By> locatorMap) {
//
//        By elementLocator = locatorMap.get(Platforms.valueOf(getCurrentPlatform()));
//
//        return this.driver.findElements(elementLocator);
//    }

    public WebElement findElement(Map<MobilePlatform, By> locatorMap) {
        By elementLocator = locatorMap.get(MobilePlatform.valueOf(getCurrentPlatform()));

        System.out.println(locatorMap);
        System.out.println(elementLocator);

        return this.driver.findElement(elementLocator);
    }

    public List<WebElement> findElements(Map<MobilePlatform, By> locatorMap) {

        By elementLocator = locatorMap.get(MobilePlatform.valueOf(getCurrentPlatform()));

        return this.driver.findElements(elementLocator);
    }

    // MobilePlatform
    public WebElement findMobileElement(Map<MobilePlatform, By> locatorMap) {

        Require.nonNull("Locator", locatorMap);
        By elementLocator = locatorMap.get(MobilePlatform.valueOf(getCurrentPlatform()));

        return this.driver.findElement(elementLocator);
    }

    public List<WebElement> findMobileElements(Map<MobilePlatform, By> locatorMap) {

        Require.nonNull("Locator", locatorMap);
        By elementLocator = locatorMap.get(MobilePlatform.valueOf(getCurrentPlatform()));
        return this.driver.findElements(elementLocator);
    }

    /* Check if an element is existed by its locator */
    public boolean isElementPresent(Map<MobilePlatform, By> locatorMap) {

        List<WebElement> elements = this.findElements(locatorMap);

        return !elements.isEmpty();
    }

    private String getCurrentPlatform() {
        Capabilities caps = this.driver.getCapabilities();

        return CapabilityHelpers.getCapability(caps, "platformName", String.class);
    }
}
