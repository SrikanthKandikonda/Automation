package io.pagelibrary.fc2.o;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.testlibrary.fc2.o.DriverAndReportClass;
import io.utils.fc2.o.Wait;

public class GoogleSignInPage extends DriverAndReportClass {

	Wait wait = new Wait();
	WebDriver driver;

	public GoogleSignInPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@type='email']")
	private WebElement googleEmailField;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement googlePasswordField;

	@FindBy(xpath = "//div[contains(@id,'Next')]")
	private WebElement nextButton;

	@FindBy(xpath = "//span[text()='Try another way']")
	private WebElement tryAnotherWayButton;

	@FindBy(xpath = "//div[@class='sZwd7c B6Vhqe']")
	private List<WebElement> progressBar;

	@FindBy(xpath = "//div[@class='sZwd7c B6Vhqe']")
	private WebElement progress;

	public HomePage googleSignIn(String email, String password) {
		wait.waitForElementToBeVisible(driver, googleEmailField, 30);
		googleEmailField.sendKeys(email);
		nextButton.click();
		wait.waitForElementToBeVisible(driver, progress, 30);
		wait.waitForElementToBeInvisible(driver, progressBar, 30);
		wait.waitForElementToBeVisible(driver, googlePasswordField, 30);
		googlePasswordField.sendKeys(password);
		nextButton.click();
		wait.waitForElementToBeInvisible(driver, progressBar, 30);
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage googleTwoStepVerification() {
		wait.waitForElementToBeClickable(driver, tryAnotherWayButton, 30);
		wait.waitForElementToBeVisible(driver, progress, 30);
		wait.waitForElementToBeInvisible(driver, progressBar, 30);
		wait.waitForWebPageRefresh(driver, 30);
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}
}
