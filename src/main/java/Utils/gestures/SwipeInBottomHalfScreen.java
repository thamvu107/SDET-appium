package Utils.gestures;

import constants.WaitConstants;
import io.appium.java_client.AppiumDriver;

public class SwipeInBottomHalfScreen extends SwipeVertically {

    public SwipeInBottomHalfScreen(AppiumDriver driver) {
        super(driver, 0.5, 0.5, 1, WaitConstants.FAST_MOVE);
    }

}
