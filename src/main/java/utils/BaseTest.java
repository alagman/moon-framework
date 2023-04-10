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
        System.out.println("this is my driver"+webDriver.toString());
        
        

        
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterTest
    public void afterTest() {
        //needs wait
        try {
            // Thread.sleep(5000);
            webDriver.close();//closes the browser
            webDriver.quit();//closes the webdriver and browser as well
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // new Steps().waitForLoad();

        // webDriver.close();//closes the browser
        // webDriver.quit();//closes the webdriver and browser as well
        
       
    }

    @AfterClass
    public void AfterClass() {
        Reporter.extentReports.flush();
        // webDriver.quit();
    }
}
