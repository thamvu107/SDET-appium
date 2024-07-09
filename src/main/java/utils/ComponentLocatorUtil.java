package utils;

import annotations.selector.ComponentAccessibilityId;
import annotations.selectors.ComponentCssSelector;
import annotations.selectors.ComponentIdSelector;
import annotations.selectors.ComponentXpathSelector;
import enums.PlatformType;
import io.appium.java_client.AppiumBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.lang.annotation.Annotation;

@Slf4j
public class ComponentLocatorUtil {
  public By getComponentLocator(Class<?> componentClass, PlatformType platform) throws NoSuchMethodException, IllegalAccessException {
    try {
      if (componentClass.isAnnotationPresent(ComponentXpathSelector.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentXpathSelector.class, AppiumBy::xpath);
      } else if (componentClass.isAnnotationPresent(ComponentAccessibilityId.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentAccessibilityId.class, AppiumBy::accessibilityId);
      } else if (componentClass.isAnnotationPresent(ComponentCssSelector.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentCssSelector.class, AppiumBy::cssSelector);
      } else if (componentClass.isAnnotationPresent(ComponentIdSelector.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentIdSelector.class, AppiumBy::id);
      } else {
        throw new IllegalArgumentException(
          "Component class " + componentClass.getSimpleName() + " must have annotation " +
            ComponentAccessibilityId.class.getSimpleName() + " or " +
            ComponentXpathSelector.class.getSimpleName() + " or " +
            ComponentCssSelector.class.getSimpleName());
      }
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

  private static By getLocatorByAnnotation(Class<?> componentClass, PlatformType platform,
                                           Class<? extends Annotation> annotationType, SelectorFunction function)
    throws NoSuchMethodException, IllegalAccessException {
    Annotation annotation = null;
    try {
      annotation = componentClass.getAnnotation(annotationType);
    } catch (NullPointerException e) {
      String errorMessage =
        "Component class " + componentClass.getSimpleName() + " must have annotation " + annotationType.getSimpleName();
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new NullPointerException(errorMessage);
    }
    try {
      String locatorValue = getAnnotationValue(annotation, platform.toString().toLowerCase());
      return function.apply(locatorValue);

    } catch (Exception e) {
      log.atError().setMessage(e.getMessage()).setCause(e.getCause()).log();
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
  }

  private static String getAnnotationValue(Annotation annotation, String platform)
    throws NoSuchMethodException, IllegalAccessException {
    try {
      return (String) annotation.annotationType().getMethod(platform).invoke(annotation);
    } catch (Exception e) {
      log.atError().setMessage(e.getMessage()).setCause(e.getCause()).log();
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
  }

  @FunctionalInterface
  private interface SelectorFunction {
    By apply(String value);
  }
}
