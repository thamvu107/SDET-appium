package entity.app;

import constants.android.IAppUnderTest;
import lombok.Getter;

import java.io.File;

@Getter
public class IOSAppUnderTest extends AppUnderTest {
    private static final String IOS_APP_PATH = APP_FILE_BASE_PATH + "ios" + File.separator;
    private final String bundleId;

    public IOSAppUnderTest() {
        this.appFileName = getProperties().getProperty("iosAppFileName");
        this.appFilePath = getAppPath();
        this.bundleId = IAppUnderTest.BUNDLE_ID;
    }

    @Override
    public String getAppPath() {

        return IOS_APP_PATH + this.getSubPathByEnv() + this.getAppFileName();
    }
}
