package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporter{
    
    public static ExtentReports extentReports = new ExtentReports();
    public static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
    public static ExtentTest extentTest;

    public void logReport(Status status, String details){
        extentTest.log(status, details);
        //TODO add screenshots
        
        
    }
}
