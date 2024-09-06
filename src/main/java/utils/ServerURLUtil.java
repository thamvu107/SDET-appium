package utils;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class ServerURLUtil {

  //  public URL configureServerURL(String isRemote) {
//    if (isRemote == null || !isRemote.equalsIgnoreCase("true")) {
//      return ServerURLUtil.getServerURL();
//    }
//
//    // Fetch hub IP address from environment variables or system properties
//    String hubIpAddress = System.getenv("hubIpAddress");
//    if (hubIpAddress == null) {
//      hubIpAddress = System.getProperty("hubIpAddress");
//    }
//
//    // Validate the hub IP address
//    if (hubIpAddress == null || hubIpAddress.trim().isEmpty()) {
////      throw new RuntimeException("Please provide a valid 'hub' environment variable or system property!");
//      hubIpAddress = "localhost";
//    }
//
//    try {
//     return new URL("http://" + hubIpAddress + ":4444");
//    } catch (MalformedURLException e) {
//      throw new RuntimeException("Failed to construct a valid URL with the provided hub IP address: " + hubIpAddress, e);
//    }
//
//  }
  public static URL getServerURL() {

    String remoteInfoViaEnvVar = System.getenv("isRemote");
    String remoteInfoViaCmdLineArgs = System.getProperty("isRemote");
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
