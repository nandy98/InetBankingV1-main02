package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjects.LoginPage;
import utilities.Reporting;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() {
	    Reporting.startTest("Login Test");  // Start reporting for this test
	    
	    try {
	        logger.info("Opening URL: " + baseURL);
	        driver.get(baseURL);
	        logger.info("URL opened successfully");

	        LoginPage lp = new LoginPage(driver);
	        lp.setUserName(username);
	        logger.info("Entered username: " + username);
	        lp.setPassword(password);
	        logger.info("Entered password");
	        lp.clickSubmit();
	        logger.info("Clicked on login button");
	        Reporting.attachScreenshot(driver); // Capture screenshot after clicking submit

	        
	        // Check if login was successful
	        boolean isLoggedIn = lp.isLoginSuccessful();

	        Assert.assertTrue(isLoggedIn, "Login failed: Actual title was: " + driver.getTitle());
	        Reporting.logPass("Login was successful.");
	        

	    } catch (AssertionError e) {
	        Reporting.attachScreenshot(driver); // Attach screenshot on failure
	        Reporting.logFail("Login test failed: " + e.getMessage());
	        logger.error("Assertion error during login test", e);
	    } catch (Exception e) {
	        Reporting.attachScreenshot(driver); // Attach screenshot on error
	        Reporting.logFail("Login test encountered an error: " + e.getMessage());
	        logger.error("Exception during login test", e);
	    } finally {
	        Reporting.endTest();  // End the test reporting
	    }
	}
	

}
