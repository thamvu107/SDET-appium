package entity.app;

import constants.android.IAppUnderTest;
import lombok.Getter;

import java.io.File;
import java.time.Duration;

@Getter
public class AndroidAppUnderTest extends AppUnderTest {
    private static final String ANDROID_APP_PATH = APP_FILE_BASE_PATH + "android" + File.separator;
    private final String appPackage;
    private final String appActivity;
    private final boolean appWaitForLaunch;
    private final Duration appWaitDuration;
    private final Duration androidInstallTimeout;
    private final boolean autoGrantPermission;
    private final int remoteAppCacheLimit;
    private final boolean enforceAppInstall;

    public AndroidAppUnderTest() {
        this.appPackage = IAppUnderTest.APP_PACKAGE;
        this.appActivity = IAppUnderTest.APP_ACTIVITY;
        this.appFilePath = getAppPath();
        this.appFileName = getProperties().getProperty("androidAppFileName");
        this.appWaitForLaunch = IAppUnderTest.APP_WAIT_FOR_LAUNCH;
        this.appWaitDuration = IAppUnderTest.APP_WAIT_DURATION;
        this.androidInstallTimeout = IAppUnderTest.ANDROID_INSTALL_TIMEOUT;
        this.autoGrantPermission = IAppUnderTest.AUTO_GRANT_PERMISSION;
        this.remoteAppCacheLimit = 0; // zero disables apps caching
        this.enforceAppInstall = IAppUnderTest.IS_ENFORCE_APP_INSTALL;
    }

    @Override
    public String getAppPath() {

        return ANDROID_APP_PATH + this.getSubPathByEnv() + getAppFileName();
    }
}
