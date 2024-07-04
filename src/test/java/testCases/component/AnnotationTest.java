package testCases.component;

import customAnnotations.selectors.ComponentXpathSelector;
import org.testng.annotations.Test;
import pageObjects.commponents.webView.NavComponent;

public class AnnotationTest {
    public static <T> void printComponent(Class<T> componentClass) {
        String xpath = componentClass.getAnnotation(ComponentXpathSelector.class).value();
        System.out.println("xpath " + xpath);
    }

    @Test
    public void testAnnotation() {
        printComponent(NavComponent.class);
    }
}
