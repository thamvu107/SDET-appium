package driver.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDesiredCapabilities {
    public static DesiredCapabilities setCapabilities() {

        // DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME_OPTION, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");

        return capabilities;
    }
}
