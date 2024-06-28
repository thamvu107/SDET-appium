package entity.appUnderTest;

import constants.android.IAppUnderTest;
import lombok.Getter;

import java.io.File;

@Getter
public class AndroidAppUnderTest extends AppUnderTest {
    private static final String ANDROID_APP_PATH = APP_FILE_BASE_PATH + "android" + File.separator;
    private final String appPackage;
    private final String appActivity;

    public AndroidAppUnderTest() {
        this.appPackage = IAppUnderTest.APP_PACKAGE;
        this.appActivity = IAppUnderTest.APP_ACTIVITY;
        this.appFilePath = getAppPath();
        this.appFileName = getProperties().getProperty("androidAppFileName");

    }

    @Override
    public String getAppPath() {

        return ANDROID_APP_PATH + this.getSubPathByEnv() + getAppFileName();
    }
}
