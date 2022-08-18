package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class CutAndPasteFieldsTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private boolean superAccountStatus;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		superAccountStatus = scriptPage.isLockedAlertDisplayed();
		if (superAccountStatus)
			scriptPage.confirmMessageAnnotationAlertNotification().clickOnSettingsButton().clickOnAccountDetailsTab()
					.clickOnSuperAcountToggleButton();
		return scriptPage;
	}

	public void deletingUnpublishedForms(ScriptPage scriptPage) {
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
		scriptPage.dragAndDropUserTemplate(1).waitForAccountLoadOrSave();
		scriptPage.clickOnSaveScriptButton().waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void cutAndPasteFieldsIntoNewForm(ScriptPage scriptPage) {
		int fieldsInScript = scriptPage.getFieldsAddedInScript();
		scriptPage.clickOnMultiSelectButton();
		for (int i = 1; i <= fieldsInScript; i++) {
			scriptPage.clickOnAnyFieldInScript(i);
		}
		scriptPage.clickOnCutMultipleFields().waitForSaveOrPublishNotification();
		scriptPage.clickOnAddNewFormButton().clickOnPasteMultipleFields().waitForSaveOrPublishNotification();
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
		scriptPage.clickOnFormbutton(1).clickOnSaveScriptButton().waitForAccountLoadOrSave();
	}

	public void cutAndPasteDecisionTreeIntoNewForm(ScriptPage scriptPage) {
		scriptPage.clickOnDecisionTree().clickOnCutDecisionTree().waitForSaveOrPublishNotification();
		scriptPage.clickOnFormbutton(2).clickOnPasteMultipleFields().waitForSaveOrPublishNotification();
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
		scriptPage.clickOnFormbutton(1).clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().waitForSaveOrPublishNotification();
	}

	public void publishNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().waitForSaveOrPublishNotification();
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		fieldCountAfterSavingForm = scriptPage.waitUntilScriptFieldsLoaded().getFieldCount();
	}

	public void validatingEditedFieldsInFc(ScriptPage scriptPage) {
		for (int i = 1; i < 6; i++) {
			if (fieldCountBeforeSavingForm == fieldCountAfterSavingForm)
				break;
			else
				refetchingScript(scriptPage);
		}
		Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		getReport().log(Status.PASS, "Cut And Paste Of Fields Are Successful In FC");
	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingScriptInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Edited Fields Loaded Sucessfully In Sb");
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
	public void verifyFieldsEdit(String accountNumber) {
		report = extent.createTest("Verify Cut And Paste Fields");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		deletingExistingHolidayScript(scriptPage);
		addingFieldsIntoNewForm(scriptPage);
		cutAndPasteFieldsIntoNewForm(scriptPage);
		cutAndPasteDecisionTreeIntoNewForm(scriptPage);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		validatingEditedFieldsInFc(scriptPage);
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
