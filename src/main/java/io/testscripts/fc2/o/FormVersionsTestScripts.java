package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.FormVersionsPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

public class FormVersionsTestScripts extends LaunchBrowser {

	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private static boolean formStatus;
	private static String actualFormName;
	private boolean spanishScriptNotificaiton;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isSpanishScriptingNotificationDisplayed();
		if (spanishScriptNotificaiton) {
			scriptPage.confirmOnBilingiualScriptNotification();
		}
		return scriptPage;
	}

	public ScriptPage deletingUnpublishedForms(ScriptPage scriptPage) {
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

		return scriptPage;
	}

	public ScriptPage addingFieldsIntoNewForm(ScriptPage scriptPage) {
		int size = scriptPage.clickOnAddNewFormButton().getGroup1FieldsCount();
		for (int i = 1; i < size; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnFormName().clickOnSettingsViewButton().clickOnFormSettingsButton().enterNewFormName();
		scriptPage.clickOnWebsiteField(0).enterUrl().clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();

		return scriptPage;
	}

	public ScriptPage refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		return scriptPage;
	}

	public FormVersionsPage openFormVersions() {
		FormVersionsPage formVersions = PageFactory.initElements(getDriver(), FormVersionsPage.class);
		formVersions.clickViewInDropDown().clickFormVersions();
		formVersions.switchToFormVersionsTab();
		return formVersions;
	}

	public void viewNewlyAddedScriptAndEditFormName(FormVersionsPage formVersions) {
		formVersions.clickViewScriptButton(1).editFormName();

	}

	public void checkOtherOperations(FormVersionsPage formVersions) {
		formVersions.navigateToNextPage().navigateToPreviousPage().clickViewDropDown().clickstarredOptionFromDropDown()
				.clickViewDropDown().clickAllOptionsFromDropDown();

	}

	public void starAForm(FormVersionsPage formVersions) {
		formStatus = formVersions.clickViewDropDown().clickstarredOptionFromDropDown().unstarAllForms()
				.clickViewDropDown().clickAllOptionsFromDropDown().clickOnStarIcon(2).clickViewDropDown()
				.clickstarredOptionFromDropDown().isStarredFormListed();
		formVersions.clickViewDropDown().clickAllOptionsFromDropDown();
	}

	public void revertFormFromFormVersions(ScriptPage scriptPage, FormVersionsPage formVersions) {
		actualFormName = formVersions.revertAForm().getFormName(1);
		formVersions.closeFormVersions();
		deletingUnpublishedForms(scriptPage);
	}

	public void waitUntilFormReverted(ScriptPage scriptPage) {
		for (int i = 1; i < 5; i++) {
			int numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();
			if (numberOfUnblishedForms == 0) {
				refetchingScript(scriptPage);
			} else
				break;
		}
		for (int i = 1; i < 5; i++) {
			fieldCountAfterSavingForm = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
			if (fieldCountAfterSavingForm == 0) {
				refetchingScript(scriptPage);
			} else
				break;
		}

	}

	public void validatingRevertedFormAndFormStatus(ScriptPage scriptPage, FormVersionsPage formVersions) {
		Assert.assertTrue(formStatus);
		getReport().log(Status.PASS, "Form Starred Successfully In Form Versions");
		for (int i = 1; i < 6; i++) {
			if (fieldCountBeforeSavingForm == fieldCountAfterSavingForm)
				break;
			else {
				refetchingScript(scriptPage);
				fieldCountAfterSavingForm = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded()
						.getFieldCount();
			}
		}
		Assert.assertEquals(actualFormName, formVersions.EditedFormName());
		getReport().log(Status.PASS, "Form Name Saved Successfully");
		Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		getReport().log(Status.PASS, "Form Reverted Successfuly In FC");

	}

	public ScriptPage publishRevertedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();

		return scriptPage;
	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		Assert.assertTrue(scriptPage.getFormStatus().equals("Published"));
		getReport().log(Status.PASS, "Reverted Script Published Successfully In FC");
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingScriptInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Reverted Script Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();

	}

	public void deleteRevertedScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Reverted Script Deleted Succesfully");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyFormVersionsPage(String accountNumber) {
		report = extent.createTest("Verify Form Versions");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		addingFieldsIntoNewForm(scriptPage);
		FormVersionsPage formVersions = openFormVersions();
		viewNewlyAddedScriptAndEditFormName(formVersions);
		checkOtherOperations(formVersions);
		starAForm(formVersions);
		revertFormFromFormVersions(scriptPage, formVersions);
		refetchingScript(scriptPage);
		waitUntilFormReverted(scriptPage);
		validatingRevertedFormAndFormStatus(scriptPage, formVersions);
		publishRevertedScript(scriptPage);
		refetchingScript(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingScriptInSb();
		deleteRevertedScript(scriptPage);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
