package learning.testNG.parallel.parallelDataProvider;

import com.google.common.reflect.ClassPath;
import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.deviceConfigure.DeviceCapConfigure;
import enums.PlatformType;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;
import utils.DataObjectBuilderUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.filePaths.IBasePaths.TEST_RESOURCES_PATH;

public class parallelDataProvider2 {
  public static void main(String[] args) {
    // Get all test classes
    List<Class<?>> testClasses;
    try {
      testClasses = getTestClasses();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    DeviceCapConfigure[] configs = readDeviceConfigsFromExternalSource();


    // Get platform
//    String platformType = System.getProperty("platform");
//    String platformType = null;
    String platformType = "android";

    // Devices under test
    List<DeviceCapConfigure> deviceList = getDeviceUnderTest(platformType, configs);

    // Assign devices to test classes
    int testNumEachDevice = testClasses.size() / deviceList.size();
    Map<DeviceCapConfigure, List<Class<?>>> deviceCapConfigureList = new HashMap<>();
    for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
      int startIndex = deviceIndex * testNumEachDevice;
      boolean isTheLastDevice = deviceIndex == deviceList.size() - 1;
      int endIndex = isTheLastDevice ? testClasses.size() : (startIndex + testNumEachDevice);
      List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
      deviceCapConfigureList.put(deviceList.get(deviceIndex), subTestList);
    }
    deviceCapConfigureList.forEach((k, v) -> System.out.println(k.getId() + " " + v.size()));

    // Build dynamic test suite
    TestNG testNG = new TestNG();
    XmlSuite suite = new XmlSuite();
    suite.setName("Dynamic Regression");

    List<XmlTest> allTests = new ArrayList<>();
    for (DeviceCapConfigure deviceCapConfigure : deviceCapConfigureList.keySet()) {
      XmlTest test = new XmlTest(suite);
      test.setName(deviceCapConfigure.getId());
      List<XmlClass> xmlClasses = new ArrayList<>();
      List<Class<?>> testClassesForDevice = deviceCapConfigureList.get(deviceCapConfigure);
      for (Class<?> testClass : testClassesForDevice) {
        xmlClasses.add(new XmlClass(testClass.getName()));
      }
      test.setXmlClasses(xmlClasses);
      test.addParameter("id", deviceCapConfigure.getId());
      test.addParameter("platformType", deviceCapConfigure.getPlatformType());
      test.addParameter("deviceType", deviceCapConfigure.getDeviceType());
      test.addParameter("deviceName", deviceCapConfigure.getDeviceName());
      test.addParameter("configureFile", deviceCapConfigure.getConfigureFile());
//      test.addParameter(AndroidCapabilityType.PLATFORM_NAME, platformName);
//      test.addParameter(AndroidCapabilityType.PLATFORM_VERSION_OPTION, "17.2");
//      test.addParameter(AndroidCapabilityType.SYSTEM_PORT,
//                        String.valueOf(new SecureRandom().nextInt(1000) + 8300));
      allTests.add(test);
    }
    suite.setTests(allTests);
    suite.setParallel(ParallelMode.TESTS);
    suite.setThreadCount(10);
    System.out.println(suite.toXml());

    //    // Generate dynamic filename with the current date
//    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
//    String fileName = "src/test/java/learning/testNG/parallel/parallelDataProvider/testSuites/testNG-" + date + ".xml";
//
//    // Write the XML suite to a file
//    try (FileWriter writer = new FileWriter(fileName)) {
//      writer.write(suite.toXml());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH"));
    String folderPath = TEST_RESOURCES_PATH + "suites" + File.separator + date;
    String fileName = "testNG-" + time + ".xml";

    // Create the directory if it doesn't exist
    File directory = new File(folderPath);
    if (!directory.exists()) {
      directory.mkdirs();
    }

    // Write the XML suite to a file
    Path filePath = Paths.get(folderPath, fileName);
    try (FileWriter writer = new FileWriter(filePath.toFile())) {
      writer.write(suite.toXml());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Add Suite into TestNG
//    testNG.setSuiteXmlFiles(Arrays.asList(filePath.toString()));
    // Add TestSuite into Suite list
    List<XmlSuite> suites = new ArrayList<>();
    suites.add(suite);

    // Invoke run method
    testNG.setXmlSuites(suites);
    testNG.run();
  }


  private static List<DeviceCapConfigure> getDeviceUnderTest(String platformType, DeviceCapConfigure[] configs) {
    boolean isAllPlatformTypes = platformType == null;
    List<DeviceCapConfigure> configsList = Arrays.asList(configs);
    List<DeviceCapConfigure> iosDeviceList = filterByPlatform(configsList, PlatformType.IOS);
    List<DeviceCapConfigure> androidDeviceList = filterByPlatform(configsList, PlatformType.ANDROID);

    List<DeviceCapConfigure> deviceList = new ArrayList<>();

    if (isAllPlatformTypes) {
      deviceList.addAll(iosDeviceList);
      deviceList.addAll(androidDeviceList);

    } else if (PlatformType.fromString(platformType).equals(PlatformType.IOS)) {
      deviceList = iosDeviceList;
    } else if (PlatformType.fromString(platformType).equals(PlatformType.ANDROID)) {
      deviceList = androidDeviceList;
    }

    return deviceList;
  }

  private static List<Class<?>> getTestClasses() throws IOException {
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    List<Class<?>> testClasses = new ArrayList<>();
    for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
      String classInfoName = info.getName();
      boolean startWithTestDot = classInfoName.startsWith("learning.testNG.parallel.parallelDataProvider.testCases.");
      if (startWithTestDot) {
        testClasses.add(info.load());
      }
    }

    System.out.println("Total test classes: " + testClasses.size());
    return testClasses;
  }

  private static void printParams(DeviceCapConfigure config) {
    // Example test logic using Appium
    System.out.println("\nRunning Appium test on platform: " + config.getPlatformType());
    System.out.println("Device Id: " + config.getId());
    System.out.println("Device Type: " + config.getDeviceType());
    System.out.println("Device Name: " + config.getDeviceName());
    System.out.println("File: " + config.getConfigureFile());
  }

  @DataProvider(name = "platformsDataProvider", parallel = true)
  public DeviceCapConfigure[] platformsDataProvider() {
    return readDeviceConfigsFromExternalSource();
  }

  private static DeviceCapConfigure[] readDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Path.of(DevicePathConstants.DEVICE_JSON_PATH);

    return DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceCapConfigure[].class);
  }


  private static List<DeviceCapConfigure> filterByPlatform(List<DeviceCapConfigure> configs, PlatformType platformType) {
    // Filter data for the specified platformType
    List<DeviceCapConfigure> filteredData = new ArrayList<>();
    for (DeviceCapConfigure config : configs) {
      if (PlatformType.fromString(config.getPlatformType()).equals(platformType)) {

        filteredData.add(config);
      }
    }
    return filteredData;
  }
}
