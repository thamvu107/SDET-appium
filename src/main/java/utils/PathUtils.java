package utils;

public class PathUtils {

    public static String getAbsolutePath(String relativeFilePath) {

        return System.getProperty("user.dir").concat(relativeFilePath);
    }
}
