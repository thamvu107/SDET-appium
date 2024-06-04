package practice;

import constants.WaitConstants;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.MobileInteractions;
import utils.WaitUtils;

import static devices.MobileFactory.getAndroidMobile;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected static WebDriverWait wait;
    protected static FluentWait<AppiumDriver> fluentWait;
    protected MobileInteractions mobileInteractions;

    DriverProvider driverProvider;


    @BeforeClass
    public void setUpAppium() {

//        this.driver = DriverFactory.getLocalServerDriver(MobileFactory.getEmulator());
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getAndroidMobile());
        driver = driverProvider.getRemoteServerDriver(caps);

        WaitUtils waitUtils = new WaitUtils(driver);
        wait = waitUtils.explicitWait();
        fluentWait = waitUtils.fluentWait(WaitConstants.SHORT_FLUENT_WAIT, WaitConstants.POLLING_EVERY);
        mobileInteractions = new MobileInteractions(this.driver);
    }

    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}
