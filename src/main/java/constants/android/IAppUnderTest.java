package constants.android;

import java.time.Duration;

public interface IAppUnderTest {
    String APP_PACKAGE = "com.wdiodemoapp";
    String APP_ACTIVITY = "com.wdiodemoapp.MainActivity";

    boolean APP_WAIT_FOR_LAUNCH = true;
    Duration APP_WAIT_DURATION = Duration.ofMillis(400_000L); // default = 20_000

    Duration ANDROID_INSTALL_TIMEOUT = Duration.ofMillis(400_000L); // default = 90_000

    boolean AUTO_GRANT_PERMISSION = true;
    boolean IS_ENFORCE_APP_INSTALL = false;
    String BUNDLE_ID = "com.wdiodemoapp";


}
