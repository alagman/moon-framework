package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporter {

    public static ExtentReports extentReports = new ExtentReports();
    public static ExtentTest extentTest;

    public void logReport(Status status, String details) {
        extentTest.log(status, details);
        // TODO add screenshots

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
}
