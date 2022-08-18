package io.pagelibrary.fc2.o;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;

public class AccountDetailsPage extends HomePage {

	Wait wait = new Wait();
	RandomData accountData = new RandomData();
	Scroll scroll = new Scroll();
	WebDriver driver;

	public AccountDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	private static String companyName;

	private static String accountName;

	private static String answerPhrase;

	private static String answerPhraseInstruction;

	private static String disabledOthersInAnswerPhraseInstruction = "g-radio-button mr-3";

	@FindBy(xpath = "//div[@class='full--h overflow-a']//div[@class='g-input-wrapper full--w mb-6']//label[text()='Company Name']//following-sibling::input[@class='g-input']")
	private WebElement companyNameTextField;

	@FindBy(xpath = "//div[@class='full--h overflow-a']//div[@class='g-input-wrapper full--w mb-6']//label[text()='Account Name']//following-sibling::input[@class='g-input']")
	private WebElement accountNameTextField;

	@FindBy(xpath = "//div[@class='full--h overflow-a']//div[@class='g-input-wrapper full--w mb-6']//label[text()='Answer Phrase']//following-sibling::textarea")
	private WebElement answerPhraseTextField;

	@FindBy(xpath = "//div[@class='fx fx-j-sb fx-a-c']//div")
	private WebElement answerPhraseInstructionToggleButton;

	@FindBy(xpath = "//div[@class='fx fx-j-sb fx-a-c']//div[@class ='g-togg-btn g-togg--on']")
	private WebElement enabledAnswerPhraseInstructionToggleButton;

	@FindBys(@FindBy(xpath = "//ul[@class='mt-5 text--sm']//li//button"))
	private List<WebElement> answerPhraseInstructionOptionsRadioButtons;

	@FindBy(xpath = "//ul[@class='mt-5 text--sm']")
	private WebElement answerPhraseInstructionBlock;

	@FindBy(xpath = "//div[@class='textarea-wrapper full--w  mt-5 ps-r']//textarea")
	private WebElement otherTextfieldInAnswerPharseInstruction;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap mb-6 has-search']//button//span")
	private WebElement outpulseNumberDropDownButton;

	@FindBy(xpath = "//div[@class='g-dropmenu' and @style ='display: block;' ]")
	private WebElement outPulseNumberBlock;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap mb-6 has-search']//div[@class='g-dropmenu']//ul/li"))
	private List<WebElement> outpulseNumberDropDownOptions;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap mb-6 has-search']//div[@class='g-searchbox']//input")
	private WebElement searchOutpulseNumberField;

	@FindBy(xpath = "//div[@id='isSuperAccount']")
	private WebElement superAccountToggleButton;

	@FindBy(xpath = "//div[@id='isTwilioSMS']")
	private WebElement twilioSmsConfigurationToggleButton;

	@FindBy(xpath = "//div[@id='isContactVerification']")
	private WebElement contactVerificationToggleButton;

	@FindBy(xpath = "//div[@id='messageAnnotation']")
	private WebElement messageAnnotationToggleButton;

	@FindBy(xpath = "//div[@id='isMessageBranding']")
	private WebElement poweredByToggleButton;

	@FindBy(xpath = "//div[@id='isMessageBrandingAddress']")
	private WebElement fromAddressToggleButton;

	@FindBy(xpath = "//section[@id='settings']//div[@class='account-details ph-8 pt-8 pb-24']//button[@class='g-btn-primary']")
	private WebElement saveAccountDetailsButton;

	@FindBy(xpath = "//section[@id='settings']//div[@class='account-details ph-8 pt-8 pb-24']//button[@class='g-btn-negative']")
	private WebElement cancelAccountDetailsChangesButton;

	@FindBy(xpath = "//div[@class='fx fx-j-sb fx-a-c mb-6']//child::div[contains(@class,'g-togg-btn ml-6')]//cite")
	private WebElement dayGreetingsToggleButton;

	@FindBys(@FindBy(xpath = "//div[@class='fx fx-j-sb fx-a-c mb-6']//child::div[@class='g-togg-btn ml-6 g-togg--on']//cite"))
	private List<WebElement> dayGrettingsEnabledToggleButton;

