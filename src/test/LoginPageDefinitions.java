package com.example.definitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class LoginPageDefinitions {
     
    private static WebDriver driver;       
    public final static int TIMEOUT = 10;
     
    @Before
    public void setUp() {
 
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }
 
    @Given("User is on HRMLogin page {string}")
    public void loginTest(String url) {
         
        driver.get(url);
  
    }
  
    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {
  
        // login to application
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(passWord);      
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit();
  
        // go the next page
         
    }
     
    @Then("User should be able to login sucessfully and new page open")
    public void verifyLogin() {
  
         String homePageHeading = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[1]/h5")).getText();
          
         //Verify new page - HomePage
         Assert.assertEquals(homePageHeading,"Employee Information");
  
    }
     
    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
  
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();
         
        // Verify Error Message
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);
  
    }
     
    @After
    public void teardown() {
  
        driver.quit();
    }
       
}