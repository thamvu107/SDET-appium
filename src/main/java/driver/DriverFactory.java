package driver;

import entity.ServerConfig;
import enums.DeviceUnderTestType;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;

import java.net.URL;

@Slf4j
public class DriverFactory {

  //  private static AppiumDriver driver;
  private AppiumDriver driver;

  public AppiumDriver getLocalServerDriver(PlatformType platformType, DeviceUnderTestType deviceType, String configureFile) {

    URL localServerURL = getLocalServerURL();
    try {
      return getDriver(localServerURL, platformType, deviceType, configureFile);
    } catch (Exception e) {
      log.atInfo().setMessage("Get local server driver is failed").setCause(e).log();
      throw new RuntimeException("Get local server driver is failed" + e);
    }
  }

  private URL getLocalServerURL() {
    // TODO: read from config file

    try {
      ServerConfig server = new ServerConfig("127.0.0.1", 4723);
      return server.getServerURL();
    } catch (Exception e) {
      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
      throw new RuntimeException("Invalid server URL" + e);
    }
  }

  private AppiumDriver getDriver(URL serverURL, PlatformType platformType, DeviceUnderTestType deviceType,
                                 String configureFile) {
    if (driver == null) {

      switch (platformType) {
        case ANDROID:
          try {
            driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);
            break;
          } catch (SessionNotCreatedException ex) {
            // ERROR: first time start emulator then  throw exception since timeout Appium setting is set only 30_000ms( caps)
            // Error: Appium Settings app is not running after 30000ms
//                        throw new SessionNotCreatedException(ex.getMessage());
//                        //Retrying to create driver
            if (ex.getMessage().contains("Appium Settings app is not running after 30000ms")) {
//                            System.out.println("Appium Settings app is not running: " + ex.getMessage());
              System.out.println("Retry to create driver");
              driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);
              break;
            }

          } catch (Exception e) {
            throw new RuntimeException("Failed to create driver: " + e);
          }

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
