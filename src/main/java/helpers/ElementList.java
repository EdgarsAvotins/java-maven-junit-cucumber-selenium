package helpers;

import org.openqa.selenium.By;

/**
 * Represents a list of elements in a page object, providing a blueprint for element lists before they are actually searched.
 */
public class ElementList {
    private final By locator;

    /**
     * Constructs an ElementList with the given locator.
     *
     * @param locator the locator used to identify the list of elements
     */
    public ElementList(By locator) {
        this.locator = locator;
    }

    /**
     * Finds the list of elements using the locator and returns a FoundElementList for interacting with the elements.
     *
     * @return a FoundElementList representing the found list of elements
     */
    public FoundElementList findList() {
        return new FoundElementList(locator);
    }
}
