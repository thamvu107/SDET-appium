package Utils;

import Utils.gestures.SwipeInTopHalfScreen;
import io.appium.java_client.AppiumDriver;

public class NotificationUtils {
    SwipeInTopHalfScreen swipe;

    public NotificationUtils(AppiumDriver driver) {
        swipe = new SwipeInTopHalfScreen(driver);
    }

    public void openNotificationPanel() {

        swipe.swipeDown();
    }

    public void closeNotificationPanel() {

        swipe.swipeUp();

    }
}
