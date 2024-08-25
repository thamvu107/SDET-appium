package driver;

import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;

import java.util.Objects;


public class ThreadSafeDriver {


  private ThreadSafeDriver() {
  }

  private static final ThreadLocal<AppiumDriver> threadDriver = new ThreadLocal<>();


  public static void initDriver(PlatformType platformType, DeviceType deviceType, String configureFile) {

    if (Objects.isNull(getDriver())) {
      AppiumDriver driver = new DriverFactoryV2(platformType, deviceType, configureFile).createDriver();

      setDriver(driver);
    }
  }

  public static AppiumDriver getDriver() {
    if (Objects.nonNull(threadDriver)) {
      return threadDriver.get();
    }
    return null;
  }


  private static void setDriver(AppiumDriver driver) {
    if (Objects.nonNull(driver)) {
      threadDriver.set(driver);
    }
  }


  public static void closeDriver() {
    if (Objects.nonNull(getDriver())) {
      getDriver().quit();
      setDriver(null);
      threadDriver.remove();
    }
  }
}
