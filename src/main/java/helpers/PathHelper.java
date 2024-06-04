package helpers;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class PathHelper {

    private final Path rootPath;
    private final Path relativePath;

    public PathHelper(Path relativePath) {
        this.relativePath = relativePath;
        this.rootPath = Paths.get(System.getProperty("user.dir"));
    }

    public Path getAbsolutePath() {

        return relativePath.isAbsolute() ? relativePath : rootPath.resolve(relativePath).toAbsolutePath();
    }
}
