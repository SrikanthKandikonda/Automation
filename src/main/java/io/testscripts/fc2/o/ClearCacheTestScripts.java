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
public class ClearCacheTestScripts extends LaunchBrowser {
	public static ExtentTest report;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		if (scriptPage.isSpanishScriptingNotificationDisplayed()) {
			scriptPage.confirmOnBilingiualScriptNotification();
			scriptPage.clickOnFormbutton(1).clickOnPublishFormButton().clickOnConfirmDeleteOrPublishFormButton()
					.waitForScriptLoad().waitForSaveOrPublishNotification();
		}
		return scriptPage;
	}

	public void clearingCache(ScriptPage scriptPage) {
		scriptPage.waitUntilScriptFieldsLoaded().clickOnThreeDotsButton().clickOnThreeDotsOptions(1);
		scriptPage.clickOnConfirmNotificationPopUp().waitForAccountLoadOrSave();
		scriptPage.waitUntilCacheGotCleared();
	}

	public void validatingClearCacheInFc(ScriptPage scriptPage) {
		Assert.assertTrue(scriptPage.isCacheCleared());
		getReport().log(Status.PASS, "Cache Cleared Successfully In FC");
	}

	public void launchingSwitchBoard(ScriptPage scriptPage) {
		scriptPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public void validatingClearCacheInSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Script Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyClearCache(String accountNumber) {
		report = extent.createTest("Verify ClearCache");
		setReport(report);
		ScriptPage scriptPage = findingAccount(accountNumber);
		clearingCache(scriptPage);
		validatingClearCacheInFc(scriptPage);
		launchingSwitchBoard(scriptPage);
		validatingClearCacheInSb();

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();

	}
}
