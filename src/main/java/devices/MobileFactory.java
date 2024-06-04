package devices;

import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import devices.ios.IOSPhysicalMobile;
import devices.ios.Simulator;

import static constants.AndroidDeviceConstants.*;
import static constants.IOSDeviceConstants.*;


public class MobileFactory {

    public static Emulator getSmallEmulator() {

        Emulator mobile = new Emulator(SMALL_AVD_DEVICE_NAME, ADV_PLATFORM_VERSION, SMALL_AVD, ADV_SYSTEMPORT)
                .setAvdTimeout(ADV_TIMEOUT);

        return mobile;
    }

    public static Emulator getEmulator() {
        Emulator mobile = new Emulator(AVD_DEVICE_NAME, ADV_PLATFORM_VERSION, AVD)
                .setAvdTimeout(ADV_TIMEOUT);

//        Emulator mobile = new Emulator(AVD_DEVICE_NAME, ADV_PLATFORM_VERSION, AVD, ADV_SYSTEMPORT)
//                .setAvdTimeout(ADV_TIMEOUT);

        return mobile;
    }

    public static AndroidPhysicalMobile getAndroidMobile() {

        return new AndroidPhysicalMobile(ANDROID_MOBILE_UUID, ANDROID_MOBILE_NAME, ADV_PLATFORM_VERSION, SYSTEMPORT);
    }

    public static AndroidPhysicalMobile getAndroidTablet() {

        return new AndroidPhysicalMobile(TABLET_UUID, SYSTEMPORT);
    }

    public static Simulator getSimulator() {

        return new Simulator(SIMULATOR_UDID, SIMULATOR_DEVICE_NAME, SIMULATOR_PLATFORM_VERSION, WDA_LOCAL_PORT);
    }

    public static IOSPhysicalMobile getIOSsMobile() {

        return new IOSPhysicalMobile(PHYSICAL_UDID, WDA_LOCAL_PORT);
    }

}
