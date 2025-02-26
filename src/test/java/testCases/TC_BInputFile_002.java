package testCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.BankruptcyInputPage;
import pageObjects.LoginPage;
import utilities.Reporting;

public class TC_BInputFile_002 extends BaseClass {

    BankruptcyInputPage bankruptcyInputPage;
    LoginPage lp;

    @Test
    public void fileUploadInput() throws InterruptedException {
        Reporting.startTest("Login and Upload Testing");
        bankruptcyInputPage = new BankruptcyInputPage(driver);
        lp = new LoginPage(driver);

        driver.get(baseURL);
        lp.setUserName(username);
        Reporting.logInfo("Entered username");
        lp.setPassword(password);
        lp.clickSubmit();
        
        // Wait for login to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Serv-U")); // Adjust based on expected title
        Thread.sleep(10000);
        Reporting.logPass("Logged in successfully");
        driver.navigate().refresh();
        
        // Wait for the bankruptcy folder structure to be visible
        wait.until(ExpectedConditions.visibilityOf(bankruptcyInputPage.bankruptcyFolderStructure));

        if (bankruptcyInputPage.bankruptcyFolderStructure != null) {
            bankruptcyInputPage.bankruptcyFolderStructure.click(); // Click if available
            Reporting.logInfo("Clicked on bankruptcy folder structure");
        } else {
            Reporting.logFail("Bankruptcy folder structure element not found");
        }

        Reporting.attachScreenshot(driver); // Take a screenshot after critical actions

        Reporting.endTest();
    }
}
