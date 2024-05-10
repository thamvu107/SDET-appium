package models.screens;

import constants.WaitConstant;
import io.appium.java_client.AppiumDriver;
import models.commponents.BottomNavComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementLocatorMapper;

import static java.time.Duration.ofMillis;

public class BaseScreen {

    protected final AppiumDriver driver;
    protected WebDriverWait wait;

    protected ElementLocatorMapper elementHandler;

    protected BottomNavComponent bottomNavComponent;

    protected BaseScreen(final AppiumDriver driver) {
        this.driver = driver;
        elementHandler = new ElementLocatorMapper(this.driver);
        bottomNavComponent = new BottomNavComponent(this.driver);
    }

//    protected BottomNavComponent bottomNavComponent() {
//        return new BottomNavComponent(this.driver);
//    }

    protected void waitForVisibility(WebElement element) {

        //TODO : custom wait

        WebDriverWait wait = new WebDriverWait(this.driver, ofMillis(WaitConstant.SHORT_EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForInvisibility(WebElement element) {

        WebDriverWait wait = new WebDriverWait(this.driver, ofMillis(WaitConstant.SHORT_EXPLICIT_WAIT));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }
}
