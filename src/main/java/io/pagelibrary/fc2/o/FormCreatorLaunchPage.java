package io.pagelibrary.fc2.o;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.testlibrary.fc2.o.DriverAndReportClass;
import io.utils.fc2.o.Wait;

public class FormCreatorLaunchPage extends DriverAndReportClass {

	Wait wait = new Wait();
	WebDriver driver;

	public FormCreatorLaunchPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//button[@class='login-btn']")
	private WebElement googleSignInButton;

	public GoogleSignInPage clickOnGoogleSignInButton() {
		wait.waitForWebPageLoad(driver, 30);
		wait.waitForElementToBeClickable(driver, googleSignInButton, 30);
		googleSignInButton.click();
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), GoogleSignInPage.class);
	}

}
