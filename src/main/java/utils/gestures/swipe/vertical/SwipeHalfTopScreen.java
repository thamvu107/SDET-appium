package utils.gestures.swipe.vertical;

import io.appium.java_client.AppiumDriver;

import static constants.SwipeConstants.FAST_MOVE;


public class SwipeHalfTopScreen extends SwipeVertically {
    public SwipeHalfTopScreen(AppiumDriver driver) {
        super(driver, 0.5f, 0, 0.5f, FAST_MOVE);
    }

}
