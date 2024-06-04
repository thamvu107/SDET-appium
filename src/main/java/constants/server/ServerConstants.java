package constants.server;

import dataProvider.PropertyController;
import lombok.Getter;

@Getter
public abstract class ServerConstants {
    protected String configureFilePath;
    protected PropertyController propertyController;
    protected String protocol;
    protected String ip;
    protected int port;
    protected int driverTimeoutInSeconds;

    public ServerConstants(String relativeConfigurePath) {
        this.configureFilePath = relativeConfigurePath;
        this.propertyController = new PropertyController(this.configureFilePath);
        this.protocol = propertyController.getStringProperty("protocol");
        this.ip = propertyController.getStringProperty("ip");
        this.port = propertyController.getIntProperty("port");
        this.driverTimeoutInSeconds = propertyController.getIntProperty("driverTimeoutInSeconds");
    }
}
