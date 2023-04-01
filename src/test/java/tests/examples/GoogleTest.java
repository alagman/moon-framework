package tests.examples;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import PageFactory.GoogleHomePage;
import utils.BaseTest;
import utils.Steps;
import static utils.Driver.webDriver;

public class GoogleTest extends BaseTest {
    Steps step = new Steps();

    /**
     * Example of code using page factory
     */
    @Test
    public void whatsMyIp() {
        
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
    public void searchMyName() {
        step.launchBrowser();
        step.sendKeys(webDriver.findElement(By.xpath("//*[@name='q']")), "aldwin lagman");
        step.click(webDriver.findElement(By.xpath(" (//*[@name='btnK'])[last()]")));
        step.click(webDriver.findElement(By.xpath("(//h3)[1]")));

    }
}
