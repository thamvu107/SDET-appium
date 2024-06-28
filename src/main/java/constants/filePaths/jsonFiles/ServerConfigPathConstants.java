package constants.filePaths.jsonFiles;

import java.io.File;

public interface ServerConfigPathConstants extends JsonFileConstants {
    String REMOTE_SERVER_CONFIG_JSON = JSON_BASE_PATH + "serverConfigure" + File.separator + "RemoteServer" + JSON_SUFFIX;
}

