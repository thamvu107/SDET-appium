package mobildeDevices;

import constants.IOSConstants;

import static constants.AndroidConstants.*;
import static constants.IOSConstants.DEVICE_NAME;
import static constants.IOSConstants.UDID;


public class MobileFactory {

    public static Mobile getEmulator() {

        Mobile mobile = new Emulator(AVD_DEVICE_NAME, AVD)
                .setAvdTimeout(ADV_TIMEOUT);
        mobile.setPlatformVersion(PLATFORM_VERSION);

        return mobile;
    }

    public static PhysicalMobile getAndroidPhysicalMobile() {

        PhysicalMobile mobile = new PhysicalMobile(ANDROID_MOBILE_UUID, ANDROID_MOBILE_NAME);
        mobile.setPlatformVersion(PLATFORM_VERSION);

        return mobile;
    }

    public static Mobile getIOSsMobile() {

        return new IOSMobile(UDID, DEVICE_NAME)
                .setPlatformVersion(IOSConstants.PLATFORM_VERSION);
    }

}
