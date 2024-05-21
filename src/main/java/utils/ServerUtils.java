package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import server.ServerConfig;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.ServerConstants.*;

public class ServerUtils {
    private static AppiumDriverLocalService service;
    public AppiumDriver driver;

    public static void startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1")
                .usingPort(4723)
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public static URL getServerURL(ServerConfig config) {
        try {
            return new URL("http", config.getServerIP(), config.getPort(), "");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid server server config");
        }
    }

    public static URL getLocalServerURL() {

        ServerConfig config = new ServerConfig(LOCAL_SERVER_IP, SERVER_PORT);

        return getServerURL(config);

    }

    public static URL getRemoteServerURL() {

        ServerConfig config = new ServerConfig(REMOTE_SERVER_IP, SERVER_PORT);

        return getServerURL(config);

    }


    public void stopServer() {
        service.stop();
    }

}
