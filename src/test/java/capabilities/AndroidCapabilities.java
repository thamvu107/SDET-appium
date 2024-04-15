package capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidCapabilities {
    public static UiAutomator2Options setOptions() {

        // Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformVersion("14");
        options.setDeviceName("Pixel 5 API 34");
        options.setAvd("Pixel_5_API_34");
        options.setAppPackage("com.wdiodemoapp");
        options.setAppActivity("com.wdiodemoapp.MainActivity");
        options.setCapability("appium:settings[ignoreUnimportantViews]", true);
        options.setPrintPageSourceOnFindFailure(true);

        return options;
    }
}
