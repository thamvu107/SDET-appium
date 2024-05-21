package driverFactory.capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;
import mobildeDevices.Emulator;
import mobildeDevices.Mobile;

import static constants.AndroidConstants.*;


public class AndroidCapabilities {

    public static UiAutomator2Options getLocalCaps() {

        // TODO: handle to read caps value from config file
        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformVersion(PLATFORM_VERSION)
                .setDeviceName(AVD_DEVICE_NAME)
                .setAvd(AVD)
                .setAvdLaunchTimeout(ADV_TIMEOUT)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setFullReset(false)
                .setNoReset(false)
                .clearDeviceLogsOnStart()
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT);
        caps.setCapability("--session-override", true);
        caps.setCapability("clearDeviceLogsOnStart", true);
        caps.setCapability("clearSystemFiles", true);

        return caps;
    }

    public static UiAutomator2Options getRemoteCaps() {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setUdid(ANDROID_MOBILE_UUID)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setFullReset(false)
                .setNoReset(false)
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT);
        caps.setCapability("--session-override", true);
        caps.setCapability("clearDeviceLogsOnStart", true);

        return caps;
    }

    public static UiAutomator2Options getEmulatorCaps(Mobile mobile) {

        Emulator emulator = (Emulator) mobile;

        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(emulator.getDeviceName())
                .setAvd(emulator.getAdv())
                .setAvdLaunchTimeout(emulator.getAdvTimeout())
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false)
                .clearDeviceLogsOnStart();

        // Set platform version if available
        if (emulator.getPlatformVersion() != null) {
            caps.setPlatformVersion(emulator.getPlatformVersion());
        }

        // Capabilities
        caps.setCapability("--session-override", true);
        caps.setCapability("clearSystemFiles", true);

        return caps;
    }


    public static UiAutomator2Options getPhysicalDeviceCaps(Mobile mobile) {

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
        caps.setCapability("--session-override", true);
        caps.setCapability("clearDeviceLogsOnStart", true);

        return caps;
    }

}
