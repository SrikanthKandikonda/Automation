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
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class FindByUniquePinTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private static int fieldStatus = 1;

	public ScriptPage findingAccountByUniquePin(String uniquePin) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().clickOnUniquePinFind().enterUniquePin(uniquePin).clickOnLoadButton()
				.waitForAccountLoadOrSave();
		return scriptPage;
	}

	public void deletingFormsInUniquePinAccount(ScriptPage scriptPage) {
		int listOfForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfForms();
		for (int i = 1; i < listOfForms; i++) {
			scriptPage.clickOnFormbutton(1).clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton()
					.waitForScriptLoad().waitForSaveOrPublishNotification();
		}
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		fieldCountAfterSavingForm = scriptPage.clickOnFormName().waitUntilScriptFieldsLoaded().getFieldCount();
	}

	public void addingFieldsIntoUniquePinAccount(ScriptPage scriptPage) {
		int size = scriptPage.clickOnAddNewFormButton().clickOnSettingsViewButton().clickOnFormSettingsButton()
				.enterNewFormName().clickOnSettingsViewButton().getGroup1FieldsCount();
		for (int i = 1; i < size; i++) {
			if (i != 4 && i != 21 && i != 23 && i != 24 && i != 25)
				scriptPage.dragAndDropGroup1Field(i);
		}
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnWebsiteField(0).enterUrl().clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void validatingFieldsForUniquePinAccount(ScriptPage scriptPage) {
		try {
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		} catch (Error fieldCount) {
			for (int i = 1; i < 4; i++) {
				refetchingScript(scriptPage);
				if (fieldCountBeforeSavingForm == fieldCountAfterSavingForm)
					break;
			}
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		}
		if (fieldStatus == 1)
			getReport().log(Status.PASS, "Fields Added Successfully In Unique Pin Account");

		else if (fieldStatus == 2)
			getReport().log(Status.PASS, "Fields Deleted Successfully In Unique Pin Account");
		fieldStatus++;
	}

	public void deleteNewlyAddedFieldsInUniquePinAccount(ScriptPage scriptPage) {
		int fieldsInScript = scriptPage.getFieldsAddedInScript();
		scriptPage.clickOnMultiSelectButton();
		for (int i = 1; i <= fieldsInScript - 2; i++) {
			scriptPage.clickOnAnyFieldInScript(i);
		}
		fieldCountBeforeSavingForm = scriptPage.clickOnDeleteMultipleFields().clickOnConfirmDeleteFields()
				.getFieldCount();
		scriptPage.clickOnSaveScriptButton().waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void deleteNewlyAddedScriptInUniquePin(ScriptPage scriptPage) {
		scriptPage.clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Newly Added Script Deleted Succesfully In Unique Pin Account");

	}

	@Parameters({ "UniquePin" })
	@Test
	public void verifyFindByUniquePin(String uniquePin) {
		report = extent.createTest("Verify Find By Unique Pin");
		setReport(report);
		ScriptPage scriptPage = findingAccountByUniquePin(uniquePin);
		deletingFormsInUniquePinAccount(scriptPage);
		addingFieldsIntoUniquePinAccount(scriptPage);
		refetchingScript(scriptPage);
		validatingFieldsForUniquePinAccount(scriptPage);
		deleteNewlyAddedFieldsInUniquePinAccount(scriptPage);
		refetchingScript(scriptPage);
		validatingFieldsForUniquePinAccount(scriptPage);
		deleteNewlyAddedScriptInUniquePin(scriptPage);
	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
