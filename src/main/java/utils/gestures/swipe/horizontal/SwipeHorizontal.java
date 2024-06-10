package utils.gestures.swipe.horizontal;

import enums.SwipeHorizontalDirection;
import exceptions.swipe.horizontal.SwipeHorizontalException;
import exceptions.swipe.horizontal.SwipeLeftException;
import exceptions.swipe.horizontal.SwipeRightException;
import io.appium.java_client.AppiumDriver;
import utils.gestures.swipe.Swipe;

public class SwipeHorizontal extends Swipe {
    protected int leftX;
    protected int rightX;

    public SwipeHorizontal(AppiumDriver driver) {
        super(driver);
        calculateCoordinates();
    }

    private void calculateCoordinates() {
        this.anchor = this.calculateAnchor();
        this.leftX = this.calculateSmallCoordinate();
        this.rightX = this.calculateLargeCoordinate();
    }

    public SwipeHorizontal(AppiumDriver driver, long duration) {
        super(driver, duration);
        calculateCoordinates();
    }

    public SwipeHorizontal(AppiumDriver driver, float anchorPercent, float smallerPercent, float largerPercent, long duration) {
        super(driver, anchorPercent, smallerPercent, largerPercent, duration);
        calculateCoordinates();
    }

    @Override
    protected int calculateAnchor() {
        return Math.round(screenSize.getHeight() * anchorPercent);
    }

    @Override
    protected int calculateSmallCoordinate() {
        return Math.round(screenSize.getWidth() * smallerPercent);
    }

    @Override
    protected int calculateLargeCoordinate() {
        return Math.round(screenSize.getWidth() * largerPercent);
    }

    public void swipeToLeft() {
        performHorizontalSwipe(SwipeHorizontalDirection.LEFT);
    }

    public void swipeToRight() {
        performHorizontalSwipe(SwipeHorizontalDirection.RIGHT);
    }

    private void performHorizontalSwipe(SwipeHorizontalDirection direction) {
        setXCoordinates(direction);
        swipe(startCoordinate, anchor, endCoordinate, anchor, moveDuration, pauseDuration);
    }

    private void setXCoordinates(SwipeHorizontalDirection direction) {
        switch (direction) {
            case LEFT:
                this.startCoordinate = rightX;
                this.endCoordinate = leftX;
                if (startCoordinate <= endCoordinate) {
                    throw new SwipeLeftException("The start percentage should be greater than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
                }
                break;

            case RIGHT:
                this.startCoordinate = leftX;
                this.endCoordinate = rightX;
                if (startCoordinate >= endCoordinate) {
                    throw new SwipeRightException("The start percentage should be less than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
                }
                break;

            default:
                throw new SwipeHorizontalException("Unsupported swipe direction: " + direction);
        }
    }
}
