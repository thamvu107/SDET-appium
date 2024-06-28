package driverFactory.capabilities;

import constants.android.AndroidDriverSetting;
import constants.android.IAppUnderTest;
import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import entity.appUnderTest.AndroidAppUnderTest;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AndroidCapabilities extends UiAutomator2Options implements IAppUnderTest, AndroidDriverSetting {

    static AndroidAppUnderTest app = new AndroidAppUnderTest();


    public static UiAutomator2Options getEmulatorCaps(Emulator mobile) {

//        AndroidAppUnderTest app = new AndroidAppUnderTest();

        UiAutomator2Options caps = new UiAutomator2Options()
                .setDeviceName(mobile.getDeviceName())
                .setPlatformVersion(mobile.getPlatformVersion())
                .setAvd(mobile.getAdv())
                .setAvdLaunchTimeout(mobile.getAdvTimeout())
                .setAvdReadyTimeout(mobile.getAdvTimeout())
                .setApp(app.getAppPath())
                .setAppPackage(app.getAppPackage())
                .setAppActivity(app.getAppActivity())
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
                .setApp(app.getAppPath())
                .setAppPackage(app.getAppPackage())
                .setAppActivity(app.getAppActivity())
                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH)
                .setAppWaitDuration(APP_WAIT_FOR_LAUNCH_TIME)
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false);

        caps.setCapability("clearDeviceLogsOnStart", true);

        return caps;
    }

}
