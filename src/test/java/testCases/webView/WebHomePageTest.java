package testCases.webView;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.screens.webView.HomeScreenV2;
import screens.screens.webView.WebHomeScreenV2;

import static devices.MobileFactory.getEmulator;

public class WebHomePageTest extends BaseTest {
  private WebHomeScreenV2 webHomeScreenV2;

  @BeforeClass
  public void beforeWebViewClass() {
    driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
//    Capabilities caps = CapabilityFactory.getCaps(getSimulator());

    driver = driverProvider.getLocalServerDriver(caps);
    setLogParams(caps);

    webHomeScreenV2 = new HomeScreenV2(driver)
      .openWebViewScreen();

  }

  @BeforeMethod
  public void beforeMethod() {
    webHomeScreenV2.switchToWebViewContext();

  }

  @Test
  public void closeAnnouncement() {
    webHomeScreenV2.closeAnnouncement();
  }

//  @Author
//  @Test(dataProvider = "menuItem", dataProviderClass = MenuItemProvider.class)
//  public void checkMenuItem(MenuItemDataModel menuItem) {
//    webHomeScreenV2.openLeftNav();
//    boolean isFound = webHomeScreenV2.hasMenuItem(menuItem);
//    Assert.assertTrue(isFound, "Can't find menu item: " + menuItem.toString());
//  }

}
