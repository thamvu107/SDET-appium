package constants;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public interface AndroidDeviceConstants {
    String ADV_PLATFORM_VERSION = "13.0";

    String SMALL_AVD_DEVICE_NAME = "Small Phone API 33";
    String SMALL_AVD = "Small_Phone_API_33_1";
    int ADV_SYSTEMPORT = 8200;
    int SYSTEMPORT = 8300;

    String AVD_DEVICE_NAME = "Pixel 5 API 33";
    String AVD = "Pixel_5_API_33_1";
    Duration ADV_TIMEOUT = ofMillis(36000);
    Duration DEFAULT_ADV_TIMEOUT = ofMillis(60000);

    String ANDROID_MOBILE_UUID = "192.168.1.6:5555";
    String ANDROID_MOBILE_NAME = "Standard_PC__i440FX___PIIX__1996_";
    String TABLET_UUID = "192.168.1.7:5555";
    String TABLET_NAME = "Standard PC (i440FX + PIIX, 1996)";

}
