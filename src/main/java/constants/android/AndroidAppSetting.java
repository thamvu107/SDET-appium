package constants.android;

import utils.PathUtil;

import java.nio.file.Path;

import static constants.filePaths.appFiles.AppFilePathConstants.ANDROID_APP_DEV;

public interface AndroidAppSetting {
    String APP_PACKAGE = "com.wdiodemoapp";
    String APP_ACTIVITY = "com.wdiodemoapp.MainActivity";
    String APP_FILE_NAME = "android.wdio.native.app.v1.0.8.apk";
    Path RELATIVE_PATH = Path.of(ANDROID_APP_DEV);
    String APP_PATH = new PathUtil(RELATIVE_PATH).getAbsolutePathString();

}
