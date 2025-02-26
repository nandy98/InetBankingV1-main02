package pageObjects;

import java.util.NoSuchElementException;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "user-box-text")
	@CacheLookup
	WebElement txtUserName;

	@FindBy(id = "pword-box-text")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(id = "loginBtnCol")
	@CacheLookup
	WebElement btnLogin;


	public void setUserName(String uname) {
		txtUserName.clear(); // Clear field before entering text
		txtUserName.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		txtPassword.clear(); // Clear field before entering text
		txtPassword.sendKeys(pwd);
	}

	public void clickSubmit() {
		btnLogin.click(); // Click the login button
	}

	public boolean isLoginSuccessful() {
	    String expectedTitle = "Serv-U"; // Update this based on your app's expected title
	    String actualTitle = ldriver.getTitle();
	    
	    return actualTitle != null && actualTitle.contains(expectedTitle);
	}

	    
	}
	

