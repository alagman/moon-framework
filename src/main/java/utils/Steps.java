package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import static utils.Driver.timeOut;
import static utils.Driver.webDriver;

import java.util.Timer;
import java.util.TimerTask;

public class Steps {
    ConfigReader configReader = new ConfigReader();
    Logger logger = LoggerFactory.getLogger(new Object() {
    }.getClass().getEnclosingClass().getSimpleName());
    Reporter reporter = new Reporter();
    // ExtentReports extent = new ExtentReports();
    

    // public Steps(){
    //     String callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
    //     ExtentTest test = extent.createTest(callerMethod);
        
    //     System.out.println(callerMethod);
    //     test.log(Status.PASS,"this is a detail");
        
        
    // }

    // public void logReport(){
    //     reporter.createReport().test
    // }


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
        String debugString = "Navigated to " + url;
        try{
            webDriver.get(url);
            logger.debug(debugString);
            // String callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName(); //this gets the method name of the method that called this method.
            // reporter.createReport(Status.PASS, callerMethod, debugString);
            reporter.logReport(Status.PASS, debugString);
        }catch(Exception e){
            reporter.logReport(Status.FAIL, e.getMessage());
        }
        
    }

    /**
     * Clicks the provided webElement
     * @param webElement the element to be clicked
     */
    public void click(WebElement webElement) {
        String debugString;
        try{
            waitElement(webElement);
            String elementName = getElementName(webElement);
            debugString = "Clicked " + "'" + elementName + "'";
            webElement.click();
            logger.debug(debugString);
            reporter.logReport(Status.PASS, debugString);
        }catch(Exception e){
            reporter.logReport(Status.FAIL, e.getMessage());
        }
        
    }

    /**
     * Enters character sequence on text box
     *
     * @param webElement webelement to enter the character sequence on
     * @param keys       character sequence to enter on the webElement
     */
    public void sendKeys(WebElement webElement, String keys) {
        String debugString;
        try{
            waitElement(webElement);
            String elementName = getElementName(webElement);
            webElement.sendKeys(keys);
            debugString = "Entered " + "'" + keys + "'" + " on " + "'" + elementName + "' text box";
            logger.debug(debugString);
            reporter.logReport(Status.PASS, debugString);
        }catch(Exception e)
        {
            reporter.logReport(Status.FAIL, e.getMessage());
        }
        
    }

    /**
     * Selects the visible text on a dropdown box
     *
     * @param webElement webelement to select the option on
     * @param option     visible text of option
     */
    public void select(WebElement webElement, String option) {
        String debugString;
        try{
            waitElement(webElement);
            String elementName = getElementName(webElement);
            Select select = new Select(webElement);
            select.selectByVisibleText(option);
            debugString = "Selected " + "'" + option + "'" + " on " + "'" + elementName + "' dropdown box";
            logger.debug(debugString);
        }catch(Exception e)
        {
            reporter.logReport(Status.FAIL, e.getMessage());
        }
    }

    public String getText(WebElement webElement) {
        String debugString;
        String elementName;
        String elementText = "";
        try{
            waitElement(webElement);
            elementName = getElementName(webElement);
            elementText = webElement.getText();
            debugString = "Text on " + "'" + elementName + "' is "+elementText;
            logger.debug(debugString);
            
        }catch(Exception e)
        {
            reporter.logReport(Status.FAIL, e.getMessage());
        }
        return elementText;
    }

}
