package utils.gestures.swipe.vertical;

import io.appium.java_client.AppiumDriver;

import static constants.SwipeConstants.FAST_MOVE;

public class SwipeHalfBottomScreen extends SwipeVertically {

    public SwipeHalfBottomScreen(AppiumDriver driver) {
        super(driver, 0.5f, 0.5f, 1, FAST_MOVE);
    }

}
