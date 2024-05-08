package driver.capabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IOSDesiredCapabilities {
    public static DesiredCapabilities setCapabilities() {

        // DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.IOS);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME_OPTION, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.UDID_OPTION, "");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");

        return capabilities;
    }
}
