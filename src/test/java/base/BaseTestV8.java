package base;

import driverFactory.DriverFactoryV8;
import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

@Slf4j
public abstract class BaseTestV8 {
  //  private DriverFactoryV8 driverFactory;
  private final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void setUp(String platformType, String deviceType, String configureFile) {

//    setLogParams(platformType, deviceType, configureFile);
    System.out.println("platformType " + platformType);
    System.out.println("deviceType " + deviceType);
    System.out.println("configureFile " + configureFile);

    driver.set(new DriverFactoryV8(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile).createDriver());
    System.out.println(driver.get());
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {

    MDC.clear(); // Mapped Diagnostic Context
//    driver.quit();
    driver.get().quit();
    driver.remove();

//    driver.remove();
  }

  protected void setLogParams(String platformType, String deviceType, String configureFile) {
    MDC.put("baseTest PlatformType:: ", platformType);
    MDC.put("baseTest PlatformType:: ", deviceType);
    MDC.put("baseTest PlatformType:: ", configureFile);

    System.out.println("platformType " + platformType);
    System.out.println("deviceType " + deviceType);
    System.out.println("configureFile " + configureFile);
  }

  private static String convertDeviceName(String deviceName) {
    System.out.println(deviceName);
    if (deviceName == null || deviceName.isEmpty()) {
      throw new IllegalArgumentException("Device name cannot be null or empty");
    }
    return deviceName.replaceAll(" ", "_");
  }

  protected AppiumDriver getDriver() {
    return driver.get();
  }

}
