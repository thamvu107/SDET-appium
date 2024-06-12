package utils.gestures.swipe;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.ScreenSize;

import java.time.Duration;
import java.util.Collections;

import static constants.SwipeConstants.*;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public abstract class Swipe {

    protected AppiumDriver driver;
    protected long moveDuration;
    protected long pauseDuration;
    protected WebElement wrapperElement;
    protected Point location;
    protected Dimension dimension;
    protected float anchorPercent;
    protected float smallerPercent;
    protected float largerPercent;
    protected int anchor;
    protected int startCoordinate;
    protected int endCoordinate;

    public Swipe(AppiumDriver driver) {
        setDriver(driver);
        setDefaultPercents();
        setDefaultDurations();
        setLocation();
        setWrapperDimension();
    }

    public Swipe(AppiumDriver driver, WebElement wrapperElement) {
        setDriver(driver);
        setDefaultPercents();
        setDefaultDurations();
        setWrapperElement(wrapperElement);
        setLocation();
        setWrapperDimension();
    }

    public Swipe(AppiumDriver driver, long duration) {
        setDriver(driver);
        setDefaultPercents();
        setMoveDuration(duration);
        setPauseDuration(SHORT_PAUSE);
        setLocation();
        setWrapperDimension();
    }

    public Swipe(AppiumDriver driver, long moveDuration, long pauseDuration) {
        setDriver(driver);
        setDefaultPercents();
        setMoveDuration(moveDuration);
        setPauseDuration(pauseDuration);
        setLocation();
        setWrapperDimension();
    }

    public Swipe(AppiumDriver driver, float anchorPercent, float smallerPercent, float largerPercent, long duration) {
        setDriver(driver);
        setSmallerPercent(smallerPercent);
        setLargerPercent(largerPercent);
        setAnchorPercent(anchorPercent);
        setMoveDuration(duration);
        setPauseDuration(SHORT_PAUSE);
        setLocation();
        setWrapperDimension();
    }

    protected void swipe(int startX, int startY, int endX, int endY, long moveDuration, long pauseDuration) {

        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence swipe = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, viewport(), startX, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, ofMillis(FIRST_PAUSE)))
                .addAction(pointerInput.createPointerMove(ofMillis(moveDuration), viewport(), endX, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, ofMillis(pauseDuration)));

        driver.perform(Collections.singletonList(swipe));
    }

    private void setDriver(AppiumDriver driver) {
        validateNotNull(driver, "AppiumDriver must not be null");
        this.driver = driver;
    }

    private void setDefaultPercents() {
        setAnchorPercent(DEFAULT_ANCHOR_PERCENT);
        setSmallerPercent(DEFAULT_SMALL_PERCENT);
        setLargerPercent(DEFAULT_LARGER_PERCENT);
    }

    private void setDefaultDurations() {
        setMoveDuration(FAST_MOVE);
        setPauseDuration(SHORT_PAUSE);
    }

    private void setWrapperElement(WebElement wrapper) {
        validateNotNull(wrapper, "Wrapper element must not be null");
        this.wrapperElement = wrapper;
    }

    private void setSmallerPercent(float smallerPercent) {
        validateSwipePercentCoordinates(smallerPercent);
        this.smallerPercent = smallerPercent;
    }

    private void setLargerPercent(float endPercentage) {
        validateSwipePercentCoordinates(endPercentage);
        this.largerPercent = endPercentage;
    }

    private void setAnchorPercent(float anchorPercent) {
        validateSwipePercentCoordinates(anchorPercent);
        this.anchorPercent = anchorPercent;
    }

    private void setMoveDuration(long duration) {
        validateMoveDuration(duration);
        this.moveDuration = duration;
    }

    private void setPauseDuration(long duration) {
        validatePauseDuration(duration);
        this.pauseDuration = duration;
    }


    private void setWrapperDimension() {
        this.dimension = this.wrapperElement == null ? new ScreenSize(this.driver).getDimension() : this.wrapperElement.getSize();
    }

    private void setLocation() {

        this.location = this.wrapperElement == null ? new Point(0, 0) : this.wrapperElement.getLocation();
    }

    protected void validateSwipePercentCoordinates(float percent) {
        if (percent < 0 || percent > 1) {
            throw new IllegalArgumentException("Swipe percentage must be between 0 and 1 inclusive. Given percentage: " + percent);
        }
    }

    private void validateMoveDuration(long duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Move duration must be positive.");
        }
        if (duration >= MAX_MOVE) {
            throw new IllegalArgumentException(String.format("Move duration is too long. Maximum allowed is" + MAX_MOVE));
        }
    }

    private void validatePauseDuration(long duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Pause duration must be positive.");
        }
        if (duration >= MAX_PAUSE) {
            throw new IllegalArgumentException(String.format("Pause duration is too long. Maximum allowed is" + MAX_PAUSE));
        }
    }

    private void validateNotNull(Object param, String message) {
        if (param == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected abstract int calculateAnchor();

    protected abstract int calculateSmallCoordinate();

    protected abstract int calculateLargeCoordinate();
}
