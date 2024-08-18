package utils;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class ServerURLUtil {

  public static URL getServerURL() {

    String remoteInfoViaEnvVar = System.getenv("remote");
    String remoteInfoViaCmdLineArgs = System.getProperty("remote");
    String isRemote = remoteInfoViaEnvVar != null ? remoteInfoViaEnvVar : remoteInfoViaCmdLineArgs;

    if (isRemote == null) {
      isRemote = "false";
    }

    switch (isRemote.toLowerCase()) {
      case "true":
        return ServerURLUtil.getRemoteServerURL();
      case "false":
      default:
        return ServerURLUtil.getLocalServerURL();
    }
  }

  private static URL getLocalServerURL() {

    Path serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

    try {
      return serverConfig.getServerURL();
    } catch (Exception e) {
      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
      throw new RuntimeException("Invalid server URL" + e);

    }
  }

  private static URL getRemoteServerURL() {

    Path serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

    try {
      return serverConfig.getServerURL();
    } catch (Exception e) {
      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
      throw new RuntimeException("Invalid server URL" + e);

    }
  }
}
