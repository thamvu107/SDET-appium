package constants.android;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public interface IAndroidDriverSetting {
    Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofSeconds(400);
    Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT = ofSeconds(400);
    Duration UIAUTOMATOR2_SERVER_READY_TIMEOUT = ofSeconds(400);

    Duration NEW_COMMAND_TIMEOUT = ofSeconds(400);
    Duration ANDROID_INSTALL_TIMEOUT = ofSeconds(300);
}
