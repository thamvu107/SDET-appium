package testCases.component;

import annotations.selectors.ComponentXpathSelector;
import org.testng.annotations.Test;
import screens.commponents.webView.component.BottomNavComponent;

public class AnnotationTest {

  @Test
  public void testAnnotation() {

    printComponent(BottomNavComponent.class);
  }

  public static <T> void printComponent(Class<T> componentClass) {
    String xpath = componentClass.getAnnotation(ComponentXpathSelector.class).android();
    System.out.println("xpath " + xpath);
  }
}
