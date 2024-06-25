package testFlows;

import io.appium.java_client.AppiumDriver;

public class BaseFlow {

    protected final AppiumDriver driver;

    public BaseFlow(AppiumDriver driver) {

        this.driver = driver;
    }


}
