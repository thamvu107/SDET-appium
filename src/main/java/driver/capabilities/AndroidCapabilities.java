package driver.capabilities;

import constants.android.AndroidAppSetting;
import constants.android.AndroidDriverSetting;
import io.appium.java_client.android.options.UiAutomator2Options;

import static constants.AndroidDeviceConstants.*;

public class AndroidCapabilities implements AndroidAppSetting, AndroidDriverSetting {
    public static UiAutomator2Options setCapabilities() {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformVersion(ADV_PLATFORM_VERSION)
                .setDeviceName(AVD_DEVICE_NAME)
                .setAvd(AVD)
                .setAvdLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY);
        caps.setCapability("appium:settings[ignoreUnimportantViews]", true);

        return caps;
    }
}
