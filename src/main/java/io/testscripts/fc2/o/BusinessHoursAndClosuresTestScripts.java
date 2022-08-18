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
public class BusinessHoursAndClosuresTestScripts extends LaunchBrowser {
	public static ExtentTest report;
	private String businessHoursInFc;
	private String customHolidayInSb;
	private String businessHoursInSb;
	private boolean appointmentSelected;

	public BusinessHoursAndClosuresPage selectingTimeZone(String accountNumber) {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		businessHoursAndClosuresPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton()
				.waitForAccountLoadOrSave();
		if (businessHoursAndClosuresPage.isSpanishScriptingNotificationDisplayed()) {
			businessHoursAndClosuresPage.confirmOnBilingiualScriptNotification();
			scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
					.waitForScriptLoad().waitForSaveOrPublishNotification();
		}
		return businessHoursAndClosuresPage;
	}

	public void selectingBusinessHours(BusinessHoursAndClosuresPage businessHoursAndClosuresPage) {
		businessHoursAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab()
				.clickOnTimeZoneDropDown().selectTimezone();
		businessHoursAndClosuresPage.clickOnBusinessHoursDropDown().selectBusinessHoursType(1)
				.clickOnBusinessHoursDropDown().selectBusinessHoursType(2).selectOrDeselectBusinessDays();
		businessHoursAndClosuresPage.clickOnSelectedBusinessDayFromTimingOrToTiming(0)
				.enterSelectedBusinessDayFromHourOrToHourTiming(0).selectEnabledBusinessDayAmOrPm(0);
		businessHoursAndClosuresPage.clickOnSelectedBusinessDayFromTimingOrToTiming(1)
				.enterSelectedBusinessDayFromHourOrToHourTiming(1).selectEnabledBusinessDayAmOrPm(1)
				.selectAppointmentForSelectedBusinessDay().clickOnAddBusinesHoursNotes().enterBusinessHoursNotes();
	}

