package constants.server;

public class RemoteServerConstants extends ServerConstants {

    private static final String DEFAULT_REMOTE_CONFIG_PATH = "src/test/resources/remote-server.properties";

    public RemoteServerConstants() {
        super(DEFAULT_REMOTE_CONFIG_PATH);
    }

    public RemoteServerConstants(String customConfigPath) {
        super(customConfigPath);
    }
}
