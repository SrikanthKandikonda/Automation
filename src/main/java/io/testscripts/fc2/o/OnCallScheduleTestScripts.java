package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.OnCallSchedulePage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class OnCallScheduleTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		return scriptPage;
	}

	public OnCallSchedulePage lauchingOnCallSchedulePage(ScriptPage scriptPage) {
		scriptPage.clickOnThreeDotsButton().clickOnThreeDotsOptions(4);
		OnCallSchedulePage onCallSchedulePage = PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
		onCallSchedulePage.waitUntilOnCallScheduleLoaded();
		return onCallSchedulePage;
	}

	public void deletingExistingSchedules(OnCallSchedulePage onCallSchedulePage) {
		int schedules = onCallSchedulePage.getNumberOfFirstSchedules();
		for (int i = 0; i < schedules; i++) {
			onCallSchedulePage.clickOnDeleteOnCallSchedulesForWholeDay(0).confirmDeleteSchedulesForWholeDay();
		}
	}

	public void addingNewCalendar(OnCallSchedulePage onCallSchedulePage) {
		onCallSchedulePage.clickOnCalenderList().clickOnAddCalendarButton().enterCalendarName()
				.clickOnSaveCalendarName().clickOnReload();
		onCallSchedulePage.onCallScheduleNotifications().clickOnCalenderList().clickOnNewlyAddedCalendar()
				.onCallScheduleNotifications();
	}

	public void addingNewSchedules(OnCallSchedulePage onCallSchedulePage) {
		onCallSchedulePage.clickOnAddSchedule(0).addStaffToSchedule().addDeliveryMethod().enterNumberOfAttempts()
				.addTimingsToSchedule().clickOnSaveSchedule().clickOnNextWeek().onCallScheduleNotifications()
				.clickOnPreviousWeek().onCallScheduleNotifications();
		onCallSchedulePage.clickOnCalenderList();
	}

	public void verifyAddedCalendarAndAddedSchedule(OnCallSchedulePage onCallSchedulePage) {
		Assert.assertEquals(onCallSchedulePage.givenCalendarName(), onCallSchedulePage.savedCalendarName());
		getReport().log(Status.PASS, "New Calendar Saved Successfully");
		onCallSchedulePage.clickOnCalenderList();
		Assert.assertEquals(onCallSchedulePage.givenStaffForOnCallSchedule(0),
				onCallSchedulePage.savedStaffForOnCallSchedule(0));
		getReport().log(Status.PASS, "Oncall Schedule Saved Successfully");
	}

	public void editingSchedule(OnCallSchedulePage onCallSchedulePage) {
		onCallSchedulePage.clickOnNewlyAddedSchedule().clickOnScheduleSettings(0).clickOnEditSchedule(0)
				.clickOnSelectedStaffCheckBox().addStaffToSchedule().addDeliveryMethod().enterNumberOfAttempts()
				.addTimingsToSchedule().clickOnSaveSchedule().clickOnNextWeek().onCallScheduleNotifications()
				.clickOnPreviousWeek().onCallScheduleNotifications();
	}

	public void performingSigleDayCopy(OnCallSchedulePage onCallSchedulePage) {
		onCallSchedulePage.clickOnNewlyAddedSchedule().clickOnSingleDayCopy(0).clickOnSingleDayCopyToDate()
				.clickOnTomorrowDateForSingledayCopy().clickOnCopySchedulePerSingleDay().onCallScheduleNotifications()
				.clickOnNextWeek().onCallScheduleNotifications().clickOnPreviousWeek().onCallScheduleNotifications();
	}

	public void verifyEditedScheduleAndSingleDayCopy(OnCallSchedulePage onCallSchedulePage) {
		int numberOfSchedules = onCallSchedulePage.getNumberOfFirstSchedules();
		Assert.assertEquals(onCallSchedulePage.givenStaffForOnCallSchedule(1),
				onCallSchedulePage.savedStaffForOnCallSchedule(0));
		getReport().log(Status.PASS, "Oncall Schedule Updated Successfully");
		if (numberOfSchedules == 1) {
			onCallSchedulePage.clickOnNextWeek().onCallScheduleNotifications();
			Assert.assertEquals(onCallSchedulePage.givenStaffForOnCallSchedule(1),
					onCallSchedulePage.savedStaffForOnCallSchedule(1));
			getReport().log(Status.PASS, "Oncall Schedule Copied Successfully For Single Day");
		} else
			Assert.assertEquals(onCallSchedulePage.givenStaffForOnCallSchedule(1),
					onCallSchedulePage.savedStaffForOnCallSchedule(1));
		getReport().log(Status.PASS, "Oncall Schedule Copied Successfully For Single Day");
	}

	public void performingCopyOnCallScheduleWithDateRange(OnCallSchedulePage onCallSchedulePage) {
		onCallSchedulePage.clickOnCopyWithRangeButton().clickOnCopyFromOrToRangeButton(0)
				.clickOnCopyFromDateWithRange(0).clickOnCopyFromDateWithRange(0).clickOnApplyDateWithRange(0)
				.clickOnCopyFromOrToRangeButton(1).clickOnNextDayToPresentDay(1).clickOnCopyToDateWithRange()
				.clickOnApplyDateWithRange(1).clickOnCopyScheduleWithRange().onCallScheduleNotifications()
				.clickOnNextWeek().onCallScheduleNotifications().clickOnPreviousWeek().onCallScheduleNotifications();
	}

	public void verifyCopyScheduleWithRange(OnCallSchedulePage onCallSchedulePage) {
		int numberOfSchedules = onCallSchedulePage.getNumberOfFirstSchedules();
		for (int i = 0; i < numberOfSchedules; i++) {
			Assert.assertEquals(onCallSchedulePage.givenStaffForOnCallSchedule(1),
					onCallSchedulePage.savedStaffForOnCallSchedule(i));
		}
		if (numberOfSchedules < 7)
			numberOfSchedules = onCallSchedulePage.clickOnNextWeek().onCallScheduleNotifications()
					.getNumberOfFirstSchedules();
		for (int i = 0; i < numberOfSchedules; i++) {
			Assert.assertEquals(onCallSchedulePage.givenStaffForOnCallSchedule(1),
					onCallSchedulePage.savedStaffForOnCallSchedule(i));
		}
		getReport().log(Status.PASS, "Oncall Schedule Copied Successfully For Multiple Day");
	}

	public void deleteOnCallCalendar(OnCallSchedulePage onCallSchedulePage) {
		int numberOfCalendars = onCallSchedulePage.clickOnCalenderList().listOfCalendars();
		onCallSchedulePage.moveToACalendar(numberOfCalendars - 1).clickOnDeleteCalendar(numberOfCalendars - 1)
				.onCallScheduleNotifications().clickOnReload();
		onCallSchedulePage.onCallScheduleNotifications();
	}

	public void verifyDeletedOnCallCalendar(OnCallSchedulePage onCallSchedulePage) {
		int numberOfCalendars = onCallSchedulePage.clickOnCalenderList().listOfCalendars();
		boolean isCalendarDeleted = true;
		for (int i = 0; i < numberOfCalendars; i++) {
			String calendarName = onCallSchedulePage.listOfCalendarNames(i);
			if (calendarName.equals(onCallSchedulePage.givenCalendarName()))
				isCalendarDeleted = false;
		}
		Assert.assertTrue(isCalendarDeleted);
		getReport().log(Status.PASS, "Oncall Calendar Deleted Successfully");
		onCallSchedulePage.onCallSchedulePageClose();
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyOnCallSchedule(String accountNumber) {
		report = extent.createTest("Verify OnCall Schedule");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		OnCallSchedulePage onCallSchedulePage = lauchingOnCallSchedulePage(scriptPage);
		deletingExistingSchedules(onCallSchedulePage);
		addingNewCalendar(onCallSchedulePage);
		addingNewSchedules(onCallSchedulePage);
		verifyAddedCalendarAndAddedSchedule(onCallSchedulePage);
		editingSchedule(onCallSchedulePage);
		performingSigleDayCopy(onCallSchedulePage);
		verifyEditedScheduleAndSingleDayCopy(onCallSchedulePage);
		performingCopyOnCallScheduleWithDateRange(onCallSchedulePage);
		verifyCopyScheduleWithRange(onCallSchedulePage);
		deletingExistingSchedules(onCallSchedulePage);
		deleteOnCallCalendar(onCallSchedulePage);
		verifyDeletedOnCallCalendar(onCallSchedulePage);
	}

	@AfterMethod
	public void reload() {
		OnCallSchedulePage onCallSchedulePage = PageFactory.initElements(getDriver(), OnCallSchedulePage.class);
		onCallSchedulePage.clickOnReload();
	}

}
