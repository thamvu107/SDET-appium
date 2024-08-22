package testcaseV8.simple;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SimpleTest extends SimpleBaseTest {
  @Test

  public void oneTest() {
    System.out.println("QA");
    waitTime();
  }

  @Test
  public void twoTest() {
    System.out.println("Dev");
    waitTime();
  }

  @Test()
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void threeTest(String platformType, String deviceType, String configureFile) {
    System.out.println("platformType");
    System.out.println("deviceType");
    System.out.println("configureFile");
    waitTime();
  }
}
