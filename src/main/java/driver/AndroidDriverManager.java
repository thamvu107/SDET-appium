package driver;

import capabilities.AndroidCapabilitiesManager;
import enums.DeviceUnderTestType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import utils.propertyReader.PropertiesMap;

import java.net.URL;

@Slf4j
public class AndroidDriverManager extends AppiumDriverManager {
  @Override
  public AppiumDriver createDriver(URL serverURL, DeviceUnderTestType deviceType, String configureFile) {
    PropertiesMap propertiesMap = getPropertiesMap(configureFile);

    try {
      UiAutomator2Options caps = null;
      switch (deviceType) {
        case EMULATOR:
          caps = AndroidCapabilitiesManager.getEmulatorCaps(propertiesMap);
          setLogParams(caps.getCapability("deviceName").toString());
          break;
        case PHYSICAL:
        case REAL:
          caps = AndroidCapabilitiesManager.getRealMobileCaps(propertiesMap);
          setLogParams(caps.getCapability("udid").toString());
          break;
        default:
          throw new IllegalStateException("Unexpected device type: " + deviceType);
      }


//      setLogParams(caps);
      if (caps == null) {
        throw new RuntimeException("Capabilities is null");
      } else {
        return new AndroidDriver(serverURL, caps);
      }

    } catch (Exception e) {
      throw new RuntimeException("Create driver is failed", e);
    }
  }

  private PropertiesMap getPropertiesMap(String configureFile) {
    PropertiesMap propertiesMap;

    try {
      propertiesMap = new PropertiesMap(configureFile);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load properties from file: " + configureFile, e);
    }
    return propertiesMap;
  }


  private void setLogParams(String fileName) {
    MDC.put("deviceName", fileName);
  }

  private String convertDeviceName(String deviceName) {
    System.out.println(deviceName);
    return deviceName.replaceAll(" ", "_");
  }


}
