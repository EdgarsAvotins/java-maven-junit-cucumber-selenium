package helpers;

import config.TestConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

import static helpers.BrowserHelper.getDriver;

/**
 * Represents a list of elements found on a page, providing methods to interact with the elements.
 * Once working with single element from the list, you get it as an object of FoundElement class with its useful methods
 */
public class FoundElementList {
    private static final Duration DEFAULT_TIMEOUT = TestConfig.getDefaultTimeout();
    private List<WebElement> currentElementList;

    /**
     * Constructs a FoundElementList by finding the list of elements using the provided locator.
     *
     * @param locator the locator used to identify the list of elements
     */
    public FoundElementList(By locator) {
        findList(locator);
    }

    /**
     * Constructs a FoundElementList by taking a previously found list of elements.
     *
     * @param elementList the list of elements used as base for the next actions
     */
    public FoundElementList(List<WebElement> elementList) {
        this.currentElementList = elementList;
    }

    private void findList(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        this.currentElementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Returns the FoundElement at the specified index in the list.
     *
     * @param index the index of the element to retrieve
     * @return the FoundElement at the specified index
     */
    public FoundElement get(int index) {
        return new FoundElement(currentElementList.get(index));
    }

    /**
     * Performs the provided action on each element in the list.
     * Before the action, element is transformed into FoundElement object for improved interaction methods
     *
     * @param action the action to be performed on each element
     */
    public void forEachFoundElement(Consumer<FoundElement> action) {
        currentElementList.forEach(element -> {
            FoundElement elementObject = new FoundElement(element);
            action.accept(elementObject);
        });
    }
}
