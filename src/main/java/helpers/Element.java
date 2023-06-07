package helpers;

import org.openqa.selenium.By;

/**
 * Represents an element in a page object, providing a blueprint for elements before they are actually searched.
 */
public class Element {
    private final By locator;

    /**
     * Constructs an Element with the given locator.
     *
     * @param locator the locator used to identify the element
     */
    public Element(By locator) {
        this.locator = locator;
    }

    /**
     * Finds the element using the locator and returns a FoundElement for interacting with the element.
     *
     * @return a FoundElement representing the found element
     */
    public FoundElement find() {
        return new FoundElement(locator);
    }

    /**
     * Finds a nested element within this element using the provided nested locator and returns a FoundElement for interacting with the nested element.
     *
     * @param nestedLocator the locator used to identify the nested element
     * @return a FoundElement representing the found nested element
     */
    public FoundElement findNestedElement(By nestedLocator) {
        return new FoundElement(locator).findNestedElement(nestedLocator);
    }
}
