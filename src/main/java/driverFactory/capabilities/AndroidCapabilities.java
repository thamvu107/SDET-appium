package driverFactory.capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;
import mobildeDevices.Mobile;
import mobildeDevices.android.AndroidPhysicalMobile;
import mobildeDevices.android.Emulator;

import static constants.AndroidConstants.*;

public class AndroidCapabilities implements SetPlatformVersion {

    public static UiAutomator2Options getAndroidCaps(Mobile mobile) {

        return (mobile instanceof Emulator) ? getEmulatorCaps((Emulator) mobile) : getPhysicalDeviceCaps((AndroidPhysicalMobile) mobile);
    }

    public static UiAutomator2Options getEmulatorCaps(Emulator mobile) {

        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(mobile.getDeviceName())
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

        // Capabilities
        caps.setCapability("--session-override", true);
        caps.setCapability("clearSystemFiles", true);

        SetPlatformVersion.setPlatformVersion(mobile, caps);

        return caps;
    }

    public static UiAutomator2Options getPhysicalDeviceCaps(AndroidPhysicalMobile mobile) {

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

        SetPlatformVersion.setPlatformVersion(mobile, caps);

        return caps;
    }

}
