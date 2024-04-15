package android;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignUpPage;

import static org.testng.Assert.assertEquals;

public class WdioDemoTest {
    private AppiumDriver driver;

    @BeforeClass
    public void setUpClass() {
        this.driver = DriverFactory.getDriver(MobilePlatform.ANDROID);
    }

    @AfterClass
    public void tearDownClass() {
        this.driver.quit();
    }

    @Test
    public void testSignUp() {

        final var signUpPage = new SignUpPage((AndroidDriver) this.driver);
        final String actualMsg = signUpPage.signUp("qa.testing@gmail.com", "qa@1234567");
        final String expectedMsg = "You successfully signed up!";

        assertEquals(actualMsg, expectedMsg);
    }
}
