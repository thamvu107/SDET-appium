package mobildeDevices.android;

import lombok.Getter;

import java.time.Duration;

import static constants.AndroidConstants.DEFAULT_ADV_TIMEOUT;

@Getter
public class Emulator extends AndroidMobile {
    private final String adv;
    private Duration advTimeout;

    public Emulator(String deviceName, String adv) {
        super(deviceName);
        this.adv = adv;
        this.advTimeout = DEFAULT_ADV_TIMEOUT;
    }

    public Emulator setAvdTimeout(Duration timeout) {

        this.advTimeout = timeout;
        return this;
    }
}
