package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {
    
    WebDriver driver;
    
    @FindBy(xpath = "//*[@name='q']")
    public WebElement txtboxSearch;

    @FindBy(xpath = "(//*[@name='btnK'])[1]")
    public WebElement btnSearch;
    
    @FindBy(xpath = "(//div[@jscontroller='PhunLe']/span)[1]")//technically this is not in the google homepage, just too lazy to bother lol
    public WebElement labelIpAddress;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
