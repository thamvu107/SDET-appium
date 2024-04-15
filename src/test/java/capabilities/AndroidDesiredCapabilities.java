package capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDesiredCapabilities {
    public static DesiredCapabilities setCapabilities() {

        // DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(AndroidCapabilityType.AUTOMATION_NAME_OPTION, "uiautomator2");
        capabilities.setCapability(AndroidCapabilityType.DEVICE_NAME_OPTION, "emulator-5554");
        capabilities.setCapability(AndroidCapabilityType.APP_PACKAGE_OPTION, "com.wdiodemoapp");
        capabilities.setCapability(AndroidCapabilityType.APP_ACTIVITY_OPTION, "com.wdiodemoapp.MainActivity");

        return capabilities;
    }
}
