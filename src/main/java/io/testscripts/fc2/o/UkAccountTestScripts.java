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
public class UkAccountTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private String customHolidayInSb;

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
		scriptPage.clickOnAddNewFormButton().dragAndDropGroup1Field(15).dragAndDropGroup1Field(18)
				.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	public void publishNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();
	}

	public void savingDayGreetings(ScriptPage scriptPage) {
		scriptPage.clickOnSettingsButton().clickOnAccountDetailsTab().clickOnDayGreetingsToggleButton();

	}

	public BusinessHoursAndClosuresPage selectingClosures(ScriptPage scriptPage) {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = scriptPage.clickOnSettingsButton()
				.clickOnBusinessHoursAndClosuresTab();
		businessHoursAndClosuresPage.selectOpenOnAllHolidays().selectConfigureHolidays()
				.enableOrDisableObservedHoliday().clickOnAddHolidays().deleteExistingHolidays()
				.clickOnSaveBusinesshoursAndClosuresButton().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
		businessHoursAndClosuresPage.clickOnAddHolidays().selectAllOrDeselectAllHolidays()
				.selectAllOrDeselectAllHolidays().clickOnAddCustomDay().enterCustomHolidayTitle()
				.selectCustomHolidayFromDate().selectCustomHolidayToDate().enterCustomHolidayNotes()
				.selectOrDeselectRepeatEveryYear().clickOnAddCustomHoliday().clickOnSaveBusinesshoursAndClosuresButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		return businessHoursAndClosuresPage;
	}

	public void refetchingAccount(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
	}

	public void verifyUkAccountUpdatesInFC(ScriptPage scriptPage) {
		int dropDownOptions = scriptPage.clickOnAnyFieldInScript(1).clickOnDateDropdown().getDateDrownOptions();
		String zipCode = scriptPage.clickOnAnyFieldInScript(2).getAnyFieldLableInScript(2);
		Assert.assertTrue(dropDownOptions == 2);
		getReport().log(Status.PASS, "Date Field Options Loaded Successfully In FC");
		Assert.assertTrue(zipCode.equals("Postal Code"));
		getReport().log(Status.PASS, "Zipcode Field Loaded Successfully In FC");
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = scriptPage.clickOnSettingsButton()
				.clickOnBusinessHoursAndClosuresTab().clickOnAddHolidays();
		Assert.assertEquals(businessHoursAndClosuresPage.CustomHolidayTitleBeforeSaving(),
				businessHoursAndClosuresPage.customHolidayTitleAfterSaving());
		getReport().log(Status.PASS, "Custom Holiday Saved Successfully In FC");

	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void verifyUkAccountUpdatesInSb(BusinessHoursAndClosuresPage businessHoursAndClosuresPage) {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Newly Added Script Loaded Sucessfully In Sb");
		Assert.assertTrue(switchBoardPage.getFieldNameInSb(2).equals("Postal Code"));
		getReport().log(Status.PASS, "Newly Added Zipcode Loaded Sucessfully In Sb");
		customHolidayInSb = switchBoardPage.clickOnHours().getCustomHolidayInSb();
		Assert.assertEquals(customHolidayInSb, businessHoursAndClosuresPage.CustomHolidayTitleBeforeSaving());
		switchBoardPage.switchBoradPageClose();
	}

	public void deletedNewlyAddedCustomHolidayAndNewlyAddedForm(ScriptPage scriptPage,
			BusinessHoursAndClosuresPage businessHoursAndClosuresPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Newly Added Script Deleted Succesfully");
		businessHoursAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab()
				.selectConfigureHolidays().deleteCustomHoliday().clickOnSaveBusinesshoursAndClosuresButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	@Parameters({ "UkAccountNumber" })
	@Test
	public void verifyUKAcccount(String accountNumber) {
		report = extent.createTest("Verify Uk Acccount");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		addingFieldsIntoNewForm(scriptPage);
		publishNewlyAddedScript(scriptPage);
		savingDayGreetings(scriptPage);
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = selectingClosures(scriptPage);
		refetchingAccount(scriptPage);
		verifyUkAccountUpdatesInFC(scriptPage);
		launchingSwitchBoard(scriptPage);
		verifyUkAccountUpdatesInSb(businessHoursAndClosuresPage);
		deletedNewlyAddedCustomHolidayAndNewlyAddedForm(scriptPage, businessHoursAndClosuresPage);
	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
