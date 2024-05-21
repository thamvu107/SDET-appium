package mobildeDevices;

import driverFactory.Platform;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AndroidMobile extends Mobile {

    public AndroidMobile(String deviceName) {
        super();
        this.platformName = Platform.ANDROID;
        this.deviceName = deviceName;
    }

    public AndroidMobile(String udid, String deviceName) {
        super();
        this.platformName = Platform.ANDROID;
        this.deviceName = deviceName;
        this.udid = udid;
    }
}
