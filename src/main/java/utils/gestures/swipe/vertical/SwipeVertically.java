package utils.gestures.swipe.vertical;

import enums.SwipeVerticalDirection;
import exceptions.swipe.vertical.SwipeDownException;
import exceptions.swipe.vertical.SwipeUpException;
import exceptions.swipe.vertical.SwipeVerticallyException;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.gestures.swipe.Swipe;

public class SwipeVertically extends Swipe {

    protected int topY;
    protected int bottomY;

    public SwipeVertically(AppiumDriver driver) {

        super(driver);
        this.anchor = this.calculateAnchor();
        this.topY = this.calculateSmallCoordinate();
        this.bottomY = this.calculateLargeCoordinate();
    }

    public SwipeVertically(AppiumDriver driver, WebElement wrapper) {

        super(driver, wrapper);
        this.anchor = this.calculateAnchor();
        this.topY = this.calculateSmallCoordinate();
        this.bottomY = this.calculateLargeCoordinate();
    }


    public SwipeVertically(AppiumDriver driver, long duration) {

        super(driver, duration);
        this.anchor = this.calculateAnchor();
        this.topY = this.calculateSmallCoordinate();
        this.bottomY = this.calculateLargeCoordinate();

    }

    public SwipeVertically(AppiumDriver driver, float anchorPercentage, float topPercent, float bottomPercent, long duration) {

        super(driver, anchorPercentage, topPercent, bottomPercent, duration);
        this.anchor = this.calculateAnchor();
        this.topY = this.calculateSmallCoordinate();
        this.bottomY = this.calculateLargeCoordinate();
    }

    @Override
    protected int calculateAnchor() {
        return location.getX() + Math.round(dimension.getWidth() * anchorPercent);
    }

    @Override
    protected int calculateSmallCoordinate() {
        return location.getY() + Math.round(dimension.getHeight() * smallerPercent);
    }

    @Override
    protected int calculateLargeCoordinate() {
        return location.getY() + Math.round(dimension.getHeight() * largerPercent);
    }

    public void swipeUp() {
        performVerticalSwipe(SwipeVerticalDirection.UP);
    }

    public void swipeDown() {
        performVerticalSwipe(SwipeVerticalDirection.DOWN);
    }

    private void performVerticalSwipe(SwipeVerticalDirection direction) {

        setYCoordinates(direction);
        swipe(anchor, this.startCoordinate, anchor, this.endCoordinate, moveDuration, pauseDuration);
    }

    private void setYCoordinates(SwipeVerticalDirection direction) {
        switch (direction) {
            case UP:
                this.startCoordinate = bottomY;
                this.endCoordinate = topY;
                if (startCoordinate <= endCoordinate) {
                    throw new SwipeUpException("The start percentage should be greater than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
                }
                break;
            case DOWN:
                this.startCoordinate = topY;
                this.endCoordinate = bottomY;
                if (startCoordinate >= endCoordinate) {
                    throw new SwipeDownException("The start percentage should be less than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
                }
                break;
            default:
                throw new SwipeVerticallyException("Unsupported swipe direction: " + direction);
        }
    }
}
