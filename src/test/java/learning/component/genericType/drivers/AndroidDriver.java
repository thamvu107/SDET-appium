package learning.component.genericType.drivers;

import devices.Mobile;
import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import driver.AppiumDriverEx;
import io.appium.java_client.android.options.UiAutomator2Options;

import static driverFactory.capabilities.AndroidCapabilities.getEmulatorCaps;
import static driverFactory.capabilities.AndroidCapabilities.getRealMobileCaps;

public class AndroidDriver extends Driver {
  public static UiAutomator2Options getAndroidCaps(Mobile mobile) {

    if (mobile instanceof Emulator) {
      return getEmulatorCaps((Emulator) mobile);
    } else if (mobile instanceof AndroidPhysicalMobile) {
      return getRealMobileCaps((AndroidPhysicalMobile) mobile);
    } else {
      throw new IllegalArgumentException("Unsupported Android mobile type");
    }
  }

  @Override
  public AppiumDriverEx innitDriver(Mobile mobile) {

    return null;//new AndroidDriver(serverURL, caps);
  }


  @Override
  public AppiumDriverEx quitDriver() {
    return null;
  }
}
