package capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class IOSDesiredCapabilities {
    public static DesiredCapabilities setCapabilities() {

        // DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(AndroidCapabilityType.AUTOMATION_NAME_OPTION, "XCUITest");
        capabilities.setCapability(AndroidCapabilityType.UDID_OPTION, "");
        capabilities.setCapability(AndroidCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        capabilities.setCapability(AndroidCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");

        return capabilities;
    }
}
