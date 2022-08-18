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

import io.pagelibrary.fc2.o.AccountDetailsPage;
import io.pagelibrary.fc2.o.BusinessHoursAndClosuresPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class CopyAccountTemplateTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private static boolean CopiedAccountTemplatevalue;
	private static int count = 1;
	private int numberOfUnblishedForms;
	private static List<String> businessHoursValuesOfMainAccount = new ArrayList<>();
	private static String answerPhraseOfMainAccount;
	private static int closuresOfMainAccount;
	private static int originalFormFieldCount;
	private static int copiedFormFieldCount;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		return scriptPage;
	}

	public void deletingUnpublishedForms(ScriptPage scriptPage) {
		numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();
		for (int i = 0; i < numberOfUnblishedForms; i++) {
			scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().clickOnDeleteButton()
					.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();
		}
	}

	public void copyAccountTemplate(ScriptPage scriptPage, String accountNumber) {
		scriptPage.clickOnThreeDotsButton().clickOnThreeDotsOptions(2);
		scriptPage.enterAccountNumberInCopyAccountFromTextField(accountNumber).clickOnCopyAccountTemplateButton()
				.waitUntilAccountTemplateGetsCopied().waitForScriptLoad();
	}

	public void refetchingScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();

	}

	public void waitUntilCopiedAccountToBeLoaded(ScriptPage scriptPage) {
		if (numberOfUnblishedForms == 0)
			refetchingScript(scriptPage);
		CopiedAccountTemplatevalue = scriptPage.isCopiedAccountTemplateLoaded();
		while (!CopiedAccountTemplatevalue) {
			if (count < 15) {
				scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
				scriptPage.waitUntilScriptFieldsLoaded();
				CopiedAccountTemplatevalue = scriptPage.isCopiedAccountTemplateLoaded();
				count++;
			} else {
				Assert.assertTrue(false);
			}
		}
		scriptPage.waitForScriptLoad();
		getReport().log(Status.PASS, "Copied Account Template Loaded Successfully In FC");
	}

	public void validateCopiedAccountTemplateInFc(ScriptPage scriptPage) {
		Assert.assertTrue(scriptPage.isAccountTemplateCopied());
		getReport().log(Status.PASS, "Account Template Copied Successfully In FC");
	}

	public void publishCopiedScript(ScriptPage scriptPage) {
		scriptPage.clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad()
				.waitForSaveOrPublishNotification();
	}

	public void refetchingAndValidatingPublishedCopiedScript(ScriptPage scriptPage) {
		scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
		scriptPage.waitUntilScriptFieldsLoaded();
		Assert.assertTrue(scriptPage.getFormStatus().equals("Published"));
		getReport().log(Status.PASS, "Copied Account Template Published Successfully In FC");
	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingCopyAccountTemplateInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Copied Script Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();

	}

	public void deleteCopiedScript(ScriptPage scriptPage) {
		scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
				.waitForScriptLoad().clickOnFormbutton(0).clickOnDeleteButton()
				.clickOnConfirmDeleteOrPublishFormButton().waitForScriptLoad().waitForSaveOrPublishNotification();

	}

	public void validateDifferentAccountCopiedTemplateInFc(ScriptPage scriptPage) {
		count = 1;
		while ((originalFormFieldCount != copiedFormFieldCount) && count < 4) {
			refetchingScript(scriptPage);
			copiedFormFieldCount = scriptPage.clickOnFormbutton(1).waitUntilScriptFieldsLoaded().getFieldCount();
			count++;
		}
		Assert.assertTrue(originalFormFieldCount >= copiedFormFieldCount);
		getReport().log(Status.PASS, "Account Template Copied For Different Account Successfully In FC");
	}

	public void getOtherCopiedComponents(ScriptPage scriptPage) {
		AccountDetailsPage accountDetailsPage = PageFactory.initElements(getDriver(), AccountDetailsPage.class);
		answerPhraseOfMainAccount = accountDetailsPage.clickOnSettingsButton().clickOnAccountDetailsTab()
				.answerPhraseAfterSaving();
		BusinessHoursAndClosuresPage businessHoursPageAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		businessHoursPageAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab();
		for (int i = 0; i < 2; i++) {
			String businessHours = businessHoursPageAndClosuresPage.businessHoursTimingsAfterSaving(i);
			businessHoursValuesOfMainAccount.add(businessHours);
		}
		closuresOfMainAccount = businessHoursPageAndClosuresPage.clickOnSettingsButton()
				.clickOnBusinessHoursAndClosuresTab().clickOnAddHolidays().getListOfHolidays();

	}

	public void copyToDifferentAccount(ScriptPage scriptPage, String accountNumber, String differentAccountNumber) {
		originalFormFieldCount = scriptPage.clickOnScriptButton().getFieldCount();
		scriptPage.clickOnThreeDotsButton().clickOnThreeDotsOptions(2);
		scriptPage.enterAccountNumberInCopyAccountToTextField(differentAccountNumber)
				.enterAccountNumberInCopyAccountFromTextField(accountNumber).clickOnCopyAccountTemplateButton()
				.waitUntilAccountTemplateGetsCopied().waitForScriptLoad();

	}

	public void waitUntilCopiedAccountToBeLoadedInDifferentAccount(ScriptPage scriptPage,
			String differentAccountNumber) {
		count = 1;
		numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();
		while (numberOfUnblishedForms == 0) {
			scriptPage.clickOnReload();
			scriptPage.waitForAccountLoadOrSave();
			findingAccount(differentAccountNumber);
			numberOfUnblishedForms = scriptPage.waitUntilScriptFieldsLoaded().getTotalNumberOfUnpublishedForms();
		}
		CopiedAccountTemplatevalue = scriptPage.isCopiedAccountTemplateLoaded();
		while (!CopiedAccountTemplatevalue) {
			if (count < 4) {
				scriptPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave();
				CopiedAccountTemplatevalue = scriptPage.isCopiedAccountTemplateLoaded();
				count++;
			} else {
				Assert.assertTrue(false);
			}
		}
		copiedFormFieldCount = scriptPage.waitUntilScriptFieldsLoaded().getFieldCount();
		getReport().log(Status.PASS, "Copied Account Template Loaded Successfully In FC");
	}

	public void validatingOtherCopiedComponents(ScriptPage scriptPage) {
		AccountDetailsPage accountDetailsPage = PageFactory.initElements(getDriver(), AccountDetailsPage.class);
		accountDetailsPage.clickOnSettingsButton().clickOnAccountDetailsTab();
		Assert.assertEquals(answerPhraseOfMainAccount, accountDetailsPage.answerPhraseAfterSaving());
		getReport().log(Status.PASS, "Answer Pharse Saved Successfully In FC For Different Account");
		BusinessHoursAndClosuresPage businessHoursPageAndClosuresPage = PageFactory.initElements(getDriver(),
				BusinessHoursAndClosuresPage.class);
		businessHoursPageAndClosuresPage.clickOnSettingsButton().clickOnBusinessHoursAndClosuresTab();
		Assert.assertEquals(businessHoursValuesOfMainAccount.get(0),
				businessHoursPageAndClosuresPage.businessHoursTimingsAfterSaving(0));
		Assert.assertEquals(businessHoursValuesOfMainAccount.get(1),
				businessHoursPageAndClosuresPage.businessHoursTimingsAfterSaving(1));
		getReport().log(Status.PASS, " Business Hours Saved Successfully In Different Account");
		businessHoursPageAndClosuresPage.clickOnAddHolidays();
		Assert.assertEquals(closuresOfMainAccount, businessHoursPageAndClosuresPage.getListOfHolidays());
		getReport().log(Status.PASS, "Holidays Saved Successfully In Different Account");
		scriptPage.clickOnScriptButton().clickOnFormbutton(1);

	}

	public void verifyCopyAccountTemplateForSameAccount(String accountNumber, ScriptPage scriptPage) {
		deletingUnpublishedForms(scriptPage);
		copyAccountTemplate(scriptPage, accountNumber);
		refetchingScript(scriptPage);
		waitUntilCopiedAccountToBeLoaded(scriptPage);
		validateCopiedAccountTemplateInFc(scriptPage);
		publishCopiedScript(scriptPage);
		refetchingAndValidatingPublishedCopiedScript(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingCopyAccountTemplateInSb();
		deleteCopiedScript(scriptPage);
	}

	public void verifyCopyAccountTemplateForDifferentAccount(ScriptPage scriptPage, String accountNumber,
			String differentAccountNumber) {
		scriptPage.clickOnReload();
		scriptPage.waitForAccountLoadOrSave();
		findingAccount(differentAccountNumber);
		deletingUnpublishedForms(scriptPage);
		scriptPage.clickOnReload();
		scriptPage.waitForAccountLoadOrSave();
		findingAccount(accountNumber);
		getOtherCopiedComponents(scriptPage);
		copyToDifferentAccount(scriptPage, accountNumber, differentAccountNumber);
		scriptPage.clickOnReload();
		findingAccount(differentAccountNumber);
		waitUntilCopiedAccountToBeLoadedInDifferentAccount(scriptPage, differentAccountNumber);
		validateDifferentAccountCopiedTemplateInFc(scriptPage);
		validatingOtherCopiedComponents(scriptPage);
		publishCopiedScript(scriptPage);
		refetchingAndValidatingPublishedCopiedScript(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingCopyAccountTemplateInSb();
		deleteCopiedScript(scriptPage);
	}

	@Parameters({ "AccountNumber", "DifferentAccount" })
	@Test
	public void verifyCopyAccountTemplate(String accountNumber, String differentAccountNumber) {
		report = extent.createTest("Verify Copy Account Template");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		verifyCopyAccountTemplateForSameAccount(accountNumber, scriptPage);
		verifyCopyAccountTemplateForDifferentAccount(scriptPage, accountNumber, differentAccountNumber);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();

	}

}
