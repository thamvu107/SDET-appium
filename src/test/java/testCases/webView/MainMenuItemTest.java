package testCases.webView;

import base.BaseTest;
import customAnnotations.author.Author;
import dataProvider.webView.MenuItemProvider;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.webView.MenuItemDataModel;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.screens.HomeScreen;
import pageObjects.screens.webView.WebHomeScreen;

import java.util.List;

import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

public class MainMenuItemTest extends BaseTest {
    private WebHomeScreen webHomeScreen;


    @BeforeClass
    public void beforeWebViewClass() {
        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());

            driver = driverProvider.getLocalServerDriver(caps);

            webHomeScreen = new HomeScreen(driver)
                    .openWebViewScreen();

            webHomeScreen.closeAnnouncement();

        } catch (Exception e) {
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        webHomeScreen.switchToWebViewContext();

    }

    @AfterMethod
    public void afterMethod() {
        webHomeScreen.closeLeftNav();
    }


    // Practice data provider: way 1
    @Author
    @Test(dataProvider = "menuItem", dataProviderClass = MenuItemProvider.class)
    public void checkMenuItem(MenuItemDataModel menuItem) {
        webHomeScreen.openLeftNav();
        boolean isFound = webHomeScreen.hasMenuItem(menuItem);
        Assert.assertTrue(isFound, "Can't find menu item: " + menuItem.toString());
    }


    // Practice data provider: way 2
    @Author(THAM_VU)
    @Test(dataProvider = "menuItemList", dataProviderClass = MenuItemProvider.class)
    public void checkMenuItemList(List<MenuItemDataModel> menuItemList) {
        webHomeScreen.openLeftNav();
        boolean isListMatch = webHomeScreen.isMenuItemListCorrect(menuItemList);
        Assert.assertTrue(isListMatch, "Menu item list doesn't match");
    }
}
