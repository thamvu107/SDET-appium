package driverFactory;

import constants.WaitConstants;
import driver.AndroidDriverManager;
import driver.IOSDriverManager;
import enums.DeviceType;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;
import utils.ServerURLUtil;
import utils.WaitUtils;

import java.net.URL;

@Slf4j
public class DriverFactoryV3 {
  private URL serverURL;
  @Getter
  private AppiumDriver driver;
  private final PlatformType platformType;

  private final DeviceType deviceType;

  private final String configureFile;

  public DriverFactoryV3(PlatformType platformType, DeviceType deviceType, String configureFile) {
    this.platformType = platformType;
    this.deviceType = deviceType;
    this.configureFile = configureFile;
  }


  //  private AppiumDriver createDriver(PlatformType platformType, DeviceType deviceType, String configureFile) {
  public AppiumDriver createDriver() {
    if (driver == null) {
      this.serverURL = ServerURLUtil.getServerURL();

      switch (platformType) {
        case ANDROID:
          try {
            driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);
            break;
          } catch (SessionNotCreatedException ex) {
            if (ex.getMessage().contains("Appium Settings app is not running after 30000ms")) {
              System.out.println("Retry to create driver");
              driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);

              break;
            }

          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        case IOS:
          driver = new IOSDriverManager().createDriver(serverURL, deviceType, configureFile);
          break;
        default:
          throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
      }

      new WaitUtils(driver).setImplicitWait(WaitConstants.SHORT_IMPLICIT_WAIT);
    }

    return driver;
  }

  public void quitDriver() {
    if (driver != null) {
      try {
        System.out.println("Thread " + Thread.currentThread().getName() + " quit driver " + driver);
        driver.quit();
        driver = null;
      } catch (Exception e) {
        log.atInfo().setMessage("Failed to quit driver").setCause(e).log();
        throw new RuntimeException("Failed to quit drive " + e);
      }
    }
  }


}
