package learning.genericType;

import annotations.selectors.ComponentXpathSelector;
import learning.componentExploring.components.BottomNavComponent;
import org.testng.annotations.Test;

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
