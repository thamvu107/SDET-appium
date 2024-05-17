package driver.capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;

import static constants.AndroidConstants.*;

public class AndroidCapabilities {
    public static UiAutomator2Options setCapabilities() {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformVersion(PLATFORM_VERSION)
                .setDeviceName(LOCAL_DEVICE_NAME)
                .setAvd(LOCAL_ADV)
                .setAvdLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY);
        caps.setCapability("appium:settings[ignoreUnimportantViews]", true);

        return caps;
    }
}
