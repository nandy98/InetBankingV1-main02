package testCases;

import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;
import utilities.Reporting;

public class BaseClass {

    ReadConfig readConfig = new ReadConfig();
    public String baseURL = readConfig.getApplicationURL();
    public String username = readConfig.getUserName();
    public String password = readConfig.getPassword();
    public static WebDriver driver;

    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String browser) {
        logger = Logger.getLogger("Scrubs");
        Reporting.setupReporting();

        String log4jConfigFile = getClass().getClassLoader().getResource("log4j.properties").getPath();
        PropertyConfigurator.configure(log4jConfigFile);

        try {
            if (browser == null) {
                logger.error("Browser parameter is null. Defaulting to Chrome.");
                browser = "chrome";
            }
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    logger.info("ChromeDriver Launched");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    logger.info("FirefoxDriver Launched");
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    logger.info("InternetExplorerDriver Launched");
                    break;
                default:
                    logger.error("Unsupported browser: " + browser);
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver Closed");
        } else {
            logger.warn("WebDriver was not initialized.");
        }
        Reporting.endTest();
    }
    public WebElement waitForElementToBeVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    
        @BeforeMethod
        public void setupTest(Method method) {
            Reporting.startTest(method.getName());
        }

        @AfterMethod
        public void tearDownTest(ITestResult result) {
            
            Reporting.endTest();
        }
    

    
}