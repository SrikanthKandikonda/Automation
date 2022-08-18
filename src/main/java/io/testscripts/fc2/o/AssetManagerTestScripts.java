package io.testscripts.fc2.o;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.AssetManagerPage;
import io.pagelibrary.fc2.o.ScriptPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class AssetManagerTestScripts extends LaunchBrowser {

	private static ExtentTest report;
	private boolean spanishScriptNotificaiton;
	public static String addedAsset;
	public static String uploadedAsset;

	public ScriptPage findingAccount(String accountNumber) {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		spanishScriptNotificaiton = scriptPage.isSpanishScriptingNotificationDisplayed();
		if (spanishScriptNotificaiton) {
			scriptPage.confirmOnBilingiualScriptNotification();
		}
		return scriptPage;
	}

	// switch to asset manager
	public AssetManagerPage goToAssetManagerTab() {
		AssetManagerPage assetManager = PageFactory.initElements(getDriver(), AssetManagerPage.class);
		assetManager.clickAssetManagerDropDownFromSettingButton().switchToAssetManagerTab()
				.verifyIfAssetManagerPageIsLoaded();
		getReport().log(Status.PASS, "Switched To Asset Manager Tab");
		return assetManager;
	}

	// check asset history button with predefined account number
	public void checkSearchAssetFunctionality(AssetManagerPage assetManager, String accountNumber) {
		assetManager.clickAssetHistoryIcon().enterNumberInAsserHistory(accountNumber).verifyAssetHistory();
		getReport().log(Status.PASS, "Asset  History Search Completed Successfully");
	}

	// Add single asset
	public void addSingleAssetToAssetPool(AssetManagerPage assetManager) {
		try {
			assetManager.clickAddNewAssetButton().selectCountryCode().chooseCarriertype().chooseAssetType()
					.selectAssetStatus().selectSingleAssetOption().enterRandomAccountNumberDetails()
					.clickSubmitButton();
			addedAsset = assetManager.getAddedAssetValue();
			assetManager.clickAssetUploadButton().verifyIfAssetAdded();
		} catch (Exception e) {
			assetManager.reloadAndConfirmAlert();
			addSingleAssetToAssetPool(assetManager);
		}
		getReport().log(Status.PASS, "Single Asset Added Successfully");
	}

	public void searchAddedSingleAssetInAssetPool(AssetManagerPage assetManager, String accountNumber) {
		assetManager.fetchAccountInAssetManager(accountNumber).clickEightXTab().clickSearchTextField()
				.enterAddedAssetValue().searchedAssetNumber();
		assertEquals(assetManager.getSearchedAssetList(), addedAsset);
		getReport().log(Status.PASS, "Added Asset is Listed In Asset Pool");
	}

	public void mapAddedAsset(AssetManagerPage assetManager) {
		assetManager.clickSearchedAssetCheckBox().addSelectedAsset().verifyAddedAssetInActiveList();
		getReport().log(Status.PASS, "Added Asset is Mapped to a Uniquepin");
	}

	public void verifyAddedAssetInFc(AssetManagerPage assetManager) {
		assetManager.clickViewInFCButton().switchingToFcTab().verifyInFc().switchBackToAssetManagerTab();
		getReport().log(Status.PASS, "Mapped Asset is Loaded in FC");
	}

	public void moveToInactiveStateAndVerifyInFC(AssetManagerPage assetManager) {
		assetManager.moveToInactiveState().clickCancelActionButton().selectDontReleaseOption().clickConfirmButton()
				.verifyIfAssetAddedInInActiveList().switchToFcToFetchInActiveAsset().closeFcPage();
		getReport().log(Status.PASS, "Asset Moved to Inactive Asset List");
	}

	public void unmapAddedAsset(AssetManagerPage assetManager) {
		assetManager.makeInactiveAssetToActive().verifyAssetAddedToActiveList().removeAssetFromActiveList()
				.closeAssetMaanagerAndSwitchToFc();
		getReport().log(Status.PASS, "Asset moved to Active State and unmapped from that Uniquepin");
	}

	public void uploadUsingCSVFile(AssetManagerPage assetManager, String accountNumber) {
		try {
			goToAssetManagerTab();
			// checkSearchAssetFunctionality(assetManager, accountNumber);
			assetManager.clickAddNewAssetButton().selectCountryCode().chooseAssetType().chooseCarriertype()
					.selectAssetStatus().selectAssetUploadOption().uploadCsvAssetFile().clickSubmitButton()
					.updateUploadFileValue();

			addedAsset = assetManager.getAddedAssetValue();

			assetManager.clickAssetUploadButton().verifyIfAssetAdded();
		} catch (Exception e) {
			System.out.println(e);
			assetManager.reloadAssetManagerPage();
			uploadUsingCSVFile(assetManager, accountNumber);
		}

		getReport().log(Status.PASS, "Asset Uploaded through CSV file");
	}

	public void searchUploadedAssetInAssetPool(AssetManagerPage assetManager) {
		assetManager.clickEightXXTab().clickSearchTextField().enterAddedAssetValue().searchedAssetNumber();
		assertEquals(assetManager.getSearchedAssetList(), addedAsset);
		getReport().log(Status.PASS, "Uploaded Asset is Listed In Asset Pool");

	}

	public void deleteUploadedAssetFromAssetPool(AssetManagerPage assetManager) {
		assetManager.clickSearchedAssetCheckBox().deleteSelectedAsset().confirmDeleteButton().clickSearchTextField()
				.enterAddedAssetValue().enterAddedAssetValue().verifyNotification().closeAssetMaanagerAndSwitchToFc();

		getReport().log(Status.PASS, "Asset Deleted Successfully from Asset Pool");
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyAssetManagerPage(String accountNumber) {
		report = extent.createTest("Verify Asset Manager");
		setReport(report);
		findingAccount(accountNumber);
		AssetManagerPage assetManager = goToAssetManagerTab();
		checkSearchAssetFunctionality(assetManager, accountNumber);
		addSingleAssetToAssetPool(assetManager);
		searchAddedSingleAssetInAssetPool(assetManager, accountNumber);
		mapAddedAsset(assetManager);
		verifyAddedAssetInFc(assetManager);
		moveToInactiveStateAndVerifyInFC(assetManager);
		unmapAddedAsset(assetManager);
		uploadUsingCSVFile(assetManager, accountNumber);
		searchUploadedAssetInAssetPool(assetManager);
		deleteUploadedAssetFromAssetPool(assetManager);

	}

	@AfterMethod
	public void reload() {
		ScriptPage scriptPage = PageFactory.initElements(getDriver(), ScriptPage.class);
		scriptPage.clickOnReload();

	}

}
