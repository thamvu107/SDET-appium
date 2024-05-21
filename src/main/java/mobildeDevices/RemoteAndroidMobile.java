package mobildeDevices;

public class RemoteAndroidMobile extends AndroidMobile {
    public RemoteAndroidMobile(String deviceName, String udid) {
        super(deviceName);
        this.udid = udid;
    }
}
