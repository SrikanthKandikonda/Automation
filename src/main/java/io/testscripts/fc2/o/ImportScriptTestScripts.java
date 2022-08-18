package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.BusinessHoursAndClosuresPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

public class ImportScriptTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private static int fieldCountStatus = 1;

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

	public void addingUserTemplateInToForm(ScriptPage scriptPage) {
		scriptPage.clickOnAddNewFormButton().dragAndDropUserTemplates(1).waitForAccountLoadOrSave();
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	public void addingImportScripting(ScriptPage scriptPage, String accountNumber) {
		int fieldCount = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
		if (fieldCount == 0 && fieldCountStatus < 6) {
			refetchingScript(scriptPage);
			fieldCount = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
		}
		scriptPage.clickOnImportScriptDropDown().clickOnImportScriptButton()
				.enterAccountNumberForImportScripting(accountNumber).selectFormForImportScripting(1)
				.dragAndDropImportScript(3).waitForAccountLoadOrSave();
		scriptPage.clickOnDeliveryGroupButton().clickOnAddDeliveryGroupButton().confirmAddDeliveryGroupNotification();
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnSaveScriptButton().waitForAccountLoadOrSave();
		scriptPage.clickOnDeliveryGroupButton();

	}

	public void publishNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();

	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		fieldCountAfterSavingForm = scriptPage.waitUntilScriptFieldsLoaded().getFieldCount();

	}

	public void validatingFieldsInFc(ScriptPage scriptPage) {
		try {
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		} catch (Error fieldCount) {
			for (int i = 1; i < 5; i++) {
				refetchingScript(scriptPage);
				if (fieldCountBeforeSavingForm == fieldCountAfterSavingForm)
					break;
			}
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		}
		getReport().log(Status.PASS, "Import Scripting Added Successfully In FC");
		Assert.assertTrue(scriptPage.getFormStatus().equals("Published"));
		getReport().log(Status.PASS, "Newly Added Script Published Successfully In FC");
	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingScriptInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Newly Added Script Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();

	}

	public void deleteNewlyAddedImportScriptings(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();

	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyImportScriptingTemplate(String accountNumber) {
		report = extent.createTest("Verify Import Scripting Templates");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		addingUserTemplateInToForm(scriptPage);
		refetchingScript(scriptPage);
		addingImportScripting(scriptPage, accountNumber);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		validatingFieldsInFc(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingScriptInSb();
		deleteNewlyAddedImportScriptings(scriptPage);
	}

	@AfterMethod
	public void reload() {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		businessHoursAndClosuresPage.clickOnReload();
	}

}
