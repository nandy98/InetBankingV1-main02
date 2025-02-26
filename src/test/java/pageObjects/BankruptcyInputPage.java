package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BankruptcyInputPage {

	WebDriver ldriver;

	public BankruptcyInputPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//span[text()='Input']")
	@CacheLookup
	WebElement inputFolder;

	@FindBy(xpath = "//button[@aria-label='Upload']")
	@CacheLookup
	WebElement uploadBtn;

	@FindBy(xpath = "//div[@class='file-dropzone']")
	@CacheLookup
	WebElement browseFile;

	@FindBy(css = "input[type='file']")
	@CacheLookup
	WebElement fileInputAIT;

	@FindBy(xpath = "//span[text()='BankruptcyFolderStructure']")
	@CacheLookup
	public
	WebElement bankruptcyFolderStructure;

	String filePath = ".\\ScrubAutomationQAFrameWork\\Documents\\DemoTextFile01.txt";

	public void clickBankruptcyFolder() {
		bankruptcyFolderStructure.click();
	}
	public void clickInputFolder() {
		inputFolder.click();
	}

	public void clickUploadBtn() {
		uploadBtn.click();
	}

	public void clickBrowseFileBtn() {
		browseFile.click();
	}

	public void setFileInputAIT() {
		fileInputAIT.sendKeys(filePath);
	}
}
