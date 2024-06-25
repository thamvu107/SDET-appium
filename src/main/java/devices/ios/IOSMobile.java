package devices.ios;

import devices.Mobile;
import enums.Platform;

public abstract class IOSMobile extends Mobile {

    protected int wdaLocalPort = 8100;

    protected IOSMobile() {
        super(Platform.IOS);
    }

    protected IOSMobile(String udid) {
        super(Platform.IOS, udid);
    }

    protected IOSMobile(String udid, int wdaLocalPort) {
        this(udid);
        this.wdaLocalPort = wdaLocalPort;
    }


    protected IOSMobile(String deviceName, String platformVersion, int wdaLocalPort) {
        super(Platform.IOS, deviceName, platformVersion);
        this.wdaLocalPort = wdaLocalPort;
    }

    protected IOSMobile(String udid, String deviceName, String platformVersion, int wdaLocalPort) {
        super(Platform.IOS, udid, deviceName, platformVersion);
        this.wdaLocalPort = wdaLocalPort;
    }
}
