package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {

    private final By loginTab = accessibilityId("Login");
    private final By signUpTab = accessibilityId("button-sign-up-container");
    private final By email = accessibilityId("input-email");
    private final By password = accessibilityId("input-password");
    private final By confirmPassword = accessibilityId("input-repeat-password");
    private final By signUpBtn = accessibilityId("button-SIGN UP");
    private final By message = androidUIAutomator("new UiSelector().resourceId(\"android:id/message\")");
    private final By okBtn = androidUIAutomator("new UiSelector().resourceId(\"android:id/button1\")");
    private final WebDriverWait wait;

    public SignUpPage(final AndroidDriver driver) {
        this.wait = new WebDriverWait(driver, ofSeconds(10L));
    }

    public String signUp(final String userName, final String pass) {
        this.wait.until(elementToBeClickable(this.loginTab))
                .click();
        this.wait.until(elementToBeClickable(this.signUpTab))
                .click();
        this.wait.until(elementToBeClickable(this.email))
                .sendKeys(userName);
        this.wait.until(elementToBeClickable(this.password))
                .sendKeys(pass);
        this.wait.until(elementToBeClickable(this.confirmPassword))
                .sendKeys(pass);
        this.wait.until(elementToBeClickable(this.signUpBtn))
                .click();

        final var messageText = this.wait.until(visibilityOfElementLocated(this.message))
                .getText();
        this.wait.until(elementToBeClickable(this.okBtn))
                .click();

        return messageText;
    }
}
