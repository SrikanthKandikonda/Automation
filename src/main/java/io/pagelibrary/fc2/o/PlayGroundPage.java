package io.pagelibrary.fc2.o;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.utils.fc2.o.Wait;

public class PlayGroundPage extends HomePage {
	Wait wait = new Wait();
	WebDriver driver;

	public PlayGroundPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//button[@class='tertiary-btn tertiary-btn-lg']")
	private WebElement generateNewTestNumberButton;

	@FindBys(@FindBy(xpath = "//div[@class='test-numbers']//ul/li"))
	private List<WebElement> listOfTestNumbers;

	@FindBys(@FindBy(xpath = "//div[@class='test-numbers']//ul/li//a"))
	private List<WebElement> listOfTestNumberTexts;

	@FindBy(xpath = "//div[@class='btn-wrapper']//button[@class='g-btn-primary']")
	private WebElement backToDashBoardButton;

	@FindBy(xpath = "//div[@class='btn-wrapper']//button[@class='g-btn-negative']")
	private WebElement signOutButton;

	public PlayGroundPage clickOnGenerateNewTestNumber() {
		wait.waitForElementToBeVisible(driver, generateNewTestNumberButton, 30);
		generateNewTestNumberButton.click();
		return PageFactory.initElements(getDriver(), PlayGroundPage.class);
	}

	public PlayGroundPage clickOnTestNumber() {
		wait.waitForElementToBeVisible(driver, backToDashBoardButton, 30);
		listOfTestNumbers.get(0).click();
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), PlayGroundPage.class);
	}

	public PlayGroundPage clickOnBackToDashBoard() {
		wait.waitForElementToBeVisible(driver, backToDashBoardButton, 30);
		backToDashBoardButton.click();
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), PlayGroundPage.class);
	}

	public String getPageTitlePlayGroundPageTitle() {
		wait.waitForElementToBeVisible(driver, backToDashBoardButton, 30);
		return driver.getTitle();
	}

	public String getAccountNumberText(int index) {
		String accountNumber = "";
		wait.waitForAllElementsToBeVisible(driver, listOfTestNumberTexts, 30);
		String text = listOfTestNumberTexts.get(index).getText();
		String[] number = text.split("-");
		for (int i = 0; i < number.length; i++) {
			accountNumber = accountNumber + number[i];
		}
		return accountNumber;
	}

}
