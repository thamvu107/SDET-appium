package base;

import driver.ThreadSafeDriver;
import enums.DeviceType;
import enums.PlatformType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import testFlows.SignInFlow;

@Slf4j
public abstract class BaseTestV9 {
  //  private DriverFactoryV8 driverFactory;
//  private final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
//  private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void setUp(String platformType, String deviceType, String configureFile) {

    //driver.set(new DriverFactoryV8(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile).createDriver());
    ThreadSafeDriver.initDriver(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult results) {
//    captureScreenShot(results);
    MDC.clear(); // Mapped Diagnostic Context

//    if (driver.get() != null) {
//      driver.get().quit();
//      driver.set(null);
//    }

    ThreadSafeDriver.closeDriver();
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

  protected SignInFlow signInFlow() {
    return new SignInFlow(ThreadSafeDriver.getDriver());
  }
}
