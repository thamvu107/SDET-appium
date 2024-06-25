package driver.capabilities;

import constants.android.AndroidAppSetting;
import constants.android.AndroidDriverSetting;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;

import static constants.AndroidDeviceConstants.*;

public class AndroidCapabilities implements AndroidAppSetting, AndroidDriverSetting {
    public static UiAutomator2Options setCapabilities() {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformVersion(ADV_PLATFORM_VERSION)
                .setDeviceName(AVD_DEVICE_NAME)
                .setAvd(AVD)
                .setAvdLaunchTimeout(Duration.ofMillis(240_000L))
                .setAvdReadyTimeout(Duration.ofMillis(240_000L))
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH)
                .setAppWaitDuration(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setUiautomator2ServerReadTimeout(UIAUTOMATOR2_SERVER_READY_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false)
                .clearDeviceLogsOnStart()
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY);
        caps.setCapability("appium:settings[ignoreUnimportantViews]", true);
        caps.setCapability("clearSystemFiles", true);

        return caps;
    }
}
