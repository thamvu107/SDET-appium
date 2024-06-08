package utils.gestures.swipe;

import enums.SwipeVerticalDirection;
import exceptions.SwipeDownException;
import exceptions.SwipeUpException;
import exceptions.SwipeVerticallyException;
import io.appium.java_client.AppiumDriver;

public class SwipeVertically extends Swipe {

    protected int topY;
    protected int bottomY;

    public SwipeVertically(AppiumDriver driver) {

        super(driver);
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

    public SwipeVertically(AppiumDriver driver, double anchorPercentage, double topPercent, double bottomPercent, long duration) {

        super(driver, anchorPercentage, topPercent, bottomPercent, duration);
        this.anchor = this.calculateAnchor();
        this.topY = this.calculateSmallCoordinate();
        this.bottomY = this.calculateLargeCoordinate();
    }

    @Override
    protected int calculateAnchor() {
        return (int) (screenSize.getWidth() * anchorPercent);
    }

    @Override
    protected int calculateSmallCoordinate() {
        return (int) (screenSize.getHeight() * smallerPercent);
    }

    @Override
    protected int calculateLargeCoordinate() {
        return (int) (screenSize.getHeight() * largerPercent);
    }

    public void swipeUp() {

        this.startCoordinate = bottomY;
        this.endCoordinate = topY;
        performSwipe(SwipeVerticalDirection.UP, anchor, this.startCoordinate, this.endCoordinate, duration);
    }

    public void swipeDown() {

        startCoordinate = topY;
        endCoordinate = bottomY;
        performSwipe(SwipeVerticalDirection.DOWN, anchor, startCoordinate, endCoordinate, duration);
    }

    private void performSwipe(SwipeVerticalDirection direction, int anchor, int startYCoordinate, int endYCoordinate, long duration) {

        validateYCoordinates(direction, startYCoordinate, endYCoordinate);
        swipe(anchor, startYCoordinate, anchor, endYCoordinate, duration);
    }

    private void validateYCoordinates(SwipeVerticalDirection direction, int startCoordinate, int endCoordinate) {
        switch (direction) {
            case UP:
                if (startCoordinate <= endCoordinate) {
                    throw new SwipeUpException("The start percentage should be greater than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
                }
                break;
            case DOWN:
                if (startCoordinate >= endCoordinate) {
                    throw new SwipeDownException("The start percentage should be less than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
                }
                break;
            default:
                throw new SwipeVerticallyException("Unknown swipe direction: " + direction);
        }
    }

//
//    protected int calculateAnchor() {
//        return (int) (screenSize.getWidth() * anchorPercent);
//    }
//
//    protected int calculateStartCoordinate() {
//        return (int) (screenSize.getHeight() * smallerPercent);
//    }
//
//    protected int calculateEndCoordinate() {
//        return (int) (screenSize.getHeight() * largerPercent);
//    }
}
