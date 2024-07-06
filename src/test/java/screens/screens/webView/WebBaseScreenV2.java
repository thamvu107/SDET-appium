package screens.screens.webView;

import context.ContextSwitching;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import screens.commponents.Component;
import screens.commponents.webView.component.BottomNavComponent;
import screens.screens.HomeScreen;
import screens.screens.SwipeScreen;
import screens.screens.login.LoginScreen;
import utils.ElementUtils;
import utils.PlatformUtil;

import java.util.Map;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

@Slf4j
public class WebBaseScreenV2 extends Component {

  protected AppiumDriver driver;
  protected ContextSwitching contextSwitching;

  protected BottomNavComponent bottomNavComponent;

  protected ElementUtils elementUtils;

  //  protected InteractionUtils interactionUtils;
  protected PlatformType currentPlatform;

  private static final By androidRootLocator = id("com.wdiodemoapp:id/action_bar_root");
  private static final By iosRootLocator = accessibilityId("wdiodemoapp");

  private final By webViewScreenLoc = By.cssSelector("#__docusaurus");
  private final By navBarLoc = By.cssSelector(".navbar-sidebar");
  private final By announcementBarLoc = By.cssSelector(".announcementBar_mb4j");
  private final By announcementCloseBtnLoc = By.cssSelector("button[class*='closeButton']");
  private static final By androidBottomLocator = id(
    "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View");
  private static final By iosBottomLocator =
    accessibilityId("(//XCUIElementTypeOther[@name=\"Home Webview Login Forms Swipe Drag\"])[1]");

  private static final Map<PlatformType, By> rootLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);


  protected static final Map<PlatformType, By> bottomNavLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);

  //protected AssertUtils assertUtils;
  public WebBaseScreenV2(AppiumDriver driver) {
    super(driver, rootLocator);
//    super(driver, driver.findElement());

    if (driver == null) {
      log.atError().setMessage("Driver cannot be null").log();
      throw new IllegalArgumentException("Driver cannot be null");
    }
    this.driver = driver;
    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.contextSwitching = new ContextSwitching(this.driver);
//    this.elementUtils = new ElementUtils(driver);
//    this.interactionUtils = new InteractionUtils(driver);
    try {
      this.bottomNavComponent = getBottomNavComponent();
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  public BottomNavComponent getBottomNavComponent() throws NoSuchMethodException {
    try {
      this.bottomNavComponent = findComponent(BottomNavComponent.class);
      return this.bottomNavComponent;
    } catch (Exception e) {
      log.atError().setMessage("Bottom nav component not found").setCause(e).log();
      throw new RuntimeException(e);
    }
  }

  public String getCurrentContext() {
    return contextSwitching.getCurrentContext(driver);
  }

  public WebDriver switchToNativeContext() {
    return contextSwitching.switchToNativeContext(LONG_EXPLICIT_WAIT);
  }

  public WebDriver switchToWebViewContext() {
    return contextSwitching.switchToWebViewContext(SHORT_EXPLICIT_WAIT);

  }

  public HomeScreen openHomeScreen() {
    return bottomNavComponent.clickOnHomeNav();
  }

  public LoginScreen openLoginScreen() {

    return bottomNavComponent.clickOnLoginNav();
  }

  public SwipeScreen goToSwipeScreen() {
    return bottomNavComponent.clickOnSwipeNav();
  }

  public WebHomeScreenV2 openWebViewScreen() {

    return bottomNavComponent.clickOnWebViewNavV2();
  }

  public boolean showingNavBar() {
    return elementUtils.isElementPresent(navBarLoc);
  }

  public WebElement announcementCloseBtnEl() {
    return elementUtils.waitForElementTobeClickable(announcementCloseBtnLoc);
  }

  public WebBaseScreenV2 closeAnnouncement() {
    if (elementUtils.isElementPresent(announcementBarLoc)) {
      announcementCloseBtnEl().click();
    }

    return this;
  }

  protected void verifyScreenLoaded(By locator) {
    // Waits for the element to be visible and throws an exception if it is not
//    WebElement element = componentElementUtils.waitForFindingElement(locator);
    WebElement element = null;
    try {
      element = componentElementUtils.waitForElementToBeVisible(locator);
      if (element == null) {
        String errorMessage = String.format("Page is not loaded. Element: '%s' is not visible", locator);
        log.atError().setMessage(errorMessage).log();
        throw new NoSuchElementException(errorMessage);
      } else {
        log.atInfo().setMessage("Page is loaded. Element: '" + locator.toString() + "' is visible").log();
      }
    } catch (Exception e) {
      String errorMessage = String.format("Page is not loaded. Element: '%s' is not visible", locator);
      log.atError().setMessage(errorMessage).setCause(e).log();

      throw new RuntimeException(errorMessage, e);
    }


  }

}
