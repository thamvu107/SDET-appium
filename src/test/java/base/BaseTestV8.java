package base;

import driverFactory.DriverFactoryV8;
import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import testFlows.SignInFlow;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
  public void tearDown(ITestResult results) {
    captureScreenShot(results);
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

  private void captureScreenShot(ITestResult results) {
    boolean testIsFailed = results.getStatus() == ITestResult.FAILURE;
    if (testIsFailed) {
      String randomScreenshotName = generateScreenshotNameBasedOnTime(results);
      // Capture screenshot and attach to the report
      File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
      try {
        String screenshotLocation =
          System.getProperty("user.dir") + "target/screenshots/" + randomScreenshotName;
        FileUtils.copyFile(screenshotBase64Data,
                           new File(screenshotLocation));
        Path screenshotContentPath = Paths.get(screenshotLocation);
        InputStream inputStream = Files.newInputStream(screenshotContentPath);
        Allure.addAttachment(results.getName(), inputStream);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private String generateScreenshotNameBasedOnTime(ITestResult results) {
    // 1. GEt the test method name
    String methodName = results.getName();

    // 2. Get current time | yyyy-mm-dd-hr-m-s
    Calendar calendar = new GregorianCalendar();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE);
    int hr = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
    int sec = calendar.get(Calendar.SECOND);
    String takenTime = year + "-" + month + "-" + day + "-" + hr + "-" + min + "-" + sec;
    return methodName + "-" + takenTime + ".png";
  }

}
