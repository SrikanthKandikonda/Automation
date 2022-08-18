package io.pagelibrary.fc2.o;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;
import io.utils.fc2.o.WindowHandles;

public class OnCallSchedulePage extends HomePage {

	Wait wait = new Wait();
	RandomData onCallData = new RandomData();
	Scroll scroll = new Scroll();
	WindowHandles handle = new WindowHandles();
	WebDriver driver;
	private static String staffName;
	private static String numberOfAttemps;
	private static String scheduleFromTime;
	private static String scheduleToTime;
	private static String calendarName;
	private static List<String> listOfStaffName = new ArrayList<>();

	public OnCallSchedulePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//i[@class = 'fa fa-bars']")
	private WebElement calendarListShowOrHideButton;

	@FindBys(@FindBy(xpath = "//ul[@class='team-name-list']//li"))
	private List<WebElement> calendarList;

	@FindBy(xpath = "//div[@class='footer']//a[@class='add-team']")
	private WebElement addACalendarButton;

	@FindBy(xpath = "//div[@class='team-name-edit']//input")
	private WebElement calendarNameTextField;

	@FindBy(xpath = "//div[@class='team-edit-footer']//button")
	private WebElement saveCalendarNameButton;

	@FindBys(@FindBy(xpath = "//div[@class='team-edit-footer']//button"))
	private List<WebElement> savedCalendarNameButton;

	@FindBys(@FindBy(xpath = "//ul[@class='team-name-list']//li//i[@class='fa fa-trash']"))
	private List<WebElement> deleteCalendarButton;

	@FindBys(@FindBy(xpath = "//ul[@class='team-name-list']//li//i[@class='fa fa-pencil']"))
	private List<WebElement> editCalendarButton;

	@FindBy(xpath = "//button[@class='otl-btn fa fa-angle-left']")
	private WebElement previousWeekButton;

	@FindBy(xpath = "//button[@class='otl-btn fa fa-angle-right']")
	private WebElement nextWeekButton;

	@FindBy(xpath = "//button[@id='week-selector']")
	private WebElement weekSelectorButton;

	@FindBy(xpath = "//div[@class='datepicker-days']//th[@class='prev']")
	private WebElement calendarPreviousButton;

	@FindBy(xpath = "//div[@class='datepicker-days']//th[@class='next']")
	private WebElement calendarNextButton;

	@FindBy(xpath = "//div[@class='datepicker-days']//tbody//tr//td[@class='day' or contains(@class,'new day')]")
	private List<WebElement> daysInCalendar;

	@FindBy(xpath = "//div[@class='datepicker-days']//th[@class='datepicker-switch']")
	private WebElement datePickerSwitch;

	@FindBys(@FindBy(xpath = "//div[@class='datepicker-months']//tbody//tr//td//span"))
	private List<WebElement> monthPickerSwitch;

	@FindBys(@FindBy(xpath = "//div[@class='datepicker-years']//tbody//tr//td//span"))
	private List<WebElement> yearPickerSwitch;

	@FindBys(@FindBy(xpath = "//div[@class='datepicker-decades']//tbody//tr//td//span"))
	private List<WebElement> decadePickerSwitch;

	@FindBys(@FindBy(xpath = "//div[@class='datepicker-centuries']//tbody//tr//td//span"))
	private List<WebElement> centuriesPickerSwitch;

	@FindBy(xpath = "//button[@class='otl-btn fa fa-copy']")
	private WebElement copyScheduleRange;

	@FindBy(xpath = "//button[@class='otl-btn fl today']")
	private WebElement currentWeekButton;

	@FindBy(xpath = "//div[@class='week-view-content']//ul//li")
	private WebElement weekViewContent;

	@FindBys(@FindBy(xpath = "//div[@class='week-view-content']//ul//li//button"))
	private List<WebElement> addScheduleButton;

	@FindBys(@FindBy(xpath = "//article[@id='week-viev']//ul//li/i[2]"))
	private List<WebElement> copySchedulePerDay;

	@FindBy(xpath = "//span[@class='pickDate']")
	private WebElement copyToDate;

	@FindBy(xpath = "//button[@id='copy-schedule-btn']")
	private WebElement copyScheduleToDayButton;

