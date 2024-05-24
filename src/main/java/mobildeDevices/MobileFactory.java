package mobildeDevices;

import constants.IOSConstants;
import mobildeDevices.android.AndroidPhysicalMobile;
import mobildeDevices.android.Emulator;
import mobildeDevices.ios.IOSPhysicalMobile;
import mobildeDevices.ios.Simulator;

import static constants.AndroidConstants.*;
import static constants.IOSConstants.*;


public class MobileFactory {

    public static Emulator getSmallEmulator() {

        Emulator mobile = new Emulator(SMALL_AVD_DEVICE_NAME, SMALL_AVD)
                .setAvdTimeout(ADV_TIMEOUT);
        mobile.setPlatformVersion(PLATFORM_VERSION);

        return mobile;
    }

    public static Emulator getEmulator() {

        Emulator mobile = new Emulator(AVD_DEVICE_NAME, AVD)
                .setAvdTimeout(ADV_TIMEOUT);
        mobile.setPlatformVersion(PLATFORM_VERSION);

        return mobile;
    }

    public static AndroidPhysicalMobile getAndroidMobile() {

        AndroidPhysicalMobile mobile = new AndroidPhysicalMobile(ANDROID_MOBILE_UUID, ANDROID_MOBILE_NAME);
        mobile.setPlatformVersion(PLATFORM_VERSION);

        return mobile;
    }

    public static AndroidPhysicalMobile getAndroidTablet() {

        AndroidPhysicalMobile mobile = new AndroidPhysicalMobile(TABLET_UUID, TABLET_NAME);
        mobile.setPlatformVersion(PLATFORM_VERSION);

        return mobile;
    }

    public static Simulator getSimulator() {

        Simulator mobile = new Simulator(SIMULATOR_UDID, SIMULATOR_DEVICE_NAME);
        mobile.setPlatformVersion(IOSConstants.SIMULATOR_PLATFORM_VERSION);

        return mobile;
    }

    public static IOSPhysicalMobile getIOSsMobile() {

        IOSPhysicalMobile mobile = new IOSPhysicalMobile(PHYSICAL_UDID, PHYSICAL_DEVICE_NAME);
        mobile.setPlatformVersion(IOSConstants.PHYSICAL_PLATFORM_VERSION);

        return mobile;
    }

}
