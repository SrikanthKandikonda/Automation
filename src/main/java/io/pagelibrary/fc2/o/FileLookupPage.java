package io.pagelibrary.fc2.o;

import java.io.File;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;
import io.utils.fc2.o.WindowHandles;

public class FileLookupPage extends HomePage {

	Wait wait = new Wait();
	Scroll scroll = new Scroll();
	WindowHandles handle = new WindowHandles();
	RandomData lookupData = new RandomData();

	WebDriver driver;

	public FileLookupPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[contains(@class, 'dropdowns messageFormDiv')]//i")
	public WebElement selectFormButton;

	@FindBys(@FindBy(xpath = "//ul[@class='ulFormDiv']//li"))
	public List<WebElement> formInSelectFormButton;

	@FindBy(xpath = "//button[@class='createLookup']")
	public WebElement createOrUpdateLookUpButton;

	@FindBy(xpath = "//a[@class='searchNewAccount']")
	public WebElement searchNewAccountButton;

	@FindBy(xpath = "//div[@class='accontDetails']//li[text()='Account Number : ']//span")
	public WebElement accountNumberText;

	@FindBy(xpath = "//a[@class='mailId']")
	public WebElement mailIdText;

	@FindBy(xpath = "//input[@class='lookup']")
	public WebElement lookUpNameTextField;

	@FindBy(xpath = "//div[@class = 'lookupNotification']")
	public WebElement lookUpNotification;

	@FindBy(xpath = "//div[@class = 'loading']")
	public WebElement lookUpLoading;

	@FindBy(xpath = "//div[@class='accontDetails']//li[text()='Message Form : ']//span")
	public WebElement messageFormText;

	@FindBy(xpath = "//div[@class='accontDetails']//li[text()='Message Form : ']//i")
	public WebElement editLookUpButton;

	@FindBy(xpath = "//a[@class='cancelFetch']")
	public WebElement cancelLookUpUpdateButton;

	@FindBy(xpath = "//div[@class='uploadFile']//div[@class='file-wrapper']//button//following-sibling::input")
	public WebElement chooseFileField;

	@FindBy(xpath = "//div[@class='uploadFile']//div[@class='file-wrapper']//button")
	public WebElement chooseFileButton;

	@FindBy(xpath = "//div[@class='deleteLookup-container']//a[@class='deleteLookup']")
	public WebElement deleteLookUpButton;

	@FindBy(xpath = "//div[@class='createFranchise-container']//a[@class='createFranchise link_common']")
	public WebElement createLookUPButton;

	@FindBy(xpath = "//div[@class='joinFranchise-container']//a[@class='joinFranchise link_common']")
	public WebElement joinFranchisButton;

	@FindBy(xpath = "//div[@class='dropdowns']//input[@class ='searchFranchise']")
	public WebElement enterFranchiseNameTextField;

	@FindBy(xpath = "//div[@class='franchise-popup']")
	public WebElement createOrJoinFranchiseBlock;

	@FindBy(xpath = "//div[@class='franchise-footer']//button[@class = 'franchisePopupJoinButton']")
	public WebElement createAndJoinFranchiseButton;

	@FindBy(xpath = "//div[@class='franchise-header']//span[@class = 'fa fa-close']")
	public WebElement closeCreateFranchiseBlock;

	@FindBy(xpath = "//div[@class='accontDetails']//li[text()='Lookup Name : ']//span")
	public WebElement lookUpNameText;

	@FindBy(xpath = "//button[@class='done']")
	public WebElement lookUpSaveButton;

	@FindBy(xpath = "//div[@class='viewTotalFiles']//p//span")
	public WebElement viewTotalLookUps;

	@FindBy(xpath = "//div[@class='popup']")
	public WebElement selectFileTypePopUpBlock;

	@FindBy(xpath = "//div[@class='popup']//button[@class='globalFile']")
	public WebElement selectFileTypeAsGlobalButton;

	@FindBy(xpath = "//div[@class='popup']//button[@class='localFile']")
	public WebElement selectFileTypeAsLocalButton;

	@FindBy(xpath = "//li[@class = 'fieldSelect']")
	public WebElement selectField;

