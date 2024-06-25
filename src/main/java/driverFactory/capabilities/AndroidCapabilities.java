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
                .setAvdReadyTimeout(mobile.getAdvTimeout())
                .setApp(System.getProperty("user.dir") + "src/test/resources/apps/dev/android.wdio.native.app.v1.0.8.apk")
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH)
                .setAppWaitDuration(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setUiautomator2ServerReadTimeout(UIAUTOMATOR2_SERVER_READY_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false)
                .clearDeviceLogsOnStart();

        caps.setCapability("clearSystemFiles", true);
        caps.setCapability("clearDeviceLogsOnStart", true);
        caps.setCapability("enableWebviewDetailsCollection", true);
        // not going to run your tests in parallel
//        caps.setCapability("--session-override", true);


        return caps;
    }

    public static UiAutomator2Options getRealMobileCaps(AndroidPhysicalMobile mobile) {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH)
                .setAppWaitDuration(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false);

        caps.setCapability("clearDeviceLogsOnStart", true);

        SetPlatformVersion.setPlatformVersion(mobile, caps);

        return caps;
    }

}
