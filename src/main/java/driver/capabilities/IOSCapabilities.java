package driver.capabilities;

import constants.ios.IOSAppSetting;
import io.appium.java_client.ios.options.XCUITestOptions;

import static constants.IOSDeviceConstants.*;

public class IOSCapabilities implements IOSAppSetting {
    public static XCUITestOptions setCapabilities() {

        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(SIMULATOR_DEVICE_NAME)
                .setPlatformName(SIMULATOR_PLATFORM_VERSION)
                .setUdid(SIMULATOR_UDID)
                .setPlatformVersion("17.4")
                .setBundleId(BUNDLEID)
                .clearSystemFiles();

        return caps;
    }
}
