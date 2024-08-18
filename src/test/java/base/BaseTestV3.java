package base;

import driverFactory.DriverFactoryV3;
import enums.DeviceType;
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
public abstract class BaseTestV3 {

  //  private static final List<DriverFactoryV3> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
  private static final ThreadLocal<DriverFactoryV3> driverFactoryThread = new ThreadLocal<>();
  private static final ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();


  protected PlatformType baseTestPlatformType;
  protected DeviceType baseTestDeviceType;
  protected String baseTestConfigureFile;

  protected HomeScreen homeScreen;


  public BaseTestV3() {

    System.out.println("Initializing the BaseTestV3 class");
  }

  protected DriverFactoryV3 getDriverFactory() {

    return driverFactoryThread.get();
  }

  protected AppiumDriver getDriver() {

    return driverThread.get();
  }

  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
    log.info("Before Suite Print");
  }

  @BeforeTest(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeTest(String platformType, String deviceType, String configureFile) {
    System.out.println("Before Test");

    this.baseTestPlatformType = PlatformType.valueOf(platformType);
    this.baseTestDeviceType = DeviceType.valueOf(deviceType);
    this.baseTestConfigureFile = configureFile;

    System.out.println("platform:  " + this.baseTestPlatformType);
    System.out.println("deviceType:  " + this.baseTestDeviceType);
    System.out.println("configureFile:  " + this.baseTestConfigureFile);

    driverFactoryThread.set(new DriverFactoryV3(baseTestPlatformType, baseTestDeviceType, baseTestConfigureFile));
    driverThread.set(getDriverFactory().createDriver());
    System.out.println("Create new driver: " + getDriver());

  }


  @AfterTest(alwaysRun = true)
  public void afterTest() {
    System.out.println("After Test - Close driver: " + getDriver());

    MDC.clear(); // Mapped Diagnostic Context

    getDriverFactory().quitDriver();
    driverThread.remove();
    driverFactoryThread.remove();
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
