package tests.examples;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


import PageFactory.GoogleHomePage;
import utils.BaseTest;
import utils.Reporter;
import utils.Steps;
import static utils.Driver.webDriver;


public class GoogleTest extends BaseTest {
    Steps step = new Steps();
    

    /**
     * Example of code using page factory
     */
    @Test
    public void TC03WhatsMyIp() {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Reporter.extentTest = Reporter.extentReports.createTest(currentMethodName);
        
        GoogleHomePage googleHomePage = new GoogleHomePage(webDriver);

        step.launchBrowser();
        step.sendKeys(googleHomePage.txtboxSearch, "whats my ip");
        step.click(googleHomePage.btnSearch);
        step.getText(googleHomePage.labelIpAddress);

    }

    /**
     * Example of code using findElement
     */
    @Test
    public void TC02SearchMyName() {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Reporter.extentTest = Reporter.extentReports.createTest(currentMethodName);


        step.launchBrowser();
        step.sendKeys(webDriver.findElement(By.xpath("//*[@name='q']")), "aldwin lagman linkedin");
        step.click(webDriver.findElement(By.xpath(" (//*[@name='btnK'])[last()]")));
        step.click(webDriver.findElement(By.xpath("(//h3)[1]")));

    }

    /**
     * Testing extent report
     */
     
    @Test
    public void TC01SearchExtentReport() {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Reporter.extentTest = Reporter.extentReports.createTest(currentMethodName);
        
        GoogleHomePage googleHomePage = new GoogleHomePage(webDriver);
        
        step.launchBrowser();
        step.sendKeys(googleHomePage.txtboxSearch, "extent report");
        step.click(googleHomePage.btnSearch);
    }


}
