package driverFactory.capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;

import static constants.AndroidConstants.*;


public class AndroidCapabilities {
    //private static final File app = new File(ClassLoader.getSystemResource(APP_FILE_NAME).getFile());
    private static final File apkPath = new File(System.getProperty("user.dir") + "/src/test/resources/apps");
    private static final File appFile = new File(apkPath, APP_FILE_NAME);

    public static UiAutomator2Options getLocalCaps() {

        // TODO: handle to read caps value from config file
        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformVersion(PLATFORM_VERSION)
                .setDeviceName(LOCAL_DEVICE_NAME)
                .setAvd(LOCAL_ADV)
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
//        caps.setCapability("autoLaunch", true);

        return caps;
    }

    public static UiAutomator2Options getRemoteCaps() {

        // Capabilities
        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(REMOTE_DEVICE_NAME)
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
}
