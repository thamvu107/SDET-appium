package constants.android;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public interface AndroidDriverSetting {
    boolean APP_WAIT_FOR_LAUNCH = true;
    Duration APP_WAIT_FOR_LAUNCH_TIME = Duration.ofMillis(180_000L);
    Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofMillis(180_0000L);
    Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT = ofMillis(180_000L);
    Duration UIAUTOMATOR2_SERVER_READY_TIMEOUT = ofMillis(180_000L);

    Duration NEW_COMMAND_TIMEOUT = ofMillis(240_000L);
}
