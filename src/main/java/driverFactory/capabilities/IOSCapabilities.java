package driverFactory.capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;

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

    public static XCUITestOptions getRemoteCaps() {

        // TODO: replace remote cap body after setting remote server (Now still keep local cap body)
        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(DEVICE_NAME)
                .setUdid(UDID)
                .setPlatformVersion(PLATFORM_VERSION)
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);

        return caps;
    }
}
