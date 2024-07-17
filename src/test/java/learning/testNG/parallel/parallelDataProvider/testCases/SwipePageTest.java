package learning.testNG.parallel.parallelDataProvider.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SwipePageTest extends BaseTest {

  @Parameters("configureFile")
  @BeforeClass(alwaysRun = true)
  public void beforeClass(String configureFile) {
    System.out.println("configureFile " + configureFile);
  }

  @Test
  public void testHorizontalSwipe() {
    System.out.println("Swipe horizontal test");
  }

  @Test
  public void testVerticalSwipe() {
    System.out.println("Test vertical test");
  }
}
