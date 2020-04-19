package tests.examples;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.Steps;
import static utils.Driver.webDriver;

public class GoogleTest extends BaseTest {
    Steps step = new Steps();
    @Test
    public void whatsMyIp() {
        step.launchBrowser();
        step.sendKeys(webDriver.findElement(By.xpath("//*[@name='q']")), "whats my ip");
        step.click(webDriver.findElement(By.xpath(" (//*[@name='btnK'])[1]")));
        step.getText(webDriver.findElement(By.xpath("//w-answer-desktop/div[1]")));

    }

    @Test
    public void searchMyName() {
        step.launchBrowser();
        step.sendKeys(webDriver.findElement(By.xpath("//*[@name='q']")), "aldwin lagman");
        step.click(webDriver.findElement(By.xpath(" (//*[@name='btnK'])[last()]")));
        step.click(webDriver.findElement(By.xpath("(//h3)[1]")));

    }
}