	public void selectingClosures(BusinessHoursAndClosuresPage businessHoursAndClosuresPage) {
		businessHoursAndClosuresPage.selectOpenOnAllHolidays().selectConfigureHolidays()
				.enableOrDisableObservedHoliday().clickOnAddHolidays().deleteExistingHolidays()
				.clickOnSaveBusinesshoursAndClosuresButton().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
		businessHoursAndClosuresPage.clickOnAddHolidays().selectAllOrDeselectAllHolidays()
				.selectAllOrDeselectAllHolidays().clickOnAddCustomDay().enterCustomHolidayTitle()
				.selectCustomHolidayFromDate().selectCustomHolidayToDate().enterCustomHolidayNotes()
				.selectOrDeselectRepeatEveryYear().clickOnAddCustomHoliday().clickOnSaveBusinesshoursAndClosuresButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void refechingBusinessHoursAndClosuers(BusinessHoursAndClosuresPage businessHoursAndClosuresPage) {
		businessHoursAndClosuresPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave()
				.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab();
	}

	public void validatingBusinessHoursInFc(BusinessHoursAndClosuresPage businessHoursAndClousers) {
		businessHoursAndClousers.clickOnShowOrHideBusinessHoursButton();
		businessHoursInFc = businessHoursAndClousers.getBusinessHoursInFc();
		Assert.assertEquals(businessHoursAndClousers.timeZoneBeforeSaving(),
				businessHoursAndClousers.timeZoneAfterSaving());
		getReport().log(Status.PASS, "TimeZone Saved Successfully In FC");
		Assert.assertEquals(businessHoursAndClousers.businessHoursTimingsBeforeSaving(0),
				businessHoursAndClousers.businessHoursTimingsAfterSaving(0));
		Assert.assertEquals(businessHoursAndClousers.businessHoursTimingsBeforeSaving(1),
				businessHoursAndClousers.businessHoursTimingsAfterSaving(1));
		getReport().log(Status.PASS, " Business Hours Saved Successfully In FC");
		Assert.assertTrue(businessHoursAndClousers.isAppointmentSelectedInFc());
		getReport().log(Status.PASS, " Appointment For Business Day Saved Successfully In FC");
		Assert.assertEquals(businessHoursAndClousers.businessHoursNotesBeforeSaving(),
				businessHoursAndClousers.businessHoursNotesAfterSaving());
		getReport().log(Status.PASS, "Business Hour Notes Saved Successfully In FC");

	}

	public void validatingClosuresInFc(BusinessHoursAndClosuresPage businessHoursAndClousers) {
		businessHoursAndClousers.clickOnAddHolidays();
		Assert.assertEquals(businessHoursAndClousers.CustomHolidayTitleBeforeSaving(),
				businessHoursAndClousers.customHolidayTitleAfterSaving());
		getReport().log(Status.PASS, "Custom Holiday Saved Successfully In FC");

	}

	public void launchingSwitchBoard(BusinessHoursAndClosuresPage businessHoursAndClousers) {
		businessHoursAndClousers.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public SwitchBoardPage gettingBusinessHoursAndClosuresFromSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		businessHoursInSb = switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().clickOnHours()
				.getBusinessHoursInSb();
		customHolidayInSb = switchBoardPage.getCustomHolidayInSb();
		appointmentSelected = switchBoardPage.isAppointmentSelectedInSb();
		return switchBoardPage;
	}

	public void validatingBusinessHoursAndClosuresInSb(SwitchBoardPage switchBoardPage,
			BusinessHoursAndClosuresPage businessHoursAndClousers) {
		Assert.assertEquals(businessHoursInFc, businessHoursInSb);
		getReport().log(Status.PASS, "Business Hours Saved Successfully In SB");
		Assert.assertEquals(customHolidayInSb, businessHoursAndClousers.CustomHolidayTitleBeforeSaving());
		getReport().log(Status.PASS, "Clousers Saved Successfully In SB");
		Assert.assertTrue(appointmentSelected);
		getReport().log(Status.PASS, "Appointment For Business Day Loaded Successfully In SB");
		getReport().log(Status.PASS, "Business Hours And Closures Loaded Sucessfully In SB");
		switchBoardPage.switchBoradPageClose();
	}

	public void deletedNewlyAddedCustomHoliday(BusinessHoursAndClosuresPage businessHoursAndClousers) {
		businessHoursAndClousers.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab().selectConfigureHolidays()
				.deleteCustomHoliday().clickOnSaveBusinesshoursAndClosuresButton().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	public void validatingDeletedCustomHoliday(BusinessHoursAndClosuresPage businessHoursAndClousers) {
		boolean customHolidayStatus = businessHoursAndClousers.clickOnDeletedCustomHolidayOrExpriedHoliday()
				.isCustomHolidayDeleted();
		businessHoursAndClousers.clickOnCloseDeletedCustomHolidayOrExpriedHoliday().clickOnAddHolidays();
		Assert.assertTrue(customHolidayStatus);
		getReport().log(Status.PASS, "Custom Holiday Deleted Successfully");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyBusinessHoursAndClosures(String accountNumber) {
		report = extent.createTest("Verify BusinessHours And Closures");
		setReport(report);
		BusinessHoursAndClosuresPage businessHoursAndClousers = selectingTimeZone(accountNumber);
		selectingBusinessHours(businessHoursAndClousers);
		selectingClosures(businessHoursAndClousers);
		refechingBusinessHoursAndClosuers(businessHoursAndClousers);
		validatingBusinessHoursInFc(businessHoursAndClousers);
		validatingClosuresInFc(businessHoursAndClousers);
		launchingSwitchBoard(businessHoursAndClousers);
		SwitchBoardPage switchBoardPage = gettingBusinessHoursAndClosuresFromSb();
		validatingBusinessHoursAndClosuresInSb(switchBoardPage, businessHoursAndClousers);
		deletedNewlyAddedCustomHoliday(businessHoursAndClousers);
		refechingBusinessHoursAndClosuers(businessHoursAndClousers);
		validatingDeletedCustomHoliday(businessHoursAndClousers);
	}

	@AfterMethod
	public void reload() {
		BusinessHoursAndClosuresPage businessHoursAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		businessHoursAndClosuresPage.clickOnReload();
	}

}