	public AccountDetailsPage enterCompanyName() {
		wait.waitForElementToBeVisible(driver, companyNameTextField, 30);
		scroll.scrollUpToWebElement(driver, companyNameTextField);
		companyNameTextField.clear();
		companyName = accountData.randomData("Company Name");
		companyNameTextField.sendKeys(companyName);
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage enterAccountName() {
		wait.waitForElementToBeVisible(driver, accountNameTextField, 30);
		scroll.scrollUpToWebElement(driver, accountNameTextField);
		accountNameTextField.clear();
		accountName = accountData.randomData("Account Name");
		accountNameTextField.sendKeys(accountName);
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage enterAnswerPhrase() {
		wait.waitForElementToBeVisible(driver, answerPhraseTextField, 30);
		scroll.scrollUpToWebElement(driver, answerPhraseTextField);
		answerPhraseTextField.clear();
		answerPhrase = accountData.randomData("Answer Phrase");
		answerPhraseTextField.sendKeys(answerPhrase);
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage enableOrDisableAnswerInsturction() {
		try {
			wait.waitForElementToBeVisible(driver, enabledAnswerPhraseInstructionToggleButton, 2);
		} catch (Exception disabledAnswerPhrase) {
			getReport().log(Status.INFO, "Answer Pharse Instruction Disabled");
			answerPhraseInstructionToggleButton.click();
		}
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);

	}

	public AccountDetailsPage clickAnswerPhraseInsturction(int instuctionIndex) {
		wait.waitForElementToBeVisible(driver, answerPhraseInstructionBlock, 30);
		scroll.scrollUpToWebElement(driver, answerPhraseInstructionOptionsRadioButtons.get(instuctionIndex));
		if (answerPhraseInstructionOptionsRadioButtons.get(instuctionIndex).getAttribute("class")
				.equals(disabledOthersInAnswerPhraseInstruction)) {
			answerPhraseInstructionOptionsRadioButtons.get(instuctionIndex).click();
		}

		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);

	}

	public AccountDetailsPage enterAnswerInsturction() {
		wait.waitForElementToBeVisible(driver, otherTextfieldInAnswerPharseInstruction, 30);
		otherTextfieldInAnswerPharseInstruction.clear();
		answerPhraseInstructionOptionsRadioButtons.get(2).click();
		wait.waitForElementToBeVisible(driver, otherTextfieldInAnswerPharseInstruction, 30);
		answerPhraseInstruction = accountData.randomData("Answer Phrase Instruction");
		otherTextfieldInAnswerPharseInstruction.sendKeys(answerPhraseInstruction);
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);

	}

	public String companyNameBeforeSaving() {

		return companyName;
	}

	public String accountNameBeforeSaving() {

		return accountName;
	}

	public String answerPhraseBeforeSaving() {

		return answerPhrase;
	}

	public String answerPhraseInstructionBeforeSaving() {

		return " " + answerPhraseInstruction;
	}

	public String companyNameAfterSaving() {
		wait.waitForElementToBeVisible(driver, companyNameTextField, 30);
		return companyNameTextField.getAttribute("value");
	}

	public String accountNameAfterSaving() {
		wait.waitForElementToBeVisible(driver, accountNameTextField, 30);
		return accountNameTextField.getAttribute("value");
	}

	public String answerPhraseAfterSaving() {
		try {
			wait.waitForElementToBeVisible(driver, otherTextfieldInAnswerPharseInstruction, 5);
		} catch (Exception AnswerPhraseInstructionNotEnabled) {

		}
		return answerPhraseTextField.getText();
	}

	public String answerPhraseInstructionAfterSaving() {
		wait.waitForElementToBeVisible(driver, otherTextfieldInAnswerPharseInstruction, 30);
		return otherTextfieldInAnswerPharseInstruction.getText();
	}

	public HomePage clickOnSaveAccountDetailsButton() {
		wait.waitForElementToBeVisible(driver, saveAccountDetailsButton, 30);
		saveAccountDetailsButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public AccountDetailsPage clickOnOutPulseNumberDropDown() {
		wait.waitForElementToBeClickable(driver, outpulseNumberDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, outpulseNumberDropDownButton);
		outpulseNumberDropDownButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);

	}

	public AccountDetailsPage clickOnOutPulseNumbers() {
		try {
			wait.waitForElementToBeVisible(driver, outPulseNumberBlock, 2);
			scroll.scrollUpToWebElement(driver, outpulseNumberDropDownOptions.get(0));
			if (outpulseNumberDropDownOptions.size() > 1) {
				int optionNumber = accountData.randomIntegerData(0, outpulseNumberDropDownOptions.size() - 2);
				outpulseNumberDropDownOptions.get(optionNumber).click();
			}
		} catch (Exception noOutPulseNumber) {

		}
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnSuperAcountToggleButton() {
		wait.waitForElementToBeClickable(driver, superAccountToggleButton, 30);
		scroll.scrollUpToWebElement(driver, superAccountToggleButton);
		superAccountToggleButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnTwilioSmsConfigurationToggleButton() {
		wait.waitForElementToBeClickable(driver, twilioSmsConfigurationToggleButton, 30);
		scroll.scrollUpToWebElement(driver, twilioSmsConfigurationToggleButton);
		twilioSmsConfigurationToggleButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnContactVerificationToggleButton() {
		wait.waitForElementToBeClickable(driver, contactVerificationToggleButton, 30);
		scroll.scrollUpToWebElement(driver, contactVerificationToggleButton);
		contactVerificationToggleButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnMessageAnnotationToggleButton() {
		wait.waitForElementToBeClickable(driver, messageAnnotationToggleButton, 30);
		scroll.scrollUpToWebElement(driver, messageAnnotationToggleButton);
		messageAnnotationToggleButton.click();
		confirmMessageAnnotationAlertNotification();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnPoweredByToggleButton() {
		wait.waitForElementToBeClickable(driver, poweredByToggleButton, 30);
		scroll.scrollUpToWebElement(driver, poweredByToggleButton);
		poweredByToggleButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnFromAddressToggleButton() {
		wait.waitForElementToBeClickable(driver, fromAddressToggleButton, 30);
		scroll.scrollUpToWebElement(driver, fromAddressToggleButton);
		fromAddressToggleButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

	public AccountDetailsPage clickOnDayGreetingsToggleButton() {
		wait.waitForElementToBeClickable(driver, dayGreetingsToggleButton, 30);
		scroll.scrollUpToWebElement(driver, dayGreetingsToggleButton);
		if (dayGrettingsEnabledToggleButton.size() == 0)
			dayGreetingsToggleButton.click();
		return PageFactory.initElements(getDriver(), AccountDetailsPage.class);
	}

}
