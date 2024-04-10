package server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.nio.file.Path;

public class AppiumServerManager {
    private static AppiumDriverLocalService appiumService;

    public static void startAppiumServer() {
        if (appiumService != null && appiumService.isRunning()) {
            return;
        }

        final var appiumPath = Path.of(System.getenv("HOME"), ".local/share/nvm/v20.12.1/lib/node_modules/appium/build/lib/main.js").toFile();
        final var nodePath = Path.of(System.getenv("HOME"), ".local/share/nvm/v20.12.1/bin/node").toFile();
        final var logFile = Path.of(System.getProperty("user.dir"), "appium.log").toFile();
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("http://localhost")
                .usingPort(4723)
                .withLogFile(logFile)
                .withArgument (GeneralServerFlag.LOG_LEVEL, "debug")
                .withAppiumJS(appiumPath)
                .usingDriverExecutable(nodePath)
                .withArgument(GeneralServerFlag.USE_DRIVERS, "uiautomator2")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appiumService = AppiumDriverLocalService.buildService(builder);
        appiumService.start();
    }

}
