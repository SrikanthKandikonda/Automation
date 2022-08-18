package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.ScheduleBatchMessagesPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class ScheduleBehaviourTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		return scriptPage;
	}

	public ScheduleBatchMessagesPage launchingScheduleBehaviour(ScriptPage scriptPage) {
		scriptPage.clickOnThreeDotsButton().clickOnThreeDotsOptions(6);
		ScheduleBatchMessagesPage scheduleBatchMessagesPage = PageFactory.initElements(getDriver(),
				ScheduleBatchMessagesPage.class);
		scheduleBatchMessagesPage.waitUntilOnScheduleBatchMessagesLoad();
		return scheduleBatchMessagesPage;
	}

	public void deleteExistingScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchScheduleBehaviour(accountNumber);
		int numberOfSchedules = scheduleBatchMessagesPage.getNumberOfSchedulesBehaviours();
		for (int i = 0; i < numberOfSchedules; i++) {
			scheduleBatchMessagesPage.selectScheduleBehaviour(i);
		}
		if (numberOfSchedules > 0)
			scheduleBatchMessagesPage.clickOnDeleteSchedule();
	}

	public void createScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage, String accountNumber) {
		scheduleBatchMessagesPage.createAccount(accountNumber).clickOnCreateScheduleBehaviour().selectSubAccount()
				.selectRecurrenceType().selectStartTimeHours().selectStartTimeMins().selectStartAmOrPm(0)
				.selectEndTimeHours().selectEndTimeMins().selectEndAmOrPm(1).selectScheduleStartDate()
				.selectScheduleEndDateAsNextDayToPresentDay().clickOnSaveScheduleBehaviour();

	}

	public void verifyNewlyCreatedScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchScheduleBehaviour(accountNumber);
		Assert.assertEquals(accountNumber, scheduleBatchMessagesPage.getSavedSubAccountNumber());
		getReport().log(Status.PASS, "Account Number Of Schedule Behaviour Saved Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleStartDate()
				.contains(scheduleBatchMessagesPage.getSelectedDate()));
		getReport().log(Status.PASS, "Start Date Of Schedule Behaviour Saved Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleEndDate()
				.contains(scheduleBatchMessagesPage.getSelectedEndDate()));
		getReport().log(Status.PASS, "End Date Of Schedule Behaviour Saved Successfully In FC");
	}

	public void editNewlyCreatedScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage) {
		scheduleBatchMessagesPage.clickOnEditSchedule().selectSubAccount().selectRecurrenceType().selectStartTimeHours()
				.selectStartTimeMins().selectStartAmOrPm(0).selectScheduleStartDate().selectEndTimeHours()
				.selectEndTimeMins().selectEndAmOrPm(1).selectScheduleEndDateAsNextDayToPresentDay()
				.clickOnSaveScheduleBehaviour();
	}

	public void verifyUpdatedScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		Assert.assertEquals(accountNumber, scheduleBatchMessagesPage.getSavedSubAccountNumber());
		getReport().log(Status.PASS, "Account Number Of Schedule Behaviour Updated Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleStartDate()
				.contains(scheduleBatchMessagesPage.getSelectedDate()));
		getReport().log(Status.PASS, "Start Date Of Schedule Behaviour Updated Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleEndDate()
				.contains(scheduleBatchMessagesPage.getSelectedEndDate()));
		getReport().log(Status.PASS, "End Date Of Schedule Behaviour Updated Successfully In FC");
	}

	public void deleteNewlyAddedScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		int numberOfSchedules = scheduleBatchMessagesPage.getNumberOfSchedulesBehaviours();
		for (int i = 0; i < numberOfSchedules; i++) {
			scheduleBatchMessagesPage.selectScheduleBehaviour(i);
		}
		scheduleBatchMessagesPage.clickOnDeleteSchedule();
	}

	public void verifyDeletedScheduleBehaviour(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchScheduleBehaviour(accountNumber);
		Assert.assertTrue(scheduleBatchMessagesPage.getNumberOfSchedulesBatchMessages() == 0);
		getReport().log(Status.PASS, "Schedule Behaviour Deleted Successfully In FC");
		scheduleBatchMessagesPage.ScheduleBatchMessagesPageClose();
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyScheduleBehaviour(String accountNumber) {
		report = extent.createTest("Verify Schedule Batch Behaviour");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		ScheduleBatchMessagesPage scheduleBatchMessagesPage = launchingScheduleBehaviour(scriptPage);
		deleteExistingScheduleBehaviour(scheduleBatchMessagesPage, accountNumber);
		createScheduleBehaviour(scheduleBatchMessagesPage, accountNumber);
		verifyNewlyCreatedScheduleBehaviour(scheduleBatchMessagesPage, accountNumber);
		editNewlyCreatedScheduleBehaviour(scheduleBatchMessagesPage);
		verifyUpdatedScheduleBehaviour(scheduleBatchMessagesPage, accountNumber);
		deleteNewlyAddedScheduleBehaviour(scheduleBatchMessagesPage, accountNumber);
		verifyDeletedScheduleBehaviour(scheduleBatchMessagesPage, accountNumber);
	}

	@AfterMethod
	public void reload() {
		ScheduleBatchMessagesPage scheduleBatchMessagesPage = PageFactory.initElements(getDriver(),
				ScheduleBatchMessagesPage.class);
		scheduleBatchMessagesPage.clickOnReload();
	}

}
