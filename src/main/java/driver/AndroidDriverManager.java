package driver;

import capabilities.AndroidCapabilitiesManager;
import enums.DeviceType;
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
  public AppiumDriver createDriver(URL serverURL, DeviceType deviceType, String configureFile) {
    PropertiesMap propertiesMap = getPropertiesMap(configureFile);

    UiAutomator2Options caps = null;
    switch (deviceType) {
      case EMULATOR:
        caps = AndroidCapabilitiesManager.getEmulatorCaps(propertiesMap);
        break;
      case REAL:
        caps = AndroidCapabilitiesManager.getRealMobileCaps(propertiesMap);
        setLogParams(caps.getCapability("udid").toString());
        break;
      default:
        throw new IllegalStateException("Unexpected device type: " + deviceType);
    }

    if (caps != null) {
      try {
        //System.out.println("serverURL " + serverURL);
        return new AndroidDriver(serverURL, caps);
      } catch (Exception e) {
        log.error("Failed to create driver: {}", e.getMessage(), e);
        e.printStackTrace();
        throw new RuntimeException("Failed to create driver", e);
      }
    } else {
      throw new IllegalArgumentException("Capabilities is null");
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
