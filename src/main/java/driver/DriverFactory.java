package driver;

import constants.WaitConstants;
import entity.ServerConfig;
import enums.DeviceType;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;
import utils.WaitUtils;

import java.net.URL;

@Slf4j
public class DriverFactory {

  //  private static AppiumDriver driver;
  private AppiumDriver driver;

  public AppiumDriver getLocalServerDriver(PlatformType platformType, DeviceType deviceType, String configureFile) {

    URL localServerURL = getLocalServerURL();
    return getDriver(localServerURL, platformType, deviceType, configureFile);
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

  private AppiumDriver getDriver(URL serverURL, PlatformType platformType, DeviceType deviceType,
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
              System.out.println("Retry to get driver");
              driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);
              break;
            }

          } catch (Exception e) {
            throw new RuntimeException("Failed to get driver: " + e);
          }

        case IOS:
          driver = new IOSDriverManager().createDriver(serverURL, deviceType, configureFile);
          break;
        default:
          throw new PlatformNotSupportException("Not support platform type: " + platformType);
      }

      new WaitUtils(driver).setImplicitWait(WaitConstants.SHORT_IMPLICIT_WAIT);

    }

    return driver;
  }


  public void closeDriver() {
    if (driver != null) {
      new AndroidDriverManager().closeDriver(driver);
    }
  }

}
