package constants;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public interface AndroidConstants {
    String APP_PACKAGE = "com.wdiodemoapp";
    String APP_ACTIVITY = "com.wdiodemoapp.MainActivity";
    String APP_FILE_NAME = "android.wdio.native.app.v1.0.8.apk";
    String PLATFORM_VERSION = "13.0";

//    String LOCAL_DEVICE_NAME = "Pixel_5_API_33";
//    String LOCAL_ADV = "Pixel_5_API_33";

    String AVD_DEVICE_NAME = "Pixel 5 API 33";
    String AVD = "Pixel_5_API_33_1";
    Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofMillis(60000);
    Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT = ofMillis(60000);
    Duration ADV_TIMEOUT = ofMillis(36000);
    Duration DEFAULT_ADV_TIMEOUT = ofMillis(60000);

    String ANDROID_MOBILE_UUID = "192.168.1.14:5555";
    String ANDROID_MOBILE_NAME = "Standard_PC__i440FX___PIIX__1996_";
    String REMOTE_TABLET_UUID = "192.168.1.15:5555";
    boolean APP_WAIT_FOR_LAUNCH_TIME = true;
    String APP_FILE_PATH = "/src/test/resources/apps/wdiodemoapp.app";

}
