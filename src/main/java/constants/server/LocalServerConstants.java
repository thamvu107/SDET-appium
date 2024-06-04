package constants.server;

public class LocalServerConstants extends ServerConstants {

    private static final String DEFAULT_LOCAL_CONFIG_PATH = "configs/configuration.properties";

    public LocalServerConstants() {
        super(DEFAULT_LOCAL_CONFIG_PATH);
    }

    public LocalServerConstants(String customConfigPath) {
        super(customConfigPath);
    }
}
