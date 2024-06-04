package driverFactory.capabilities;

import constants.ios.IOSAppSetting;
import devices.ios.IOSPhysicalMobile;
import devices.ios.Simulator;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSCapabilities extends XCUITestOptions implements IOSAppSetting {

    public static XCUITestOptions getSimulatorCaps(Simulator mobile) {

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

    public static XCUITestOptions getRealMobileCaps(IOSPhysicalMobile mobile) {

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