	@FindBys(@FindBy(xpath = "//div[@class='options-drop fa fa-ellipsis-v']"))
	private List<WebElement> scheduleSettingsButton;

	@FindBys(@FindBy(xpath = "//div[@class='options-drop fa fa-ellipsis-v']//ul//li[@class='edit-staff']"))
	private List<WebElement> editScheduleButton;

	@FindBys(@FindBy(xpath = "//div[@class='options-drop fa fa-ellipsis-v']//ul//li[@class='remove-schedule']"))
	private List<WebElement> deleteScheduleButton;

	@FindBys(@FindBy(xpath = "//div[@class='staff c1 ui-sortable-handle']//i[@class='fa fa-pencil']"))
	private List<WebElement> editStaffButton;

	@FindBys(@FindBy(xpath = "//div[@class='staff c1 ui-sortable-handle']//i[@class='fa fa-close']"))
	private List<WebElement> removeStaffButton;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'sortable-handle')]"))
	private List<WebElement> staffContentBlock;

	@FindBy(xpath = "//div[@id='from-time']/div[@class='cust-dropdown']//span//input")
	private WebElement addScheduleFromTimeField;

	@FindBy(xpath = "//div[@id='to-time']/div[@class='cust-dropdown']//span//input")
	private WebElement addScheduleToTimeField;

	@FindBy(xpath = "//input[@id='add-staff-search-user']")
	private WebElement staffSearchButton;

	@FindBys(@FindBy(xpath = "//div[@id='from-time']//button"))
	private List<WebElement> fromTimeAmOrPMButton;

	@FindBys(@FindBy(xpath = "//div[@id='to-time']//button"))
	private List<WebElement> toTimeAmOrPMButton;

	@FindBy(xpath = "//div//h4//following::input[@id='repeatStaffInputfield']")
	private WebElement numOfAttemptsField;

	@FindBys(@FindBy(xpath = "//ul[@id='staffList']//li"))
	private List<WebElement> allstaff;

	@FindBy(xpath = "//div[@class='popup-content-wrapper show fade']//span[@id='closePopup']")
	private WebElement closeAddScheduleBlock;

	@FindBy(xpath = "//ul[@id='staffList']//li[@style='display: list-item;']//i")
	private WebElement selectOrDeselectStaffCheckBox;

	@FindBy(xpath = "//ul[@id='staffList']//li//a//span[@class='addDelivery']")
	private WebElement addDeliveryMethod;

	@FindBy(xpath = "//ul[@id='staffList']//li[@style='display: list-item;']//li[@class='repeat-call-staff']//input")
	private WebElement numberOfAttemptsForEachStaff;

	@FindBys(@FindBy(xpath = "//ul[@id='staffList']//li//li[@class='phone']//i"))
	private List<WebElement> selectOrDeselectContactMethod;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveScheduleButton;

	@FindBy(xpath = "//p[@id='message-notification']")
	private WebElement onCallScheduleLoading;

	@FindBys(@FindBy(xpath = "//p[@id='message-notification']"))
	private List<WebElement> onCallScheduleLoaded;

	@FindBy(xpath = "//span[@class='fa fa-close close-datepicker']")
	private WebElement closeCopyScheduleButton;

	@FindBys(@FindBy(xpath = "//div[@class='formInput date-range']"))
	private List<WebElement> copyFromAndCopyToDatesFields;

	@FindBy(xpath = "//div[@class='calendar-table']//th[@class='prev available']//i")
	private WebElement copyScheduleWithRangeCalendarPreviousButton;

	@FindBy(xpath = "//div[@class='calendar-table']//th[@class='next available']//i")
	private WebElement copyScheduleWithRangeCalendarNextButton;

	@FindBys(@FindBy(xpath = "//button[@class='applyBtn btn btn-sm btn-success']"))
	private List<WebElement> applyCopyScheduleWithRange;

	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
	private WebElement cancelCopyScheduleWithRange;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//td"))
	private List<WebElement> daysInLeftCalendarOfCopyScheduleWithRange;

	@FindBys(@FindBy(xpath = "//div[@class='calendar right']//div[@class='calendar-table']//tbody//td"))
	private List<WebElement> daysInRightCalendarOfCopyScheduleWithRange;

	@FindBy(xpath = "//div[@class='copy-schedule-footer']//button")
	private WebElement copyScheduleWithRangeButton;

	@FindBy(xpath = "//button[@id='confirmation-button']")
	private WebElement confirmRemoveScheduleButton;

	@FindBy(xpath = "//button[@id='cancel-confirmation-button']")
	private WebElement cancelRemoveScheduleButton;

	@FindBy(xpath = "//button[@id='week-selector']//span")
	private WebElement textInWeekSelectorButton;

	@FindBys(@FindBy(xpath = "//ul[@class='default-ft calc-header']/li"))
	private List<WebElement> textInWeekColoumn;

	@FindBy(xpath = "//div[@class ='time-zone-wrapper']//h4")
	private WebElement onCallScheduleTimeZone;

	@FindBys(@FindBy(xpath = "//button[@class ='add-schedule']//ancestor::li"))
	private List<WebElement> weekContentView;

	@FindBys(@FindBy(xpath = "//ul[@id='staffList']//li//i//following-sibling::a//span[not(contains(text(),'Add Delivery method'))]"))
	private List<WebElement> allStaffNames;

	@FindBys(@FindBy(xpath = "//ul[@id='staffList']//li[@style='display: list-item;']//child::i[@class='chkbox']"))
	private List<WebElement> searchedStaffSelectOrDeselectButton;

	@FindBys(@FindBy(xpath = "//ul[@id='staffList']//li[@style='display: list-item;']//ul[@class='edit-field']//li[@class='phone']//span//i"))
	private List<WebElement> searchedStaffcontactMethodSelectOrDeselectButton;

	@FindBy(xpath = "//ul[@class='edit-field' and @style ='display: block;']//div[@class='dropdowns']")
	private WebElement selectDeliveryMethod;

	@FindBys(@FindBy(xpath = "//div[@class='dropdowns']//ul[@class='show']//li[not(contains(@class,'selected'))]"))
	private List<WebElement> optionsInDeliveryMethod;

	@FindBys(@FindBy(xpath = "//div[@class='week-view-content']//ul//li//div[@class='schedule gp ui-sortable']"))
	private List<WebElement> numberOfSchedules;

	@FindBys(@FindBy(xpath = "//ul[@class='default-ft calc-header']/li/i//ancestor::li"))
	private List<WebElement> dateofOnCallScheduleAdded;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')]"))
	private List<WebElement> presentDayInCopyWithRangeCalendar;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')]//following-sibling::td[1]"))
	private List<WebElement> nextDayTopresentDayInCopyWithRangeCalendar;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')and contains(@data-title,'c6')]//ancestor::tr//following::tr[1]//td[1]"))
	private List<WebElement> nextDayToPresentDayInCopyWithRangeCalenderIfPresentDayIsSaturday;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')]//following::td[7]"))
	private List<WebElement> seventhDayTopresentDayInCopyWithRangeCalendar;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')and contains(@data-title,'c6')]"))
	private List<WebElement> presentDayIsSaturday;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')and contains(@data-title,'c0')]"))
	private List<WebElement> presentDayIsSunday;

	@FindBys(@FindBy(xpath = "//div[@class='calendar left']//div[@class='calendar-table']//tbody//tr//td[contains(@class,'today')and contains(@data-title,'c0')]//ancestor::tr//preceding-sibling::tr[1]//child::td[7]"))
	private List<WebElement> previousDayToPresentDayInCopyWithRangeCalenderIfPresentDayIsSunday;

	@FindBys(@FindBy(xpath = "//div[@class='datepicker-days']//tbody//tr//td[@class='old day']"))
	private List<WebElement> oldDaysInCalendar;

	@FindBy(xpath = "//div[@class='week-view-content']//ul//li//div[@class='schedule gp ui-sortable']//parent::li[@class='colm-7']")
	private WebElement lastDayOfTheWeek;

	@FindBys(@FindBy(xpath = "//div[@class='week-view-content']//ul//li//div[@class='schedule gp ui-sortable']//parent::li[@class='colm-1']//div[@class='schedule gp ui-sortable']"))
	private List<WebElement> numberOfSchedulesPerFirstDayOfTheWeek;

	@FindBys(@FindBy(xpath = "//div[@class='week-view-content']//ul//li//div[@class='schedule gp ui-sortable']//child::div[@class='staff c1 ui-sortable-handle']"))
	private List<WebElement> configuredStaff;

	@FindBys(@FindBy(xpath = "//article[@id='week-viev']//ul//li/i[1]"))
	private List<WebElement> deleteSchedulesForWholeDayIcon;

	@FindBy(xpath = "//div[@id='alert-con']//footer//button[@id='confirmation-button']")
	private WebElement deleteScheduleForWholeDayConfirmButton;

	@FindBy(xpath = "//div[@id='alert-con']//footer//button[@id='cancel-confirmation-button']")
	private WebElement deleteScheduleForWholeDayCancelButton;

	@FindBys(@FindBy(xpath = "//div[@class='week-view-content']//ul//li//div[1][contains(@class,'schedule gp ui-sortable')]"))
	private List<WebElement> oncallScheduleBlock;

	@FindBys(@FindBy(xpath = "//div[@class='week-view-content']//ul//li//button//ancestor::li"))
	private List<WebElement> addScheduleWeek;

	@FindBy(xpath = "//div[@id='addStaff']")
	private WebElement addScheduleBlock;

	@FindBys(@FindBy(xpath = "//button[@class ='add-schedule']//ancestor::li//div[contains(@class,'staff')]//h4"))
	private List<WebElement> listOfScheduleStaffName;

	@FindBy(xpath = "//ul[@id='staffList']//li[contains(@class,'selected')]//child::i[@class='chkbox']")
	private WebElement selectedStaffCheckBox;

	@FindBys(@FindBy(xpath = "//div[@class='calendar-table']//table//td[contains(@class,'today')]"))
	private List<WebElement> todayInCopyWithRangeCalendar;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'daterangepicker') and contains(@style,'display: block;')]//div[@class='calendar-table']//table//td[contains(@class,'today available')]//following::td[6]"))
	private WebElement oneWeekFromTodayInCopyWithRangeCalendar;

	public OnCallSchedulePage waitUntilOnCallScheduleLoaded() {
		handle.parentToChildWindowControl(driver, 1);
		wait.waitForWebPageLoad(driver, 30);
		wait.waitForElementToBeVisible(driver, onCallScheduleLoading, 30);
		wait.waitForElementToBeInvisible(driver, onCallScheduleLoaded, 30);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);

	}

	public OnCallSchedulePage onCallScheduleNotifications() {
		wait.waitForWebPageLoad(driver, 30);
		try {
			wait.waitForElementToBeVisible(driver, onCallScheduleLoading, 30);
		} catch (Exception onCallScheduleLoaded) {
		}
		wait.waitForElementToBeInvisible(driver, onCallScheduleLoaded, 30);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);

	}

	public OnCallSchedulePage clickOnDeleteOnCallSchedulesForWholeDay(int index) {
		wait.waitForAllElementsToBeVisible(driver, oncallScheduleBlock, 30);
		oncallScheduleBlock.get(index).click();
		wait.waitForElementToBeVisible(driver, deleteSchedulesForWholeDayIcon.get(index), 30);
		wait.waitForElementToBeClickable(driver, deleteSchedulesForWholeDayIcon.get(index), 30);
		deleteSchedulesForWholeDayIcon.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);

	}

	public OnCallSchedulePage confirmDeleteSchedulesForWholeDay() {
		wait.waitForElementToBeClickable(driver, deleteScheduleForWholeDayConfirmButton, 30);
		deleteScheduleForWholeDayConfirmButton.click();
		waitUntilOnCallScheduleLoaded();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnAddSchedule(int index) {
		wait.waitForElementToBeClickable(driver, addScheduleWeek.get(0), 30);
		addScheduleWeek.get(index).click();
		wait.waitForElementToBeClickable(driver, addScheduleButton.get(0), 30);
		addScheduleButton.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage addStaffToSchedule() {
		wait.waitForElementToBeVisible(driver, addScheduleBlock, 30);
		int size = allStaffNames.size();
		int staffNameValue = onCallData.randomIntegerData(0, size - 2);
		staffName = allStaffNames.get(staffNameValue).getText();
		listOfStaffName.add(staffName);
		staffSearchButton.sendKeys(staffName);
		wait.waitForElementToBeClickable(driver, selectOrDeselectStaffCheckBox, 30);
		selectOrDeselectStaffCheckBox.click();
		wait.waitForAllElementsToBeVisible(driver, searchedStaffcontactMethodSelectOrDeselectButton, 30);
		wait.waitForElementToBeClickable(driver, searchedStaffcontactMethodSelectOrDeselectButton.get(0), 30);
		searchedStaffcontactMethodSelectOrDeselectButton.get(0).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage addDeliveryMethod() {
		wait.waitForElementToBeVisible(driver, addDeliveryMethod, 30);
		addDeliveryMethod.click();
		wait.waitForElementToBeVisible(driver, selectDeliveryMethod, 30);
		selectDeliveryMethod.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInDeliveryMethod, 30);
		scroll.scrollUpToWebElement(driver, optionsInDeliveryMethod.get(0));
		optionsInDeliveryMethod.get(0).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage enterNumberOfAttempts() {
		wait.waitForElementToBeVisible(driver, numOfAttemptsField, 30);
		wait.waitForElementToBeClickable(driver, numOfAttemptsField, 30);
		numberOfAttemps = "" + onCallData.randomIntegerData(2, 4);
		numOfAttemptsField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		numOfAttemptsField.sendKeys(numberOfAttemps);
		wait.waitForElementToBeClickable(driver, numberOfAttemptsForEachStaff, 30);
		numberOfAttemptsForEachStaff.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		numberOfAttemptsForEachStaff.sendKeys(numberOfAttemps);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage addTimingsToSchedule() {
		wait.waitForElementToBeClickable(driver, addScheduleFromTimeField, 30);
		scroll.scrollUpToWebElement(driver, addScheduleFromTimeField);
		addScheduleFromTimeField.click();
		addScheduleFromTimeField.clear();
		scheduleFromTime = onCallData.randomIntegerData(1, 12) + ":00";
		addScheduleFromTimeField.sendKeys(scheduleFromTime);
		addScheduleToTimeField.click();
		addScheduleToTimeField.clear();
		scheduleToTime = onCallData.randomIntegerData(1, 11) + ":00";
		addScheduleToTimeField.sendKeys(scheduleToTime);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnSaveSchedule() {
		scroll.scrollUpToWebElement(driver, saveScheduleButton);
		saveScheduleButton.click();
		wait.waitForElementToBeVisible(driver, onCallScheduleLoading, 30);
		wait.waitForElementToBeInvisible(driver, onCallScheduleLoaded, 30);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCalenderList() {
		wait.waitForElementToBeVisible(driver, calendarListShowOrHideButton, 30);
		calendarListShowOrHideButton.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCalendar(int index) {
		wait.waitForAllElementsToBeVisible(driver, calendarList, 30);
		calendarList.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnNewlyAddedCalendar() {
		wait.waitForAllElementsToBeVisible(driver, calendarList, 30);
		int size = calendarList.size();
		calendarList.get(size - 1).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage moveToACalendar(int index) {
		wait.waitForAllElementsToBeVisible(driver, calendarList, 30);
		Actions moveToElement = new Actions(driver);
		moveToElement.moveToElement(calendarList.get(index)).perform();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnAddCalendarButton() {
		wait.waitForElementToBeVisible(driver, addACalendarButton, 30);
		addACalendarButton.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage enterCalendarName() {
		wait.waitForElementToBeVisible(driver, calendarNameTextField, 30);
		calendarName = onCallData.randomData("Calendar");
		calendarNameTextField.sendKeys(calendarName);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnSaveCalendarName() {
		wait.waitForElementToBeVisible(driver, saveCalendarNameButton, 30);
		saveCalendarNameButton.click();
		wait.waitForElementToBeInvisible(driver, savedCalendarNameButton, 30);
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnDeleteCalendar(int index) {
		wait.waitForElementToBeVisible(driver, deleteCalendarButton.get(index), 30);
		deleteCalendarButton.get(index).click();
		try {
			Thread.sleep(1000);
		} catch (Exception waiting) {

		}
		driver.switchTo().alert().accept();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnNewlyAddedSchedule() {
		wait.waitForAllElementsToBeVisible(driver, listOfScheduleStaffName, 30);
		listOfScheduleStaffName.get(0).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnScheduleSettings(int index) {
		wait.waitForAllElementsToBeVisible(driver, scheduleSettingsButton, 30);
		scheduleSettingsButton.get(0).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnEditSchedule(int index) {
		wait.waitForAllElementsToBeVisible(driver, editScheduleButton, 30);
		editScheduleButton.get(0).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnSelectedStaffCheckBox() {
		wait.waitForElementToBeVisible(driver, addScheduleBlock, 30);
		selectedStaffCheckBox.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnNextWeek() {
		wait.waitForElementToBeVisible(driver, nextWeekButton, 30);
		wait.waitForElementToBeClickable(driver, nextWeekButton, 30);
		nextWeekButton.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnPreviousWeek() {
		wait.waitForElementToBeVisible(driver, previousWeekButton, 30);
		previousWeekButton.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnSingleDayCopy(int index) {
		wait.waitForAllElementsToBeVisible(driver, copySchedulePerDay, 30);
		copySchedulePerDay.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnSingleDayCopyToDate() {
		wait.waitForElementToBeVisible(driver, copyToDate, 30);
		copyToDate.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public int getNumberOfFirstSchedules() {
		return oncallScheduleBlock.size();
	}

	public String givenCalendarName() {
		return calendarName;
	}

	public int listOfCalendars() {
		wait.waitForAllElementsToBeVisible(driver, calendarList, 30);
		int size = calendarList.size();
		return size;
	}

	public String savedCalendarName() {
		wait.waitForAllElementsToBeVisible(driver, calendarList, 30);
		int size = calendarList.size();
		return calendarList.get(size - 1).getAttribute("currentvalue");
	}

	public String listOfCalendarNames(int index) {
		wait.waitForAllElementsToBeVisible(driver, calendarList, 30);
		return calendarList.get(index).getAttribute("currentvalue");
	}

	public String givenStaffForOnCallSchedule(int index) {
		return listOfStaffName.get(index);
	}

	public String savedStaffForOnCallSchedule(int index) {
		wait.waitForAllElementsToBeVisible(driver, listOfScheduleStaffName, 30);
		return listOfScheduleStaffName.get(index).getText();
	}

	public OnCallSchedulePage clickOnTomorrowDateForSingledayCopy() {
		wait.waitForElementToBeVisible(driver, copyToDate, 30);
		int i = 0;
		int index = copyToDate.getText().indexOf(" ");
		String todayDate = copyToDate.getText().substring(index + 1);
		if (todayDate.startsWith("0"))
			todayDate = todayDate.substring(1);
		while (daysInCalendar.get(i).getText().contentEquals(todayDate) == false)
			i++;
		daysInCalendar.get(i + 1).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCopySchedulePerSingleDay() {
		wait.waitForElementToBeClickable(driver, copyScheduleToDayButton, 30);
		copyScheduleToDayButton.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCopyWithRangeButton() {
		wait.waitForElementToBeClickable(driver, copyScheduleRange, 30);
		copyScheduleRange.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCopyFromOrToRangeButton(int index) {
		wait.waitForElementToBeClickable(driver, copyFromAndCopyToDatesFields.get(index), 30);
		copyFromAndCopyToDatesFields.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCopyFromDateWithRange(int index) {
		wait.waitForElementToBeClickable(driver, todayInCopyWithRangeCalendar.get(index), 30);
		todayInCopyWithRangeCalendar.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnNextDayToPresentDay(int index) {
		wait.waitForElementToBeClickable(driver, nextDayTopresentDayInCopyWithRangeCalendar.get(index), 30);
		nextDayTopresentDayInCopyWithRangeCalendar.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCopyToDateWithRange() {
		wait.waitForElementToBeClickable(driver, oneWeekFromTodayInCopyWithRangeCalendar, 30);
		oneWeekFromTodayInCopyWithRangeCalendar.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnApplyDateWithRange(int index) {
		wait.waitForElementToBeClickable(driver, applyCopyScheduleWithRange.get(index), 30);
		applyCopyScheduleWithRange.get(index).click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public OnCallSchedulePage clickOnCopyScheduleWithRange() {
		wait.waitForElementToBeClickable(driver, copyScheduleWithRangeButton, 30);
		copyScheduleWithRangeButton.click();
		return PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
	}

	public HomePage onCallSchedulePageClose() {
		handle.childWindowClose(driver, 1);
		handle.childToParentWindowControl(driver);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

}
