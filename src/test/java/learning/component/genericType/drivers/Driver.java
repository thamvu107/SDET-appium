package learning.component.genericType.drivers;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import devices.Mobile;
import driver.AppiumDriver;
import entity.ServerConfig;
import utils.DataObjectBuilderUtil;

import java.net.URL;
import java.nio.file.Path;

public abstract class Driver {

    public abstract AppiumDriver innitDriver(Mobile mobile);

    public abstract AppiumDriver quitDriver();

    protected URL getLocalServer() {
        Path serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
        ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

        return serverConfig.getServerURL();
    }

    protected URL getRemoteServer() {
        Path serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
        ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

        return serverConfig.getServerURL();
    }
}
