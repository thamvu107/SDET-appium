package driverFactory.capabilities;

import io.appium.java_client.android.options.UiAutomator2Options;
import mobildeDevices.Mobile;
import mobildeDevices.android.AndroidPhysicalMobile;
import mobildeDevices.android.Emulator;

import static driverFactory.capabilities.AndroidCapabilities.getEmulatorCaps;
import static driverFactory.capabilities.AndroidCapabilities.getPhysicalDeviceCaps;

public class CapabilityFactory {
    public static UiAutomator2Options getAndroidCaps(Mobile mobile) {

        return (mobile instanceof Emulator) ? getEmulatorCaps((Emulator) mobile) : getPhysicalDeviceCaps((AndroidPhysicalMobile) mobile);
    }
}
