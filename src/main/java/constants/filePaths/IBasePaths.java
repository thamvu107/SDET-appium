package constants.filePaths;

import java.io.File;

public interface IBasePaths {
//    private static final String USER_DIR_PATH = System.getProperty("user.dir") + File.separator;
//    private static final String TEST_RESOURCES_PATH = USER_DIR_PATH + "src" + File.separator + "test"
//            + File.separator + "resources" + File.separator;
//    protected static final String APP_FILE_BASE_PATH = TEST_RESOURCES_PATH + "apps" + File.separator;
//    protected static final String JSON_BASE_PATH = TEST_RESOURCES_PATH + "testData" + File.separator + "jsonFiles" + File.separator;

    String USER_DIR_PATH = System.getProperty("user.dir") + File.separator;
    String TEST_RESOURCES_PATH = USER_DIR_PATH + "src" + File.separator + "test"
            + File.separator + "resources" + File.separator;
//    String APP_FILE_BASE_PATH = TEST_RESOURCES_PATH + "apps" + File.separator;
//    /Users/tham/Code/appium-driver-improve/src/test/resources/testData/jsonFiles/appFileName.json

}
