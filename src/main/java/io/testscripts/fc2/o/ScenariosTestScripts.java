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

import io.pagelibrary.fc2.o.ScenariosPage;
import io.pagelibrary.fc2.o.SwitchBoardPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class ScenariosTestScripts extends LaunchBrowser {

	private static ExtentTest report;
	private static String roleInSb;
	private static String newlyAddedScenarioNameInSb;
	private static String accountGoalInSB;
	private static String companyWorkingStatus;
	private static List<String> newlyAddedScenarioStepsInSb = new ArrayList<>();
	private boolean superAccountStatus;

	public ScenariosPage findingAccount(String accountNumber) {
		ScenariosPage scenariosPage = PageFactory.initElements(getDriver(), ScenariosPage.class);
		scenariosPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton()
				.waitForAccountLoadOrSave();
		superAccountStatus = scenariosPage.isLockedAlertDisplayed();
		if (superAccountStatus)
			scenariosPage.confirmMessageAnnotationAlertNotification().clickOnSettingsButton().clickOnAccountDetailsTab()
					.clickOnSuperAcountToggleButton();
		return scenariosPage;
	}

	public void savingYourRole(ScenariosPage scenariosPage) {
		scenariosPage.clickOnScenariosButton().clickOnChooseYourRoleDropDown().selectCustomYourRole().enterCustomRole()
				.enterAccountGoal().clickOnAddAfterHours().clickOnChooseAfterHourRoleDropDown().selectAnyAfterHourRole()
				.clickOnSaveScenarioChanges().waitForAccountLoadOrSave().waitForAccountLoadOrSave();
	}

	public void savingScenarios(ScenariosPage scenariosPage) {
		scenariosPage.clickOnAddScenario().clickOnEditScenario().enterScenarioName().enterScenrioStep(1)
				.clickOnAddScenarioStep().enterScenrioStep(2).clickOnSaveScenarioChanges().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
	}

	public void refechingAccountDetails(ScenariosPage scenariosPage) {
		scenariosPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave().clickOnScenariosButton();
	}

	public void validatingScenariosChangesInFc(ScenariosPage scenariosPage) {
		Assert.assertEquals(scenariosPage.getCustomRoleBeforeSaving(), scenariosPage.getYourRoleAfterSaving());
		getReport().log(Status.PASS, "Your Role Saved Successfully In FC");
		Assert.assertEquals(scenariosPage.getAccountGoalBeforeSaving(), scenariosPage.getAccountGoalAfterSaving());
		getReport().log(Status.PASS, "Account Goal Saved Successfully In FC");
		Assert.assertEquals(scenariosPage.getAfterHourRoleBeforeSaving(), scenariosPage.getAfterHourRoleAfterSaving());
		getReport().log(Status.PASS, "AfterHour Role Saved Successfully In FC");
		scenariosPage.clickOnNewlyAddedScenario();
		Assert.assertEquals(scenariosPage.getScenariosNameBeforeSaving(), scenariosPage.getScenarioNameAfterSaving());
		getReport().log(Status.PASS, "Scenario Name Saved Successfully In FC");
		Assert.assertEquals(scenariosPage.getScenarioStepBeforeSaving(1), scenariosPage.getScenarioStepAfterSaving(1));
		Assert.assertEquals(scenariosPage.getScenarioStepBeforeSaving(2), scenariosPage.getScenarioStepAfterSaving(2));
		getReport().log(Status.PASS, "Scenario Steps Saved Successfully In FC");

	}

	public void launchingSwitchBoard(ScenariosPage scenariosPage) {
		scenariosPage.clickOnScriptButton().clickOnViewInDropDownButton().clickOnViewInMenuOptions(1);
	}

	public SwitchBoardPage gettingScenariosDetailsFromSb() {
		SwitchBoardPage switchBoardPage = PageFactory.initElements(getDriver(), SwitchBoardPage.class);
		roleInSb = switchBoardPage.waitForSbLoad().clickOnCloseAnswerPhraseButton().clickOnNewlyAddedScenario()
				.getRole();
		newlyAddedScenarioNameInSb = switchBoardPage.getNewlyAddedScenario();
		companyWorkingStatus = switchBoardPage.getCompanyWorkingStatus();
		newlyAddedScenarioStepsInSb.add(switchBoardPage.getNewlyAddedScenarioSteps(1));
		newlyAddedScenarioStepsInSb.add(switchBoardPage.getNewlyAddedScenarioSteps(2));
		accountGoalInSB = switchBoardPage.clickOnAccountInfo().getAccountGoal();
		return switchBoardPage;
	}

	public void validatingScenarioChangesInSb(ScenariosPage scenariosPage) {
		if (companyWorkingStatus.equals("CLOSED"))
			Assert.assertEquals(scenariosPage.getAfterHourRoleBeforeSaving(), roleInSb);
		else
			Assert.assertEquals(scenariosPage.getCustomRoleBeforeSaving(), roleInSb);
		getReport().log(Status.PASS, "Your Role Saved Successfully In SB");
		Assert.assertEquals(scenariosPage.getScenariosNameBeforeSaving(), newlyAddedScenarioNameInSb);
		getReport().log(Status.PASS, "Scenario Name Saved Successfully In SB");
		Assert.assertEquals(scenariosPage.getAccountGoalBeforeSaving(), accountGoalInSB);
		getReport().log(Status.PASS, "Account Goal Saved Successfully In SB");
		Assert.assertEquals(scenariosPage.getScenarioStepBeforeSaving(1), newlyAddedScenarioStepsInSb.get(0));
		Assert.assertEquals(scenariosPage.getScenarioStepBeforeSaving(2), newlyAddedScenarioStepsInSb.get(1));
		getReport().log(Status.PASS, "Scenario Steps Saved Successfully In FC");
	}

	public void closingSwitchBoard(SwitchBoardPage switchBoardPage) {
		switchBoardPage.switchBoradPageClose();
		getReport().log(Status.PASS, "Scenarios Loaded Successfully In SB");
	}

	public void deletingAddedScenario(ScenariosPage scenariosPage) {
		scenariosPage.clickOnScenariosButton().clickOnNewlyAddedScenario().clickOnDeleteScenario()
				.clickOnDeleteScenarioConfirmationPopUp().waitForAccountLoadOrSave();
		Assert.assertTrue(scenariosPage.isScenarioDeleted());
		getReport().log(Status.PASS, "Scenarios Deleted Successfully");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyScenarios(String accountNumber) {
		report = extent.createTest("Verify scenarios Page");
		setReport(report);
		ScenariosPage scenariosPage = findingAccount(accountNumber);
		savingYourRole(scenariosPage);
		savingScenarios(scenariosPage);
		refechingAccountDetails(scenariosPage);
		validatingScenariosChangesInFc(scenariosPage);
		launchingSwitchBoard(scenariosPage);
		SwitchBoardPage switchBoardPage = gettingScenariosDetailsFromSb();
		validatingScenarioChangesInSb(scenariosPage);
		closingSwitchBoard(switchBoardPage);
		deletingAddedScenario(scenariosPage);

	}

	@AfterMethod
	public void reload() {
		ScenariosPage scenariosPage = PageFactory.initElements(getDriver(), ScenariosPage.class);
		scenariosPage.clickOnReload();
	}

}
