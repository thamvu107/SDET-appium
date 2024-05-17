package server;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ServerConfig {

    @NonNull
    private String serverIP;

    @NonNull
    private int port;

    public void setServerIP(String serverIP) {

        if (serverIP == null || serverIP.isEmpty()) {
            throw new IllegalArgumentException("Server IP cannot be null or empty");
        }
        this.serverIP = serverIP;
    }

    public void setPort(int port) {

        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Port must be between 1 and 65535");
        }
        this.port = port;
    }

}
