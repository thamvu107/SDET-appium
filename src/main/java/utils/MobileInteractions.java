package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class MobileInteractions {
    private final AppiumDriver driver;
    private final MobileActions mobileActions;

    public MobileInteractions(AppiumDriver driver) {
        this.driver = driver;
        mobileActions = new MobileActions(this.driver);
    }

    public void swipeVertical() {

        Dimension screenSize = mobileActions.getScreenSize();
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();

        int x = screenWidth / 2;
        int startY = (int) (screenHeight * 0.80);
        int endY = (int) (screenHeight * 0.20);

        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, viewport(), x, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, ofMillis(250)))
                .addAction(pointerInput.createPointerMove(ofMillis(250), viewport(), x, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }
}
