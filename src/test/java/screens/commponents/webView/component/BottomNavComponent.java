package screens.commponents.webView.component;

import annotations.selectors.ComponentXpathSelector;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screens.commponents.Component;
import screens.screens.HomeScreen;
import screens.screens.SwipeScreen;
import screens.screens.login.LoginScreen;
import screens.screens.webView.WebHomeScreen;
import screens.screens.webView.WebHomeScreenV2;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;

// TODO: Explore Different Strategy Component Locators by platform types (Map?)
@ComponentXpathSelector(
  android = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View",
  ios = "(//XCUIElementTypeOther[@name=\"Home Webview Login Forms Swipe Drag\"])[1]")
public class BottomNavComponent extends Component {
  //  private final AppiumDriver driver;
  private final By homeNavLoc = accessibilityId("Home");
  private final By webViewNavLoc = accessibilityId("Webview");
  private final By loginNavLoc = accessibilityId("Login");
  private final By formsNavLoc = accessibilityId("Forms");
  private final By swipeNavLoc = accessibilityId("Swipe");

//  private final ElementUtils elementUtils;

//  private final InteractionUtils mobileInteractions;


  public BottomNavComponent(AppiumDriver driver, Map<PlatformType, By> componentLocator) {
    super(driver, componentLocator);
    this.driver = driver;
  }

  public WebElement homeNavEl() {
//    return componentEl.findElement(homeNavLoc);

//    wait to find element
    return componentElementUtils.waitForFindingElement(homeNavLoc);


  }


  public WebElement webNavEl() {

    return componentEl.findElement(webViewNavLoc);
  }


  public WebElement loginNavEl() {

    return componentEl.findElement(loginNavLoc);
  }

  public WebElement formsNavEl() {

    return componentEl.findElement(formsNavLoc);
  }

  public WebElement swipeNavEl() {

    return componentEl.findElement(swipeNavLoc);
  }

  public WebElement webViewNavEl() {
    // wait to find element

//    return componentEl.findElement(webViewNavLoc);

    return componentElementUtils.waitForFindingElement(webViewNavLoc);
  }

  public LoginScreen clickOnLoginNav() {
    loginNavEl().click();

    return new LoginScreen(driver);
  }

  public HomeScreen clickOnHomeNav() {
    homeNavEl().click();

    return new HomeScreen(driver);
  }

  public SwipeScreen clickOnSwipeNav() {
    swipeNavEl().click();

    return new SwipeScreen(driver);
  }

  public WebHomeScreen clickOnWebViewNav() {
    webViewNavEl().click();

    return new WebHomeScreen(driver);
  }

  // Explore Component V2
  public WebHomeScreenV2 clickOnWebViewNavV2() {
    webViewNavEl().click();

    return new WebHomeScreenV2(driver);
  }
}
