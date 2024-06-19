package pageObjects.screens;

import entity.MenuItems;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;

public class WebViewScreen extends BaseScreen {
    private final AppiumDriver driver;
    private final By webViewScreenLoc = AppiumBy.id("__docusaurus");
    private final By announcementBarLoc = By.cssSelector(".announcementBar_mb4j");
    private final By announcementCloseBtnLoc = By.cssSelector("button[class*='closeButton']");
    private final By toggleNavBarLoc = By.cssSelector("button[class*='navbar__toggle']");
    private final By menuListLoc = By.cssSelector(".menu__list li a");


    public WebViewScreen(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public WebViewScreen verifyWebViewScreen() {
        elementUtils.isElementDisplayed(webViewScreenLoc);

        return this;
    }

    private WebElement announcementCloseBtnEl() {
        return elementUtils.waitForElementTobeClickable(announcementCloseBtnLoc);
    }

    private WebElement toggleNavBarEl() {
        return elementUtils.waitForElementToBeVisible(toggleNavBarLoc);
    }

    public List<WebElement> menuListEl() {
        return driver.findElements(menuListLoc);
    }

    public WebViewScreen switchToNativeContext() {
        platformUtil.switchToNativeContext(currentPlatform, LONG_EXPLICIT_WAIT);

        return this;
    }

    public WebViewScreen switchToWebViewContext() {
        platformUtil.switchToWebViewContext(currentPlatform, LONG_EXPLICIT_WAIT);

        return this;
    }

    public WebViewScreen clickOnAnnouncementCloseButton() {
        announcementCloseBtnEl().click();

        return this;
    }

    public WebViewScreen invisibleAnnouncementBar() {
        Boolean invisibleAnnouncement = elementUtils.waitForElementsToBeInvisible(announcementBarLoc);
        Assert.assertTrue(invisibleAnnouncement, "Invisible announcement failed");

        return this;

    }

    public WebViewScreen clickOnToggleNavBar() {
        toggleNavBarEl().click();
        return this;
    }

    public WebViewScreen verifyMenuItemList() {
        List<MenuItems> menuItems = getMenuItems(menuListEl());

        // TODO:  Verification

        Assert.assertNotNull(menuItems, "menuItems is null");
        System.out.println(menuItems);

        return this;
    }

    private List<MenuItems> getMenuItems(List<WebElement> elements) {

        assertListNotEmpty(elements);

        List<MenuItems> actualMenuItems = new ArrayList<>();

        for (WebElement menuItemEl : elements) {

            String text = menuItemEl.getText();
            if (text.isEmpty()) {
                text = menuItemEl.getAttribute("aria-label");
            }

            String href = menuItemEl.getAttribute("href");
            actualMenuItems.add(new MenuItems(text, href));
        }

        return actualMenuItems;

    }

    private static void assertListNotEmpty(List<WebElement> elements) {
        if (elements.isEmpty()) {
            Assert.fail("The menuListEl is empty");
        }
    }

}
