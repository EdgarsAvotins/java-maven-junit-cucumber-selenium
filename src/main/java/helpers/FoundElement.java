package helpers;

import config.TestConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static helpers.BrowserHelper.getDriver;

/**
 * Represents an element found on a page, providing methods to interact with the element.
 */
public class FoundElement {
    private static final Duration DEFAULT_TIMEOUT = TestConfig.getDefaultTimeout();
    private WebElement currentElement;

    /**
     * Constructs a FoundElement with the provided WebElement.
     *
     * @param element the WebElement representing the found element
     */
    public FoundElement(WebElement element) {
        this.currentElement = element;
    }

    /**
     * Constructs a FoundElement by finding the element using the provided locator.
     *
     * @param locator the locator used to identify the element
     */
    public FoundElement(By locator) {
        find(locator);
    }

    private void find(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        this.currentElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Returns the underlying WebElement object.
     *
     * @return the WebElement object
     */
    public WebElement getAsSeleniumWebElement() {
        return currentElement;
    }

    /**
     * Finds a nested element within this element using the provided nested locator and returns a FoundElement for interacting with the nested element.
     *
     * @param nestedElementLocator the locator used to identify the nested element
     * @return a FoundElement representing the found nested element
     */
    public FoundElement findNestedElement(By nestedElementLocator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        this.currentElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(currentElement, nestedElementLocator));
        return this;
    }

    /**
     * Clicks on the element.
     *
     * @return the same FoundElement instance for method chaining
     */
    public FoundElement click() {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        wait.ignoring(ElementClickInterceptedException.class, ElementNotInteractableException.class);
        wait.until(_driver -> {
            currentElement.click();
            return true;
        });
        return this;
    }

    /**
     * Enters the provided text into the element.
     *
     * @param text the text to be entered into the element
     * @return the same FoundElement instance for method chaining
     */
    public FoundElement sendKeys(String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        wait.ignoring(ElementClickInterceptedException.class, ElementNotInteractableException.class);
        wait.until(_driver -> {
            currentElement.sendKeys(text);
            return true;
        });
        return this;
    }

    /**
     * Scrolls the page to bring the element into view.
     *
     * @return the same FoundElement instance for method chaining
     */
    public FoundElement scrollIntoView() {
        JavascriptExecutor j = (JavascriptExecutor) getDriver();
        j.executeScript("arguments[0].scrollIntoView({block: 'center'})", currentElement);
        return this;
    }

    /**
     * Validates that the element is visible on the page.
     *
     * @return the same FoundElement instance for method chaining
     */
    public FoundElement validateIsVisible() {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(currentElement));
        return this;
    }

    /**
     * Validates that the element has the expected RGB color value.
     *
     * @param rgb the expected RGB color value, e.g., "rgb(255, 0, 0)"
     * @return the same FoundElement instance for method chaining
     */
    public FoundElement validateIsRGBColor(String rgb) {
        String actualHex = getAsSeleniumWebElement().getCssValue("color");
        assert(rgb.equals(actualHex));
        return this;
    }

    /**
     * Finds a list of nested elements within this element using the provided nested locator and returns a FoundElementList for interacting with the list of elements.
     *
     * @param parentElement       the parent element within which to search for the nested elements
     * @param nestedElementLocator the locator used to identify the nested elements
     * @return a FoundElementList representing the found list of nested elements
     */
    public FoundElementList findNestedElementList(WebElement parentElement, By nestedElementLocator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT);
        List<WebElement> elementList = wait.until(presenceOfNestedElementsLocatedBy(parentElement, nestedElementLocator));
        return new FoundElementList(elementList);
    }

    // Missing method in ExpectedCondition
    private ExpectedCondition<List<WebElement>> presenceOfNestedElementsLocatedBy(final WebElement parent, final By childLocator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> allChildren = parent.findElements(childLocator);
                return allChildren.isEmpty() ? null : allChildren;
            }

            public String toString() {
                return String.format("visibility of element located by %s", childLocator);
            }
        };
    }
}
