package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.FileLookupPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.testlibrary.fc2.o.LaunchBrowser;

public class FileLookupTestScripts extends LaunchBrowser {

	private static ExtentTest report;
	private boolean spanishScriptNotificaiton;
	public static String addedAsset;
	public static String uploadedAsset;

	public ScriptPage findingAccount(String accountNumber) {

		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isSpanishScriptingNotificationDisplayed();
		if (spanishScriptNotificaiton) {
			scriptPage.confirmOnBilingiualScriptNotification();
		}
		// lockAccountNotification = scriptPage.isLockedAlertDisplayed();
		// if(lockAccountNotification) {
		// scriptPage.confirm
		// }

		getReport().log(Status.PASS, "Logged In into FC");
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

		getReport().log(Status.PASS, "Unpublished forms are deleted from the account");
		return scriptPage;
	}

	public ScriptPage addingFieldsIntoNewForm(ScriptPage scriptPage) {
		int size = scriptPage.clickOnAddNewFormButton().getGroup1FieldsCount();
		for (int i = 1; i < size; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		scriptPage.clickOnFormName().clickOnFormSettingsButton().enterNewFormName();
		scriptPage.clickOnWebsiteField(0).enterUrl().clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();

		getReport().log(Status.PASS, "New form for Lookup is added");
		return scriptPage;

	}

	public FileLookupPage publishNewlyAddedScript(ScriptPage scriptPage) {
		FileLookupPage lookup = PageFactory.initElements(getDriver(), FileLookupPage.class);
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();
		lookup.clickFetchButton();
		scriptPage.clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isSpanishScriptingNotificationDisplayed();
		if (spanishScriptNotificaiton) {
			scriptPage.confirmOnBilingiualScriptNotification();
		}
		deletingUnpublishedForms(scriptPage);

		getReport().log(Status.PASS, "Newly Added form is published");
		return lookup;
	}

	public FileLookupPage switchToLookUpTab() {
		FileLookupPage lookup = PageFactory.initElements(getDriver(), FileLookupPage.class);
		lookup.clickViewInDropDown().clickFileLookupDropDown().switchToFileLookupTab();
		getReport().log(Status.PASS, "Switched to Lookup Tab");
		return lookup;
	}

	public void deleteExistingLookup(FileLookupPage lookup) {
		lookup.deleteExistingLookup();
		getReport().log(Status.PASS, "Existing lookup configuration is deleted");
	}

	public void configureNewLookup(FileLookupPage lookup, ScriptPage scriptPage) {
		// upload local CSV file
		lookup.enterLookupName().clickCreateNewLookupButton().uploadFirstLookupCSV();

		// configure lookup
		lookup.selectLookupField().selectLookupOperator().selectLookupFile().selectLookupColumn()
				.selectResultsToDisplay().saveLookupChanges().switchToFcTab();
		getReport().log(Status.PASS, "CSV file is uploaded and new lookup is configured");
	}

	public void verifyLookupConfigInFC(ScriptPage scriptPage, FileLookupPage lookup) {

		// Re fetch form to verify if lookup enabled for Postal code field
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isSpanishScriptingNotificationDisplayed();
		if (spanishScriptNotificaiton) {
			scriptPage.confirmOnBilingiualScriptNotification();
		}

		lookup.verifylookupConfigurationInFC();
		getReport().log(Status.PASS, "Configured lookup is verified in FC");
	}

	public void VerifyLookupConfigInSB(FileLookupPage lookup) {
		// verify lookup in SB
		lookup.clickViewInDropDown().clickSBDropDown().switchToSb().verifyLookUpInSb().closeSbPage();
		getReport().log(Status.PASS, "Configured lookup is verified in SB");
	}

	public void joinFranchiseAndUploadGlobalFile(FileLookupPage lookup) {
		// join franchise
		lookup.clickCreateFranchiseButton().clickJoinFranchiseButton().enterFranchiseName().clickJoinFranchise();
		// join franchise and upload a global file
		lookup.uploadGlobalCSVFile().verifyIfGlobalFileUploaded();
		// configure filter for global file verify in SB
		getReport().log(Status.PASS, "Joined a Franchise and global field is uploaded");
	}

	public void configureOrConditionOperationAndVerifyInSB(FileLookupPage lookup) {
		// configure with OR operation and verify in SB
		lookup.clickORCondition().orConditionSelectField().orConditionSelectOperator().orConditionSelectFile()
				.orConditionSelectColumn().orConditionSelectResult().saveLookupChanges().switchToFcTab()
				.clickViewInDropDown().clickSBDropDown().switchToSb().verifyOrConditionLookUpInSb().closeSbPage();
		getReport().log(Status.PASS, "OR condition is configured with global file and verified in SB");

	}

	public void configureAndConditionOperationAndVerifyInSB(FileLookupPage lookup) {
		// configure another filter with AND operation
		lookup.deleteConditionFilter().saveLookupChanges().clickANDCondition().orConditionSelectField()
				.orConditionSelectOperator().orConditionSelectFile().orConditionSelectColumn().orConditionSelectResult()
				.saveLookupChanges();
		getReport().log(Status.PASS, "AND condition is configured");
		// verify in SB
		// .switchToFcTab().clickViewInDropDown().clickSBDropDown().switchToSb().verifyOrConditionLookUpInSb().closeSbPage();
	}

	public void removeConfiguredFranchise(FileLookupPage lookup) {
		lookup.deleteConditionFilter().saveLookupChanges().removeGlobalCSVFile();
		getReport().log(Status.PASS, "Condition filters removed and global file is deleteds");
	}

	public void deleteConfiguredLookupAndVerifyInSB(FileLookupPage lookup) {
		// delete lookup and verify in SB
		lookup.removeFranchise().saveLookupChanges();
		// remove lookup
		deleteExistingLookup(lookup);
		getReport().log(Status.PASS, "Configured franchise is removed and verified in SB");
	}

	public void backToFC(FileLookupPage lookup, ScriptPage scriptPage) {
		lookup.closeFileLookupPage().clickViewInDropDown().clickSBDropDown().switchToSbFinal()
				.verifyNoLookupIsConfigured().closeSbPageFinalCheck();
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isSpanishScriptingNotificationDisplayed();
		if (spanishScriptNotificaiton) {
			scriptPage.confirmOnBilingiualScriptNotification();
		}
		lookup.verifylookupNotConfiguredInFC();
		getReport().log(Status.PASS, "Lookup configuration completely removed and verified in FC and SB");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyFormVersionsPage(String accountNumber) {
		report = extent.createTest("Verify Form Versions");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		addingFieldsIntoNewForm(scriptPage);
		publishNewlyAddedScript(scriptPage);
		FileLookupPage lookup = switchToLookUpTab();
		deleteExistingLookup(lookup);
		configureNewLookup(lookup, scriptPage);
		verifyLookupConfigInFC(scriptPage, lookup);
		VerifyLookupConfigInSB(lookup);
		joinFranchiseAndUploadGlobalFile(lookup);
		configureOrConditionOperationAndVerifyInSB(lookup);
		configureAndConditionOperationAndVerifyInSB(lookup);
		removeConfiguredFranchise(lookup);
		deleteConfiguredLookupAndVerifyInSB(lookup);
		backToFC(lookup, scriptPage);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
