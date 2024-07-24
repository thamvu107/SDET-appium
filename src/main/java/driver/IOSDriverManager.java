package driver;

import enums.DeviceUnderTestType;
import io.appium.java_client.AppiumDriver;

import java.net.URL;

public class IOSDriverManager extends AppiumDriverManager {


  @Override
  public AppiumDriver createDriver(URL serverURL, DeviceUnderTestType deviceType, String ConfigureFile) {
    return null;
  }
}
