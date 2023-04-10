package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver webDriver;
    public static int timeOut = 60;

    public Driver() {
        webDriver = setupDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
    }

    public WebDriver setupDriver() {
        WebDriverManager.edgedriver().setup();
        ConfigReader configReader = new ConfigReader();
        String browser = configReader.getBrowser();// get this from config file

        if (browser.equalsIgnoreCase("chrome")) {
            // dc.setCapability("acceptInsecureCerts", true);
            // dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
            // UnexpectedAlertBehaviour.ACCEPT);
            // webDriver = new ChromeDriver(dc);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);
            webDriver = new ChromeDriver(chromeOptions);
        }

        if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            webDriver = new EdgeDriver(edgeOptions);
        }

        return webDriver;
    }
}
