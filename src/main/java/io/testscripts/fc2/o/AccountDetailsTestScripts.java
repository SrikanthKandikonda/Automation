package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.AccountDetailsPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class AccountDetailsTestScripts extends LaunchBrowser {
	private static ExtentTest report;
	private String answerPhraseInSb;
	private String accountNameInSb;
	private String answerPhraseInstruction;
	private boolean superAccountStatus;

	public AccountDetailsPage findingAccount(String accountNumber) {
		AccountDetailsPage accountDetailsPage = PageFactory.initElements(getDriver(), AccountDetailsPage.class);
		accountDetailsPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton()
				.waitForAccountLoadOrSave();
		superAccountStatus = accountDetailsPage.isLockedAlertDisplayed();
		if (superAccountStatus)
			accountDetailsPage.confirmMessageAnnotationAlertNotification().clickOnSettingsButton()
					.clickOnAccountDetailsTab().clickOnSuperAcountToggleButton();
		return accountDetailsPage;
	}

	public void savingAccountDetails(AccountDetailsPage accountDetailsPage) {
		accountDetailsPage.clickOnSettingsButton().clickOnAccountDetailsTab().enableOrDisableAnswerInsturction()
				.clickAnswerPhraseInsturction(2).enterAnswerInsturction().enterAccountName().enterCompanyName()
				.enterAnswerPhrase().clickOnOutPulseNumberDropDown().clickOnOutPulseNumbers()
				.clickOnSuperAcountToggleButton().clickOnTwilioSmsConfigurationToggleButton()
				.clickOnContactVerificationToggleButton().clickOnMessageAnnotationToggleButton()
				.clickOnPoweredByToggleButton().clickOnFromAddressToggleButton().clickOnSaveAccountDetailsButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void refetchingAccountDetails(AccountDetailsPage accountDetailsPage) {
		superAccountStatus = accountDetailsPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave()
				.isLockedAlertDisplayed();
		accountDetailsPage.confirmMessageAnnotationAlertNotification().clickOnSettingsButton()
				.clickOnAccountDetailsTab().clickOnSuperAcountToggleButton().clickOnSaveAccountDetailsButton()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void validatingAccountDetailsInFc(AccountDetailsPage accountDetailsPage) {
		Assert.assertEquals(accountDetailsPage.companyNameBeforeSaving(), accountDetailsPage.companyNameAfterSaving());
		getReport().log(Status.PASS, "Company Name Saved Successfully In FC");
		Assert.assertEquals(accountDetailsPage.accountNameBeforeSaving(), accountDetailsPage.accountNameAfterSaving());
		getReport().log(Status.PASS, "Account Name Saved Successfully In FC");
		Assert.assertEquals(accountDetailsPage.answerPhraseBeforeSaving(),
				accountDetailsPage.answerPhraseAfterSaving());
		getReport().log(Status.PASS, "Answer Pharse Saved Successfully In FC");
		Assert.assertEquals(accountDetailsPage.answerPhraseInstructionBeforeSaving(),
				accountDetailsPage.answerPhraseInstructionAfterSaving());
		Assert.assertTrue(superAccountStatus);
		getReport().log(Status.PASS, "Answer Pharse Instruction Saved Successfully In FC");
		getReport().log(Status.PASS, "Super Account Skill Saved Successfully In FC");
		getReport().log(Status.PASS, "Account Details Saved Successfully In FC");

	}

	public void launchingSwitchBoard(AccountDetailsPage accountDetailsPage) {
		accountDetailsPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public SwitchBoardPage gettingAccountDetailsFromSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		answerPhraseInSb = switchBoardPage.waitForSbLoad().getAnswerPhraseInSb();
		switchBoardPage.clickOnCloseAnswerPhraseButton().clickOnAccountInfo();
		accountNameInSb = switchBoardPage.getAccountNameInSb();
		answerPhraseInstruction = switchBoardPage.getAnswerPhraseInstructionInSb();
		return switchBoardPage;
	}

	public void validatingAccountDetailsInSb(AccountDetailsPage accountDetailsPage) {
		Assert.assertEquals(answerPhraseInSb, accountDetailsPage.answerPhraseBeforeSaving());
		getReport().log(Status.PASS, "Answer Pharse Loaded Successfully In SB");
		Assert.assertEquals(accountNameInSb, accountDetailsPage.accountNameBeforeSaving());
		getReport().log(Status.PASS, "Account Name Loaded Successfully In SB");
		Assert.assertEquals(answerPhraseInstruction, accountDetailsPage.answerPhraseInstructionBeforeSaving());
		getReport().log(Status.PASS, "Answer Pharse Instruction Loaded Successfully In SB");
	}

	public void closingSwitchBoard(SwitchBoardPage switchBoard) {
		switchBoard.switchBoradPageClose();
		getReport().log(Status.PASS, "Account Details Loaded Successfully In SB");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyAcccountDetails(String accountNumber) {
		report = extent.createTest("Verify Acccount Details");
		setReport(report);
		AccountDetailsPage accountDetailsPage = findingAccount(accountNumber + "()- ");
		savingAccountDetails(accountDetailsPage);
		refetchingAccountDetails(accountDetailsPage);
		validatingAccountDetailsInFc(accountDetailsPage);
		launchingSwitchBoard(accountDetailsPage);
		SwitchBoardPage switchBoardPage = gettingAccountDetailsFromSb();
		validatingAccountDetailsInSb(accountDetailsPage);
		closingSwitchBoard(switchBoardPage);

	}

	@AfterMethod
	public void reload() {
		AccountDetailsPage accountDetailsPage = PageFactory.initElements(getDriver(), AccountDetailsPage.class);
		accountDetailsPage.clickOnReload();
	}

}
