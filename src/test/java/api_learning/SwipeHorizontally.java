package api_learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import pageObjects.screens.SwipeScreen;

import static constants.SwipeScreenConstants.SWIPE_SCREEN_TITLE;
import static devices.MobileFactory.getEmulator;

public class SwipeHorizontally {
    public static void main(String[] args) {


        AppiumDriver driver;
        DriverProvider driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        try {
            SwipeScreen swipeScreen = new SwipeScreen(driver);
            swipeScreen.goToSwipeScreen()
                    .verifySwipeScreenDisplayed()
                    .verifySwipeScreenTitle(SWIPE_SCREEN_TITLE)
                    .verifyCarouselDisplayed();
            swipeScreen.swipeLeftToTargetCard("SUPPORT VIDEOS", 5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }

    }

    // Method to extract the numeric part from the element name
    private static int extractNumericPart(String element) {
        // Assuming the numeric part is at the end of the string
        String numericPart = element.substring(element.lastIndexOf('_') + 1, element.lastIndexOf('_') + 2);
        return Integer.parseInt(numericPart);
    }
}
