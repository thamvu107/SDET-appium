package learning;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.Test;

import static devices.MobileFactory.getEmulator;

public class LaunchAppTest extends BaseTest {

    @Test
    public void launchApp() {

        try {
            driverProvider = new DriverProvider();
            Capabilities caps = CapabilityFactory.getCaps(getEmulator());
            driver = driverProvider.getLocalServerDriver(caps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
