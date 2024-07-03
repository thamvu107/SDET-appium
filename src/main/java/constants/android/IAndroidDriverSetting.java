package constants.android;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public interface IAndroidDriverSetting {
    Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofSeconds(90); // 30_000ms
    Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT = ofSeconds(60); // 20_000ms
    Duration UIAUTOMATOR2_SERVER_READY_TIMEOUT = ofSeconds(300); // 240_000ms

    Duration NEW_COMMAND_TIMEOUT = ofSeconds(120); // 60 seconds
    Duration ADB_EXEC_TIMEOUT = ofSeconds(50); // 20_000ms
    Duration ANDROID_INSTALL_TIMEOUT = ofSeconds(120); // 90_000ms
}
