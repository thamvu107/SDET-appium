package utils.gestures.swipe;

import constants.WaitConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.ScreenSize;

import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public abstract class Swipe {
    protected AppiumDriver driver;
    protected double smallerPercent;
    protected double largerPercent;
    protected double anchorPercent;
    protected long duration;

    protected ScreenSize screenSize;

    protected int anchor;
    protected int startCoordinate;
    protected int endCoordinate;

    public Swipe(AppiumDriver driver) {
        this.driver = driver;
        setAnchorPercent(0.5);
        setSmallerPercent(0.2);
        setLargerPercent(0.8);
        setDuration(WaitConstants.FAST_MOVE);
        this.screenSize = new ScreenSize(this.driver);
    }

    public Swipe(AppiumDriver driver, long duration) {
        this.driver = driver;
        setSmallerPercent(0.2);
        setLargerPercent(0.8);
        setAnchorPercent(0.5);
        setDuration(duration);
        this.screenSize = new ScreenSize(this.driver);
    }

    public Swipe(AppiumDriver driver, double anchorPercent, double smallerPercent, double largerPercent, long duration) {

        this.driver = driver;
        setSmallerPercent(smallerPercent);
        setLargerPercent(largerPercent);
        setAnchorPercent(anchorPercent);
        setDuration(duration);
        this.screenSize = new ScreenSize(this.driver);
    }

    protected void swipe(int startX, int startY, int endX, int endY, long duration) {

        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence swipe = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, viewport(), startX, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, ofMillis(WaitConstants.SHORT_PAUSE)))
                .addAction(pointerInput.createPointerMove(ofMillis(duration), viewport(), endX, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    private void setSmallerPercent(double smallerPercent) {
        validateSwipePercentCoordinates(smallerPercent);
        this.smallerPercent = smallerPercent;
    }

    private void setLargerPercent(double endPercentage) {
        validateSwipePercentCoordinates(endPercentage);
        this.largerPercent = endPercentage;
    }

    private void setAnchorPercent(double anchorPercent) {
        validateSwipePercentCoordinates(anchorPercent);
        this.anchorPercent = anchorPercent;
    }

    private void setDuration(long duration) {
        validateDuration(duration);
        this.duration = duration;
    }

    protected void validateSwipePercentCoordinates(double percent) {
        if (percent < 0 || percent > 1) {
            throw new IllegalArgumentException("Swipe percentage must be between 0 and 1 inclusive. Given percentage: " + percent);
        }
    }

    private void validateDuration(long duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Move duration must be positive.");
        }
        if (duration >= 1200) {
            throw new IllegalArgumentException("Move duration is too long. Maximum allowed is 1200ms.");
        }
    }

    protected abstract int calculateAnchor();

    protected abstract int calculateSmallCoordinate();

    protected abstract int calculateLargeCoordinate();
}
