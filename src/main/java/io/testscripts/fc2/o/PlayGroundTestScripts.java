package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.PlayGroundPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class PlayGroundTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	public PlayGroundPage launchingToPlayGroundPage() {
		PlayGroundPage playGroundPage = PageFactory.initElements(getDriver(), PlayGroundPage.class);
		String title = playGroundPage.clickOnProfileButton().clickOnPlayGroundButton()
				.getPageTitlePlayGroundPageTitle();
		Assert.assertTrue(title.equals("FormCreator - PlayGround"));
		getReport().log(Status.PASS, "Play Ground Page Loaded Successfully");
		return playGroundPage;

	}

	public ScriptPage validatingTestAccountNumberInFc(PlayGroundPage playGroundPage) {
		String testAccountNumber = playGroundPage.getAccountNumberText(0);
		playGroundPage.clickOnTestNumber().waitForAccountLoadOrSave();
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		Assert.assertEquals(testAccountNumber, scriptPage.getAccountNumber());
		getReport().log(Status.PASS, "Test Account Number Loaded Successfully In FC");
		return scriptPage;
	}

	public void validatingTestNumberInSb(ScriptPage scriptPage) {
		scriptPage.clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().waitUntilFormGotLoaded();
		Assert.assertTrue(switchBoardPage.isScriptLoaded());
		getReport().log(Status.PASS, "Test Account Number Script Loaded Sucessfully In Sb");
		switchBoardPage.switchBoradPageClose();
	}

	public void navigatingBackToDashBoard(PlayGroundPage playGroundPage) {
		playGroundPage.clickOnProfileButton();
		String title = playGroundPage.clickOnProfileButton().clickOnPlayGroundButton().clickOnBackToDashBoard()
				.getHomePageTitle();
		Assert.assertTrue(title.equals("FormCreator"));
		getReport().log(Status.PASS, "Dash Board Page Loaded Successfully");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyPlayGroundPage(String accountNumber) {
		report = extent.createTest("Verify Play Ground Page");
		setReport(report);
		PlayGroundPage playGroundPage = launchingToPlayGroundPage();
		ScriptPage scriptPage = validatingTestAccountNumberInFc(playGroundPage);
		validatingTestNumberInSb(scriptPage);
		navigatingBackToDashBoard(playGroundPage);
	}

	@AfterMethod
	public void reload() {
		PlayGroundPage playGroundPage = PageFactory.initElements(getDriver(), PlayGroundPage.class);
		playGroundPage.clickOnReload();
	}

}
