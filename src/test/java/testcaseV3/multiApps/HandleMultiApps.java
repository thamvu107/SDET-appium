package testcaseV3.multiApps;

import annotations.author.Author;
import base.BaseTestV3;
import com.google.common.base.Verify;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.setting.SettingScreen;

import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class HandleMultiApps extends BaseTestV3 {

  @BeforeClass
  public void beforeWebViewClass() {
    log.atInfo().log("Before Class ReportableTestStatus get driver " + getDriver());
  }

  @Author(THAM_VU)
  @Test
  public void openSettingApp() {

    homeScreen = new HomeScreen(getDriver()).openHomeScreen();
    Verify.verify(homeScreen.homeScreenDisplayed(), "Home screen is not open");

    SettingScreen settingScreen = new SettingScreen(getDriver())
      .runAppInBackground()
      .switchSettingApp();
    Assert.assertNotNull(settingScreen.seeSettingPage(), "Setting screen is not open");

    settingScreen.switchAppUnderTest();
    Assert.assertTrue(homeScreen.homeScreenDisplayed(), "Home screen is not open");
  }
}
