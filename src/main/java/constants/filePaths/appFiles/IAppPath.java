package constants.filePaths.appFiles;

import constants.filePaths.IBasePaths;

import java.io.File;

public interface IAppPath extends IBasePaths {
    String APP_FILE_BASE_PATH = TEST_RESOURCES_PATH + "apps" + File.separator;
    //    String APP_CONFIG_FILE_BASE_PATH = TEST_RESOURCES_PATH + "config" + File.separator;
    String APP_CONFIG_FILE_BASE_PATH = "src" + File.separator + "test"
            + File.separator + "resources" + File.separator + "config" + File.separator;
    String DEV_ENV = "dev" + File.separator;
    String QA_ENV = "qa" + File.separator;
    String PROD_ENV = "prod" + File.separator;

}
