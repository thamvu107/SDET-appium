package testCases.MultiApps;

import base.BaseTest;
import customAnnotations.author.Author;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.setting.SettingScreen;

import static devices.MobileFactory.getSimulator;
import static interfaces.IAuthor.THAM_VU;

public class HandleMultiApps extends BaseTest {

    @BeforeClass
    public void beforeWebViewClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getSimulator());
//            Capabilities caps = CapabilityFactory.getCaps(getEmulator());
            driver = driverProvider.getLocalServerDriver(caps);
            putMDC(caps);

        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @Author(THAM_VU)
    @Test
    public void openSettingApp() {
        homeScreen = new HomeScreen(driver);
        Assert.assertTrue(homeScreen.homeScreenDisplayed(), "Home screen is not open");
        SettingScreen settingScreen = new SettingScreen(driver)
                .runAppInBackground()
                .switchSettingApp();
        Assert.assertTrue(settingScreen.showingSettingTitle(), "Setting screen is not open");

        settingScreen.switchAppUnderTest();
        Assert.assertTrue(homeScreen.homeScreenDisplayed(), "Home screen is not open");
    }
}
