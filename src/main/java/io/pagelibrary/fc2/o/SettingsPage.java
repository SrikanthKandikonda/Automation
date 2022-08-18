package io.pagelibrary.fc2.o;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.testlibrary.fc2.o.DriverAndReportClass;
import io.utils.fc2.o.Wait;

public class SettingsPage extends DriverAndReportClass {
	Wait wait = new Wait();
	WebDriver driver;

	public SettingsPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//li[@data-setting-sec='accountstab']")
	private WebElement accountDetailsTab;

	@FindBy(xpath = "//li[@data-setting-sec='businesshours']")
	private WebElement businessHoursAndClosuresTab;

	@FindBy(xpath = "//li[@data-setting-sec='helpandsupport']")
	private WebElement helpAndSupportTab;

	public AccountDetailsPage clickOnAccountDetailsTab() {
		wait.waitForElementToBeClickable(driver, accountDetailsTab, 30);
		accountDetailsTab.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnBusinessHoursAndClosuresTab() {
		wait.waitForElementToBeClickable(driver, businessHoursAndClosuresTab, 30);
		businessHoursAndClosuresTab.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public HelpAndSupportPage clickOnHelpAndSupportTab() {
		wait.waitForElementToBeClickable(driver, helpAndSupportTab, 30);
		helpAndSupportTab.click();
		return PageFactory.initElements(getDriver(), HelpAndSupportPage.class);
	}
}
