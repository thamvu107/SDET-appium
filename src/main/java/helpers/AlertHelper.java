package helpers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public class AlertHelper {
    private final AppiumDriver driver;

    public AlertHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    public void closeAlertIfPresent() {
        if (isAlertPresent()) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No alert was present.");
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
