package Utils;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.Dimension;

public class ScreenSize {
    private final AppiumDriver driver;

    @Getter
    private final Dimension screenSize;

    @Getter
    private final int width;
    @Getter
    private final int height;

    public ScreenSize(AppiumDriver driver) {

        this.driver = driver;
        synchronized (driver) {
            this.screenSize = driver.manage().window().getSize();
            this.width = screenSize.getWidth();
            this.height = screenSize.getHeight();
        }
    }
}
