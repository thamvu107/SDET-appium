package practice;

import driverFactory.Driver;
import io.appium.java_client.AppiumDriver;
import mobildeDevices.MobileFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.MobileInteractions;
import utils.WaitUtils;

import static constants.WaitConstant.POLLING_EVERY;
import static constants.WaitConstant.SHORT_FLUENT_WAIT;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected static WebDriverWait wait;
    protected static FluentWait<AppiumDriver> fluentWait;
    protected MobileInteractions mobileInteractions;


    @BeforeClass
    public void setUpAppium() {

        this.driver = Driver.getLocalServerDriver(MobileFactory.getEmulator());

        WaitUtils waitUtils = new WaitUtils(driver);
        wait = waitUtils.explicitWait();
        fluentWait = waitUtils.fluentWait(SHORT_FLUENT_WAIT, POLLING_EVERY);
        mobileInteractions = new MobileInteractions(this.driver);
    }

    @AfterClass
    public void tearDown() {
        Driver.quitDriver(driver);
    }

}
