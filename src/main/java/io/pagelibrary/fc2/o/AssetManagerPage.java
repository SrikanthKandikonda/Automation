package io.pagelibrary.fc2.o;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.testscripts.fc2.o.AssetManagerTestScripts;
import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;
import io.utils.fc2.o.WindowHandles;

public class AssetManagerPage extends HomePage {

	Wait wait = new Wait();
	Scroll scroll = new Scroll();
	WindowHandles handle = new WindowHandles();
	WebDriver driver;
	RandomData assetManagerData = new RandomData();

	public AssetManagerPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class = 'asset-dropdown-container']//input[@type='text' and @placeholder='Active/inactive/uniquepin/companyname']")
	private WebElement getAssetDetailsTextField;

	@FindBy(xpath = "//div[@class = 'asset-dropdown-container']//input[@type='text' and @placeholder='Active/inactive/uniquepin/companyname']//following-sibling::button")
	private WebElement searchAssetDeatailsButton;

	@FindBy(xpath = "//ol[@class = 'rt_group']//li[@class = 'fa fa-ellipsis-v more']")
	private WebElement settingsButton;

	@FindBy(xpath = "//ol[@class = 'rt_group']//li[@class = 'fa fa-ellipsis-v more']//ul[@class = 'dropdown-list lt-ar']")
	private WebElement signOutButton;

	@FindBy(xpath = "//button[@class='fa fa-cog']")
	private WebElement gearSettingIcon;

	@FindBy(xpath = "//span[@class ='fa fa-history']")
	private WebElement assetHistoryButton;

	@FindBy(xpath = "//div[@class='asset-history-wrapper']//div//input")
	private WebElement assetHistorySearchButton;

	@FindBy(xpath = "//button[@class='add']")
	private WebElement addAssetButton;

	@FindBy(xpath = "//button[contains(text(),'Add New Assets')]")
	private WebElement addNewAssetButton;

	@FindBy(xpath = "//button//i[@class='fa fa-angle-down']")
	private WebElement selectCountryDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class = 'dropdown full-btn']//ul[@class='dropdown-list']//li"))
	private List<WebElement> countriesInSelectCountryDropDown;

	@FindBy(xpath = "//ul[@class='model-list']//li[@class = 'expandable']")
	private WebElement tollFreeNumbersDropDownButton;

	@FindBys(@FindBy(xpath = "//ul[@class='model-list']//li[@class = 'expandable']//div"))
	private List<WebElement> optionsInTollFreeNumberDropDown;

	@FindBys(@FindBy(xpath = "//ul[@class='model-list']//li"))
	private List<WebElement> typesOfNumbersButton;

	@FindBys(@FindBy(xpath = "//div[@class = 'number-list-container']//td//i"))
	private List<WebElement> selectorDelesectNumbers;

	@FindBy(xpath = "//div[@class ='search_holder']//i")
	private WebElement closeListOfOpenAssetsBlock;

	@FindBys(@FindBy(xpath = "//table[@class ='assets-list']//td//a[@class = 'deactive']"))
	private List<WebElement> deactiveAssetButton;

	@FindBys(@FindBy(xpath = "//div[@id='activeAssets']//table[@class = 'assets-list']//tbody//tr//td[contains(text(),'(X)') or contains (text(),'(Z)')]"))
	private List<WebElement> activeAssetNumbersList;

	@FindBy(xpath = "//div[@id='notification']")
	private WebElement assetManagerNotification;

	@FindBy(xpath = "//div[@id='notification'][@style='display: none;']")
	private WebElement assetNotification;

	@FindBy(xpath = "//input[@id='searchAsset']")
	private WebElement searchActiveAssetListButton;

	@FindBys(@FindBy(xpath = "//div[@class = 'number-list-container']//td[2]"))
	private List<WebElement> openAssetListNumbersText;

	@FindBy(xpath = "//div[@class='search-box']//input")
	private WebElement subAccountSearchField;

	@FindBy(xpath = "//div[@class='activeAssetList_View']//select")
	private WebElement assetListViewDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='activeAssetList_View']//select//option"))
	private List<WebElement> optionsInassetListViewDropDown;

	@FindBy(xpath = "//button[@class='g-drop-btn']/span[contains(text(),'View in')]")
	private WebElement viewInIcon;

	@FindBy(xpath = "//a[contains(text(),'Asset Manager')]")
	private WebElement assetManagerDropDown;

	@FindBy(xpath = "//ul[@class='asset-history-details']/li[4]")
	private WebElement asserHistoryData;

	@FindBy(xpath = "//li[@class='assets-li-assetUploadContinue']/button")
	private WebElement addAssetContinueButton;

	@FindBys(@FindBy(xpath = "//div[@class='assets-dropdown']"))
	private List<WebElement> addAssetDropDropDowns;

	@FindBys(@FindBy(xpath = "//div[@id='activeAssets']/table/tbody/tr[1]"))
	private List<WebElement> activeAssetList;

	@FindBy(xpath = "//div[@id='notification'][@style='display: none;']")
	private WebElement loadingAssetNotification;

	@FindBys(@FindBy(xpath = "//div[@class='assets-dropdown open']/ul/li"))
	private List<WebElement> dropDownOptions;

	@FindBy(xpath = "//ul[@class='assets-upload-wrapper']/li[6]/p/span")
	private WebElement singleSelectRadioButton;

	@FindBy(xpath = "//ul[@class='assets-upload-wrapper']/li[7]/p/span")
	private WebElement uploadRadioButton;

	@FindBys(@FindBy(xpath = "//li[@class='add-asset-inputs']/input"))
	private List<WebElement> addingAssetDetails;

	@FindBy(xpath = "//button[@class='toggle-btn checked']")
	private WebElement addAssetStatusTogglebutton;

	@FindBy(xpath = "//button[@class='btn']")
	private WebElement uploadButton;

	@FindBy(xpath = "//button[@class='addUpdateAsset-btn']")
	private WebElement assetAddedButton;

	@FindBy(xpath = "//input[@placeholder='Asset']")
	private WebElement addedAssetValue;

	@FindBy(xpath = "//div[@id='eightX']")
	private WebElement eightXbutton;

	@FindBy(xpath = "//li[@id='eightX'][@data-name='8xx']")
	private WebElement eightXForUpload;

	@FindBy(xpath = "//input[@id='openNumber']")
	private WebElement searchOpenAssetTextField;

	@FindBy(xpath = "//tbody/tr[2]/td[2]")
	private WebElement searchedAssetList;

	@FindBy(xpath = "//i[@class='fa fa-square-o']")
	private WebElement assetListCheckBox;

	@FindBy(xpath = "//a[@class='deleteAssetOptions']")
	private WebElement deleteSelectedAssetButton;

	@FindBy(xpath = "//input[@id='searchAsset']")
	private WebElement assetFetchButton;

	@FindBy(xpath = "//a[@class='addAssetOptions']")
	private WebElement addSelectedAssetButton;

	@FindBy(xpath = "//div[@class='impt-header']/button[@class='canc']")
	private WebElement backButtonAfterAssetAdded;

	@FindBy(xpath = "//b[@class='new_label']")
	private WebElement newAssetLabel;

	@FindBy(xpath = "//a[@class='sync'][contains(text(),'Sync')]")
	private WebElement syncingLabel;

	@FindBy(xpath = "//input[@placeholder='Search number']")
	private WebElement searchActiveNumberTextBox;

	@FindBys(@FindBy(xpath = "//a[@class='deactive']"))
	private List<WebElement> deactivateButton;

	@FindBy(xpath = "//div[@id='bg-screen'][@style='display: none;']")
	private WebElement blackNotificationAfterAssetAdded;

	@FindBy(xpath = "//div[@id='notification'][contains(text(),'We are updating the active asset list...')][@style]")
	private WebElement weAreUpdatedNotification;

	@FindBy(xpath = "//button[@class='fa fa-ban']")
	private WebElement duplicateAssetFoundIcon;

	@FindBy(xpath = "//i[@class='fa fa-external-link']")
	private WebElement viewInFcbutton;

	@FindBy(xpath = "//button[@class='g-btn-primary g-btn-sm']")
	private WebElement publishButtonInFc;

	@FindBy(xpath = "//div[@class='dropdown']/button[contains(text(),'Select option')]")
	private WebElement selectCancelOption;

	@FindBy(xpath = "//li[@id='back_s_after']")
	private WebElement dontReleaseImmediatelyOption;

	@FindBy(xpath = "//li[@id='back_s_n']")
	private WebElement releaseToOpenPool;

	@FindBy(xpath = "//button[@class='confirm']")
	private WebElement confirmDeactivateButton;

	@FindBys(@FindBy(xpath = "//button[@class='active']"))
	private List<WebElement> InActiveAssetAvailableButton;

	@FindBy(xpath = "//div[@id='inactiveAssets']/table/tbody/tr[1]/td[6]/button[@class='active']")
	private WebElement availableButton;

	@FindBy(xpath = "//div[1]/div[@class='g-input-msg']/span[contains(text(),'Account number doesn')]")
	private WebElement accountNumberNotExistText;

	@FindBy(xpath = "//button[@class='confirm']")
	private WebElement confirmbutton;

	@FindBy(xpath = "//button[@class='confirm-generic']")
	private WebElement releaseToAssetPoolConfirmButton;

	@FindBy(xpath = "//h2[@data-name='inactive']")
	private WebElement inactiveAssetsLabel;

	@FindBy(xpath = "//div[@class='upload-csv']/span")
	private WebElement uploadCsvFileButton;

	@FindBy(xpath = "//input[@class='uploadcsv']")
	private WebElement uploadDomElement;

	@FindBy(xpath = "//a[@class='deleteAssetOptions']")
	private WebElement removeSelectedAssetButton;

	@FindBy(xpath = "//button[@class='confirm-generic']")
	private WebElement confirmDeleteButton;

	@FindBy(xpath = "//div[@class='event_notification_holder'][contains(text(),'Requested Asset not available')]")
	private WebElement assetNotAvailableNotification;

	// switch to asset manager
	public AssetManagerPage clickAssetManagerDropDownFromSettingButton() {
		wait.waitForElementToBeClickable(driver, viewInIcon, 30);
		viewInIcon.click();
		wait.waitForElementToBeClickable(driver, assetManagerDropDown, 30);
		assetManagerDropDown.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage switchToAssetManagerTab() {
		handle.parentToChildWindowControl(getDriver(), 1);
		wait.waitForWebPageLoad(driver, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyIfAssetManagerPageIsLoaded() {
		wait.waitForElementToBeClickable(driver, getAssetDetailsTextField, 30);
		wait.waitForElementToBeClickable(driver, assetHistoryButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickAssetHistoryIcon() {
		wait.waitForElementToBeClickable(driver, assetHistoryButton, 30);
		assetHistoryButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage enterNumberInAsserHistory(String accountNumber) {
		wait.waitForElementToBeClickable(driver, assetHistorySearchButton, 30);
		assetHistorySearchButton.click();
		assetHistorySearchButton.sendKeys(accountNumber + Keys.ENTER);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyAssetHistory() {
		wait.waitForElementToBeVisible(driver, asserHistoryData, 30);
		assetHistoryButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	// fetch an account number in asset manager
	public AssetManagerPage fetchAnAccountNumberInAssetManager(String accountNumber) {
		getAssetDetailsTextField.sendKeys(accountNumber + Keys.ENTER);
		wait.waitForElementToBeVisible(driver, assetManagerNotification, 30);
		wait.waitForAllElementsToBeVisible(driver, deactivateButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage addSingleAsset() {
		wait.waitForElementToBeClickable(driver, gearSettingIcon, 30);

		wait.waitForElementToBeVisible(driver, addAssetButton, 30);
		addAssetButton.click();
		wait.waitForElementToBeClickable(driver, addAssetContinueButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage selectCountryCode() {
		wait.waitForElementToBeClickable(driver, addAssetDropDropDowns.get(0), 30);
		addAssetDropDropDowns.get(0).click();
		wait.waitForAllElementsToBeVisible(driver, dropDownOptions, 30);
		dropDownOptions.get(0).click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickAddNewAssetButton() {
		wait.waitForElementToBeClickable(driver, addAssetButton, 30);
		addAssetButton.click();
		wait.waitForElementToBeClickable(driver, addAssetContinueButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickAddAssetButton() {
		wait.waitForElementToBeClickable(driver, addNewAssetButton, 30);
		addNewAssetButton.click();
		wait.waitForElementToBeClickable(driver, addAssetContinueButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage chooseAssetType() {
		wait.waitForElementToBeClickable(driver, addAssetDropDropDowns.get(1), 30);
		addAssetDropDropDowns.get(1).click();
		wait.waitForAllElementsToBeVisible(driver, dropDownOptions, 30);
		dropDownOptions.get(0).click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage chooseCarriertype() {
		wait.waitForElementToBeClickable(driver, addAssetDropDropDowns.get(2), 30);
		addAssetDropDropDowns.get(2).click();
		wait.waitForAllElementsToBeVisible(driver, dropDownOptions, 30);
		dropDownOptions.get(0).click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage selectAssetStatus() {
		wait.waitForElementToBeClickable(driver, addAssetDropDropDowns.get(3), 30);
		addAssetDropDropDowns.get(3).click();
		wait.waitForAllElementsToBeVisible(driver, dropDownOptions, 30);
		dropDownOptions.get(0).click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage selectSingleAssetOption() {
		singleSelectRadioButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage enterRandomAccountNumberDetails() {
		wait.waitForAllElementsToBeVisible(driver, addingAssetDetails, 30);
		addingAssetDetails.get(0).sendKeys(assetManagerData.randomTenDigitData());
		addingAssetDetails.get(1).sendKeys("10");
		// addingAssetDetails.get(2).sendKeys(assetManagerData.randomTenDigitData());

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickSubmitButton() {
		addAssetContinueButton.click();
		wait.waitForElementToBeVisible(driver, addAssetStatusTogglebutton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickAssetUploadButton() {
		wait.waitForElementToBeClickable(driver, uploadButton, 30);
		uploadButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage reloadAssetManagerPage() {
		driver.navigate().refresh();
		wait.waitForWebPageLoad(driver, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage reloadAndConfirmAlert() {
		driver.navigate().refresh();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().defaultContent();
		wait.waitForWebPageLoad(driver, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyIfAssetAdded() {
		wait.waitForElementToBeVisible(driver, assetAddedButton, 30);
		wait.waitForElementToBeClickable(driver, backButtonAfterAssetAdded, 30);
		backButtonAfterAssetAdded.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickEightXTab() {
		wait.waitForElementToBeClickable(driver, eightXbutton, 30);
		eightXbutton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickSearchTextField() {
		wait.waitForElementToBeClickable(driver, searchOpenAssetTextField, 30);
		searchOpenAssetTextField.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage enterAddedAssetValue() {
		searchOpenAssetTextField.clear();
		searchOpenAssetTextField.sendKeys(AssetManagerTestScripts.addedAsset + Keys.ENTER);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage searchedAssetNumber() {
		wait.waitForElementToBeVisible(driver, searchedAssetList, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage fetchAccountInAssetManager(String accountNumber) {
		wait.waitForElementToBeClickable(driver, assetFetchButton, 30);
		assetFetchButton.click();
		assetFetchButton.sendKeys(accountNumber + Keys.ENTER);

		wait.waitForAllElementsToBeVisible(driver, deactivateButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickSearchedAssetCheckBox() {
		wait.waitForElementToBeClickable(driver, assetListCheckBox, 30);
		assetListCheckBox.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage addSelectedAsset() {
		wait.waitForElementToBeClickable(driver, addSelectedAssetButton, 30);
		addSelectedAssetButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyAddedAssetInActiveList() {
		wait.waitForElementToBeVisible(driver, weAreUpdatedNotification, 30);
		wait.waitForElementToBeClickable(driver, searchActiveNumberTextBox, 30);
		searchActiveNumberTextBox.click();
		searchActiveNumberTextBox.sendKeys(AssetManagerTestScripts.addedAsset + Keys.ENTER);
		wait.waitForElementToBeVisible(driver, syncingLabel, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickViewInFCButton() {
		wait.waitForElementToBeClickable(driver, viewInFcbutton, 30);
		viewInFcbutton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage switchingToFcTab() {
		handle.parentToChildWindowControl(getDriver(), 2);
		wait.waitForWebPageLoad(driver, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyInFc() {
		wait.waitForElementToBeClickable(driver, publishButtonInFc, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage switchBackToAssetManagerTab() {
		handle.childToParentWindowControl(driver);
		handle.parentToChildWindowControl(getDriver(), 1);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage moveToInactiveState() {
		wait.waitForElementToBeClickable(driver, deactivateButton.get(0), 30);
		deactivateButton.get(0).click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickCancelActionButton() {
		wait.waitForElementToBeClickable(driver, selectCancelOption, 30);
		selectCancelOption.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage selectDontReleaseOption() {
		wait.waitForElementToBeClickable(driver, dontReleaseImmediatelyOption, 30);
		dontReleaseImmediatelyOption.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickConfirmButton() {
		wait.waitForElementToBeClickable(driver, confirmDeactivateButton, 30);
		confirmDeactivateButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyIfAssetAddedInInActiveList() {
		wait.waitForElementToBeVisible(driver, availableButton, 30);
		wait.waitForElementToBeClickable(driver, availableButton, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage switchToFcToFetchInActiveAsset() {
		handle.parentToChildWindowControl(getDriver(), 2);
		driver.navigate().refresh();
		wait.waitForElementToBeVisible(driver, accountNumberNotExistText, 30);
		handle.childWindowClose(driver, 2);
		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage closeFcPage() {
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(windows);
		driver.switchTo().window(tabs.get(1));

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage makeInactiveAssetToActive() {
		wait.waitForElementToBeClickable(driver, inactiveAssetsLabel, 30);
		inactiveAssetsLabel.click();
		wait.waitForElementToBeClickable(driver, availableButton, 30);
		availableButton.click();
		wait.waitForElementToBeClickable(driver, confirmbutton, 30);
		confirmbutton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyAssetAddedToActiveList() {
		wait.waitForElementToBeVisible(driver, newAssetLabel, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage removeAssetFromActiveList() {
		wait.waitForElementToBeClickable(driver, deactivateButton.get(0), 30);
		deactivateButton.get(0).click();
		wait.waitForElementToBeClickable(driver, selectCancelOption, 30);
		selectCancelOption.click();
		wait.waitForElementToBeClickable(driver, releaseToOpenPool, 30);
		releaseToOpenPool.click();
		wait.waitForElementToBeClickable(driver, confirmbutton, 30);
		confirmbutton.click();
		wait.waitForElementToBeClickable(driver, releaseToAssetPoolConfirmButton, 30);
		releaseToAssetPoolConfirmButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage selectAssetUploadOption() {
		wait.waitForElementToBeClickable(driver, uploadRadioButton, 30);
		uploadRadioButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage uploadCsvAssetFile() {
		uploadDomElement.sendKeys(new File("./TestData/8xxNumbers.csv").getAbsolutePath());

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage updateUploadFileValue() {
		wait.waitForElementToBeClickable(driver, addedAssetValue, 30);
		addedAssetValue.clear();
		addedAssetValue.sendKeys(assetManagerData.randomTenDigitData());

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage clickEightXXTab() {
		wait.waitForElementToBeClickable(driver, eightXForUpload, 30);
		eightXForUpload.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage deleteSelectedAsset() {
		wait.waitForElementToBeClickable(driver, removeSelectedAssetButton, 30);
		removeSelectedAssetButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage confirmDeleteButton() {
		wait.waitForElementToBeClickable(driver, confirmDeleteButton, 30);
		confirmDeleteButton.click();

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage verifyNotification() {
		wait.waitForElementToBeVisible(driver, assetNotAvailableNotification, 30);

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public AssetManagerPage closeAssetMaanagerAndSwitchToFc() {
		driver.navigate().refresh();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().defaultContent();
		driver.close();
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(windows);
		driver.switchTo().window(tabs.get(0));

		return PageFactory.initElements(getDriver(), AssetManagerPage.class);
	}

	public String getAddedAssetValue() {
		return addedAssetValue.getAttribute("value");
	}

	public String getSearchedAssetList() {
		return searchedAssetList.getText();
	}

}
