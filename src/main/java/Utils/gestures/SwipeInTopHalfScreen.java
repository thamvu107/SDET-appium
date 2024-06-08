package Utils.gestures;

import constants.WaitConstants;
import io.appium.java_client.AppiumDriver;


public class SwipeInTopHalfScreen extends SwipeVertically {
    public SwipeInTopHalfScreen(AppiumDriver driver) {
        super(driver, 0.5, 0, 0.5, WaitConstants.FAST_MOVE);
    }

}
