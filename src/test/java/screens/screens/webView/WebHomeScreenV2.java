package screens.screens.webView;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WebHomeScreenV2 extends WebBaseScreenV2 {
  private final AppiumDriver driver;
  private final By homeScreenLoc = By.id(".__docusaurus");
//  private final By heroTitleLoc = By.cssSelector(".hero__title");

  public WebHomeScreenV2(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    switchToNativeContext();
    verifyScreenLoaded(homeScreenLoc);
  }


}
