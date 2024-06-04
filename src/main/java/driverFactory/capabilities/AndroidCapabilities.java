package driverFactory.capabilities;

import constants.android.AndroidAppSetting;
import constants.android.AndroidDriverSetting;
import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AndroidCapabilities extends UiAutomator2Options implements AndroidAppSetting, AndroidDriverSetting {

    public static UiAutomator2Options getEmulatorCaps(Emulator mobile) {

        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(mobile.getDeviceName())
                .setPlatformVersion(mobile.getPlatformVersion())
                .setAvd(mobile.getAdv())
                .setAvdLaunchTimeout(mobile.getAdvTimeout())
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false)
                .clearDeviceLogsOnStart();

        caps.setCapability("clearSystemFiles", true);
        // not going to run your tests in parallel
//        caps.setCapability("--session-override", true);

        // TODO: platform version value is null then it remove cap options
//        SetPlatformVersion.setPlatformVersion(mobile, caps);

        return caps;
    }

    public static UiAutomator2Options getRealMobileCaps(AndroidPhysicalMobile mobile) {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false);

        caps.setCapability("clearDeviceLogsOnStart", true);

        SetPlatformVersion.setPlatformVersion(mobile, caps);

        return caps;
    }

}
