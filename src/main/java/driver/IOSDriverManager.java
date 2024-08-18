package driver;

import enums.DeviceType;
import io.appium.java_client.AppiumDriver;

import java.net.URL;

public class IOSDriverManager extends AppiumDriverManager {
  @Override
  public AppiumDriver createDriver(URL serverURL, DeviceType deviceType, String ConfigureFile) {
    return null;
  }
}
