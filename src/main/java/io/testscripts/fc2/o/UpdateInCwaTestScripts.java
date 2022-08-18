package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.AccountDetailsPage;
import io.pagelibrary.fc2.o.BusinessHoursAndClosuresPage;
import io.pagelibrary.fc2.o.CwaPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class UpdateInCwaTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static String answerPhraseInSb;
	private boolean spanishScriptNotificaiton;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isLockedAlertDisplayed();
		if (spanishScriptNotificaiton)
			scriptPage.confirmMessageAnnotationAlertNotification().clickOnSettingsButton().clickOnAccountDetailsTab()
					.clickOnSuperAcountToggleButton();
		return scriptPage;
	}

	public void deletingUnpublishedForms(ScriptPage scriptPage) {
		if (spanishScriptNotificaiton) {
			scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
					.waitForScriptLoad().waitForSaveOrPublishNotification();
			scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		}
		int numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();
		for (int i = 0; i < numberOfUnblishedForms; i++) {
			scriptPage.clickOnFormbutton(1).clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton()
					.waitForScriptLoad().waitForSaveOrPublishNotification();
		}
	}

	public void deletingExistingHolidayScript(ScriptPage scriptPage) {
		if (scriptPage.isHolidayScriptingEnabled()) {
			scriptPage.clickOnFormbutton(1).clickOnSettingsViewButton().clickOnFormSettingsButton()
					.EnableOrDisableHolidayScripting().clickOnConfirmNotificationPopUp().waitForAccountLoadOrSave()
					.waitForSaveOrPublishNotification();
			scriptPage.clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
					.waitForSaveOrPublishNotification();
		}

	}

	public void addingFieldsIntoNewForm(ScriptPage scriptPage) {
		int group1FieldsSize = scriptPage.clickOnAddNewFormButton().getGroup1FieldsCount();
		int group2FieldsSize = scriptPage.getGroup2FieldsCount();

		for (int i = 1; i < group1FieldsSize; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		for (int i = 1; i < group2FieldsSize; i++) {
			scriptPage.dragAndDropGroup2Field(i);
		}
		scriptPage.clickOnWebsiteField(0).enterUrl().clickOnCustomerStatusField().enterCustomerStatus()
				.selectCustomerStatusExpireDate().clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();

	}

	public void publishNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
	}

	public void launchingCwa(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(2);
	}

	public CwaPage updateAnswerPhraseInCwa(String accountNumber) {
		CwaPage cwaPage = PageFactory.initElements(getDriver(), CwaPage.class);
		cwaPage.waitForCwaLoad().clickOnGoogleSignInInCwa().clickOnExistingEmailId()
				.enterAccountNumberInCwa(accountNumber).clickOnFindAccount().clickOnSearchResult()
				.waitForAccountLoadInCwa().clickOnManageTab().clickOnAccountNumber(accountNumber).waitForScriptLoad()
				.clickOnAnswerPhraseDropDown().clickOnAnswerPhraseOption().waitForScriptSave();
		return cwaPage;
	}

	public void updateFieldScriptsInCwa(CwaPage cwaPage) {
		int size = cwaPage.getListofScriptingFields();
		for (int i = 0; i < size; i++) {
			cwaPage.clickOnEditScriptFieldButton(i + 1).updateFieldScriptInCwa(i + 1);
		}
		cwaPage.clickOncloseAccountSearchButton().clickOnSaveScriptButton().waitForScriptSave();

	}

	public void updateBusinessDetailsAndNoteForAgentInCwa(CwaPage cwaPage) {
		cwaPage.clickOnBusinessDetails().clickOnTimeZoneDropDown().clickOnAnyTimeZone()
				.clickOnShowMoreOrShowLessButton().clickOnAnyBusinessDayCheckBox().clickOnSaveBusinessDay()
				.clickOnAddClosures().clickOnAnyClosuresCheckBox().clickOnSaveClosures().clickOnNoteToAgentButton()
				.enterCustomerStatus().clickOnCustomerStatusExpireDateSelectionButton().selectCustomerStatusExpireDate()
				.clickOnSaveNoteForAgent();
		cwaPage.cwaPageClose();
	}

	public void validatingAnswerPhraseAndScriptingFieldsInFc(ScriptPage scriptPage, CwaPage cwaPage) {
		Assert.assertEquals(cwaPage.getUpdatedFieldScripts(1), scriptPage.updatedScriptField(1));
		getReport().log(Status.PASS, "Scripting Fields Updated Successfully In FC");
		AccountDetailsPage accountDetailsPage = scriptPage.clickOnSettingsButton().clickOnAccountDetailsTab();
		Assert.assertTrue(
				accountDetailsPage.answerPhraseAfterSaving().contains(cwaPage.getUpdatedAnswerPhraseTextInCwa()));
		getReport().log(Status.PASS, "Answer Pharse Updated Successfully In FC");
	}

	public void validateBusinessDetailsAndNoteForAgentDetailsInFc(ScriptPage scriptPage, CwaPage cwaPage) {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = scriptPage.clickOnSettingsButton()
				.clickOnBusinessHoursAndClosuresTab().clickOnShowOrHideBusinessHoursButton();
		Assert.assertTrue(cwaPage.getTimeZone().contains(businessHoursAndClosuresPage.timeZoneAfterSaving()));
		getReport().log(Status.PASS, "Time Zone Updated Successfully In FC");
		Assert.assertEquals(businessHoursAndClosuresPage.getBusinessHours(), cwaPage.getBusinessHours());
		getReport().log(Status.PASS, "Business Hours Updated Successfully In FC");
		businessHoursAndClosuresPage.clickOnAddHolidays();
		Assert.assertEquals(businessHoursAndClosuresPage.getListOfHolidays(), cwaPage.getClosures());
		getReport().log(Status.PASS, "Closures Updated Successfully In FC");
		scriptPage.clickOnScriptButton().clickOnCustomerStatusField();
		Assert.assertEquals(cwaPage.getCustomerStatus(), scriptPage.getCustomerStatusText());
		getReport().log(Status.PASS, "Customer Status Updated Successfully In FC");

	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public SwitchBoardPage validatingCwaScriptChangesInSb(CwaPage cwaPage) {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		answerPhraseInSb = switchBoardPage.waitForSbLoad().getAnswerPhraseInSb();
		Assert.assertTrue(answerPhraseInSb.contains(cwaPage.getUpdatedAnswerPhraseTextInCwa()));
		getReport().log(Status.PASS, "Answer Pharse Updated Successfully In SB");
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		getReport().log(Status.PASS, "Updated Scripting Fields Loaded Successfully In SB");
		switchBoardPage.clickOnHours();
		Assert.assertEquals(switchBoardPage.getBusinessHours(), cwaPage.getBusinessHours());
		getReport().log(Status.PASS, "Business Hours Updated Successfully In SB");
		Assert.assertEquals(switchBoardPage.getClosures(), cwaPage.getClosures());
		getReport().log(Status.PASS, "Closures Updated Successfully In SB");
		return switchBoardPage;
	}

	public void closingSwitchBoard(SwitchBoardPage switchBoardPage) {
		switchBoardPage.switchBoradPageClose();
		getReport().log(Status.PASS, "Account Details Loaded Successfully In SB");
	}

	public void deleteNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Newly Added Script Deleted Succesfully");

	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyCwaUpdates(String accountNumber) {
		report = extent.createTest("Verify CWA Updates");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		deletingExistingHolidayScript(scriptPage);
		addingFieldsIntoNewForm(scriptPage);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		launchingCwa(scriptPage);
		CwaPage cwaPage = updateAnswerPhraseInCwa(accountNumber);
		updateFieldScriptsInCwa(cwaPage);
		updateBusinessDetailsAndNoteForAgentInCwa(cwaPage);
		refetchingScript(scriptPage);
		validatingAnswerPhraseAndScriptingFieldsInFc(scriptPage, cwaPage);
		validateBusinessDetailsAndNoteForAgentDetailsInFc(scriptPage, cwaPage);
		launchingSwitchBoard(scriptPage);
		SwitchBoardPage switchBoardPage = validatingCwaScriptChangesInSb(cwaPage);
		closingSwitchBoard(switchBoardPage);
		deleteNewlyAddedScript(scriptPage);
	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
