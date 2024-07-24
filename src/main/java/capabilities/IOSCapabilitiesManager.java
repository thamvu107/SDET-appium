package capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;
import utils.propertyReader.PropertiesMap;

import java.time.Duration;

public class IOSCapabilitiesManager {

  public static XCUITestOptions getSimulatorCaps(PropertiesMap deviceProps) {
//    PropertiesUtils deviceProps = new PropertiesUtils(deviceCapConfig);


    // TODO: handle to read caps value from config file
    XCUITestOptions caps = new XCUITestOptions()
      .setDeviceName(deviceProps.getProperty("deviceName"))
      .setUdid(deviceProps.getProperty("udid"))
      .setPlatformVersion(deviceProps.getProperty("platformVersion"))
//      .setBundleId(deviceProps.getProperty(""))
//                .setApp(app.getAppPath())
      .setEnforceAppInstall(true) // default:false
      .setWdaLaunchTimeout(Duration.ofSeconds(90)) // wdaLaunchTimeout
      .setFullReset(false)
      .setNoReset(false)
      .setUseNewWDA(true)
//                .setWdaLocalPort(mobile.getWdaLocalPort())
      .setShowXcodeLog(true)
      .setPrintPageSourceOnFindFailure(true)
      .setLaunchWithIdb(true)
      .clearSystemFiles();
    caps.setCapability("--session-override", true);
    return caps;
  }

  public static XCUITestOptions getRealIosMobileCaps(PropertiesMap deviceProps) {
//    PropertiesUtils deviceProps = new PropertiesUtils(deviceCapConfig);


    // TODO: handle to read caps value from config file
    XCUITestOptions caps = new XCUITestOptions()
      .setDeviceName(deviceProps.getProperty("deviceName"))
      .setUdid(deviceProps.getProperty("udid"))
      .setPlatformVersion(deviceProps.getProperty("platformVersion"))
//      .setBundleId(deviceProps.getProperty(""))
//                .setApp(app.getAppPath())
      .setEnforceAppInstall(true) // default:false
      .setWdaLaunchTimeout(Duration.ofSeconds(90)) // wdaLaunchTimeout
      .setFullReset(false)
      .setNoReset(false)
      .setUseNewWDA(true)
//                .setWdaLocalPort(mobile.getWdaLocalPort())
      .setShowXcodeLog(true)
      .setPrintPageSourceOnFindFailure(true)
      .setLaunchWithIdb(true)
      .clearSystemFiles();
    caps.setCapability("--session-override", true);


    return caps;
  }

}
