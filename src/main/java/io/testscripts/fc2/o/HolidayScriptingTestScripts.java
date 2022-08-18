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

public class HolidayScriptingTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private static String HolidayInSb;
	private static String companyWorkingStatus;
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

	public void configureHolidayScript(ScriptPage scriptPage) {
		int size = scriptPage.clickOnAddNewFormButton().getGroup1FieldsCount();
		for (int i = 1; i < size; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnWebsiteField(0).enterUrl().clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		scriptPage.clickOnFormSettingsButton().EnableOrDisableHolidayScripting().clickOnConfirmNotificationPopUp()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public BusinessHoursAndClosuresPage configureHoliday() {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		businessHoursAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab()
				.selectConfigureHolidays().clickOnAddHolidays().clickOnAddCustomDay().enterCustomHolidayTitle()
				.clickOnCustomHolidayFormDate().clickOnCustomHolidayToDate().enterCustomHolidayNotes()
				.clickOnAddCustomHoliday().clickOnSaveBusinesshoursAndClosuresButton().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
		return businessHoursAndClosuresPage;
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
	}

	public void validatingHolidayScriptingInFc(ScriptPage scriptPage,
			BusinessHoursAndClosuresPage businessHoursAndClosuresPage) {
		fieldCountAfterSavingForm = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
		if (fieldCountAfterSavingForm == 0)
			for (int i = 1; i < 5; i++) {
				refetchingScript(scriptPage);
				fieldCountAfterSavingForm = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded()
						.getFieldCount();
				if (fieldCountBeforeSavingForm >= fieldCountAfterSavingForm)
					break;
			}
		Assert.assertTrue(fieldCountBeforeSavingForm >= fieldCountAfterSavingForm);
		getReport().log(Status.PASS, "Fields Added Successfully For Holiday Script In FC");
		Assert.assertTrue(scriptPage.isHolidayScriptingEnabled());
		getReport().log(Status.PASS, "Holiday Script Added Successfully In FC");
		businessHoursAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab().clickOnAddHolidays();
		Assert.assertEquals(businessHoursAndClosuresPage.CustomHolidayTitleBeforeSaving(),
				businessHoursAndClosuresPage.customHolidayTitleAfterSaving());
		getReport().log(Status.PASS, "Holiday Saved Successfully In FC");

	}

	public void launchingSwitchBoard(BusinessHoursAndClosuresPage businessHoursAndClousers) {
		businessHoursAndClousers.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public SwitchBoardPage gettingHolidayScriptDetailsInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		HolidayInSb = switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded()
				.clickOnHours().getCustomHolidayInSb();
		companyWorkingStatus = switchBoardPage.getCompanyWorkingStatus();
		return switchBoardPage;
	}

	public void validatingHolidayScriptInSb(SwitchBoardPage switchBoardPage,
			BusinessHoursAndClosuresPage businessHoursPage) {
		Assert.assertEquals(HolidayInSb, businessHoursPage.CustomHolidayTitleBeforeSaving());
		getReport().log(Status.PASS, "Holiday Loaded Successfully In SB");
		Assert.assertTrue(companyWorkingStatus.equals("CLOSED"));
		getReport().log(Status.PASS, "Holiday Status Loaded Successfully In SB");
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Holiday Script Loaded Successfully In SB");
		switchBoardPage.switchBoradPageClose();
	}

	public void deletingHolidayScripting(ScriptPage scriptPage, BusinessHoursAndClosuresPage businessHoursAndClousers) {
		scriptPage.clickOnSettingsViewButton().clickOnFormSettingsButton().EnableOrDisableHolidayScripting()
				.clickOnConfirmNotificationPopUp().waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		scriptPage.clickOnDeleteButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Holiday Script Deleted Succesfully");
		businessHoursAndClousers.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab().selectConfigureHolidays()
				.deleteCustomHoliday().clickOnSaveBusinesshoursAndClosuresButton().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyHolidayScripting(String accountNumber) {
		report = extent.createTest("Verify Holiday Scripting");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		deletingExistingHolidayScript(scriptPage);
		configureHolidayScript(scriptPage);
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = configureHoliday();
		refetchingScript(scriptPage);
		validatingHolidayScriptingInFc(scriptPage, businessHoursAndClosuresPage);
		launchingSwitchBoard(businessHoursAndClosuresPage);
		SwitchBoardPage switchBoardPage = gettingHolidayScriptDetailsInSb();
		validatingHolidayScriptInSb(switchBoardPage, businessHoursAndClosuresPage);
		deletingHolidayScripting(scriptPage, businessHoursAndClosuresPage);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
