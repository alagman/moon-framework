package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static utils.Driver.webDriver;

public class Reporter {

    public static ExtentReports extentReports = new ExtentReports();
    public static ExtentTest extentTest;

    public void logReport(Status status, String details) {
        // String base64Code = captureScreenshot();
        // extentTest.log(status, details, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
        
        String path = captureScreenshot("test");
        extentTest.log(status, details, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    public void attachReporter() {
        String currentDateTime = getCurrentDateTime("yyyyMMdd_HHmm");
        ExtentSparkReporter spark = new ExtentSparkReporter(
                "target/ExtentReports/Report/Spark_" + currentDateTime + ".html");
        extentReports.attachReporter(spark);

    }

    public void flushReport() {
        extentReports.flush();
    }

    public String getCurrentDateTime(String format) {
        String dateTime = "";
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        dateTime = dateFormat.format(date);
        return dateTime;
    }

    public String captureScreenshot(String fileName) {
        String currentDateTime = getCurrentDateTime("yyyyMMdd_HHmmssSS");    
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File ("target/Screenshots/SC_"+fileName+currentDateTime+".jpg");
        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved!");
        return destFile.getAbsolutePath();
    }

    public String captureScreenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        String base64Code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        System.out.println("Screenshot saved!");
        return base64Code;
    }
}
