package driverFactory.capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;

import static constants.IOSConstants.BUNDLEID;
import static constants.IOSConstants.UDID;

public class IOSCapabilities {
    public static XCUITestOptions getCaps() {

        // TODO: handle to read caps value from config file
        XCUITestOptions caps = new XCUITestOptions();
        caps.setUdid(UDID);
        caps.setBundleId(BUNDLEID);
        caps.setCapability("setting[ignoreUNimportantViews]", true);

        return caps;
    }
}
