package utils;

import static utils.Driver.webDriver;
import org.testng.annotations.*;



public class BaseTest {
    

    @BeforeClass
    public void setupClass() {
        Reporter.extentReports.attachReporter(Reporter.spark);
    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @BeforeTest
    public void beforeTest() {
        new Driver();//initializes the driver
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterTest
    public void afterTest() {
        webDriver.close();//closes the browser
        webDriver.quit();//closes the webdriver and browser as well
        
    }

    @AfterClass
    public void AfterClass() {
        Reporter.extentReports.flush();
    }
}
