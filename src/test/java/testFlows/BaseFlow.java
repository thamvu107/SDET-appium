package testFlows;

import io.appium.java_client.AppiumDriver;
import screens.BaseScreen;

public class BaseFlow extends BaseScreen {

  protected final AppiumDriver driver;
//  @Getter
//  protected final HomeScreen homeScreen;

  public BaseFlow(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
  }

}
