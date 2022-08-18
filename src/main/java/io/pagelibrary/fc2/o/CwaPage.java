package io.pagelibrary.fc2.o;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;
import io.utils.fc2.o.WindowHandles;

public class CwaPage extends HomePage {
	Wait wait = new Wait();
	RandomData cwaPageData = new RandomData();
	Scroll scroll = new Scroll();
	WindowHandles handle = new WindowHandles();
	WebDriver driver;
	private static String selectedAnswerPhrase;
	private static List<String> updatedFieldScriptInCwa = new ArrayList<>();
	private static String customerStatus;
	private static int businessDays;
	private static int closures;
	private static String timeZone;

	public CwaPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='login-fields-hold']//button[@class='button-default ']")
	private WebElement cwaGoogleSignInButton;

	@FindBy(xpath = "//div[@class='lCoei YZVTmd SmR8']//div[@class='d2laFc']")
	private WebElement existingUserSignInButton;

	@FindBy(xpath = "//div[@class='fetch_ac_hold']//input")
	private WebElement findAccountInCwaTextField;

	@FindBy(xpath = "//a[@class='fetch_ac_btn search']")
	private WebElement findAccountInCwaButton;

	@FindBy(xpath = "//ul[@id='search_Result']//li")
	private WebElement searchResultInCwa;

	@FindBy(xpath = "//div[@class='loader_icon']")
	private WebElement accountLoaderInCwa;

	@FindBys(@FindBy(xpath = "//div[@class='loader_icon']"))
	private List<WebElement> accountLoadedInCwa;

	@FindBy(xpath = "//nav[@id='top_navigator']//ul//li[@id='manage']")
	private WebElement manangeTab;

	@FindBys(@FindBy(xpath = "//div[@class='accounts-list']//ul//li//h6"))
	private List<WebElement> listOfAccountNumbers;

	@FindBy(xpath = "//div[@class='subrightsection callflow-section']//div[@class='square-loader']")
	private WebElement scriptloaderInCwa;

	@FindBys(@FindBy(xpath = "//div[@class='subrightsection callflow-section']//div[@class='square-loader']"))
	private List<WebElement> scriptLoadedInCwa;

	@FindBy(xpath = "//i[@class='greetings-dropdown-icon']")
	private WebElement answerPhraseDropDownButton;

	@FindBy(xpath = "//a[@class='answerPhrase-edit']")
	private WebElement answerPhraseEditButton;

	@FindBy(xpath = "//ul[@class='greetings-dropdown' and @style='display: inline-block;']")
	private WebElement answerPhraseOptionsBlock;

	@FindBys(@FindBy(xpath = "//ul[@class='greetings-dropdown' and @style='display: inline-block;']//li"))
	private List<WebElement> answerPhraseDropDownOptions;

	@FindBy(xpath = "//div[@class='notification']")
	private WebElement savingNotification;

	@FindBys(@FindBy(xpath = "//div[@class='notification']"))
	private List<WebElement> savedNotification;

	@FindBys(@FindBy(xpath = "//div[@class='formDetails']//li//div[2][not(contains(@class,'hiddenfield'))]//i[not(@style='display : none')]"))
	private List<WebElement> listOfScriptEditButtons;

	@FindBys(@FindBy(xpath = "//div[@class='formDetails']//li//div[2][not(contains(@class,'hiddenfield'))]//input[not(contains(@type,'radio'))and not(contains(@class,'radioSelectionInput'))]"))
	private List<WebElement> scriptEditTextField;

	@FindBy(xpath = "//button[@id='saveButton' and @style='display: block;']")
	private WebElement saveScriptButton;

	@FindBy(xpath = "//div[@class='actions']//button[@class='nostyle ']")
	private WebElement closeAccountSearchButton;

	@FindBys(@FindBy(xpath = "//div[@class='subnavigation ft-left']//ul//li//a"))
	private List<WebElement> editAccountButtons;

	@FindBy(xpath = "//div[@class='field-hold timezone-content']//button")
	private WebElement timeZoneDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='dropdown-field open']//ul//li"))
	private List<WebElement> listOfTimeZones;

	@FindBy(xpath = "//div[@class='form-section bussinesshours-section']//a[@class='brand-color show-more']")
	private WebElement showMoreOrShowLessButton;

	@FindBys(@FindBy(xpath = "//div[@class='working-hours-container show']//ul//li//button[contains(@class,'checkbox-field')]"))
	private List<WebElement> listOfWeekDayCheckBoxes;

	@FindBys(@FindBy(xpath = "//div[@class='working-hours-container show']//ul//li//div"))
	private List<WebElement> listOfWeekDayDropDownButtons;

	@FindBys(@FindBy(xpath = "//div[@class='flatpickr-time']//div[@class='numInputWrapper'][1]//input"))
	private List<WebElement> listOfWeekDayHoursTextFields;

	@FindBys(@FindBy(xpath = "//div[@class='flatpickr-time']//div[@class='numInputWrapper'][2]//input"))
	private List<WebElement> listOfWeekDayMinsTextFields;

	@FindBys(@FindBy(xpath = "//div[@class='flatpickr-time']//span[@class='flatpickr-am-pm']"))
	private List<WebElement> listOfWeekDayAmOrPmButtons;

	@FindBy(xpath = "//div[@class='working-hours-container show']//button[@class='button-default fill']")
	private WebElement saveBusinessHoursButton;

	@FindBys(@FindBy(xpath = "//div[@class='working-hours-container show']//ul//li//button[@class='checkbox-field']"))
	private List<WebElement> listOfWeekDayUnSelectedCheckBoxes;

	@FindBy(xpath = "//div[@class='field-hold closures-content']//a[@class='brand-color']")
	private WebElement addClosuresOrHiClosuresButton;

	@FindBys(@FindBy(xpath = "//div[@class='closures-list show']//button[contains(@class,'checkbox-field ')]"))
	private List<WebElement> listOfClosuresCheckBoxes;

	@FindBys(@FindBy(xpath = "//div[@class='closures-list show']//button[@class='checkbox-field checked']"))
	private List<WebElement> listOfSelectedClosuresCheckBoxes;

	@FindBys(@FindBy(xpath = "//div[@class='closures-list show']//button[@class='checkbox-field ']"))
	private List<WebElement> listOfUnselectedClosuresCheckBoxes;

	@FindBy(xpath = "//div[@class='closures-list show']//button[@class='button-default fill  ']")
	private WebElement saveClosuresButton;

	@FindBy(xpath = "//div[@class='special-notes-content']//textarea")
	private WebElement customerStatusTextField;

	@FindBy(xpath = "//div[@class='special-notes-content']//div[@class='dropdown-button'][1]")
	private WebElement customerStatusCalendarButton;

	@FindBy(xpath = "//div[@class='special-notes-content']//div[@class='dropdown-button'][2]")
	private WebElement customerStatusTimeButton;

	@FindBy(xpath = "//div[@class='dayContainer']//span[contains(@class,'flatpickr-day today')]")
	private WebElement customerStatusPresentDayButton;

	@FindBy(xpath = "//span[contains(@class,'today')]//following-sibling::span[1]")
	private WebElement customerStatusTomorrowButton;

	@FindBy(xpath = "//div[@class='button-holder']//button[@class='button-default fill ']")
	private WebElement customerStatusSaveButton;

	@FindBy(xpath = "//div[@class='global-notification-cont']//p")
	private WebElement detailsSaveNotification;

	@FindBys(@FindBy(xpath = "//div[@class='global-notification-cont']//p"))
	private List<WebElement> detailsSavedNotification;

	@FindBy(xpath = "//span[contains(@class,'flatpickr-next-month')]")
	private WebElement calendarNextMonthButton;

	@FindBys(@FindBy(xpath = "//div[@class='button-hold']//button[1]//span"))
	private List<WebElement> closuresSavedButton;

	@FindBy(xpath = "//div[@class='button-hold']//button[1]//span")
	private WebElement closuresSavingButton;

	@FindBy(xpath = "//div[@class='dropdown-field open']//div[@class='dropdownmenu bottom']")
	private WebElement timeZoneDropDownBlock;

	public CwaPage waitForCwaLoad() {
		handle.parentToChildWindowControl(driver, 1);
		wait.waitForWebPageLoad(driver, 180);
		return PageFactory.initElements(getDriver(), CwaPage.class);

	}

	public CwaPage clickOnGoogleSignInInCwa() {
		wait.waitForElementToBeVisible(driver, cwaGoogleSignInButton, 90);
		wait.waitForElementToBeClickable(driver, cwaGoogleSignInButton, 30);
		cwaGoogleSignInButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnExistingEmailId() {
		wait.waitForWebPageLoad(driver, 30);
		wait.waitForElementToBeVisible(driver, existingUserSignInButton, 90);
		wait.waitForElementToBeClickable(driver, existingUserSignInButton, 30);
		existingUserSignInButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage enterAccountNumberInCwa(String accountNumber) {
		wait.waitForElementToBeVisible(driver, findAccountInCwaTextField, 90);
		wait.waitForElementToBeClickable(driver, findAccountInCwaTextField, 30);
		findAccountInCwaTextField.sendKeys(accountNumber);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnFindAccount() {
		wait.waitForElementToBeClickable(driver, findAccountInCwaButton, 30);
		findAccountInCwaButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnSearchResult() {
		wait.waitForElementToBeClickable(driver, searchResultInCwa, 30);
		searchResultInCwa.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage waitForAccountLoadInCwa() {
		wait.waitForElementToBeVisible(driver, accountLoaderInCwa, 30);
		wait.waitForElementToBeInvisible(driver, accountLoadedInCwa, 180);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnManageTab() {
		wait.waitForElementToBeClickable(driver, manangeTab, 30);
		manangeTab.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAccountNumber(String accountNumber) {
		wait.waitForAllElementsToBeVisible(driver, listOfAccountNumbers, 30);
		int size = listOfAccountNumbers.size();
		for (int i = 0; i < size; i++) {
			if (listOfAccountNumbers.get(i).getText().equals(accountNumber)) {
				listOfAccountNumbers.get(i).click();
				break;
			}
		}
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage waitForScriptLoad() {
		wait.waitForElementToBeVisible(driver, scriptloaderInCwa, 30);
		wait.waitForElementToBeInvisible(driver, scriptLoadedInCwa, 180);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAnswerPhraseDropDown() {
		switchIntoIframe();
		try {
			wait.waitForElementToBeClickable(driver, answerPhraseDropDownButton, 2);
			answerPhraseDropDownButton.click();
		} catch (Exception answerPhrase) {
			wait.waitForElementToBeClickable(driver, answerPhraseEditButton, 30);
			answerPhraseEditButton.click();
		}
		switchIntoDefualtContent();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAnswerPhraseOption() {
		switchIntoIframe();
		wait.waitForAllElementsToBeVisible(driver, answerPhraseDropDownOptions, 30);
		int optionNumber = cwaPageData.randomIntegerData(0, 1);
		selectedAnswerPhrase = answerPhraseDropDownOptions.get(optionNumber).getAttribute("data");
		answerPhraseDropDownOptions.get(optionNumber).click();
		switchIntoDefualtContent();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage waitForScriptSave() {
		switchIntoIframe();
		wait.waitForElementToBeVisible(driver, savingNotification, 30);
		wait.waitForElementToBeInvisible(driver, savedNotification, 30);
		switchIntoDefualtContent();
		return PageFactory.initElements(getDriver(), CwaPage.class);

	}

	public String getUpdatedAnswerPhraseTextInCwa() {
		int index = selectedAnswerPhrase.indexOf('g');
		String updatedAnswerPhrase = selectedAnswerPhrase.substring(index + 2);
		index = updatedAnswerPhrase.indexOf('.');
		updatedAnswerPhrase = updatedAnswerPhrase.substring(0, index - 1);
		updatedAnswerPhrase = "TYFC " + updatedAnswerPhrase;
		return updatedAnswerPhrase;
	}

	public CwaPage clickOnEditScriptFieldButton(int fieldNumber) {
		switchIntoIframe();
		wait.waitForAllElementsToBeVisible(driver, listOfScriptEditButtons, 30);
		scroll.scrollUpToWebElement(driver, listOfScriptEditButtons.get(fieldNumber - 1));
		listOfScriptEditButtons.get(fieldNumber - 1).click();
		switchIntoDefualtContent();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage updateFieldScriptInCwa(int fieldNumber) {
		switchIntoIframe();
		wait.waitForAllElementsToBeVisible(driver, scriptEditTextField, 30);
		String fieldScript = cwaPageData.randomData("Updated In Cwa");
		updatedFieldScriptInCwa.add(fieldScript);
		scriptEditTextField.get(fieldNumber - 1).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		scriptEditTextField.get(fieldNumber - 1).sendKeys(fieldScript);
		scriptEditTextField.get(fieldNumber - 1).sendKeys(Keys.ENTER);
		switchIntoDefualtContent();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnSaveScriptButton() {
		switchIntoIframe();
		wait.waitForElementToBeVisible(driver, saveScriptButton, 30);
		wait.waitForElementToBeClickable(driver, saveScriptButton, 30);
		saveScriptButton.click();
		switchIntoDefualtContent();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public String getUpdatedFieldScripts(int fieldNumber) {
		return updatedFieldScriptInCwa.get(fieldNumber - 1);
	}

	public int getListofScriptingFields() {
		switchIntoIframe();
		int size = listOfScriptEditButtons.size();
		switchIntoDefualtContent();
		return size;
	}

	public HomePage cwaPageClose() {
		handle.childWindowClose(driver, 1);
		handle.childToParentWindowControl(driver);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public CwaPage clickOncloseAccountSearchButton() {
		wait.waitForElementToBeClickable(driver, closeAccountSearchButton, 30);
		closeAccountSearchButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnBusinessDetails() {
		wait.waitForAllElementsToBeVisible(driver, editAccountButtons, 30);
		editAccountButtons.get(1).click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnNoteToAgentButton() {
		wait.waitForAllElementsToBeVisible(driver, editAccountButtons, 30);
		editAccountButtons.get(2).click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnTimeZoneDropDown() {
		wait.waitForElementToBeVisible(driver, timeZoneDropDownButton, 30);
		timeZoneDropDownButton.click();
		wait.waitForElementToBeVisible(driver, timeZoneDropDownBlock, 30);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAnyTimeZone() {
		int attempts = 0;
		while (attempts < 5)
			try {
				wait.waitForAllElementsToBeVisible(driver, listOfTimeZones, 30);
				wait.waitForElementToBeClickable(driver, listOfTimeZones.get(0), 30);
				int size = listOfTimeZones.size();
				int timezoneNumber = cwaPageData.randomIntegerData(0, size - 3);
				wait.waitForElementToBeClickable(driver, listOfTimeZones.get(timezoneNumber), 30);
				listOfTimeZones.get(timezoneNumber).click();
				break;
			} catch (Exception timeZoneOptions) {
				attempts++;
			}
		wait.waitForElementToBeVisible(driver, detailsSaveNotification, 30);
		wait.waitForElementToBeInvisible(driver, detailsSavedNotification, 30);
		timeZone = timeZoneDropDownButton.getText();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnShowMoreOrShowLessButton() {
		wait.waitForElementToBeVisible(driver, showMoreOrShowLessButton, 30);
		showMoreOrShowLessButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAnyBusinessDayCheckBox() {
		wait.waitForAllElementsToBeVisible(driver, listOfWeekDayCheckBoxes, 30);
		int weekDay = cwaPageData.randomIntegerData(0, 5);
		scroll.scrollUpToWebElement(driver, listOfWeekDayCheckBoxes.get(weekDay));
		listOfWeekDayCheckBoxes.get(weekDay).click();
		businessDays = listOfWeekDayUnSelectedCheckBoxes.size();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnSaveBusinessDay() {
		wait.waitForElementToBeVisible(driver, saveBusinessHoursButton, 30);
		scroll.scrollUpToWebElement(driver, saveBusinessHoursButton);
		saveBusinessHoursButton.click();
		wait.waitForElementToBeVisible(driver, detailsSaveNotification, 30);
		wait.waitForElementToBeInvisible(driver, detailsSavedNotification, 30);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAddClosures() {
		wait.waitForElementToBeVisible(driver, addClosuresOrHiClosuresButton, 30);
		scroll.scrollUpToWebElement(driver, addClosuresOrHiClosuresButton);
		addClosuresOrHiClosuresButton.click();
		try {
			Thread.sleep(3000);
		} catch (Exception waiting) {

		}
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnAnyClosuresCheckBox() {
		wait.waitForAllElementsToBeVisible(driver, listOfClosuresCheckBoxes, 30);
		int size = listOfClosuresCheckBoxes.size();
		int weekDay = cwaPageData.randomIntegerData(0, size - 3);
		scroll.scrollUpToWebElement(driver, listOfClosuresCheckBoxes.get(weekDay));
		listOfClosuresCheckBoxes.get(weekDay).click();
		closures = listOfSelectedClosuresCheckBoxes.size();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnSaveClosures() {
		wait.waitForElementToBeVisible(driver, saveClosuresButton, 30);
		scroll.scrollUpToWebElement(driver, saveClosuresButton);
		saveClosuresButton.click();
		wait.waitForElementToBeVisible(driver, closuresSavingButton, 30);
		wait.waitForElementToBeInvisible(driver, closuresSavedButton, 30);
		wait.waitForElementToBeVisible(driver, detailsSaveNotification, 30);
		wait.waitForElementToBeInvisible(driver, detailsSavedNotification, 30);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage enterCustomerStatus() {
		wait.waitForElementToBeVisible(driver, customerStatusTextField, 30);
		scroll.scrollUpToWebElement(driver, customerStatusTextField);
		customerStatus = cwaPageData.randomData("CustomerStatus");
		customerStatusTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		customerStatusTextField.sendKeys(customerStatus);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnCustomerStatusExpireDateSelectionButton() {
		wait.waitForElementToBeVisible(driver, customerStatusCalendarButton, 30);
		scroll.scrollUpToWebElement(driver, customerStatusCalendarButton);
		customerStatusCalendarButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage selectCustomerStatusExpireDate() {
		try {
			wait.waitForElementToBeVisible(driver, customerStatusTomorrowButton, 2);
		} catch (Exception customDate) {
			calendarNextMonthButton.click();
		}
		scroll.scrollUpToWebElement(driver, customerStatusTomorrowButton);
		wait.waitForElementToBeClickable(driver, customerStatusTomorrowButton, 30);
		customerStatusTomorrowButton.click();
		customerStatusTimeButton.click();
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public CwaPage clickOnSaveNoteForAgent() {
		wait.waitForElementToBeVisible(driver, customerStatusSaveButton, 30);
		scroll.scrollUpToWebElement(driver, customerStatusSaveButton);
		customerStatusSaveButton.click();
		wait.waitForElementToBeVisible(driver, detailsSaveNotification, 30);
		wait.waitForElementToBeInvisible(driver, detailsSavedNotification, 30);
		return PageFactory.initElements(getDriver(), CwaPage.class);
	}

	public void switchIntoIframe() {
		driver.switchTo().frame(0);
	}

	public void switchIntoDefualtContent() {
		driver.switchTo().defaultContent();
	}

	public String getTimeZone() {
		StringBuilder sb = new StringBuilder();
		sb.append(timeZone);
		int index = timeZone.indexOf('-');
		char zone = timeZone.charAt(index + 1);
		String value = zone + "";
		if (value.equals("0"))
			sb.deleteCharAt(index + 1);
		timeZone = sb.toString();
		return timeZone;
	}

	public int getBusinessHours() {
		return businessDays;
	}

	public int getClosures() {
		return closures;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

}
