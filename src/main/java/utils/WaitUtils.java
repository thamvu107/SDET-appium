package utils;

import constants.WaitConstant;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofMillis;

public class WaitUtils {

    public static WebDriverWait wait;

    public WaitUtils(AppiumDriver driver) {
        wait = new WebDriverWait(driver, ofMillis(WaitConstant.LONG_EXPLICIT_WAIT));
    }


}
