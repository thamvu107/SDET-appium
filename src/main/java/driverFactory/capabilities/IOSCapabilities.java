package driverFactory.capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;

import static constants.IOSConstants.*;

public class IOSCapabilities {
    public static XCUITestOptions getCaps() {

        // TODO: handle to read caps value from config file
        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(DEVICE_NAME)
                .setPlatformName(PLATFORM_VERSION)
                .setUdid(UDID)
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("setting[ignoreUNimportantViews]", true);
//        caps.setCapability("–session-override", true);


        return caps;
    }
}
