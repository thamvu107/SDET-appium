package testCaseV2.multiApps;

import annotations.author.Author;
import base.BaseTestV2;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.setting.SettingScreen;

import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class HandleMultiApps extends BaseTestV2 {

  @BeforeClass
  public void beforeClass() {
    System.out.println("Before Class: driver " + driver);
  }

  @Author(THAM_VU)
  @Test
  public void openSettingApp() {
    homeScreen = new HomeScreen(driver).openHomeScreen();
    Assert.assertTrue(homeScreen.homeScreenDisplayed(), "Home screen is not open");
    SettingScreen settingScreen = new SettingScreen(driver)
      .runAppInBackground()
      .switchSettingApp();
    Assert.assertTrue(settingScreen.showingSettingTitle(), "Setting screen is not open");

    settingScreen.switchAppUnderTest();
    Assert.assertTrue(homeScreen.homeScreenDisplayed(), "Home screen is not open");
  }
}
