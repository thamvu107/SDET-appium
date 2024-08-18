package driver;

import entity.ServerConfig;
import enums.DeviceType;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public class DriverFactoryEx {

  private AppiumDriver driver;

  public AppiumDriver getLocalServerDriver(PlatformType platformType, DeviceType deviceType, String configureFile) {

    URL localServerURL = getLocalServerURL();
    try {
      return getDriver(localServerURL, platformType, deviceType, configureFile);
    } catch (Exception e) {
      log.atInfo().setMessage("Innit local server driver is failed").setCause(e).log();
      throw new RuntimeException(e);
    }
  }

  private URL getLocalServerURL() {
    // TODO: read from config file

    ServerConfig server = new ServerConfig("127.0.0.1", 4723);
    try {
      return server.getServerURL();
    } catch (Exception e) {
      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
      throw new RuntimeException("Invalid server URL" + e);
    }
  }

  private AppiumDriver getDriver(URL serverURL, PlatformType platformType, DeviceType deviceType, String configureFile) {
    if (driver == null) {

      switch (platformType) {
        case ANDROID:
          driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);
          break;
        case IOS:
          driver = new IOSDriverManager().createDriver(serverURL, deviceType, configureFile);
          break;
        default:
          throw new PlatformNotSupportException("Not support platform type: " + platformType);
      }
    }
    return driver;
  }


  public void closeDriver() {
    if (driver != null) {
      new AndroidDriverManager().closeDriver(driver);
    }
  }

}
