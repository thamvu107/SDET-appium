package testCaseV2.swipe;

import annotations.author.Author;
import base.BaseTestV2;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.SwipeScreen;

import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class VerticalSwipeTest extends BaseTestV2 {
  private SwipeScreen swipeScreen;

  @BeforeClass
  public void setupSwipeTestClass() {
    swipeScreen = new HomeScreen(driver)
      .goToSwipeScreen();
  }

  @Author(THAM_VU)
  @Test
  public void swipeUp() {
    boolean isFoundSwipeUpTarget = swipeScreen.scrollToWebDriverIOLogo();
    Assert.assertTrue(isFoundSwipeUpTarget, "Can't find WebDriverIO logo");

//    boolean isFoundSwipeDownTarget = swipeScreen.scrollToScreenTitle();
//    Assert.assertTrue(isFoundSwipeDownTarget, "Can't find screen title");
  }

  @Author(THAM_VU)
  @Test(dependsOnMethods = "swipeUp")
  public void swipeDown() {

    boolean isFoundSwipeDownTarget = swipeScreen.scrollToScreenTitle();
    Assert.assertTrue(isFoundSwipeDownTarget, "Can't find screen title");
  }
}
