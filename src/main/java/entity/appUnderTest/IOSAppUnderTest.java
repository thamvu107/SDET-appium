package entity.appUnderTest;

import constants.android.IAppUnderTest;
import lombok.Getter;

import java.io.File;

@Getter
public class IOSAppUnderTest extends AppUnderTest {
    private static final String IOS_APP_PATH = APP_FILE_BASE_PATH + "ios" + File.separator;
    private static final String BUNDLE_ID = IAppUnderTest.BUNDLE_ID;

    public IOSAppUnderTest() {
        this.appFileName = getProperties().getProperty("iosAppFileName");
        this.appFilePath = getAppPath();
    }

    @Override
    public String getAppPath() {

        return IOS_APP_PATH + this.getSubPathByEnv() + this.getAppFileName();
    }
}
