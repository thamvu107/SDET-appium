package base;

import driverFactory.DriverFactoryV3;
import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
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
  }

  @BeforeTest(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeTest(String platformType, String deviceType, String configureFile) {

    setLogParams(platformType, deviceType, configureFile);

    this.baseTestPlatformType = PlatformType.valueOf(platformType);
    this.baseTestDeviceType = DeviceType.valueOf(deviceType);
    this.baseTestConfigureFile = configureFile;

    driverFactoryThread.set(new DriverFactoryV3(baseTestPlatformType, baseTestDeviceType, baseTestConfigureFile));
    driverThread.set(driverFactoryThread.get().createDriver()
    );
  }

  @AfterTest(alwaysRun = true)
  public void afterTest() {

    MDC.clear(); // Mapped Diagnostic Context
    getDriverFactory().quitDriver();
    driverThread.remove();
    driverFactoryThread.remove();
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

}
