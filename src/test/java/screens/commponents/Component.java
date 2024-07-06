package screens.commponents;

import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ComponentElementUtils;
import utils.ComponentLocatorUtil;
import utils.ElementUtils;
import utils.PlatformUtil;
import utils.WaitUtils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Component {
  protected AppiumDriver driver;
  @Getter
  protected By componentLoc;
  protected WebElement componentEl;
  protected WaitUtils waitUtils;
  protected ElementUtils elementUtils;
  //  protected ComponentLocatorUtil componentLocatorUtil;
  protected ComponentElementUtils componentElementUtils;
  protected PlatformType platformType;


  public Component(AppiumDriver driver, Map<PlatformType, By> componentLocator) {
    this.driver = driver;
    this.waitUtils = new WaitUtils(this.driver);
    this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
    this.elementUtils = new ElementUtils(driver);
    this.componentLoc = elementUtils.getLocator(componentLocator);
    this.componentEl = elementUtils.waitForFindingElement(this.componentLoc);
    this.componentElementUtils = new ComponentElementUtils(driver, componentEl);

    checkComponentElementFound();
  }

  public Component(AppiumDriver driver, By componentLocator) {
    this.driver = driver;
    this.waitUtils = new WaitUtils(this.driver);
    this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
    this.elementUtils = new ElementUtils(driver);
    this.componentLoc = componentLocator;
    this.componentEl = elementUtils.waitForFindingElement(this.componentLoc);
    this.componentElementUtils = new ComponentElementUtils(driver, componentEl);

    checkComponentElementFound();
  }

//  public Component(AppiumDriver driver, WebElement componentEl) {
//    this.driver = driver;
//    this.componentEl = componentEl;
//    this.waitUtils = new WaitUtils(this.driver);
//    this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
//  }

  public WebElement findElement(By by) {
    // Narrow down searching scope
    // wait until the component displayed
    return this.componentEl.findElement(by);
  }

  public List<WebElement> findElements(By by) {
    // Narrow down searching scope
    return this.componentEl.findElements(by);
  }

  public <T extends Component> T findComponent(Class<T> componentClass) throws NoSuchMethodException, IllegalAccessException {
    return findComponents(componentClass).get(0);
  }

  public <T extends Component> List<T> findComponents(Class<T> componentClass) throws NoSuchMethodException, IllegalAccessException {
    // 1. Get the component selector
//    By componentLoc;
    By componentLoc = null;
    try {
      componentLoc = new ComponentLocatorUtil().getComponentLocator(componentClass, platformType);
      System.out.println("componentLoc " + componentLoc);
    } catch (Exception e) {
      log.atError().setMessage(e.getMessage()).setCause(e).log();
      throw new RuntimeException(e);
    }

    // 2. Find the elements and Build Constructor

    // Wait until the component displayed on the page
    // In case the component is not on screen(for Android) need to swipe the screen
    // TODO: Explore this logic - null -> swipe the screen to find the component on the screen or not ?
    waitUntilComponentsDisplayed(componentLoc);

    // 2.1 Find the elements
    // TODO: null
    List<WebElement> elements = findElements(componentLoc);

    // 2.2 Define component class's constructor
    Constructor<T> constructor = null;
    try {
      constructor = getComponentConstructor(componentClass);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }


    // 3. Map the elements to components
    List<T> components = mapElementsToComponents(elements, constructor);

    return components;
  }

  private <T extends Component> List<T> mapElementsToComponents(List<WebElement> elements, Constructor<T> constructor) {
    List<T> components = elements.stream().map(webElement -> {
      try {
        return constructor.newInstance(driver, webElement);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }).collect(Collectors.toList());
    return components;
  }


//  private <T extends Component> Constructor<T> getComponentConstructor(Class<T> componentClass) throws NoSuchMethodException {
//    Class<?>[] constructorParams = new Class[] {AppiumDriver.class, WebElement.class};
//    return componentClass.getConstructor(constructorParams);
//  }

  private <T extends Component> Constructor<T> getComponentConstructor(Class<T> componentClass) throws NoSuchMethodException {
    Class<?>[] constructorParams = new Class[] {AppiumDriver.class, By.class};
    return componentClass.getConstructor(constructorParams);
  }


  private void waitUntilComponentsDisplayed(By componentLoc) {
    waitUtils.explicitWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(componentLoc));
    // TODO: Implement swiping logic for Android if necessary
  }

  private void checkComponentElementFound() {
    if (this.componentEl == null) {
      String errorMessage = "Component element is not found";
      log.atError().setMessage(errorMessage).log();
      throw new IllegalArgumentException("errorMessage");
    }
  }

//  private <T extends Component> By getComponentSel(Class<T> componentClass) {
//    if (componentClass.isAnnotationPresent(ComponentXpathSelector.class)) {
//      return AppiumBy.xpath(componentClass.getAnnotation(ComponentXpathSelector.class).value());
//    } else if (componentClass.isAnnotationPresent(ComponentAccessibilityIdSelector.class)) {
//      return AppiumBy.accessibilityId(
//        componentClass.getAnnotation(ComponentAccessibilityIdSelector.class).value());
//    } else {
//      throw new IllegalArgumentException(
//        "Component class " + componentClass + " must have annotation"
//          + ComponentAccessibilityIdSelector.class.getSimpleName() + " or"
//          + ComponentXpathSelector.class.getSimpleName());
//    }
//  }

}
