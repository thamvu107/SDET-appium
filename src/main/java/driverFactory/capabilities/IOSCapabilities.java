package driverFactory.capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;
import mobildeDevices.IOSMobile;

import static constants.IOSConstants.*;

public class IOSCapabilities {
    public static XCUITestOptions getLocalCaps() {

        // TODO: handle to read caps value from config file
        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(DEVICE_NAME)
                .setUdid(UDID)
                .setPlatformVersion(PLATFORM_VERSION)
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);

        return caps;
    }

    public static XCUITestOptions getIOSCaps(IOSMobile mobile) {

        // TODO: handle to read caps value from config file
        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setPlatformVersion(mobile.getPlatformVersion())
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);

        return caps;
    }
}
