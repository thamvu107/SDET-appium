package devices;

import enums.Platform;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Mobile {
    protected Platform platformName;
    protected String deviceName;
    protected String platformVersion;
    protected String udid;


    protected Mobile(Platform platformName) {
        this.platformName = platformName;
    }

    protected Mobile(Platform platformName, String udid) {
        this(platformName);
        this.udid = udid;
    }

    protected Mobile(Platform platformName, String deviceName, String platformVersion) {
        this(platformName);
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
    }

    public Mobile(Platform platformName, String udid, String deviceName, String platformVersion) {
        this(platformName, deviceName, platformVersion);
        this.udid = udid;
    }
}
