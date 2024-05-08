package driver.capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSCapabilities {
    public static XCUITestOptions setCapabilities() {

        XCUITestOptions caps = new XCUITestOptions();
        caps.setUdid("emulator-5554");
        caps.setDeviceName("Pixel 5 API 34");
        // not yet complete and tobe continue in next lesson

        return caps;
    }
}
