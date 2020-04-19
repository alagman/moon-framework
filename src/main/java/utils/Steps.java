package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utils.Driver.timeOut;
import static utils.Driver.webDriver;

public class Steps {
    ConfigReader configReader = new ConfigReader();
    Logger logger = LoggerFactory.getLogger(new Object() {
    }.getClass().getEnclosingClass().getSimpleName());

    /**
     * Waits the element until it is visible while ignoring StaleElement
     *
     * @param webElement webElement that needs waiting
     */
    public void waitElement(WebElement webElement) {
        new WebDriverWait(webDriver, timeOut).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
        scrollIntoView(webElement);
    }

    /**
     * scrolls the webElement into view. Scrolling function depends on the current browser
     *
     * @param webElement webElement to be scrolled into
     */
    public void scrollIntoView(WebElement webElement) {
        if ("chrome".equals(configReader.getBrowser())) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoViewIfNeeded();", webElement);
        } else if ("edge".equals(configReader.getBrowser())) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoViewIfNeeded();", webElement);
        } else if ("firefox".equals(configReader.getBrowser())) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webElement);
        } else if ("ie".equals(configReader.getBrowser())) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", webElement);
        }
    }

    /**
     * gets the name of the element
     *
     * @param webElement element to get the name from
     * @return this returns the element name of the given webElement
     */
    public String getElementName(WebElement webElement) {
        waitElement(webElement);
        String elementName = webElement.getText();

        if (elementName.equalsIgnoreCase("") || elementName.equalsIgnoreCase("null")) {
            try {
                elementName = webElement.getAttribute("value");
            } catch (Exception e) {
                elementName = "";
            }
        }

        if (elementName.equalsIgnoreCase("") || elementName.equalsIgnoreCase("null")) {
            try {
                elementName = webElement.getAttribute("innerText");
            } catch (Exception e) {
                elementName = "";
            }
        }

        if (elementName.equalsIgnoreCase("") || elementName.equalsIgnoreCase("null")) {
            try {
                elementName = webElement.getAttribute("title");
            } catch (Exception e) {
                elementName = "";
            }
        }

        if (elementName.equalsIgnoreCase("") || elementName.equalsIgnoreCase("null")) {
            try {
                elementName = webElement.getAttribute("id");
            } catch (Exception e) {
                elementName = "";
            }
        }

        if (elementName.equalsIgnoreCase("") || elementName.equalsIgnoreCase("null")) {
            try {
                elementName = webElement.getAttribute("name");
            } catch (Exception e) {
                elementName = "";
            }
        }

        if (elementName.equalsIgnoreCase("") || elementName.equalsIgnoreCase("null")) {
            try {
                elementName = webElement.getAttribute("aria-label");
            } catch (Exception e) {
                elementName = "";
            }
        }

        return elementName;
    }

    /**
     * Opens the url given on the config file
     */
    public void launchBrowser() {
        String url = configReader.getURL();
        webDriver.get(url);
        logger.debug("Navigated to " + url);
    }

    /**
     * Clicks the provided webElement
     * @param webElement the element to be clicked
     */
    public void click(WebElement webElement) {
        waitElement(webElement);
        String elementName = getElementName(webElement);
        webElement.click();
        logger.debug("Clicked " + "'" + elementName + "'");
    }

    /**
     * Enters character sequence on text box
     *
     * @param webElement webelement to enter the character sequence on
     * @param keys       character sequence to enter on the webElement
     */
    public void sendKeys(WebElement webElement, String keys) {
        waitElement(webElement);
        String elementName = getElementName(webElement);
        webElement.sendKeys(keys);
        logger.debug("Entered " + "'" + keys + "'" + " on " + "'" + elementName + "' text box");
    }

    /**
     * Selects the visible text on a dropdown box
     *
     * @param webElement webelement to select the option on
     * @param option     visible text of option
     */
    public void select(WebElement webElement, String option) {
        waitElement(webElement);
        String elementName = getElementName(webElement);
        Select select = new Select(webElement);
        select.selectByVisibleText(option);
        logger.debug("Selected " + "'" + option + "'" + " on " + "'" + elementName + "' dropdown box");
    }

    public String getText(WebElement webElement) {
        waitElement(webElement);
        String elementName = getElementName(webElement);
        String elementText = webElement.getText();
        logger.debug("Text on " + "'" + elementName + "' is "+elementText);
        return elementText;
    }

}
