package io.testscripts.fc2.o;

import java.util.ArrayList;
import java.util.List;

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
public class SpanishScriptingTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static int fieldCountBeforeSavingForm;
	private static int fieldCountAfterSavingForm;
	private static boolean spanishScriptingStatus;
	private static List<String> englishScriptingFieldsInSb = new ArrayList<String>();
	private static List<String> spanishScriptingFieldsInSb = new ArrayList<String>();
	private int count = 1;
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

	public void configureSpanishScript(ScriptPage scriptPage) {
		scriptPage.clickOnAddNewFormButton();
		for (int i = 1; i < 4; i++) {
			scriptPage.dragAndDropGroup1Field(i);
		}
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnSettingsViewButton().clickOnFormSettingsButton().EnableOrDisableSpanishScripting();
		for (int i = 1; i < 4; i++) {
			scriptPage.clickOnAnyFieldInScript(i).enterFieldScriptEnglish().enterFieldScriptSpanish();
		}
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
	}

	public void validatingSpanishScriptingInFc(ScriptPage scriptPage) {
		fieldCountAfterSavingForm = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
		while (fieldCountAfterSavingForm == 0 && count < 10) {
			refetchingScript(scriptPage);
			fieldCountAfterSavingForm = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
			count++;
		}
		Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		getReport().log(Status.PASS, "Fields Added Successfully For Spanish Script In FC");
		for (int i = 1; i < 4; i++) {
			scriptPage.clickOnAnyFieldInScript(i);
			Assert.assertEquals(scriptPage.englishFieldScriptBeforeSaving(i),
					scriptPage.englishFieldScriptAfterSaving());
			Assert.assertEquals(scriptPage.spanishFieldScriptBeforeSaving(i),
					scriptPage.spanishFieldScriptAfterSaving());
		}
		getReport().log(Status.PASS, "English Scripting Added Successfully For Spanish Script In FC");
		getReport().log(Status.PASS, "Spanish Scripting Added Successfully For Spanish Script In FC");
	}

	public void publishNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();

	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.confirmOnBilingiualScriptNotification();
		scriptPage.clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public SwitchBoardPage gettingSpanishScriptDetailsInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		spanishScriptingStatus = switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton()
				.waitUntilFormGotLoaded().isSpanishScriptingLoaded();
		for (int i = 1; i < 4; i++) {
			englishScriptingFieldsInSb.add(switchBoardPage.getEnglishScriptingFieldsInSb(i));
		}
		switchBoardPage.enableOrDisableSpanishScripting();
		for (int i = 1; i < 4; i++) {
			spanishScriptingFieldsInSb.add(switchBoardPage.getSpanishScriptingFieldsInSb(i));
		}
		return switchBoardPage;
	}

	public void validatingScriptInSb(ScriptPage scriptPage, SwitchBoardPage switchBoardPage) {
		Assert.assertTrue(spanishScriptingStatus);
		getReport().log(Status.PASS, "Spanish Script Loaded Sucessfully In Sb");
		for (int i = 1; i < 4; i++) {
			Assert.assertEquals(scriptPage.englishFieldScriptBeforeSaving(i), englishScriptingFieldsInSb.get(i - 1));
			Assert.assertEquals(scriptPage.spanishFieldScriptBeforeSaving(i), spanishScriptingFieldsInSb.get(i - 1));
		}
		getReport().log(Status.PASS, "English Scripting Fields Loaded Sucessfully In Sb");
		getReport().log(Status.PASS, "Spanish Scripting Fields Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();

	}

	public void deleteNewlyAddedSpanishScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		getReport().log(Status.PASS, "Newly Added Spanish Script Deleted Succesfully");

	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifySpanishScripting(String accountNumber) {
		report = extent.createTest("Verify Spanish Scripting");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		deletingExistingHolidayScript(scriptPage);
		configureSpanishScript(scriptPage);
		refetchingScript(scriptPage);
		validatingSpanishScriptingInFc(scriptPage);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		launchingSwitchBoard(scriptPage);
		SwitchBoardPage switchBoardPage = gettingSpanishScriptDetailsInSb();
		validatingScriptInSb(scriptPage, switchBoardPage);
		deleteNewlyAddedSpanishScript(scriptPage);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
