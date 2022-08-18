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
public class AddingAndDeletingFieldsTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private static int fieldStatus = 1;

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

	public void addingFieldsIntoNewForm(ScriptPage scriptPage) {
		int group1FieldsSize = scriptPage.clickOnAddNewFormButton().getGroup1FieldsCount();
		int group2FieldsSize = scriptPage.getGroup2FieldsCount();

		for (int i = 1; i < group1FieldsSize; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		for (int i = 1; i < group2FieldsSize; i++) {
			scriptPage.dragAndDropGroup2Field(i);
		}
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
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
		fieldCountAfterSavingForm = scriptPage.waitUntilScriptFieldsLoaded().getFieldCount();
	}

	public void validatingFieldsInFc(ScriptPage scriptPage) {
		try {
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		} catch (Error fieldCount) {
			for (int i = 1; i < 8; i++) {
				refetchingScript(scriptPage);
				if (fieldCountBeforeSavingForm >= fieldCountAfterSavingForm)
					break;
			}
			Assert.assertTrue(fieldCountBeforeSavingForm >= fieldCountAfterSavingForm);
		}
		if (fieldStatus == 1)
			getReport().log(Status.PASS, "Fields Added Successfully In FC");
		else if (fieldStatus == 2)
			getReport().log(Status.PASS, "Fields Deleted Successfully In FC");
		Assert.assertTrue(scriptPage.getFormStatus().equals("Published"));
		getReport().log(Status.PASS, "Newly Added Script Published Successfully In FC");
		fieldStatus++;
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

	public void deleteNewlyAddedFields(ScriptPage scriptPage) {
		int fieldsInScript = scriptPage.getFieldsAddedInScript();
		scriptPage.clickOnMultiSelectButton();
		for (int i = 1; i <= fieldsInScript; i++) {
			scriptPage.clickOnAnyFieldInScript(i);
		}
		fieldCountBeforeSavingForm = scriptPage.clickOnDeleteMultipleFields().clickOnConfirmDeleteFields()
				.getFieldCount();
		scriptPage.clickOnSaveScriptButton().waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void deleteNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Newly Added Script Deleted Succesfully");

	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyAddedAndDeletedFields(String accountNumber) {
		report = extent.createTest("Verify Added And Deleted Fields");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		addingFieldsIntoNewForm(scriptPage);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		validatingFieldsInFc(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingScriptInSb();
		deleteNewlyAddedFields(scriptPage);
		refetchingScript(scriptPage);
		validatingFieldsInFc(scriptPage);
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
