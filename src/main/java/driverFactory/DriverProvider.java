package driverFactory;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import utils.DataObjectBuilderUtil;

import java.net.URL;
import java.nio.file.Path;

public class DriverProvider {

    public AppiumDriver getLocalServerDriver(Capabilities caps) {

        URL localServerURL = getLocalServerURL();
        return createDriver(localServerURL, caps);
    }

    public AppiumDriver getRemoteServerDriver(Capabilities caps) {

        URL remoteServerURL = getRemoteServerURL();
        return createDriver(remoteServerURL, caps);

    }

    private URL getLocalServerURL() {

        ServerConfig server = new ServerConfig("127.0.0.1", 4723);
        return server.getServerURL();
    }

    private URL getRemoteServerURL() {

        Path serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
        ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

        return serverConfig.getServerURL();
    }

    private AppiumDriver createDriver(URL serverURL, Capabilities caps) {
        DriverFactory factory = new DriverFactory(serverURL, caps);
        return factory.getDriver();
    }

    public void quitDriver(AppiumDriver driver) {
        if (driver != null) {
            try {
                System.out.println(driver);
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
