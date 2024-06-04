package driverFactory.capabilities;

import devices.Mobile;
import org.openqa.selenium.MutableCapabilities;

public interface SetPlatformVersion {
    static <T extends MutableCapabilities> T setPlatformVersion(Mobile mobile, T caps) {
        // Set platform version if available
        if (mobile.getPlatformVersion() != null) {
            caps.setCapability("platformVersion", mobile.getPlatformVersion());
        }

        return caps;
    }
}
