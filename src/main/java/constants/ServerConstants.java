package constants;

import java.time.Duration;

public interface ServerConstants {
    //    String LOCAL_SERVER_IP = "127.0.0.1";
    String LOCAL_SERVER_IP = "localhost";
    int LOCAL_SERVER_PORT = 4723;
    Duration WDA_LAUNCH_TIMEOUT = Duration.ofMinutes(4);

    Duration SERVER_START_TIMEOUT = Duration.ofMinutes(3);
}
