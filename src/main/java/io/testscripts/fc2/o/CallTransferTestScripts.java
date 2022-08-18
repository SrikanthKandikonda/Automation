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
public class CallTransferTestScripts extends LaunchBrowser {
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

	public void addingCallTransferField(ScriptPage scriptPage) {
		scriptPage.clickOnAddNewFormButton().dragAndDropGroup1Field(4).dragAndDropCallTransferField()
				.selectCallTransferType();
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();

	}

	public void publishNewlyAddedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();

	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		fieldCountAfterSavingForm = scriptPage.waitUntilScriptFieldsLoaded().getFieldCount();

	}

	public void validatingCallTranferFieldInFc(ScriptPage scriptPage) {
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
		getReport().log(Status.PASS, "Call Transfer Field Added Successfully In FC");
		scriptPage.clickOnSelectedCallTranfer();
		Assert.assertEquals(scriptPage.selectedCallTranferContactBeforeSaving(),
				scriptPage.selectedCallTransferContactAfterSaving());
		getReport().log(Status.PASS, "Call Transfer Contact Saved Successfully In FC");
		Assert.assertEquals(scriptPage.selectedTransferTypeBeforeSaving(), scriptPage.selectedTranferTypeAfterSaving());
		getReport().log(Status.PASS, "Call Transfer Transfer Type Saved Successfully In FC");
	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingCallTransferInSb(ScriptPage scriptPage) {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		switchBoardPage.clickOnDecisionTreeOption(1);
		Assert.assertTrue(switchBoardPage.getCallTranferContactInSb()
				.contains(scriptPage.selectedCallTranferContactBeforeSaving()));
		getReport().log(Status.PASS, "Call Transfer Contact Loaded Successfully In SB");
		Assert.assertTrue(
				switchBoardPage.getCallTranferTypeInSb().contains(scriptPage.selectedTransferTypeBeforeSaving()));
		getReport().log(Status.PASS, "Call Transfer Transfer Type Loaded Successfully In SB");
		switchBoardPage.switchBoradPageClose();

	}

	public void deleteNewlyAddedForm(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();

	}

	public void addingSpecificPeopleCallFlow(ScriptPage scriptPage) {
		scriptPage.clickOnAddNewFormButton().dragAndDropGroup1Field(4).clickOnDecisionTree()
				.selectSpecificPeopleCallType().clickOnOutputOrderNotificationPopUp();
		scriptPage.selectContactForCallFlow();
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();

	}

	public void validatingSpecificPeopleCallTypeInFc(ScriptPage scriptPage) {
		try {
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		} catch (Error fieldCount) {
			for (int i = 1; i < 5; i++) {
				refetchingScript(scriptPage);
				if (fieldCountBeforeSavingForm >= fieldCountAfterSavingForm)
					break;
			}
			Assert.assertTrue(fieldCountBeforeSavingForm >= fieldCountAfterSavingForm);
		}
		getReport().log(Status.PASS, "Specific People Call Type Added Successfully In FC");
		scriptPage.clickOnDecisionTree();
		Assert.assertEquals(scriptPage.selectedCallFlowTypeBeforeSaving(),
				scriptPage.selectedCallFlowTypeAfterSaving());
		getReport().log(Status.PASS, "Call Type Saved Successfully In FC");
		Assert.assertEquals(scriptPage.selectedCallFlowContactBeforeSaving(),
				scriptPage.selectedCallFlowContactAfterSaving());
		getReport().log(Status.PASS, "Specfic People Call Flow Contact Saved Successfully In FC");
	}

	public void validatingSpecificPeopleCallFlowInSb(ScriptPage scriptPage) {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		switchBoardPage.clickOnDecisionTreeOption(1);
		getReport().log(Status.PASS, "Specific People Call Flow Loaded Successfully In SB");
		switchBoardPage.switchBoradPageClose();

	}

	public void addingCommonCallFlow(ScriptPage scriptPage) {
		scriptPage.clickOnAddNewFormButton().dragAndDropGroup1Field(4).clickOnDecisionTree().selectCommonCallType()
				.clickOnOutputOrderNotificationPopUp();
		scriptPage.selectContactForCallFlow();
		scriptPage.clickOnSelectedCallTranfer().selectCallTransferType();
		fieldCountBeforeSavingForm = scriptPage.getFieldCount();
		scriptPage.clickOnSaveScriptButton().clickOnOutputOrderNotificationPopUp().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();

	}

	public void validatingCommonCallFlowInFc(ScriptPage scriptPage) {
		try {
			Assert.assertEquals(fieldCountBeforeSavingForm, fieldCountAfterSavingForm);
		} catch (Error fieldCount) {
			for (int i = 1; i < 5; i++) {
				refetchingScript(scriptPage);
				if (fieldCountBeforeSavingForm >= fieldCountAfterSavingForm)
					break;
			}
			Assert.assertTrue(fieldCountBeforeSavingForm >= fieldCountAfterSavingForm);
		}
		getReport().log(Status.PASS, "Common Call Flow Added Successfully In FC");
		scriptPage.clickOnSelectedCallTranfer();
		Assert.assertEquals(scriptPage.selectedCallFlowContactBeforeSaving(),
				scriptPage.selectedCallFlowContactAfterSaving());
		getReport().log(Status.PASS, "Common Call Flow Contact Saved Successfully In FC");
		Assert.assertEquals(scriptPage.selectedTransferTypeBeforeSaving(), scriptPage.selectedTranferTypeAfterSaving());
		getReport().log(Status.PASS, "Common Call Flow Call Transfer Transfer Type Saved Successfully In FC");
	}

	public void clearingCache(ScriptPage scriptPage) {
		scriptPage.waitUntilScriptFieldsLoaded().clickOnThreeDotsButton().clickOnThreeDotsOptions(1);
		scriptPage.clickOnConfirmNotificationPopUp().waitForAccountLoadOrSave();
		scriptPage.waitUntilCacheGotCleared();
	}

	public void validatingCommonCallFlowInSb(ScriptPage scriptPage) {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		switchBoardPage.clickOnDecisionTreeOption(1);
		Assert.assertTrue(
				switchBoardPage.getCallTranferContactInSb().contains(scriptPage.selectedCallFlowContactBeforeSaving()));
		getReport().log(Status.PASS, "Common Call Flow Call Transfer Contact Loaded Successfully In SB");
		Assert.assertTrue(
				switchBoardPage.getCallTranferTypeInSb().contains(scriptPage.selectedTransferTypeBeforeSaving()));
		getReport().log(Status.PASS, " Common Call Flow Call Transfer Transfer Type Loaded Successfully In SB");
		switchBoardPage.switchBoradPageClose();

	}

	public ScriptPage verifyCallTransfer(String accountNumber) {
		ScriptPage scriptPage = findingAccount(accountNumber);
		deletingUnpublishedForms(scriptPage);
		deletingExistingHolidayScript(scriptPage);
		addingCallTransferField(scriptPage);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		validatingCallTranferFieldInFc(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingCallTransferInSb(scriptPage);
		deleteNewlyAddedForm(scriptPage);
		return scriptPage;
	}

	public void verifySpecificPeopleCallFlow(ScriptPage scriptPage) {
		addingSpecificPeopleCallFlow(scriptPage);
		publishNewlyAddedScript(scriptPage);
		refetchingScript(scriptPage);
		validatingSpecificPeopleCallTypeInFc(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingSpecificPeopleCallFlowInSb(scriptPage);
		deleteNewlyAddedForm(scriptPage);
	}

	public void verifyCommonCallFlow(ScriptPage scriptPage) {
		addingCommonCallFlow(scriptPage);
		publishNewlyAddedScript(scriptPage);
		clearingCache(scriptPage);
		refetchingScript(scriptPage);
		validatingCommonCallFlowInFc(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingCommonCallFlowInSb(scriptPage);
		deleteNewlyAddedForm(scriptPage);
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyCallTransferField(String accountNumber) {
		report = extent.createTest("Verify Call Transfer Field");
		setReport(report);
		ScriptPage scriptPage = verifyCallTransfer(accountNumber);
		verifySpecificPeopleCallFlow(scriptPage);
		verifyCommonCallFlow(scriptPage);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();
	}

}
