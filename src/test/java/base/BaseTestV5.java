package base;

import driverFactory.DriverFactoryV5;
import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

@Slf4j
public abstract class BaseTestV5 {

  protected static final ThreadLocal<DriverFactoryV5> driverFactoryThread = new ThreadLocal<>();


  protected PlatformType baseTestPlatformType;
  protected DeviceType baseTestDeviceType;
  protected String baseTestConfigureFile;

//  protected HomeScreen homeScreen;


  public BaseTestV5() {

    System.out.println("Initializing the BaseTestV3 class");
  }

  protected DriverFactoryV5 getDriverFactory() {

    return driverFactoryThread.get();
  }

  protected AppiumDriver getDriver() {

    return driverFactoryThread.get().getDriver();
  }


  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
  }

  @BeforeClass(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeClass(String platformType, String deviceType, String configureFile) {

    setLogParams(platformType, deviceType, configureFile);

    this.baseTestPlatformType = PlatformType.valueOf(platformType);
    this.baseTestDeviceType = DeviceType.valueOf(deviceType);
    this.baseTestConfigureFile = configureFile;

    driverFactoryThread.set(new DriverFactoryV5(baseTestPlatformType, baseTestDeviceType, baseTestConfigureFile));
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {

    MDC.clear(); // Mapped Diagnostic Context
//    getDriverFactory().quitDriver();
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
