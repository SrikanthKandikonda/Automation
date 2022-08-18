package io.pagelibrary.fc2.o;

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

public class ScheduleBatchMessagesPage extends HomePage {
	Wait wait = new Wait();
	RandomData scheduleData = new RandomData();
	Scroll scroll = new Scroll();
	WindowHandles handle = new WindowHandles();
	WebDriver driver;
	private static String savedDeliveryMethod;
	private static String selectedScheduleDate;
	private static String selectedScheduleEndDate;

	public ScheduleBatchMessagesPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@id='SearchBatch']")
	private WebElement searchBatchAccountTextField;

	@FindBy(xpath = "//input[@id='createId']")
	private WebElement createBatchMessageButton;

	@FindBy(xpath = "//input[@id='Delete']")
	private WebElement deleteBatchMessageButton;

	@FindBy(xpath = "//button[@id='tab_previous']")
	private WebElement previousButton;

	@FindBy(xpath = "//button[@id='tab_next']")
	private WebElement nextButton;

	@FindBys(@FindBy(xpath = "//input[@name='deleteBatchCheckBox']"))
	private List<WebElement> deleteBatchMessageCheckBoxes;

	@FindBys(@FindBy(xpath = "//tr[@class]//td[2]"))
	private List<WebElement> accountNumbersText;

	@FindBys(@FindBy(xpath = "//tr[@class]//td[3]"))
	private List<WebElement> scheduleStartDateText;

	@FindBys(@FindBy(xpath = "//tr[@class]//td[4]"))
	private List<WebElement> scheduleEndDateText;

	@FindBys(@FindBy(xpath = "//tr[@class]//td[5]"))
	private List<WebElement> deliveryMethodText;

	@FindBys(@FindBy(xpath = "//tr[@class]//td[6]"))
	private List<WebElement> batchMessageStatusText;

	@FindBys(@FindBy(xpath = "//input[@name='editBatchButton']"))
	private List<WebElement> editBatchMessageButton;

	@FindBy(xpath = "//img[@id='searching' and not(contains(@style,'display: none;')) or @id ='loading' and not(contains(@style,'display: none;'))]")
	private WebElement batchMessagesLoadingStatus;

	@FindBys(@FindBy(xpath = "//img[@id='searching' and not(contains(@style,'display: none;')) or @id ='loading' and not(contains(@style,'display: none;'))]"))
	private List<WebElement> batchMessagesLoadedStatus;

	@FindBys(@FindBy(xpath = "//div[@id='scheduleConfiguration' and @style='display: block;']//div[@id='delvierymethodList']//input"))
	private List<WebElement> deliveryMethodRadioButton;

	@FindBys(@FindBy(xpath = "//div[@id='scheduleConfiguration' and @style='display: block;']//div[@id='delvierymethodList']//label"))
	private List<WebElement> deliveryMethodTextInScheduleBlock;

	@FindBys(@FindBy(xpath = "//div[contains(@id,'schedule') and @style='display: block;']//div//input[@name='recurrencetypelist']"))
	private List<WebElement> recurrenceTypeRadioButton;

	@FindBys(@FindBy(xpath = "//div[@class='weekdays clearall']//input"))
	private List<WebElement> dayInWeeklyScheduleCheckBox;

	@FindBy(xpath = "//div[@class='clearall']//input[@id='WeeklyInterval']")
	private WebElement repeatForNumberOfWeeksTextField;

	@FindBy(xpath = "//fieldset[@id='monthly']")
	private WebElement monthlyScheduleBlock;

	@FindBy(xpath = "//fieldset[@id='weekly']")
	private WebElement weeklyScheduleBlock;

	@FindBys(@FindBy(xpath = "//div[@class='months']//input"))
	private List<WebElement> monthInMonthlyScheduleCheckBox;

	@FindBy(xpath = "//span[text()='On week of month:']//ancestor::div[@class='scheduleOpt']//child::input")
	private WebElement onWeekOfMonthRadioButton;

	@FindBy(xpath = "//span[text()='On week of month:']//ancestor::div[@class='scheduleOpt']//child::select")
	private WebElement onWeekOfMonthDropDownButton;

	@FindBys(@FindBy(xpath = "//span[text()='On week of month:']//ancestor::div[@class='scheduleOpt']//child::select//option"))
	private List<WebElement> optionsInOnWeekOfMonthDropDown;

	@FindBys(@FindBy(xpath = "//div[@class='weekopt weekdays']//input[@name='month-week-day']"))
	private List<WebElement> onDayOfTheWeekCheckBox;

	@FindBy(xpath = "//span[text()='On Calendar day(s): ']//ancestor::div[@class='scheduleOpt']//child::input[@name='month-radio']")
	private WebElement onCalendarDayRadioButton;

	@FindBy(xpath = "//span[text()='On Calendar day(s): ']//ancestor::div[@class='scheduleOpt']//child::input[@name='month-date']")
	private WebElement onCalendarDayTextField;

	@FindBy(xpath = "//fieldset[@id='daily']")
	private WebElement dailyScheduleBlock;

	@FindBys(@FindBy(xpath = "//fieldset[@id='daily']//div//input[@type = 'radio']"))
	private List<WebElement> dailyScheduleRadioButtons;

	@FindBys(@FindBy(xpath = "//fieldset[@id='daily']//div[@class='weekopt weekdays']//input"))
	private List<WebElement> dailyScheduleDayCheckBox;

	@FindBy(xpath = "//div[@class='clearall']//input[@id='daily-interval']")
	private WebElement repeatAfterThisNumberOfWeeksTextField;

	@FindBy(xpath = "//div[@class='schedTime']//select[@id='start-hh']")
	private WebElement startScheduleTimeHoursDropDownButton;

	@FindBy(xpath = "//div[@class='schedTime']//select[@id='end-hh']")
	private WebElement endScheduleTimeHoursDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='schedTime']//select[@id='start-hh']//option[not(contains(text(),'HH'))]"))
	private List<WebElement> optionsInStartScheduleTimeHoursDropDown;

	@FindBys(@FindBy(xpath = "//div[@class='schedTime']//select[@id='end-hh']//option[not(contains(text(),'HH'))]"))
	private List<WebElement> optionsInEndScheduleTimeHoursDropDown;

	@FindBy(xpath = "//div[@class='schedTime']//select[@id='start-mm']")
	private WebElement startScheduleTimeMinutesDropDownButton;

	@FindBy(xpath = "//div[@class='schedTime']//select[@id='end-mm']")
	private WebElement endScheduleTimeMinutesDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='schedTime']//select[@id='start-mm']//option[not(contains(text(),'MM'))]"))
	private List<WebElement> optionsInStartScheduleTimeMinutesDropDown;

	@FindBys(@FindBy(xpath = "//div[@class='schedTime']//select[@id='end-mm']//option[not(contains(text(),'MM'))]"))
	private List<WebElement> optionsInEndScheduleTimeMinutesDropDown;

	@FindBy(xpath = "//div[@class='schedTime']//select[@id='start-ampm']")
	private WebElement startScheduleTimeAmAndPmDropDownButton;

	@FindBy(xpath = "//div[@class='schedTime']//select[@id='end-ampm']")
	private WebElement endScheduleTimeAmAndPmDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='schedTime']//select[@id='start-ampm']//option"))
	private List<WebElement> optionsInStartScheduleTimeAmAndPmDropDown;

	@FindBys(@FindBy(xpath = "//div[@class='schedTime']//select[@id='end-ampm']//option"))
	private List<WebElement> optionsInEndScheduleTimeAmAndPmDropDown;

	@FindBy(xpath = "//input[@id='firstBatchMessages']")
	private WebElement specifyNumberOfMessageTextField;

	@FindBy(xpath = "//div[@class='buttons']//button[1]")
	private WebElement saveScheduleMessageButton;

	@FindBy(xpath = "//div[@class='buttons']//button[2]")
	private WebElement deleteScheduleMessageButton;

	@FindBys(@FindBy(xpath = "//div[@id='accountList']//input"))
	private List<WebElement> subAccountRadioButton;

	@FindBy(xpath = "//div[@class='errormess']//b[@id='msg']")
	private WebElement savingNotification;

	@FindBy(xpath = "//td[@class= 'dataTables_empty']")
	private WebElement noSchedulePresentText;

	@FindBy(xpath = "//div[@id= 'scheduleConfiguration']")
	private WebElement recurringScheduledMessageDeliveryBlock;

	@FindBy(xpath = "//fieldset[@id='once']//div[@class='clearall']//span")
	private WebElement reportRunsOnlyOnceText;

	@FindBy(xpath = "//div[@class='sddate']//input[@id='startDate']")
	private WebElement beginRunningThisScheduleOnTextField;

	@FindBy(xpath = "//div[@class='sddate']//input[@id='endDate']")
	private WebElement stopRunningThisScheduleOnTextField;

	@FindBy(xpath = "//fieldset[@id='daily']//span")
	private WebElement reportRunsEveryDayText;

	@FindBy(xpath = "//button[@id='createId']")
	private WebElement createSheduleBehaviourButton;

	@FindBys(@FindBy(xpath = "//input[@name='deleteCheckBox']"))
	private List<WebElement> deleteScheduleBehaviourCheckBox;

	@FindBy(xpath = "//div[@id='notification' and contains(@style,'opacity')]")
	private WebElement loadingScheduleNotification;

	@FindBys(@FindBy(xpath = "//div[@id='notification' and contains(@style,'opacity')]"))
	private List<WebElement> loadedScheduleNotification;

	@FindBy(xpath = "//div[@class='sddate']//input[@id='startDate']")
	private WebElement scheduleStartDateTextField;

	@FindBy(xpath = "//div[@class='sddate']//input[@id='endDate']")
	private WebElement scheduleEndDateTextField;

	@FindBy(xpath = "//div[contains(@class,'xdsoft_datetimepicker') and contains(@style, 'display: block;')]//tr//td[contains(@class,'today')]")
	private WebElement todayDateInCalendar;

	@FindBy(xpath = "//div[contains(@class,'xdsoft_datetimepicker') and contains(@style, 'display: block;')]//tr//td[contains(@class,'today')]//following::td[1]")
	private WebElement nextDayInCalendar;

	@FindBy(xpath = "//tr[@class='odd']//td[2]")
	private WebElement accountNumberText;

	@FindBy(xpath = "//tr[@class='odd']//td[5]")
	private WebElement deliveryGroupText;

	@FindBy(xpath = "//tr[@class='odd']//td[5]")
	private WebElement subAccountNumberText;

	@FindBy(xpath = "//tr[@class='odd']//td[3]")
	private WebElement startDateText;

	@FindBy(xpath = "//tr[@class='odd']//td[4]")
	private WebElement endDateText;

	@FindBy(xpath = "//input[contains(@name,'edit')]")
	private WebElement editSchduleBatchMessageButton;

	@FindBys(@FindBy(xpath = "//div[contains(@id,'schedulebehaviourConfiguration') and contains(@style, 'display: block;')]"))
	private List<WebElement> scheduleBehaviourBlock;

	public ScheduleBatchMessagesPage waitUntilOnScheduleBatchMessagesLoad() {
		handle.parentToChildWindowControl(driver, 1);
		wait.waitForWebPageLoad(driver, 30);
		wait.waitForElementToBeVisible(driver, batchMessagesLoadingStatus, 30);
		try {
			wait.waitForToBeAlertLoaded(driver, 15);
			driver.switchTo().alert().accept();
		} catch (Exception scheduleBehaviour) {
		}
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);

	}

	public HomePage ScheduleBatchMessagesPageClose() {
		handle.childWindowClose(driver, 1);
		handle.childToParentWindowControl(driver);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public ScheduleBatchMessagesPage createAccount(String accountNumber) {
		wait.waitForElementToBeVisible(driver, searchBatchAccountTextField, 30);
		searchBatchAccountTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		searchBatchAccountTextField.sendKeys(accountNumber);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage searchAccount(String accountNumber) {
		wait.waitForElementToBeVisible(driver, searchBatchAccountTextField, 30);
		searchBatchAccountTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		searchBatchAccountTextField.sendKeys(accountNumber);
		searchBatchAccountTextField.sendKeys(Keys.ENTER);
		wait.waitForElementToBeVisible(driver, batchMessagesLoadingStatus, 30);
		wait.waitForElementToBeInvisible(driver, batchMessagesLoadedStatus, 30);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage searchScheduleBehaviour(String accountNumber) {
		wait.waitForElementToBeVisible(driver, searchBatchAccountTextField, 30);
		searchBatchAccountTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		searchBatchAccountTextField.sendKeys(accountNumber);
		searchBatchAccountTextField.sendKeys(Keys.ENTER);
		wait.waitForElementToBeVisible(driver, loadingScheduleNotification, 30);
		wait.waitForElementToBeInvisible(driver, loadedScheduleNotification, 30);
		try {
			wait.waitForToBeAlertLoaded(driver, 3);
			driver.switchTo().alert().accept();
		} catch (Exception scheduleBehaviour) {
		}
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage clickOnCreateBatchMessage() {
		wait.waitForElementToBeVisible(driver, createBatchMessageButton, 30);
		wait.waitForElementToBeClickable(driver, createBatchMessageButton, 30);
		createBatchMessageButton.click();
		wait.waitForElementToBeVisible(driver, loadingScheduleNotification, 30);
		wait.waitForElementToBeInvisible(driver, loadedScheduleNotification, 30);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage clickOnCreateScheduleBehaviour() {
		wait.waitForElementToBeVisible(driver, createSheduleBehaviourButton, 30);
		wait.waitForElementToBeClickable(driver, createSheduleBehaviourButton, 30);
		createSheduleBehaviourButton.click();
		wait.waitForElementToBeVisible(driver, loadingScheduleNotification, 30);
		wait.waitForElementToBeInvisible(driver, loadedScheduleNotification, 30);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectDeliveryGroup() {
		wait.waitForAllElementsToBeVisible(driver, deliveryMethodRadioButton, 30);
		int size = deliveryMethodRadioButton.size();
		int value = scheduleData.randomIntegerData(0, size - 1);
		deliveryMethodRadioButton.get(value).click();
		savedDeliveryMethod = deliveryMethodTextInScheduleBlock.get(value).getText();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectSubAccount() {
		wait.waitForAllElementsToBeVisible(driver, subAccountRadioButton, 30);
		int size = subAccountRadioButton.size();
		int value = scheduleData.randomIntegerData(0, size - 1);
		subAccountRadioButton.get(value).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectRecurrenceType() {
		wait.waitForAllElementsToBeVisible(driver, recurrenceTypeRadioButton, 30);
		scroll.scrollUpToWebElement(driver, recurrenceTypeRadioButton.get(1));
		recurrenceTypeRadioButton.get(1).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectStartTimeHours() {
		wait.waitForElementToBeVisible(driver, startScheduleTimeHoursDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, startScheduleTimeHoursDropDownButton);
		startScheduleTimeHoursDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInStartScheduleTimeHoursDropDown, 30);
		int size = optionsInStartScheduleTimeHoursDropDown.size();
		int startTime = scheduleData.randomIntegerData(0, size - 3);
		scroll.scrollUpToWebElement(driver, optionsInStartScheduleTimeHoursDropDown.get(startTime));
		optionsInStartScheduleTimeHoursDropDown.get(startTime).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectStartTimeMins() {
		wait.waitForElementToBeVisible(driver, startScheduleTimeMinutesDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, startScheduleTimeMinutesDropDownButton);
		startScheduleTimeMinutesDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInStartScheduleTimeMinutesDropDown, 30);
		int size = optionsInStartScheduleTimeMinutesDropDown.size();
		int startTime = scheduleData.randomIntegerData(0, size - 3);
		scroll.scrollUpToWebElement(driver, optionsInStartScheduleTimeMinutesDropDown.get(startTime));
		optionsInStartScheduleTimeMinutesDropDown.get(startTime).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectStartAmOrPm(int index) {
		wait.waitForElementToBeVisible(driver, startScheduleTimeAmAndPmDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, startScheduleTimeAmAndPmDropDownButton);
		startScheduleTimeAmAndPmDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInStartScheduleTimeAmAndPmDropDown, 30);
		scroll.scrollUpToWebElement(driver, optionsInStartScheduleTimeAmAndPmDropDown.get(1));
		optionsInStartScheduleTimeAmAndPmDropDown.get(index).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectEndTimeHours() {
		wait.waitForElementToBeVisible(driver, endScheduleTimeHoursDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, endScheduleTimeHoursDropDownButton);
		endScheduleTimeHoursDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInEndScheduleTimeHoursDropDown, 30);
		int size = optionsInEndScheduleTimeHoursDropDown.size();
		int startTime = scheduleData.randomIntegerData(0, size - 3);
		scroll.scrollUpToWebElement(driver, optionsInEndScheduleTimeHoursDropDown.get(startTime));
		optionsInEndScheduleTimeHoursDropDown.get(startTime).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectEndTimeMins() {
		wait.waitForElementToBeVisible(driver, endScheduleTimeMinutesDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, endScheduleTimeMinutesDropDownButton);
		endScheduleTimeMinutesDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInEndScheduleTimeMinutesDropDown, 30);
		int size = optionsInEndScheduleTimeMinutesDropDown.size();
		int startTime = scheduleData.randomIntegerData(0, size - 3);
		scroll.scrollUpToWebElement(driver, optionsInEndScheduleTimeMinutesDropDown.get(startTime));
		optionsInEndScheduleTimeMinutesDropDown.get(startTime).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectEndAmOrPm(int index) {
		wait.waitForElementToBeVisible(driver, endScheduleTimeAmAndPmDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, endScheduleTimeAmAndPmDropDownButton);
		endScheduleTimeAmAndPmDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, optionsInEndScheduleTimeAmAndPmDropDown, 30);
		scroll.scrollUpToWebElement(driver, optionsInEndScheduleTimeAmAndPmDropDown.get(1));
		optionsInEndScheduleTimeAmAndPmDropDown.get(index).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectScheduleStartDate() {
		wait.waitForElementToBeVisible(driver, scheduleStartDateTextField, 30);
		scroll.scrollUpToWebElement(driver, scheduleStartDateTextField);
		scheduleStartDateTextField.click();
		wait.waitForElementToBeVisible(driver, todayDateInCalendar, 30);
		scroll.scrollUpToWebElement(driver, todayDateInCalendar);
		selectedScheduleDate = todayDateInCalendar.getAttribute("data-date");
		todayDateInCalendar.click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectScheduleEndDate() {
		wait.waitForElementToBeVisible(driver, scheduleEndDateTextField, 30);
		scroll.scrollUpToWebElement(driver, scheduleEndDateTextField);
		scheduleEndDateTextField.click();
		wait.waitForElementToBeVisible(driver, todayDateInCalendar, 30);
		scroll.scrollUpToWebElement(driver, todayDateInCalendar);
		todayDateInCalendar.click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectScheduleEndDateAsNextDayToPresentDay() {
		wait.waitForElementToBeVisible(driver, scheduleEndDateTextField, 30);
		scroll.scrollUpToWebElement(driver, scheduleEndDateTextField);
		scheduleEndDateTextField.click();
		wait.waitForElementToBeVisible(driver, todayDateInCalendar, 30);
		scroll.scrollUpToWebElement(driver, todayDateInCalendar);
		selectedScheduleEndDate = nextDayInCalendar.getAttribute("data-date");
		nextDayInCalendar.click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage enterNumberOfMessgesPerDay() {
		wait.waitForElementToBeVisible(driver, specifyNumberOfMessageTextField, 30);
		scroll.scrollUpToWebElement(driver, specifyNumberOfMessageTextField);
		specifyNumberOfMessageTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		int numberOfMessages = scheduleData.randomIntegerData(1, 8);
		specifyNumberOfMessageTextField.sendKeys(numberOfMessages + "");
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage clickOnSaveSchedule() {
		wait.waitForElementToBeVisible(driver, saveScheduleMessageButton, 30);
		scroll.scrollUpToWebElement(driver, saveScheduleMessageButton);
		saveScheduleMessageButton.click();
		wait.waitForElementToBeVisible(driver, loadingScheduleNotification, 30);
		wait.waitForElementToBeInvisible(driver, loadedScheduleNotification, 30);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage clickOnSaveScheduleBehaviour() {
		wait.waitForElementToBeVisible(driver, saveScheduleMessageButton, 30);
		scroll.scrollUpToWebElement(driver, saveScheduleMessageButton);
		saveScheduleMessageButton.click();
		try {
			wait.waitForToBeAlertLoaded(driver, 6);
			driver.switchTo().alert().accept();
		} catch (Exception scheduleBehaviour) {
		}
		try {
			wait.waitForElementToBeInvisible(driver, scheduleBehaviourBlock, 10);
		} catch (Exception scheduleTime) {
			selectStartTimeHours().selectStartTimeMins().selectStartAmOrPm(0).selectEndTimeHours().selectEndTimeMins()
					.selectEndAmOrPm(1).selectScheduleStartDate().selectScheduleEndDateAsNextDayToPresentDay();
			saveScheduleMessageButton.click();
			try {
				wait.waitForToBeAlertLoaded(driver, 10);
				driver.switchTo().alert().accept();
			} catch (Exception alert) {
			}
			wait.waitForElementToBeInvisible(driver, scheduleBehaviourBlock, 10);
		}

		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage clickOnEditSchedule() {
		int count = 1;
		while (count < 5) {
			try {
				wait.waitForElementToBeVisible(driver, editSchduleBatchMessageButton, 30);
				count++;
				break;
			} catch (Exception staleElement) {
				count++;
			}
		}

		scroll.scrollUpToWebElement(driver, editSchduleBatchMessageButton);
		editSchduleBatchMessageButton.click();
		wait.waitForElementToBeVisible(driver, loadingScheduleNotification, 30);
		wait.waitForElementToBeInvisible(driver, loadedScheduleNotification, 30);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectScheduleBatchMessage(int index) {
		wait.waitForAllElementsToBeVisible(driver, deleteBatchMessageCheckBoxes, 30);
		scroll.scrollUpToWebElement(driver, deleteBatchMessageCheckBoxes.get(index));
		deleteBatchMessageCheckBoxes.get(index).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage clickOnDeleteSchedule() {
		wait.waitForElementToBeVisible(driver, deleteBatchMessageButton, 30);
		scroll.scrollUpToWebElement(driver, deleteBatchMessageButton);
		deleteBatchMessageButton.click();
		wait.waitForToBeAlertLoaded(driver, 5);
		driver.switchTo().alert().accept();
		wait.waitForElementToBeVisible(driver, loadingScheduleNotification, 30);
		wait.waitForElementToBeInvisible(driver, loadedScheduleNotification, 30);
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public ScheduleBatchMessagesPage selectScheduleBehaviour(int index) {
		wait.waitForAllElementsToBeVisible(driver, deleteScheduleBehaviourCheckBox, 30);
		scroll.scrollUpToWebElement(driver, deleteScheduleBehaviourCheckBox.get(index));
		deleteScheduleBehaviourCheckBox.get(index).click();
		return PageFactory.initElements(getDriver(), ScheduleBatchMessagesPage.class);
	}

	public int getNumberOfSchedulesBatchMessages() {
		return deleteBatchMessageCheckBoxes.size();
	}

	public int getNumberOfSchedulesBehaviours() {
		return deleteScheduleBehaviourCheckBox.size();
	}

	public String getSavedtAccountNumberOfScheduleBatchMessage() {
		return accountNumberText.getText();
	}

	public String getSavedSubAccountNumber() {
		return subAccountNumberText.getText();
	}

	public String getSavedDeliveryMethodOfScheduleBatchMessage() {
		return deliveryGroupText.getText();
	}

	public String getSelectedDeliveryMethodOfScheduleBatchMessage() {
		return savedDeliveryMethod;
	}

	public String getSelectedDate() {
		return selectedScheduleDate;
	}

	public String getSelectedEndDate() {
		return selectedScheduleEndDate;
	}

	public String getSavedScheduleStartDate() {
		return startDateText.getText();
	}

	public String getSavedScheduleEndDate() {
		return endDateText.getText();
	}

}
