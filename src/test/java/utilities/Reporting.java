package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter spark;

    // Call this method once at the beginning of your test suite
    public static void setupReporting() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("index.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Extent Reports QA");
        extent.attachReporter(spark);
    }

    // Start a new test
    public static void startTest(String testName) {
        test = extent.createTest(testName);
        
    }

    public static void logInfo(String message) {
        test.info(message);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }

    // Call this method at the end of your test suite
    public static void endTest() {
        extent.flush();
    }

    public static void attachScreenshot(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = "screenshots/" + System.currentTimeMillis() + ".png"; // Create a unique file name
        try {
            Files.copy(screenshot.toPath(), new File(filePath).toPath());
            test.addScreenCaptureFromPath(filePath); // Attach screenshot to report
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}