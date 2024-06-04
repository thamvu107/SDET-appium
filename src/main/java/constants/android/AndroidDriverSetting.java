package constants.android;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public interface AndroidDriverSetting {
    boolean APP_WAIT_FOR_LAUNCH_TIME = true;
    Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofMillis(60000);
    Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT = ofMillis(60000);
}
