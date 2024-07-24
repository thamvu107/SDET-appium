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

  private AppiumDriver driver; //  none static
  protected DriverFactory driverFactory;
  protected HomeScreen homeScreen;

  protected PlatformType baseTestPlatformType;
  protected DeviceUnderTestType baseTestDeviceType;
  protected String baseTestConfigureFile;

  public BaseTestV2() {
    // NOTE: This version to learn from error the initialization of driver setting in parallel multiple classes

    System.out.println("Initializing parent BaseTestV2 class");
  }


  protected AppiumDriver getDriver(PlatformType platformType, DeviceUnderTestType deviceType, String configureFile) {
    if (driver == null) {
      driverFactory = new DriverFactory();
      driver = driverFactory.getLocalServerDriver(platformType, deviceType, configureFile);
      System.out.println("Create new driver: " + driver);

    } else {
      System.out.println("Reuse driver: " + driver);
    }

    return driver;
  }

  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
    log.info("Before Suite");
    System.out.println("beforeSuite");
  }

  @BeforeTest(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeTest(String platformType, String deviceType, String configureFile) {

    this.baseTestPlatformType = PlatformType.valueOf(platformType);
    this.baseTestDeviceType = DeviceUnderTestType.valueOf(deviceType);
    this.baseTestConfigureFile = configureFile;

    System.out.println("platform:  " + this.baseTestPlatformType);
    System.out.println("deviceType:  " + this.baseTestDeviceType);
    System.out.println("configureFile:  " + this.baseTestConfigureFile);

  }


  @AfterTest(alwaysRun = true)
  public void afterTest() {
    System.out.println("After Test - Close driver: " + driver);

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
