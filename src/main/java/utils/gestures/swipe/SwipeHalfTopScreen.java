package utils.gestures.swipe;

import constants.WaitConstants;
import io.appium.java_client.AppiumDriver;


public class SwipeHalfTopScreen extends SwipeVertically {
    public SwipeHalfTopScreen(AppiumDriver driver) {
        super(driver, 0.5, 0, 0.5, WaitConstants.FAST_MOVE);
    }

}