	@FindBys(@FindBy(xpath = "//li[@class = 'fieldSelect']//ul//li"))
	public List<WebElement> optionsInSelectField;

	@FindBy(xpath = "//li[@class = 'operatorSelect']")
	public WebElement selectOperator;

	@FindBys(@FindBy(xpath = "//li[@class = 'operatorSelect']//ul//li"))
	public List<WebElement> optionsInSelectOperator;

	@FindBy(xpath = "//li[@class = 'fileSelect']")
	public WebElement selectFile;

	@FindBys(@FindBy(xpath = "//li[@class = 'fileSelect']//ul//li"))
	public List<WebElement> optionsInSelectFile;

	@FindBy(xpath = "//li[@class = 'columnSelect']")
	public WebElement selectColoumn;

	@FindBys(@FindBy(xpath = "//li[@class = 'columnSelect']//ul//li"))
	public List<WebElement> optionsInSelectColoumn;

	@FindBy(xpath = "//span[contains(text() ,'Choose Result')]")
	public WebElement chooseResult;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'dropdowns open')]//ul//li"))
	public List<WebElement> optionsInChooseResult;

	@FindBys(@FindBy(xpath = "//ul[@class='selected']//li"))
	public List<WebElement> selectedResults;

	@FindBy(xpath = "//div[@class='joinFranchise-container']//i[@class='fa fa-trash']")
	public WebElement deleteFranchiseButton;

	@FindBy(xpath = "//button[@class='g-drop-btn']/span[contains(text(),'View in')]")
	public WebElement viewInDropDown;

	@FindBy(xpath = "//a[contains(text(),'File Lookup')]")
	public WebElement fileLookup;

	@FindBy(xpath = "//a[normalize-space()='Delete Lookup']")
	public WebElement deleteLookupButton;

	@FindBy(xpath = "//button[normalize-space()='Create Lookup']")
	public WebElement createLookupButton;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/nav[1]/div[1]/div[1]/button[1]/*[local-name()='svg'][1]/*[name()='use'][1]")
	public WebElement fetchButton;

	@FindBy(xpath = "//input[@name='lookupName']")
	public WebElement lookupNameTextBox;

	@FindBy(xpath = "//div[@class='file-wrapper']//button")
	public WebElement uploadFileButton;

	@FindBy(xpath = "//input[@type='file']")
	public WebElement uploadFileInputButton;

	@FindBy(xpath = "//span[normalize-space()='Select Field']")
	public WebElement selectFieldOneDropDown;

	@FindBy(xpath = "//li[@id='Zip/Postal']")
	public WebElement postalCode;

	@FindBy(xpath = "//span[normalize-space()='Select Operator']")
	public WebElement operatorDropDown;

	@FindBy(xpath = "//ul/li[2][contains(text(),'Contains')]")
	public WebElement operatorContains;

	@FindBys(@FindBy(xpath = "//div[@class='lookupNotification' and @style='display: block;' ]"))
	public List<WebElement> notification;

	@FindBy(xpath = "//li[@class='fileSelect']")
	public WebElement selectFileDropDown;

	@FindBy(xpath = "//div[@class='dropdowns open']/ul/li")
	public WebElement selectUploadedFile;

	@FindBy(xpath = "//span[normalize-space()='Select Column']")
	public WebElement selectColumn;

	@FindBy(xpath = "//div[@class='dropdowns open']//li[contains(text(),'asset')]")
	public WebElement selectAssetOption;

	@FindBy(xpath = "//span[normalize-space()='Choose Result']")
	public WebElement chooseResultOption;

	@FindBy(xpath = "//li[normalize-space()='All']")
	public WebElement listAllResultOption;

	@FindBy(xpath = "//ul[@class='selected']//li[contains(text(),'asset')]")
	public WebElement allResult;

	@FindBy(xpath = "//ul[@class='selected']//li[contains(text(),'coolingperiod')]")
	public WebElement coolingPeriodResult;

	@FindBy(xpath = "//ul[@class='selected']//li[contains(text(),'forwarding')]")
	public WebElement forwardingResult;

	@FindBy(xpath = "//a[normalize-space()='SwitchBoard']")
	public WebElement viewInSb;

	@FindBy(xpath = "//div[@class='greeting']//i[@class='close-btn']")
	public WebElement answerPhraseCloseIcon;

	@FindBy(xpath = "//div[@class='input-field'][5]/input")
	public WebElement zipCodeTextField;

	@FindBy(xpath = "//a[normalize-space()='+1 800-000-0000']")
	public WebElement sbAssetDetails;

	@FindBy(xpath = "//td[normalize-space()='10']")
	public WebElement sbCoolingPeriod;

	@FindBy(xpath = "//tr[4]/td[3][contains(text(),'')]")
	public WebElement sbforwarding;

	@FindBy(xpath = "//a[normalize-space()='Create Franchise']")
	public WebElement createFranchiseButton;

	@FindBy(xpath = "//input[@placeholder='Enter the franchise name...']")
	public WebElement createFranchiseTextBox;

	@FindBy(xpath = "//button[normalize-space()='Create and Join']")
	public WebElement createAndJoinButton;

	@FindBy(xpath = "//span[normalize-space()='Franchise already exists']")
	public WebElement franchiseAlreadyExist;

	@FindBy(xpath = "//span[@class='fa fa-close']")
	public WebElement closeFranchisePopUp;

	@FindBy(xpath = "//a[normalize-space()='Join Franchise']")
	public WebElement joinFranchiseButton;

	@FindBy(xpath = "//input[@placeholder='Search the franchise name...']")
	public WebElement searchFranchiseTextBox;

	@FindBy(xpath = "//button[normalize-space()='Join']")
	public WebElement joinFranchisePopUpButton;

	@FindBy(xpath = "//div[@class='joinFranchise-container']//div//i[@class='fa fa-trash']")
	public WebElement removeFranchiseIcon;

	@FindBy(xpath = "//button[normalize-space()='Choose a File']")
	public WebElement uploadGlobalFileButton;

	@FindBy(xpath = "//button[normalize-space()='Global']")
	public WebElement globalButton;

	@FindBy(xpath = "//div[@class='file-wrapper']/input[@name]")
	public WebElement globalFile;

	@FindBy(xpath = "//li[@class='fileSelect']//i[@class='fa fa-angle-down']")
	public WebElement lookupFileDropDown;

	@FindBy(xpath = "//span[normalize-space()='2']")
	public WebElement allFilesTwo;

	@FindBy(xpath = "//div[@class='global_files']/ul/li/div/span[@class='fa fa-trash deleteFile']")
	public WebElement uploadedGlobalFileDeleteIcon;

	@FindBy(xpath = "//li[@class='fileSelect']/div/ul/li[2]")
	public WebElement selectGlobalFile;

	@FindBy(xpath = "//span[@class='fa fa-plus-circle addCondition']")
	public WebElement addFilterIcon;

	@FindBy(xpath = "//div[@data='and']//span[contains(text(),'Or')]//i")
	public WebElement orCondition;

	@FindBy(xpath = "//div[@class='lookupFilter']//div[2]//div[1]//ul[1]//li[1]//div[1]//a[1]//i[1]")
	public WebElement orSelectField;

	@FindBy(xpath = "//div[@class='dropdowns open']//li[@id='First']")
	public WebElement orfirstNameDropDownOption;

	@FindBy(xpath = "//div[@class='lookupFilter']//div[2]//div[1]//ul[1]//li[2]//div[1]//a[1]//i[1]")
	public WebElement orSelectOperator;

	@FindBys(@FindBy(xpath = "//ul/li[2][contains(text(),'Contains')]"))
	public List<WebElement> orChooseOperator;

	@FindBys(@FindBy(xpath = "//li[@class='fileSelect']"))
	public List<WebElement> orSelectFileDropDown;

	@FindBys(@FindBy(xpath = "//div[@class='dropdowns open']//li"))
	public List<WebElement> orChooseFile;

	@FindBy(xpath = "//div[@class='dropdowns open']//li[contains(text(),'FirstName')]")
	public WebElement orFirstNameDropDownOption;

	@FindBys(@FindBy(xpath = "//span[normalize-space()='Choose Result']"))
	public List<WebElement> orChooseResultDropDown;

	@FindBy(xpath = "//div[@class='dropdowns open']//li[contains(text(),'LastName')]")
	public WebElement orLastNameResultOption;

	@FindBy(xpath = "//ul[@class='selected']//li[contains(text(),'LastName')]")
	public WebElement orLastNameResults;

	@FindBy(xpath = "//div[@class='dropdowns open']//li[@id='Zip/Postal']")
	public WebElement orZipCode;

	@FindBy(xpath = "//td[normalize-space()='Payne']")
	public WebElement orSbPayne;

	@FindBys(@FindBy(xpath = "//span[@class='fa fa-trash deleteCondition']"))
	public List<WebElement> deleteFilterIcon;

	@FindBy(xpath = "//span[@title='Delete File']")
	public WebElement deleteGlobalFile;

	@FindBy(xpath = "//div[@class='viewTotalFiles']/p/span[contains(text(),'1')]")
	public WebElement totalFilesOne;

	@FindBys(@FindBy(xpath = "//span[normalize-space()='No lookup information found']"))
	public List<WebElement> noLookupFound;

	@FindBy(xpath = "//label[@class='g-input-label fieldLabel'][contains(text(),'Zip/Postal Code')]")
	public WebElement zipCodeFC;

	@FindBy(xpath = "//span[@class='text--sm tw-m']")
	public WebElement lookupConfiguredInFC;

	@Override
	public FileLookupPage clickViewInDropDown() {
		wait.waitForElementToBeClickable(driver, viewInDropDown, 30);
		viewInDropDown.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickFetchButton() {
		wait.waitForElementToBeClickable(driver, fetchButton, 30);
		fetchButton.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickFileLookupDropDown() {
		wait.waitForElementToBeClickable(driver, fileLookup, 30);
		fileLookup.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage switchToFileLookupTab() {
		handle.parentToChildWindowControl(getDriver(), 1);
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage closeFileLookupPage() {
		handle.childWindowClose(driver, 1);
		handle.childToParentWindowControl(driver);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage deleteExistingLookup() {
		try {
			wait.waitForElementToBeClickable(driver, deleteLookupButton, 10);
			deleteLookupButton.click();
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			wait.waitForElementToBeInvisible(driver, noLookupFound, 30);
		}
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage enterLookupName() {
		wait.waitForElementToBeClickable(driver, lookupNameTextBox, 30);
		String lookupName = lookupData.randomData("New Lookup ");
		lookupNameTextBox.sendKeys(lookupName);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickCreateNewLookupButton() {
		try {
			wait.waitForElementToBeClickable(driver, createLookupButton, 30);
			createLookupButton.click();

		} catch (Exception e) {
			wait.waitForElementToBeInvisible(driver, notification, 10);
			clickCreateNewLookupButton();
		}
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage uploadFirstLookupCSV() {
		wait.waitForElementToBeInvisible(driver, notification, 30);
		wait.waitForElementToBeVisible(driver, uploadFileButton, 30);
		wait.waitForElementToBeClickable(driver, uploadFileButton, 30);
		scroll.scrollUpToWebElement(driver, uploadFileButton);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementsByClassName('button chooseFile')[0].setAttribute('data', 'local');");
		uploadFileInputButton.sendKeys(new File("./TestData/8xxNumbers.csv").getAbsolutePath());
		wait.waitForElementToBeClickable(driver, lookUpSaveButton, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage selectLookupField() {
		wait.waitForElementToBeClickable(driver, selectFieldOneDropDown, 30);
		selectFieldOneDropDown.click();
		postalCode.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage selectLookupOperator() {
		operatorDropDown.click();
		wait.waitForElementToBeClickable(driver, operatorContains, 30);
		operatorContains.click();

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage selectLookupFile() {
		selectFileDropDown.click();
		wait.waitForElementToBeClickable(driver, selectUploadedFile, 30);
		selectUploadedFile.click();

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage selectLookupColumn() {
		selectColumn.click();
		wait.waitForElementToBeClickable(driver, selectAssetOption, 30);
		selectAssetOption.click();

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage selectResultsToDisplay() {
		chooseResultOption.click();
		wait.waitForElementToBeClickable(driver, listAllResultOption, 30);
		listAllResultOption.click();
		wait.waitForElementToBeVisible(driver, allResult, 30);
		wait.waitForElementToBeVisible(driver, coolingPeriodResult, 30);
		wait.waitForElementToBeVisible(driver, forwardingResult, 30);

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage saveLookupChanges() {
		wait.waitForElementToBeClickable(driver, lookUpSaveButton, 30);
		lookUpSaveButton.click();
		wait.waitForElementToBeInvisible(driver, notification, 30);

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage switchToFcTab() {
		handle.parentToChildWindowControl(getDriver(), 0);
		wait.waitForWebPageLoad(driver, 30);

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickSBDropDown() {
		wait.waitForElementToBeClickable(driver, viewInSb, 30);
		viewInSb.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage switchToSb() {
		handle.parentToChildWindowControl(getDriver(), 2);
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage switchToSbFinal() {
		handle.parentToChildWindowControl(getDriver(), 1);
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage verifyLookUpInSb() {
		wait.waitForElementToBeClickable(driver, answerPhraseCloseIcon, 50);
		answerPhraseCloseIcon.click();
		wait.waitForElementToBeClickable(driver, zipCodeTextField, 30);
		zipCodeTextField.sendKeys("8000000000" + Keys.ENTER);
		try {
			if (sbAssetDetails.isDisplayed()) {
				wait.waitForElementToBeVisible(driver, sbCoolingPeriod, 30);
				wait.waitForElementToBeVisible(driver, sbforwarding, 30);
			}
		} catch (Exception e) {
			System.out.println("lookup data not updated yet in SB...");
			driver.navigate().refresh();
			verifyLookUpInSb();
		}

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage closeSbPage() {
		handle.childWindowClose(getDriver(), 2);
		handle.parentToChildWindowControl(getDriver(), 1);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage closeSbPageFinalCheck() {
		handle.childWindowClose(getDriver(), 1);
		handle.parentToChildWindowControl(getDriver(), 0);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickCreateFranchiseButton() {
		driver.navigate().refresh();
		wait.waitForElementToBeClickable(driver, createFranchiseButton, 30);
		createFranchiseButton.click();
		wait.waitForElementToBeClickable(driver, createFranchiseTextBox, 30);
		createFranchiseTextBox.sendKeys("TestAutomation");
		wait.waitForElementToBeClickable(driver, createAndJoinButton, 30);
		createAndJoinButton.click();
		wait.waitForElementToBeVisible(driver, franchiseAlreadyExist, 30);
		closeFranchisePopUp.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage EnterNewFranchiseName() {

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickRemoveFranchiseIcon() {

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickJoinFranchiseButton() {
		wait.waitForElementToBeClickable(driver, joinFranchiseButton, 30);
		joinFranchiseButton.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage enterFranchiseName() {
		wait.waitForElementToBeClickable(driver, searchFranchiseTextBox, 30);
		searchFranchiseTextBox.sendKeys("TestAutomation");
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickJoinFranchise() {
		wait.waitForElementToBeClickable(driver, joinFranchisePopUpButton, 30);
		joinFranchisePopUpButton.click();
		wait.waitForElementToBeClickable(driver, removeFranchiseIcon, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage uploadGlobalCSVFile() {
		try {
			wait.waitForElementToBeClickable(driver, uploadGlobalFileButton, 10);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(
					"document.getElementsByClassName('button chooseFile')[0].setAttribute('data', 'global');");
			globalFile.sendKeys(new File("./TestData/Staff.csv").getAbsolutePath());
			wait.waitForElementToBeClickable(driver, lookUpSaveButton, 10);
		} catch (Exception E) {
			driver.switchTo().alert().accept();
			// System.out.println(E);
		}
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage verifyIfGlobalFileUploaded() {
		wait.waitForElementToBeClickable(driver, allFilesTwo, 30);
		allFilesTwo.click();
		wait.waitForElementToBeClickable(driver, uploadedGlobalFileDeleteIcon, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickORCondition() {
		wait.waitForElementToBeClickable(driver, addFilterIcon, 30);
		addFilterIcon.click();
		wait.waitForElementToBeClickable(driver, orCondition, 30);
		orCondition.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage clickANDCondition() {
		wait.waitForElementToBeClickable(driver, addFilterIcon, 30);
		addFilterIcon.click();

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage orConditionSelectField() {
		wait.waitForElementToBeClickable(driver, orSelectField, 30);
		orSelectField.click();
		wait.waitForElementToBeClickable(driver, orZipCode, 30);
		orZipCode.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage orConditionSelectOperator() {
		wait.waitForElementToBeClickable(driver, orSelectOperator, 30);
		orSelectOperator.click();
		wait.waitForElementToBeClickable(driver, orChooseOperator.get(1), 0);
		orChooseOperator.get(1).click();

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage orConditionSelectFile() {
		wait.waitForElementToBeClickable(driver, orSelectFileDropDown.get(1), 30);
		orSelectFileDropDown.get(1).click();
		wait.waitForElementToBeClickable(driver, orChooseFile.get(1), 0);
		orChooseFile.get(1).click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage orConditionSelectColumn() {
		wait.waitForElementToBeClickable(driver, selectColumn, 30);
		selectColumn.click();
		wait.waitForElementToBeClickable(driver, orFirstNameDropDownOption, 0);
		orFirstNameDropDownOption.click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage orConditionSelectResult() {
		wait.waitForElementToBeClickable(driver, orChooseResultDropDown.get(1), 30);
		orChooseResultDropDown.get(1).click();
		wait.waitForElementToBeClickable(driver, orLastNameResultOption, 30);
		orLastNameResultOption.click();
		wait.waitForElementToBeVisible(driver, orLastNameResults, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage verifyOrConditionLookUpInSb() {
		wait.waitForElementToBeClickable(driver, answerPhraseCloseIcon, 40);
		answerPhraseCloseIcon.click();
		wait.waitForElementToBeClickable(driver, zipCodeTextField, 30);
		zipCodeTextField.sendKeys("Kevin" + Keys.ENTER);
		try {
			if (orSbPayne.isDisplayed()) {

			}
		} catch (Exception e) {
			driver.navigate().refresh();
			verifyOrConditionLookUpInSb();
		}
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage deleteConditionFilter() {
		wait.waitForElementToBeClickable(driver, deleteFilterIcon.get(1), 30);
		deleteFilterIcon.get(1).click();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage removeGlobalCSVFile() {
		wait.waitForElementToBeClickable(driver, allFilesTwo, 30);
		allFilesTwo.click();
		wait.waitForElementToBeClickable(driver, deleteGlobalFile, 30);
		deleteGlobalFile.click();
		driver.switchTo().alert().accept();
		wait.waitForElementToBeClickable(driver, lookUpSaveButton, 30);
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage removeFranchise() {
		wait.waitForElementToBeClickable(driver, totalFilesOne, 30);
		wait.waitForElementToBeClickable(driver, deleteFranchiseButton, 30);
		deleteFranchiseButton.click();
		driver.switchTo().alert().accept();
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage verifyNoLookupIsConfigured() {
		wait.waitForElementToBeClickable(driver, answerPhraseCloseIcon, 40);
		answerPhraseCloseIcon.click();
		wait.waitForElementToBeClickable(driver, zipCodeTextField, 30);
		zipCodeTextField.sendKeys("8000000000" + Keys.ENTER);
		try {
			if (sbAssetDetails.isDisplayed() == false) {

			}
		} catch (Exception e) {
			driver.navigate().refresh();
			verifyNoLookupIsConfigured();
		}
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage verifylookupConfigurationInFC() {
		wait.waitForElementToBeClickable(driver, zipCodeFC, 30);
		zipCodeFC.click();
		wait.waitForElementToBeVisible(driver, lookupConfiguredInFC, 10);

		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

	public FileLookupPage verifylookupNotConfiguredInFC() {
		try {
			wait.waitForElementToBeClickable(driver, zipCodeFC, 30);
			zipCodeFC.click();
			wait.waitForElementToBeVisible(driver, lookupConfiguredInFC, 10);

		} catch (Exception e) {
			getReport().log(Status.PASS, "Lookup configuration removed from FC field");
		}
		return PageFactory.initElements(getDriver(), FileLookupPage.class);
	}

}
