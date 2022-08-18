package io.pagelibrary.fc2.o;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;

public class BusinessHoursAndClosuresPage extends HomePage {
	Wait wait = new Wait();
	Scroll scroll = new Scroll();
	RandomData businessHoursAndClosuresData = new RandomData();
	WebDriver driver;
	private static String customHolidayTitle;
	private static String customHolidayNotes;
	private static String businessHoursNotes;
	private static List<String> businessHoursFromAndToHoursTimings = new ArrayList<>();
	private static List<String> businessHoursFromAndToMinsTimings = new ArrayList<>();
	private static List<String> businessHoursFromAndToAmAndPmTimings = new ArrayList<>();
	private static String selectedTimeZone;
	private static List<String> businessHoursValues = new ArrayList<>();
	private static int businessDaySelect;

	public BusinessHoursAndClosuresPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='full--h overflow-a ']//div[contains(@class,'g-dropdown-wrap has-search')]//button")
	private WebElement timeZoneDropDownButton;

	@FindBy(xpath = "//div[@class='full--h overflow-a ']//div[contains(@class,'g-dropdown-wrap has-search')]//button//span")
	private WebElement timeZoneText;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap has-search open']")
	private WebElement timeZonesBlock;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'g-dropdown-wrap has-search')]//div[@class='g-dropmenu']//ul//li"))
	private List<WebElement> timeZoneOptions;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'g-dropdown-wrap has-search')]//div[@class='g-dropmenu']//ul//li//span"))
	private List<WebElement> timeZoneOptionsText;

	@FindBy(xpath = "//div[@class='g-searchbox ignore-click']//input")
	private WebElement timeZoneSearchField;

	@FindBy(xpath = "//div[@class='full--h overflow-a ']//div[contains(@class,'g-business-hrs')]//button[@class='g-drop-btn']")
	private WebElement businessHoursDropDownButton;

	@FindBy(xpath = "//a[@class='g-text-primary']")
	private WebElement businessHoursShowButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap open']")
	private WebElement businessHoursOptionsBlock;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'g-business-hrs')]//div//ul//li"))
	private List<WebElement> businessHoursOptions;

	@FindBys(@FindBy(xpath = "//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul//li//button[contains(@class, 'g-checkbox')]"))
	private List<WebElement> businessDaysCheckBoxes;

	@FindBys(@FindBy(xpath = "//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul//li//div[contains(@class,'g-drop-btn ')]"))
	private List<WebElement> businessHoursFromTimeAndToTimeButtons;

	@FindBys(@FindBy(xpath = "//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul//li//div[@class='g-timerange enabled']//div[contains(@class,'g-drop-btn ')]"))
	private List<WebElement> selectedDayBusinessHoursFromTimeAndToTimeButtons;

	@FindBys(@FindBy(xpath = "//div[@class='g-timerange enabled']//div[@class='flatpickr-wrapper']//input[@class='flatpickr-input']"))
	private List<WebElement> selectedDayBusinessHoursFromTimeAndToTimeTextFields;

	@FindBy(xpath = "//button[@class='g-checkbox g-checkbox-checked']//following-sibling::label")
	private WebElement selectedDayText;

	@FindBys(@FindBy(xpath = "//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul//li//div[contains(@class,'g-drop-btn ')]//div[@class='flatpickr-wrapper']//input[contains(@class,'flatpickr-input')]"))
	private List<WebElement> businessHoursFromTimeAndToTime;

	@FindBy(xpath = "//input[@class='flatpickr-input active']")
	private WebElement businessHoursSelectTimeBlock;

	@FindBys(@FindBy(xpath = "//div[@class='flatpickr-time']//input[@class='numInput flatpickr-hour']"))
	private List<WebElement> businessHoursFromTimeAndToTimeHoursTextFields;

	@FindBys(@FindBy(xpath = "//div[@class='g-timerange enabled']//div[@class='flatpickr-time']//input[@class='numInput flatpickr-hour']"))
	private List<WebElement> selectedDayBusinessHoursFromTimeAndToTimeHoursTextFields;

	@FindBys(@FindBy(xpath = "//div[@class='numInputWrapper']//input[@class='numInput flatpickr-minute']"))
	private List<WebElement> businessHoursFromTimeAndToTimeMinsTextFields;

	@FindBys(@FindBy(xpath = "//div[@class='g-timerange enabled']//div[@class='numInputWrapper']//input[@class='numInput flatpickr-minute']"))
	private List<WebElement> selectedDayBusinessHoursFromTimeAndToTimeMinsTextFields;

	@FindBys(@FindBy(xpath = "//span[@class='flatpickr-am-pm']"))
	private List<WebElement> businessHoursFromSelectAmAndPmButtons;

	@FindBys(@FindBy(xpath = "//div[@class='g-timerange enabled']//span[@class='flatpickr-am-pm']"))
	private List<WebElement> selectedDayBusinessHoursFromSelectAmAndPmButtons;

	@FindBys(@FindBy(xpath = "//i[@class='icon-appointment ']"))
	private List<WebElement> businessHoursAppointmentButtons;

	@FindBys(@FindBy(xpath = "//i[@class='icon-add']"))
	private List<WebElement> businessHoursAddButtons;

	@FindBys(@FindBy(xpath = "//i[@class='icon-delete']"))
	private List<WebElement> businessHoursDeleteButtons;

	@FindBys(@FindBy(xpath = "//button[@class='nostyle g-text-primary g-apply-all']"))
	private List<WebElement> businessHoursApplyAllButtons;

	@FindBy(xpath = "//div[@class='add-notes']//following::div[contains(@class,'textarea-wrapper')]//textarea")
	private WebElement businessHoursNotesTextField;

	@FindBy(xpath = "//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ancestor::div[@style='display: block;']")
	private WebElement businessHoursTimingsBlock;

	@FindBy(xpath = "//a[@class='tw-m text--sm']")
	private WebElement addNotesButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-business-hrs']//ul[@class='g-custom-hrs ']//li//button[@class='g-checkbox']"))
	private List<WebElement> listOfUnselectedDays;

	// Closures And Holidays

	@FindBy(xpath = "//div[@class='g-fields-hold']//span[@class='mr-5']//button")
	private WebElement openOnAllHolidaysRadioButton;

	@FindBy(xpath = "//div[@class='g-fields-hold']//span[text()='Configure holidays']//button")
	private WebElement configureHolidaysRadioButton;

	@FindBy(xpath = "//div[contains(@class,'config-holidays')and @style ='display: block;']")
	private WebElement configureHolidayBlock;

	@FindBy(xpath = "//div[contains(@class,'g-togg-btn mr-4')]")
	private WebElement observedHolidayToggleButton;

	@FindBy(xpath = "//a[@class='tc-blue-800']")
	private WebElement addHolidayButton;

	@FindBy(xpath = "//a[@class='deleted-Holidays']")
	private WebElement viewExpriedAndDeletedHolidaysButton;

	@FindBy(xpath = "//div[@class='searchbox ps-r mb-6']//input")
	private WebElement searchHolidaysTextField;

	@FindBy(xpath = "//div[@class='select-all fx fx-a-c mb-5']//button")
	private WebElement selectAllCheckBox;

	@FindBy(xpath = "//div[@class='select-all fx fx-a-c mb-5']//a[@class='ml-a tc-blue-800 text--xs tw-sb']")
	private WebElement addCustomHolidayButton;

	@FindBys(@FindBy(xpath = "//ul[@class='holidays-list text--xs']//li//button"))
	private List<WebElement> selectHolidayCheckBoxes;

	@FindBy(xpath = "//div[@class='modal-window add-holidays-modal' and @style = 'display: block;']")
	private WebElement customHolidayBlock;

	@FindBy(xpath = "//div[@class='modal-window add-holidays-modal']//div[@class='g-input-wrapper']//input")
	private WebElement customHolidayTitleTextField;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap date-time-picker open']")
	private WebElement customHolidayFromDateBlock;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap date-time-picker ml-7 open']")
	private WebElement customHolidayToDateBlock;

	@FindBys(@FindBy(xpath = "//span[contains(@class,'flatpickr-day today')]"))
	private List<WebElement> customHolidayCalendarToday;

	@FindBys(@FindBy(xpath = "//span[contains(@class,'today')]//following-sibling::span[1]"))
	private List<WebElement> customHolidayTomorrow;

	@FindBys(@FindBy(xpath = "//span[contains(@class,'flatpickr-next-month')]"))
	private List<WebElement> customHolidayNextMonth;

	@FindBy(xpath = "//div[@class='textarea-wrapper full--w']//textarea")
	private WebElement customHolidayNotesTextField;

	@FindBy(xpath = "//footer[@class='g-modal-foot']//button[contains(@class,'g-btn-primary ')]//span[text()='Add']")
	private WebElement addButtonForCustomHoliday;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//span//button[@class='g-checkbox mr-2']")
	private WebElement repeatEveryYearCheckBox;

	@FindBy(xpath = "//header[@class='g-modal-head']//h4[text()='Add Custom Holiday']//following-sibling::i")
	private WebElement closeCustomHolidayBlockButton;

	@FindBy(xpath = "//div[@class='footer-overlay ps-f ps-btm ps-right main-view-width ']//button[@class='g-btn-negative']")
	private WebElement cancelBusinesshoursAndClosuresButton;

	@FindBy(xpath = "//div[@class='footer-overlay ps-f ps-btm ps-right main-view-width ']//button[@class='g-btn-primary']")
	private WebElement saveBusinesshoursAndClosuresButton;

	@FindBys(@FindBy(xpath = "//i[@class='icon ic-delete mr-3']"))
	private List<WebElement> deleteHolidaysbuttons;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap date-time-picker']//button")
	private WebElement customHolidayFromDateButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap date-time-picker ml-7 ']//button")
	private WebElement customHolidayToDateButton;

	@FindBy(xpath = "//ul[@class='holidays-list text--xs']//li//span[contains(text(),'-')]//ancestor::li")
	private WebElement customHoliday;

	@FindBy(xpath = "//button[@class= 'g-checkbox mr-2 g-checkbox-checked']//following-sibling::span[contains(text(),'-')]//ancestor::li//button")
	private WebElement customHolidayCheckBox;

	@FindBys(@FindBy(xpath = "//button[@class= 'g-checkbox mr-2 g-checkbox-checked']//following-sibling::span[contains(text(),'-')]//ancestor::li//button"))
	private List<WebElement> customHolidayCheckBoxes;

	@FindBy(xpath = "//div[contains(@class,'flatpickr-calendar hasTime animate open arrowTop')]")
	private WebElement calendarBlock;

	@FindBys(@FindBy(xpath = "//div[@class='dayContainer']//span[@class='flatpickr-day today']"))
	private List<WebElement> presentDay;

	@FindBys(@FindBy(xpath = "//i[@class='icon-appointment enabled']"))
	private List<WebElement> selectedAppointment;

	@FindBy(xpath = "//h4[text()='Deleted/Expired Holidays']//ancestor::header//ancestor::div[@class='g-modal-wrapper alternate']")
	private WebElement deleteOrExpriedHolidaysBlock;

	@FindBys(@FindBy(xpath = "//h4[text()='Deleted/Expired Holidays']//ancestor::header//ancestor::div[@class='g-modal-wrapper alternate']//td[contains(text(),'Custom Holiday')]"))
	private List<WebElement> listOfDeletedCustomHolidays;

	@FindBy(xpath = "//h4[text()='Deleted/Expired Holidays']//ancestor::header//i")
	private WebElement closeDeleteOrExpriedHolidaysBlock;

	@FindBys(@FindBy(xpath = "//ul[@class='holidays-list text--xs']/li"))
	private List<WebElement> listOfHolidays;

	@FindBys(@FindBy(xpath = "//ul[@class='holidays-list text--xs']//li//button[@class='g-checkbox mr-2 g-checkbox-checked']"))
	private List<WebElement> listOfExistingHolidayCheckBoxes;

	@FindBy(xpath = "//button[@class='text--sm tc-blue-700']")
	private WebElement useBusinessHoursButton;

	public BusinessHoursAndClosuresPage clickOnTimeZoneDropDown() {
		wait.waitForElementToBeVisible(driver, timeZoneDropDownButton, 30);
		timeZoneDropDownButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectTimezone() {
		wait.waitForElementToBeVisible(driver, timeZonesBlock, 30);
		int index = businessHoursAndClosuresData.randomIntegerData(0, 174);
		scroll.scrollUpToWebElement(driver, timeZoneOptions.get(index));
		selectedTimeZone = timeZoneOptionsText.get(index).getText();
		timeZoneOptions.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage searchTimeZone(String timeZone) {
		wait.waitForElementToBeVisible(driver, timeZonesBlock, 30);
		timeZoneSearchField.sendKeys(timeZone);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnBusinessHoursDropDown() {
		wait.waitForElementToBeVisible(driver, businessHoursDropDownButton, 30);
		businessHoursDropDownButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectBusinessHoursType(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursOptionsBlock, 30);
		businessHoursOptions.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnShowOrHideBusinessHoursButton() {
		wait.waitForElementToBeVisible(driver, businessHoursShowButton, 30);
		businessHoursShowButton.click();
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectOrDeselectBusinessDays() {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		businessDaySelect = businessHoursAndClosuresData.randomIntegerData(0, 5);
		scroll.scrollUpToWebElement(driver, businessDaysCheckBoxes.get(businessDaySelect));
		businessDaysCheckBoxes.get(businessDaySelect).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnBusinessDayFromTimingOrToTiming(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		scroll.scrollUpToWebElement(driver, businessHoursFromTimeAndToTimeButtons.get(index));
		businessHoursFromTimeAndToTimeButtons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnSelectedBusinessDayFromTimingOrToTiming(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		scroll.scrollUpToWebElement(driver, selectedDayBusinessHoursFromTimeAndToTimeButtons.get(index));
		selectedDayBusinessHoursFromTimeAndToTimeButtons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterBusinessDayFromHourOrToHourTiming(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		scroll.scrollUpToWebElement(driver, businessHoursFromTimeAndToTimeHoursTextFields.get(index));
		businessHoursFromTimeAndToTimeHoursTextFields.get(index).clear();
		String hoursTime = businessHoursAndClosuresData.randomStringData(1, 11);
		businessHoursFromAndToHoursTimings.add(hoursTime);
		businessHoursFromTimeAndToTimeHoursTextFields.get(index).sendKeys(hoursTime);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterSelectedBusinessDayFromHourOrToHourTiming(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		scroll.scrollUpToWebElement(driver, selectedDayBusinessHoursFromTimeAndToTimeHoursTextFields.get(index));
		selectedDayBusinessHoursFromTimeAndToTimeHoursTextFields.get(index).clear();
		String hoursTime = businessHoursAndClosuresData.randomStringData(1, 11);
		businessHoursFromAndToHoursTimings.add(hoursTime);
		selectedDayBusinessHoursFromTimeAndToTimeHoursTextFields.get(index).sendKeys(hoursTime);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterBusinessDayFromMinsOrToMinsTiming(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		scroll.scrollUpToWebElement(driver, businessHoursFromTimeAndToTimeMinsTextFields.get(index));
		businessHoursFromTimeAndToTimeMinsTextFields.get(index).click();
		businessHoursFromTimeAndToTimeMinsTextFields.get(index).clear();
		String minsTime = businessHoursAndClosuresData.randomStringData(0, 58);
		businessHoursFromAndToMinsTimings.add(minsTime);
		businessHoursFromTimeAndToTimeMinsTextFields.get(index).sendKeys(minsTime);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterSelectedBusinessDayFromMinsOrToMinsTiming(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		scroll.scrollUpToWebElement(driver, selectedDayBusinessHoursFromTimeAndToTimeMinsTextFields.get(index));
		selectedDayBusinessHoursFromTimeAndToTimeMinsTextFields.get(index).click();
		selectedDayBusinessHoursFromTimeAndToTimeMinsTextFields.get(index).clear();
		String minsTime = businessHoursAndClosuresData.randomStringData(0, 58);
		businessHoursFromAndToMinsTimings.add(minsTime);
		selectedDayBusinessHoursFromTimeAndToTimeMinsTextFields.get(index).sendKeys(minsTime);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectBusinessDayAmOrPm(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		scroll.scrollUpToWebElement(driver, businessHoursFromSelectAmAndPmButtons.get(index));
		businessHoursFromSelectAmAndPmButtons.get(index).click();
		businessHoursFromAndToAmAndPmTimings.add(businessHoursFromSelectAmAndPmButtons.get(index).getText());
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectEnabledBusinessDayAmOrPm(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		scroll.scrollUpToWebElement(driver, selectedDayBusinessHoursFromTimeAndToTimeButtons.get(1));
		businessHoursFromAndToAmAndPmTimings.add(selectedDayBusinessHoursFromSelectAmAndPmButtons.get(index).getText());
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectAppointmentForBusinessDay(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		businessHoursAppointmentButtons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectAppointmentForSelectedBusinessDay() {
		wait.waitForElementToBeVisible(driver, businessHoursSelectTimeBlock, 30);
		businessHoursAppointmentButtons.get(businessDaySelect).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage addTimingsForBusinessDay(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(businessHoursAddButtons.get(index)).perform();
		businessHoursAddButtons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage deleteTimingsForBusinessDay(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(businessHoursDeleteButtons.get(index)).perform();
		businessHoursDeleteButtons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage applyAllTimingsForBusinessDay(int index) {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(businessHoursApplyAllButtons.get(index)).perform();
		businessHoursApplyAllButtons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnAddBusinesHoursNotes() {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		scroll.scrollUpToWebElement(driver, addNotesButton);
		addNotesButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterBusinessHoursNotes() {
		wait.waitForElementToBeVisible(driver, businessHoursTimingsBlock, 30);
		scroll.scrollUpToWebElement(driver, businessHoursNotesTextField);
		businessHoursNotes = businessHoursAndClosuresData.randomData("Business Hours Notes");
		businessHoursNotesTextField.clear();
		businessHoursNotesTextField.sendKeys(businessHoursNotes);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public String businessHoursNotesBeforeSaving() {

		return businessHoursNotes;
	}

	public String businessHoursNotesAfterSaving() {
		return businessHoursNotesTextField.getText();
	}

	public BusinessHoursAndClosuresPage selectOpenOnAllHolidays() {
		wait.waitForElementToBeVisible(driver, openOnAllHolidaysRadioButton, 30);
		openOnAllHolidaysRadioButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectConfigureHolidays() {
		wait.waitForElementToBeVisible(driver, configureHolidaysRadioButton, 30);
		configureHolidaysRadioButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enableOrDisableObservedHoliday() {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, observedHolidayToggleButton);
		observedHolidayToggleButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage deleteExistingHolidays() {
		wait.waitForElementToBeVisible(driver, addCustomHolidayButton, 30);
		int size = listOfExistingHolidayCheckBoxes.size();
		for (int i = 0; i < size; i++) {
			scroll.scrollUpToWebElement(driver, listOfExistingHolidayCheckBoxes.get(0));
			listOfExistingHolidayCheckBoxes.get(0).click();
		}

		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnAddHolidays() {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, addHolidayButton);
		addHolidayButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnViewDeletedOrExpiredHolidays() {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, viewExpriedAndDeletedHolidaysButton);
		viewExpriedAndDeletedHolidaysButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectAllOrDeselectAllHolidays() {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, selectAllCheckBox);
		selectAllCheckBox.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage deleteHolidays(int index) {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, deleteHolidaysbuttons.get(index));
		deleteHolidaysbuttons.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage SelectAllOrDeselectHolidays(int index) {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, selectHolidayCheckBoxes.get(index));
		selectHolidayCheckBoxes.get(index).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnAddCustomDay() {
		wait.waitForElementToBeVisible(driver, configureHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, addCustomHolidayButton);

		if (customHolidayCheckBoxes.size() != 0) {
			scroll.scrollUpToWebElement(driver, customHolidayCheckBox);
			customHolidayCheckBox.click();
		}

		addCustomHolidayButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterCustomHolidayTitle() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		customHolidayTitle = businessHoursAndClosuresData.randomData("Custom Holiday");
		customHolidayTitleTextField.sendKeys(customHolidayTitle);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public String CustomHolidayTitleBeforeSaving() {

		return customHolidayTitle;
	}

	public BusinessHoursAndClosuresPage clickOnCustomHolidayFormDate() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		customHolidayFromDateButton.click();
		wait.waitForElementToBeVisible(driver, calendarBlock, 30);
		presentDay.get(0).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnCustomHolidayToDate() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		customHolidayToDateButton.click();
		wait.waitForElementToBeVisible(driver, calendarBlock, 30);
		presentDay.get(0).click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectCustomHolidayFromDate() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		customHolidayFromDateButton.click();
		wait.waitForElementToBeVisible(driver, calendarBlock, 30);
		try {
			customHolidayTomorrow.get(0).click();
		} catch (Exception customDate) {
			customHolidayNextMonth.get(0).click();
			customHolidayTomorrow.get(0).click();
		}

		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage selectCustomHolidayToDate() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		customHolidayToDateButton.click();
		wait.waitForElementToBeVisible(driver, calendarBlock, 30);
		try {
			customHolidayTomorrow.get(1).click();
		} catch (Exception customDate) {
			customHolidayNextMonth.get(1).click();
			customHolidayTomorrow.get(1).click();
		}
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage enterCustomHolidayNotes() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		customHolidayNotes = businessHoursAndClosuresData.randomData("Custom Holiday Notes");
		customHolidayNotesTextField.clear();
		customHolidayNotesTextField.sendKeys(customHolidayNotes);
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public String CustomHolidayNotesBeforeSaving() {

		return customHolidayNotes;
	}

	public BusinessHoursAndClosuresPage selectOrDeselectRepeatEveryYear() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		repeatEveryYearCheckBox.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public BusinessHoursAndClosuresPage clickOnAddCustomHoliday() {
		wait.waitForElementToBeVisible(driver, customHolidayBlock, 30);
		scroll.scrollUpToWebElement(driver, addButtonForCustomHoliday);
		addButtonForCustomHoliday.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public HomePage clickOnSaveBusinesshoursAndClosuresButton() {
		wait.waitForElementToBeVisible(driver, saveBusinesshoursAndClosuresButton, 30);
		saveBusinesshoursAndClosuresButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public String businessHoursTimingsBeforeSaving(int index) {
		String time = businessHoursFromAndToHoursTimings.get(index) + ":" + "00" + " "
				+ businessHoursFromAndToAmAndPmTimings.get(index);
		return time;
	}

	public String businessHoursTimingsAfterSaving(int index) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		final String java_script = "return document.querySelectorAll('.g-timerange.enabled .flatpickr-wrapper >input')["
				+ index + "].value";
		String value = (String) executor.executeScript(java_script);
		return value;
	}

	public String timeZoneBeforeSaving() {
		return selectedTimeZone;
	}

	public String timeZoneAfterSaving() {
		return timeZoneText.getText();

	}

	public String customHolidayTitleAfterSaving() {
		wait.waitForElementToBeVisible(driver, customHoliday, 30);
		int index = customHoliday.getText().indexOf("-");
		String customHolidayTitle = customHoliday.getText().substring(0, index - 4);
		return customHolidayTitle;
	}

	public BusinessHoursAndClosuresPage deleteCustomHoliday() {
		customHolidayCheckBox.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public String getBusinessHoursInFc() {
		scroll.scrollUpToWebElement(driver, selectedDayBusinessHoursFromTimeAndToTimeTextFields.get(0));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		for (int i = 0; i < 2; i++) {
			final String java_script = "return document.querySelectorAll('.g-timerange.enabled .flatpickr-wrapper >input')["
					+ i + "].value";
			String value = (String) executor.executeScript(java_script);
			businessHoursValues.add(value);
		}
		String fromTime = businessHoursValues.get(0);
		fromTime = fromTime.replace(':', '.');
		if (fromTime.length() == 7)
			fromTime = "0" + fromTime;
		String toTime = businessHoursValues.get(1);
		toTime = toTime.replace(':', '.');
		if (toTime.length() == 7)
			toTime = "0" + toTime;
		String businessHours = selectedDayText.getText().substring(0, 3) + " " + fromTime + " " + "-" + " " + toTime
				+ "   ";
		return businessHours;
	}

	public boolean isAppointmentSelectedInFc() {
		if (selectedAppointment.size() == 1)
			return true;
		else
			return false;

	}

	public BusinessHoursAndClosuresPage clickOnDeletedCustomHolidayOrExpriedHoliday() {
		wait.waitForElementToBeVisible(driver, viewExpriedAndDeletedHolidaysButton, 30);
		viewExpriedAndDeletedHolidaysButton.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public boolean isCustomHolidayDeleted() {
		wait.waitForElementToBeVisible(driver, deleteOrExpriedHolidaysBlock, 30);
		boolean isDeleted = true;
		int size = listOfDeletedCustomHolidays.size();
		for (int i = 0; i < size; i++) {
			if (listOfDeletedCustomHolidays.get(i).getText().equals(customHolidayTitle)) {
				isDeleted = true;
				break;
			}
		}
		return isDeleted;
	}

	public BusinessHoursAndClosuresPage clickOnCloseDeletedCustomHolidayOrExpriedHoliday() {
		wait.waitForElementToBeVisible(driver, closeDeleteOrExpriedHolidaysBlock, 30);
		closeDeleteOrExpriedHolidaysBlock.click();
		return PageFactory.initElements(getDriver(), BusinessHoursAndClosuresPage.class);
	}

	public int getListOfHolidays() {
		wait.waitForElementToBeVisible(driver, selectAllCheckBox, 30);
		return listOfExistingHolidayCheckBoxes.size();

	}

	public int getBusinessHours() {
		wait.waitForAllElementsToBeVisible(driver, listOfUnselectedDays, 30);
		return listOfUnselectedDays.size();
	}
}
