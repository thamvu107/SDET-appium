package mobildeDevices;

import driverFactory.Platform;

public class IOSMobile extends Mobile {
    public IOSMobile(String udid, String deviceName) {
        super();
        this.platformName = Platform.IOS;
        this.udid = udid;
        this.deviceName = deviceName;
    }
}
