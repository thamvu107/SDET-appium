package driverFactory;

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

import java.net.URL;
import java.util.Objects;

@Slf4j
public class DriverFactoryV7 {
  private URL serverURL;
  private final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
  private final PlatformType platformType;

  @Getter
  private final DeviceType deviceType;

  @Getter
  private final String configureFile;

  public DriverFactoryV7(PlatformType platformType, DeviceType deviceType, String configureFile) {
    this.platformType = platformType;
    this.deviceType = deviceType;
    this.configureFile = configureFile;
  }


  //  private AppiumDriver createDriver(PlatformType platformType, DeviceType deviceType, String configureFile) {
  public AppiumDriver createDriver() {
    if (Objects.isNull(getDriver())) {
      this.serverURL = ServerURLUtil.getServerURL();

      switch (platformType) {
        case ANDROID:
          try {
            driver.set(new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile));
            break;
          } catch (SessionNotCreatedException ex) {
            if (ex.getMessage().contains("Appium Settings app is not running after 30000ms")) {
              driver.set(new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile));
              break;
            }
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        case IOS:
          driver.set(new IOSDriverManager().createDriver(serverURL, deviceType, configureFile));
          break;
        default:
          throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
      }
    }


    return getDriver();
  }

  public AppiumDriver getDriver() {
    return driver.get();
  }

  public void quitDriver() {
    if (driver != null) {
      try {
        System.out.println("Thread " + Thread.currentThread().getName() + " quit driver " + driver);
        driver.get().quit();
        driver.set(null);
      } catch (Exception e) {
        log.atInfo().setMessage("Failed to quit driver").setCause(e).log();
        throw new RuntimeException("Failed to quit drive " + e);
      }
    }
  }


}
