package devices.android;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

import static constants.AndroidDeviceConstants.DEFAULT_ADV_TIMEOUT;

@Getter
@Setter
public class Emulator extends AndroidMobile {
    private String adv;
    private Duration advTimeout;
    private static final Duration defaultADVTimeout = Duration.ofMillis(2000);

    public Emulator(String deviceName, String platformVersion, String adv) {
        super(deviceName, platformVersion);
        this.adv = adv;
        this.advTimeout = DEFAULT_ADV_TIMEOUT;
    }

    public Emulator(String deviceName, String platformVersion, String adv, int systemPort) {
        this(deviceName, platformVersion, adv);
        this.systemPort = systemPort;
    }

    public Emulator(String udid, int systemPort) {
        super(udid, systemPort);
    }

    public Emulator setAvdTimeout(Duration timeout) {
        this.advTimeout = timeout;
        return this;
    }
}