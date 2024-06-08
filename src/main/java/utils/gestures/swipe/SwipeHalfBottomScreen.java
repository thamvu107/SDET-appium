package utils.gestures.swipe;

import constants.WaitConstants;
import io.appium.java_client.AppiumDriver;

public class SwipeHalfBottomScreen extends SwipeVertically {

    public SwipeHalfBottomScreen(AppiumDriver driver) {
        super(driver, 0.5, 0.5, 1, WaitConstants.FAST_MOVE);
    }

}
