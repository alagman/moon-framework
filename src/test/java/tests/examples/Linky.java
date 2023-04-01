package tests.examples;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import PageFactory.GoogleHomePage;
import utils.BaseTest;
import utils.Steps;
import static utils.Driver.webDriver;

public class Linky extends BaseTest {
    Steps step = new Steps();
    
    @Test
    public void searchMyName() {
        step.launchBrowser();
        step.sendKeys(webDriver.findElement(By.xpath("//*[@name='q']")), "aldwin lagman");
        step.click(webDriver.findElement(By.xpath(" (//*[@name='btnK'])[last()]")));
        step.click(webDriver.findElement(By.xpath("(//h3)[1]")));

    }
}
