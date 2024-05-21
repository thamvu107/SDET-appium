package driverFactory.capabilities;

import exceptions.PlatformNotSupportException;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import mobildeDevices.Emulator;
import mobildeDevices.Mobile;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;

import static constants.AndroidConstants.*;
import static constants.IOSConstants.BUNDLEID;

public class CapabilitiesManager {

    public static Capabilities getCaps(Mobile mobile) {
        switch (mobile.getPlatformName()) {
            case ANDROID:
                return getAndroidCaps(mobile);
            case IOS:
                return getIOSCaps(mobile);
            default:
                throw new PlatformNotSupportException("Platform " + mobile.getPlatformName() + " is not supported");
        }
    }

    public static UiAutomator2Options getAndroidCaps(Mobile mobile) {

        return (mobile instanceof Emulator) ? getEmulatorCaps((Emulator) mobile) : getPhysicalDeviceCaps(mobile);
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

        setPlatformVersion(mobile, caps);

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

        setPlatformVersion(mobile, caps);

        return caps;
    }

    public static XCUITestOptions getIOSCaps(Mobile mobile) {

        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);

        setPlatformVersion(mobile, caps);

        return caps;
    }

    private static <T extends MutableCapabilities> T setPlatformVersion(Mobile mobile, T caps) {
        // Set platform version if available
        if (mobile.getPlatformVersion() != null) {
            caps.setCapability("platformVersion", mobile.getPlatformVersion());
        }

        return caps;
    }

}
