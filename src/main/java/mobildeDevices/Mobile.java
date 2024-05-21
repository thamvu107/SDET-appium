package mobildeDevices;

import driverFactory.Platform;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Mobile {
    protected Platform platformName;
    protected String deviceName;
    protected String platformVersion;
    protected String udid;

    public Mobile setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
        return this;
    }
}
