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
import testFlows.SignInFlow;

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


    driver.set(new DriverFactoryV8(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile).createDriver());
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {

    MDC.clear(); // Mapped Diagnostic Context
    if (driver.get() != null) {
      driver.get().quit();
      driver.set(null);
    }
  }

  protected void setLogParams(String platformType, String deviceType, String configureFile) {
    MDC.put("baseTest PlatformType:: ", platformType);
    MDC.put("baseTest PlatformType:: ", deviceType);
    MDC.put("baseTest PlatformType:: ", configureFile);

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

  protected SignInFlow signInFlow(AppiumDriver driver) {
    return new SignInFlow(driver);
  }

}
