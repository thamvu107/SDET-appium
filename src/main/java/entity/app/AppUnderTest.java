package entity.app;

import constants.filePaths.appFiles.IAppPath;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Getter
//@RequiredArgsConstructor
public abstract class AppUnderTest implements IAppPath {

    protected String appFileName;
    protected String appFilePath;
    protected String envVar;
    protected Properties properties;

//    static {
//        try (InputStream input = AppUnderTest.class.getClassLoader().getResourceAsStream(getPropertiesFileName(envVar))) {
//            if (input == null) {
//                throw new IOException("Unable to find properties file for environment: " + envVar);
//            }
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    protected AppUnderTest() {
        this.envVar = Optional.ofNullable(System.getenv("env")).orElse("dev").toLowerCase();
        this.properties = loadProperties();
    }

    protected static final Map<String, String> ENV_PATHS = Map.of(
            "dev", DEV_ENV,
            "qa", QA_ENV,
            "prod", PROD_ENV
    );


    protected String getSubPathByEnv() {

        if (!ENV_PATHS.containsKey(envVar)) {
            throw new IllegalArgumentException("Invalid environment: " + envVar + ". Valid values are: dev, qa, prod.");
        }
        String fileAddress = ENV_PATHS.get(envVar);

        return fileAddress;
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        String env = Optional.ofNullable(System.getenv("env")).orElse("dev").toLowerCase();

        try (InputStream input = new FileInputStream(getPropertiesFileName())) {
            if (input == null) {
                throw new IOException("Unable to find properties file for environment: " + env);
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return props;
    }

    private String getPropertiesFileName() {
        return APP_CONFIG_FILE_BASE_PATH + getSubPathByEnv() + "app.properties";
    }

    public abstract String getAppPath();
}
