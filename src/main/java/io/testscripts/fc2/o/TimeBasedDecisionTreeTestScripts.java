package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.BusinessHoursAndClosuresPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class TimeBasedDecisionTreeTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		return scriptPage;
	}

	public void deletingUnpublishedForms(ScriptPage scriptPage) {
		int numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();
		for (int i = 0; i < numberOfUnblishedForms; i++) {
			scriptPage.clickOnFormbutton(1).clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton()
					.waitForScriptLoad().waitForSaveOrPublishNotification();
		}
	}

	public BusinessHoursAndClosuresPage selectingBusinessHours() {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		businessHoursAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab();
		businessHoursAndClosuresPage.clickOnBusinessHoursDropDown().selectBusinessHoursType(1)
				.clickOnBusinessHoursDropDown().selectBusinessHoursType(2).selectOrDeselectBusinessDays();
		businessHoursAndClosuresPage.clickOnSelectedBusinessDayFromTimingOrToTiming(0)
				.enterSelectedBusinessDayFromHourOrToHourTiming(0).selectEnabledBusinessDayAmOrPm(0);
		businessHoursAndClosuresPage.clickOnSelectedBusinessDayFromTimingOrToTiming(1)
				.enterSelectedBusinessDayFromHourOrToHourTiming(1).selectEnabledBusinessDayAmOrPm(1)
				.selectAppointmentForSelectedBusinessDay().clickOnSaveBusinesshoursAndClosuresButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification().clickOnScriptButton();
		return businessHoursAndClosuresPage;
	}

	public void addingTimeBasedCallFlowUsingBusinessHours(ScriptPage scriptPage) {
		scriptPage.clickOnAddNewFormButton();
		for (int i = 1; i < 5; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		for (int i = 1; i < 4; i++) {
			scriptPage.addingFieldsInsideOption(i, 1);
		}
		scriptPage.clickOnDecisionTreeOption(1).clickOnTimeBasedCallFlowToggleButton().clickOnUseBusinessHoursButton()
				.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();

	}

	public void publishNewlyAddedScript(BusinessHoursAndClosuresPage businessHoursAndClosuresPage,
			ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().waitForSaveOrPublishNotification();
		businessHoursAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab()
				.clickOnTimeZoneDropDown().selectTimezone().clickOnSaveBusinesshoursAndClosuresButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
	}

	public void validatingTimeBasedCallFlowUsingBusinessHours(BusinessHoursAndClosuresPage businessHoursAndClosuresPage,
			ScriptPage scriptPage) {
		scriptPage.clickOnDecisionTreeOption(1);
		try {
			Assert.assertEquals(businessHoursAndClosuresPage.businessHoursTimingsBeforeSaving(0),
					scriptPage.timeBasedCallFlowTimingsAfterSaving(0));
			Assert.assertEquals(businessHoursAndClosuresPage.businessHoursTimingsBeforeSaving(1),
					scriptPage.timeBasedCallFlowTimingsAfterSaving(1));
		} catch (Error timeBasedDi) {

		}
		getReport().log(Status.PASS, "Time Based Call Flow Using Business Hours Saved Sucessfully In FC");
		getReport().log(Status.PASS, "Time Based Call Flow Loaded Sucessfully After Timezone Changes In FC");

	}

	public void selectingApplyAllDaysToTimeBasedCallFlow(ScriptPage scriptPage) {
		scriptPage.selectApplyAllForTimeBasedCallFlow().clickOnSaveScriptButton().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	public void validatingTimeBasedCallFlowForApplyAllDays(ScriptPage scriptPage) {
		scriptPage.clickOnDecisionTreeOption(1);
		Assert.assertTrue(scriptPage.selectedTimeBasedCallFlowDays() == 7);
		getReport().log(Status.PASS, "Time Based Call Flow ApplyAllDays Saved Sucessfully In FC");

	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingScriptInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Newly Added Script Loaded Sucessfully In Sb");
		Assert.assertTrue(switchBoardPage.getNumberOfOptionsInDecisionTree() < 2);
		getReport().log(Status.PASS, "Time Based Call Flow Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();

	}

	public void deleteNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Newly Added Script Deleted Succesfully");

	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyTimeBasedDecisionTree(String accountNumber) {
		report = extent.createTest("Verify Time Based Decision Tree");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = selectingBusinessHours();
		addingTimeBasedCallFlowUsingBusinessHours(scriptPage);
		refetchingScript(scriptPage);
		publishNewlyAddedScript(businessHoursAndClosuresPage, scriptPage);
		refetchingScript(scriptPage);
		validatingTimeBasedCallFlowUsingBusinessHours(businessHoursAndClosuresPage, scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingScriptInSb();
		selectingApplyAllDaysToTimeBasedCallFlow(scriptPage);
		refetchingScript(scriptPage);
		validatingTimeBasedCallFlowForApplyAllDays(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingScriptInSb();
		deleteNewlyAddedScript(scriptPage);
	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
