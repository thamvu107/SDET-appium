package driver;

import enums.DeviceType;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;
import utils.ServerURLUtil;
import utils.WaitUtils;

import java.net.URL;
import java.util.Objects;

@Slf4j
public class DriverFactoryV2 {

  private URL serverURL;
  private AppiumDriver driver;
  private final PlatformType platformType;

  private final DeviceType deviceType;

  private final String configureFile;

  public DriverFactoryV2(PlatformType platformType, DeviceType deviceType, String configureFile) {
    this.platformType = platformType;
    this.deviceType = deviceType;
    this.configureFile = configureFile;
  }

  public AppiumDriver createDriver() {
    if (Objects.isNull(driver)) {
      this.serverURL = ServerURLUtil.getServerURL();

      switch (platformType) {
        case ANDROID:
          try {
            driver = new AndroidDriverManager().createDriver(serverURL, deviceType, configureFile);
            break;
          } catch (SessionNotCreatedException ex) {
            if ((DeviceType.EMULATOR).equals(deviceType) &&
              ex.getMessage().contains("Appium Settings app is not running after 30000ms")) {
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

      new WaitUtils(driver).setImplicitWait(0L);
    }

    return driver;
  }
}
