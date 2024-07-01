package driverFactory.capabilities;

import constants.android.IAndroidDriverSetting;
import constants.android.IAppUnderTest;
import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import entity.appUnderTest.AndroidAppUnderTest;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;

import static java.time.Duration.ofSeconds;


public class AndroidCapabilities extends UiAutomator2Options implements IAppUnderTest, IAndroidDriverSetting {

    static AndroidAppUnderTest app = new AndroidAppUnderTest();


    public static UiAutomator2Options getEmulatorCaps(Emulator mobile) {

//        AndroidAppUnderTest app = new AndroidAppUnderTest();

        UiAutomator2Options caps = new UiAutomator2Options()
                .setUiautomator2ServerLaunchTimeout(ofSeconds(240))//UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT // 30_000
                .setUiautomator2ServerInstallTimeout(ofSeconds(240))//(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT) //  20_000
                .setUiautomator2ServerReadTimeout(ofSeconds(240)) //UIAUTOMATOR2_SERVER_READY_TIMEOUT) // 240_000
                .setSkipServerInstallation(false)
                .setDeviceName(mobile.getDeviceName())
                .setPlatformVersion(mobile.getPlatformVersion())
                .setNewCommandTimeout(ofSeconds(120))//NEW_COMMAND_TIMEOUT)
                .setAdbExecTimeout(Duration.ofSeconds(180L)) // 20_000
                .setSuppressKillServer(true)
//                .setUdid("emulator-5554") // update later
                .setAvd(mobile.getAvd())
                .setAvdLaunchTimeout(mobile.getAvdLaunchTimeout())
                .setAvdReadyTimeout(mobile.getAvdReadyTimeout())
//                .setAvdEnv()
                .setApp(app.getAppPath())
                .setAppPackage(app.getAppPackage())
                .setAppWaitPackage(app.getAppPackage())
                .setAppActivity(app.getAppActivity())
                .setAppWaitActivity(app.getAppActivity())
                .setAppWaitForLaunch(app.isAppWaitForLaunch())
                .setAppWaitDuration(app.getAppWaitDuration())
                .setAutoGrantPermissions(app.isAutoGrantPermission())
                .setRemoteAppsCacheLimit(app.getRemoteAppCacheLimit())
                .setAndroidInstallTimeout(app.getAndroidInstallTimeout())

//                .setNetworkSpeed(String.valueOf(NetworkSpeed.LTE).toLowerCase()) // Original error: Cannot read properties of undefined (reading 'NETWORK_SPEED')
//                .setAvdArgs(mobile.getAvdArgs())
                .setIsHeadless(mobile.isHeadless())
                .setIgnoreHiddenApiPolicyError(true)
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
                .setAppWaitForLaunch(app.isAppWaitForLaunch())
                .setAppWaitDuration(app.getAppWaitDuration())
                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
                .setFullReset(false)
                .setNoReset(false);

        caps.setCapability("clearDeviceLogsOnStart", true);

        return caps;
    }

}
