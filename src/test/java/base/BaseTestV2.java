package base;

import driver.DriverFactory;
import enums.DeviceUnderTestType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.slf4j.MDC;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import screens.HomeScreen;

@Slf4j
public abstract class BaseTestV2 {

  protected AppiumDriver driver; //  none static
  //  protected static AppiumDriver driver; // static
  protected DriverFactory driverFactory;
  protected HomeScreen homeScreen;

  private PlatformType platformType;
  private DeviceUnderTestType deviceType;
  private String configureFile;

  @BeforeSuite
  public void beforeSuite() {
    MDC.put("logDir", "logs");
  }

  @BeforeTest
  @Parameters({"platform", "deviceType", "configureFile"})
  public void beforeTest(String platform, String deviceType, String configureFile) {
    System.out.println("platform:  " + platform);
    System.out.println("deviceType:  " + deviceType);
    System.out.println("configureFile:  " + configureFile);
    if (driver == null) {
      driverFactory = new DriverFactory();
      driver = driverFactory.getLocalServerDriver(PlatformType.valueOf(platform), DeviceUnderTestType.valueOf(deviceType),
                                                  configureFile);

      System.out.println("Before Test: create driver " + driver);
    }

  }

  @AfterTest(alwaysRun = true)
  public void afterTest() {
    System.out.println("After Test: driver " + driver);

    MDC.clear(); // Mapped Diagnostic Context
    driverFactory.closeDriver();
  }

  protected void setLogParams(Capabilities capabilities) {
    String deviceName = capabilities.getCapability("deviceName").toString();
    MDC.put("logDir", "logs");
    MDC.put("deviceName", convertDeviceName(deviceName));
  }

  private static String convertDeviceName(String deviceName) {
    System.out.println(deviceName);
    if (deviceName == null || deviceName.isEmpty()) {
      throw new IllegalArgumentException("Device name cannot be null or empty");
    }
    return deviceName.replaceAll(" ", "_");
  }

}
