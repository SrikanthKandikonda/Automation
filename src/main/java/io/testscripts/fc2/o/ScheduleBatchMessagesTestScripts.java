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
public class ScheduleBatchMessagesTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		return scriptPage;
	}

	public ScheduleBatchMessagesPage launchingScheduleBatchMessages(ScriptPage scriptPage) {
		scriptPage.clickOnThreeDotsButton().clickOnThreeDotsOptions(5);
		ScheduleBatchMessagesPage scheduleBatchMessagesPage = PageFactory.initElements(getDriver(),
				ScheduleBatchMessagesPage.class);
		scheduleBatchMessagesPage.waitUntilOnScheduleBatchMessagesLoad();
		return scheduleBatchMessagesPage;
	}

	public void deleteExistingScheduleBatchMessages(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchAccount(accountNumber);
		int numberOfSchedules = scheduleBatchMessagesPage.getNumberOfSchedulesBatchMessages();
		for (int i = 0; i < numberOfSchedules; i++) {
			scheduleBatchMessagesPage.selectScheduleBatchMessage(i);
		}
		if (numberOfSchedules > 0)
			scheduleBatchMessagesPage.clickOnDeleteSchedule();
	}

	public void createScheduleBatchMessage(ScheduleBatchMessagesPage scheduleBatchMessagesPage, String accountNumber) {
		scheduleBatchMessagesPage.createAccount(accountNumber).clickOnCreateBatchMessage().selectDeliveryGroup()
				.selectRecurrenceType().selectStartTimeHours().selectStartTimeMins().selectStartAmOrPm(1)
				.selectScheduleStartDate().selectScheduleEndDate().enterNumberOfMessgesPerDay().clickOnSaveSchedule();

	}

	public void verifyNewlyCreatedScheduleBatchMessage(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchAccount(accountNumber);
		Assert.assertEquals(accountNumber, scheduleBatchMessagesPage.getSavedtAccountNumberOfScheduleBatchMessage());
		getReport().log(Status.PASS, "Account Number Of Schedule Batch Message Saved Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleStartDate()
				.contains(scheduleBatchMessagesPage.getSelectedDate()));
		getReport().log(Status.PASS, "Start Date Of Schedule Batch Message Saved Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleEndDate()
				.contains(scheduleBatchMessagesPage.getSelectedDate()));
		getReport().log(Status.PASS, "End Date Of Schedule Batch Message Saved Successfully In FC");
		Assert.assertEquals(scheduleBatchMessagesPage.getSelectedDeliveryMethodOfScheduleBatchMessage(),
				scheduleBatchMessagesPage.getSavedDeliveryMethodOfScheduleBatchMessage());
		getReport().log(Status.PASS, "Delivery Method Of Schedule Batch Message Saved Successfully In FC");
	}

	public void editNewlyCreatedScheduleBatchMessage(ScheduleBatchMessagesPage scheduleBatchMessagesPage) {

		scheduleBatchMessagesPage.clickOnEditSchedule().selectDeliveryGroup().selectRecurrenceType()
				.selectStartTimeHours().selectStartTimeMins().selectStartAmOrPm(1).selectScheduleStartDate()
				.selectScheduleEndDate().clickOnSaveSchedule();
	}

	public void verifyUpdatedScheduleBatchMessage(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchAccount(accountNumber);
		Assert.assertEquals(accountNumber, scheduleBatchMessagesPage.getSavedtAccountNumberOfScheduleBatchMessage());
		getReport().log(Status.PASS, "Account Number Of Schedule Batch Message Updated Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleStartDate()
				.contains(scheduleBatchMessagesPage.getSelectedDate()));
		getReport().log(Status.PASS, "Start Date Of Schedule Batch Message Updated Successfully In FC");
		Assert.assertTrue(scheduleBatchMessagesPage.getSavedScheduleEndDate()
				.contains(scheduleBatchMessagesPage.getSelectedDate()));
		getReport().log(Status.PASS, "End Date Of Schedule Batch Message Updated Successfully In FC");
		Assert.assertEquals(scheduleBatchMessagesPage.getSelectedDeliveryMethodOfScheduleBatchMessage(),
				scheduleBatchMessagesPage.getSavedDeliveryMethodOfScheduleBatchMessage());
		getReport().log(Status.PASS, "Delivery Method Of Schedule Batch Message Updated Successfully In FC");
	}

	public void deleteNewlyAddedScheduleBatchMessages(ScheduleBatchMessagesPage scheduleBatchMessagesPage) {
		int numberOfSchedules = scheduleBatchMessagesPage.getNumberOfSchedulesBatchMessages();
		for (int i = 0; i < numberOfSchedules; i++) {
			scheduleBatchMessagesPage.selectScheduleBatchMessage(i);
		}
		scheduleBatchMessagesPage.clickOnDeleteSchedule();
	}

	public void verifyDeletedScheduleBatchMessages(ScheduleBatchMessagesPage scheduleBatchMessagesPage,
			String accountNumber) {
		scheduleBatchMessagesPage.searchAccount(accountNumber);
		Assert.assertTrue(scheduleBatchMessagesPage.getNumberOfSchedulesBatchMessages() == 0);
		getReport().log(Status.PASS, "Schedule Batch Message Deleted Successfully In FC");
		scheduleBatchMessagesPage.ScheduleBatchMessagesPageClose();
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyScheduleBatchMessages(String accountNumber) {
		report = extent.createTest("Verify Schedule Batch Messages");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		ScheduleBatchMessagesPage scheduleBatchMessagesPage = launchingScheduleBatchMessages(scriptPage);
		deleteExistingScheduleBatchMessages(scheduleBatchMessagesPage, accountNumber);
		createScheduleBatchMessage(scheduleBatchMessagesPage, accountNumber);
		verifyNewlyCreatedScheduleBatchMessage(scheduleBatchMessagesPage, accountNumber);
		editNewlyCreatedScheduleBatchMessage(scheduleBatchMessagesPage);
		verifyUpdatedScheduleBatchMessage(scheduleBatchMessagesPage, accountNumber);
		deleteNewlyAddedScheduleBatchMessages(scheduleBatchMessagesPage);
		verifyDeletedScheduleBatchMessages(scheduleBatchMessagesPage, accountNumber);
	}

	@AfterMethod
	public void reload() {
		ScheduleBatchMessagesPage scheduleBatchMessagesPage = PageFactory.initElements(getDriver(),
				ScheduleBatchMessagesPage.class);
		scheduleBatchMessagesPage.clickOnReload();
	}

}
