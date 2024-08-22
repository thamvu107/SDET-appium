package testcaseV8.simple;

import driverFactory.DriverFactoryV8;
import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class SimpleBaseTest {

  private final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

  @BeforeMethod(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void setUp(String platformType, String deviceType, String configureFile) {

//    setLogParams(platformType, deviceType, configureFile);
    System.out.println("platformType " + platformType);
    System.out.println("deviceType " + deviceType);
    System.out.println("configureFile " + configureFile);


    driver.set(new DriverFactoryV8(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile).createDriver());
    System.out.println(driver.get());

//    driver.set(new DriverFactoryV8(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile).createDriver());
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {

    MDC.clear(); // Mapped Diagnostic Context
    System.out.println("quit driver " + driver.get());
//    driver.quit();
    driver.get().quit();
    driver.remove();

//    driver.remove();
  }

  protected void waitTime() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
