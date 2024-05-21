package driverFactory.capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;
import mobildeDevices.Mobile;
import mobildeDevices.ios.IOSMobile;

import static constants.IOSConstants.BUNDLEID;

public class IOSCapabilities {

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

    public static XCUITestOptions getIOSCaps(Mobile mobile) {

        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);

        SetPlatformVersion.setPlatformVersion(mobile, caps);

        return caps;
    }

}
