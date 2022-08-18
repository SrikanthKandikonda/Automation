package io.pagelibrary.fc2.o;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;

public class StaffPage extends HomePage {
	Wait wait = new Wait();
	Scroll scroll = new Scroll();
	WebDriver driver;
	RandomData staffData = new RandomData();
	private static String newStaffName;
	private static String newStaffRole;
	private static String newStaffPhoneNumber;
	private static String newStaffCountryCode;
	private static String newStaffEmail;
	private static String newStaffWeb;
	private static String newStaffFaxNumber;
	private static String newStaffText;
	private static String newStaffMailText;
	private static String newStaffEmailAttachment;
	private static String newStaffEmailCsv;
	private static String newDeliveryGroupName;
	private static boolean staffIsDeleted = true;
	private static boolean deliveryGroupIsDeleted = true;
	private static List<String> multipleNewDeliveryGroupName = new ArrayList<>();
	private static List<String> multipleStaffName = new ArrayList<>();
	private static int selectContactMethodIndex;
	private static String customPhoneName;

	public StaffPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//nav[@class='g-header-nav ps-a']//a[@data-attr='Staffs']")
	private WebElement staffButton;

	@FindBy(xpath = "//nav[@class='g-header-nav ps-a']//a[@data-attr='Delivery Groups']")
	private WebElement deliveryGroupButton;

	@FindBy(xpath = "//nav[@class='g-header-nav ps-a']//div//button")
	private WebElement staffSortByDropDownButton;

	@FindBy(xpath = "//nav[@class='g-header-nav ps-a']//div[contains(@class,'open')]//div[@class='g-dropmenu has-arrow']")
	private WebElement staffSortByDropDownBlock;

	@FindBys(@FindBy(xpath = "//nav[@class='g-header-nav ps-a']//div[@class='g-dropmenu has-arrow']//ul/li"))
	private List<WebElement> staffSortByDropDownOptions;

	@FindBy(xpath = "//li[@data-attr='CSV-U']//input[@type='file']")
	private WebElement uploadCsvField;

	@FindBy(xpath = "//div[@class='add-to-list mt-2']//figure")
	private WebElement addStaffButton;

	@FindBys(@FindBy(xpath = "//ul[@id='staff-sidebar']//li"))
	private List<WebElement> listOfStaff;

	@FindBys(@FindBy(xpath = "//ul[@id='staff-sidebar']//li//h4"))
	private List<WebElement> listOfStaffNames;

	@FindBy(xpath = "//aside[@class='g-sidebar full--h bdr-r multiple-select-view']//button[contains(@class,'mr-2 va-m g-checkbox')]")
	private WebElement selectAllStaffCheckBox;

	@FindBy(xpath = "//aside[@class='g-sidebar full--h bdr-r multiple-select-view']//i[@class='close va-m']")
	private WebElement cancelMutipleStaffSelectMode;

	@FindBy(xpath = "//*[name()='svg' and @class='icon va-m']//*[name()='use']")
	private WebElement addMultipleStaffToDeliveryGroupButton;

	@FindBy(xpath = "//*[name()='svg' and @class='icon ml-4 va-m']")
	private WebElement deleteMultipleStaffButton;

	@FindBys(@FindBy(xpath = "//ul[@id='staff-sidebar']//li//figure//figcaption[text()]"))
	private List<WebElement> multipleStaffSelectButtons;

	@FindBys(@FindBy(xpath = "//ul[@id='staff-sidebar']//li//button"))
	private List<WebElement> multipleStaffSelectCheckBoxes;

	// Add New Staff Block

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//li[1]//input")
	private WebElement staffNameTextField;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//li[2]//input")
	private WebElement staffRoleTextField;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//button[@class='g-drop-btn add-step']")
	private WebElement addFieldToNewStaffDropDownButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='g-dropmenu has-arrow']")
	private WebElement addFieldToStaffOptionsBlock;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='g-dropdown-wrap dropdown--manipulate add-field top-pos open']//div[@class='g-dropmenu has-arrow']//ul//li"))
	private List<WebElement> addFieldToStaffOptions;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//button[@class='g-btn-primary']")
	private WebElement saveAddStaffButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//button[@class='g-btn-negative']")
	private WebElement cancelAddStaffButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//i[@class='g-modal-close']")
	private WebElement closeAddStaffBlock;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='phone-field-wrap ext']//button"))
	private List<WebElement> phoneFieldCountryCodeDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='phone-field-wrap ext']//div[@class='g-dropdown-wrap has-search g-country-dropdown open']//div[@class='g-dropmenu has-arrow align-left']//ul//li//button"))
	private List<WebElement> phoneFieldCountryCodeOptions;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='phone-field-wrap ext']//div[@class='g-dropdown-wrap has-search g-country-dropdown open']//div[@class='g-dropmenu has-arrow align-left']//div[@class='g-searchbox ignore-click']//input")
	private WebElement phoneFieldCountryCodeSearchTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='phone-field-wrap ext']//input[@class='g-input']"))
	private List<WebElement> phoneNumberTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='phone-field-wrap ext']//input[@class='g-input ext-field ml-2']"))
	private List<WebElement> phoneNumberExtensionTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Email']//following-sibling::input[@class='g-input']"))
	private List<WebElement> emailTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Web']//following-sibling::input[@class='g-input']"))
	private List<WebElement> webTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Fax']//following-sibling::input[@class='g-input']"))
	private List<WebElement> faxTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Text']//following-sibling::input[@class='g-input']"))
	private List<WebElement> textTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Mail Text']//following-sibling::input[@class='g-input']"))
	private List<WebElement> mailTextTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Email Attachment']//following-sibling::input[@class='g-input']"))
	private List<WebElement> emailAttachmentTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//label[text()='Email CSV']//following-sibling::input[@class='g-input']"))
	private List<WebElement> emailCsvTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//span[@class='popover-btn mr-1 tw-m']"))
	private List<WebElement> deleteAddedFieldDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='g-popover__content has-arrow']//ul//li[1]//div"))
	private List<WebElement> makeAddedFieldAsPrivateToggleButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']//div[@class='g-popover__content has-arrow']//ul//li[2]"))
	private List<WebElement> deleteAddedFieldButton;

	// Update Staff Block

	@FindBy(xpath = "//div[@class='profile-details ps-r']//input[@class='contact-name g-input']")
	private WebElement updateStaffNameTextField;

	@FindBy(xpath = "//h2[@class='input-swap g-input']//span")
	private WebElement updateDeliveryGroupTextField;

	@FindBy(xpath = "//div[@class='profile-details ps-r']//input[@class='designation g-input tw-m']")
	private WebElement staffDesignationTextField;

	@FindBy(xpath = "//div[@class='profile-details ps-r']//button[@class='g-drop-btn']")
	private WebElement addToGroupDropDownButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate has-search add open']//div[@class='g-dropmenu has-arrow']//input")
	private WebElement searchDeliveryGroupInAddToGroupOptions;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate has-search add open']//div[@class='g-dropmenu has-arrow']//li[@class='has-avatar add-new']")
	private WebElement addNewDeliveryGroupFromAddToGroupOptions;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate has-search add open']//div[@class='g-dropmenu has-arrow']//li[@class='has-avatar']"))
	private List<WebElement> existingDeliveryGroupsInAddToGroupOptions;

	@FindBy(xpath = "//span//*[name()='svg' and @class='icon']//*[name()='use']")
	private WebElement deleteStaffDropDownButton;

	@FindBy(xpath = "//div[@class='g-popover__content has-arrow ']//ul//li[1]//div")
	private WebElement makeStaffAsPrivateToggleButton;

	@FindBy(xpath = "//div[@class='g-popover__content has-arrow ']//ul//li[2]")
	private WebElement deletestaffButton;

	@FindBy(xpath = "//div[contains(@class,'contact-details-wrapper')]//button[@class='g-drop-btn add-step']")
	private WebElement addFieldToExistingStaffDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate add-field popover-style mt-6 open']//div[@class='g-dropmenu has-arrow']//ul//li"))
	private List<WebElement> addFieldToExistingStaffOptions;

	@FindBys(@FindBy(xpath = "//div[@class='phone-field-wrap ext']//button"))
	private List<WebElement> updatePhoneFieldCountryCodeDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='phone-field-wrap ext']//div[@class='g-dropdown-wrap has-search g-country-dropdown open']//div[@class='g-dropmenu has-arrow align-left']//ul//li//button"))
	private List<WebElement> updatePhoneFieldCountryCodeOptions;

	@FindBy(xpath = "//div[@class='phone-field-wrap ext']//div[@class='g-dropdown-wrap has-search g-country-dropdown open']//div[@class='g-dropmenu has-arrow align-left']//div[@class='g-searchbox ignore-click']//input")
	private WebElement updatePhoneFieldCountryCodeSearchTextField;

	@FindBys(@FindBy(xpath = "//div[@class='phone-field-wrap ext']//input[@class='g-input']"))
	private List<WebElement> listUpdatePhoneNumberTextField;

	@FindBy(xpath = "//div[@class='phone-field-wrap ext']//input[@class='g-input' and @value='']")
	private WebElement updatePhoneNumberTextField;

	@FindBys(@FindBy(xpath = "//div[@class='phone-field-wrap ext']//input[@class='g-input ext-field ml-2']"))
	private List<WebElement> updatePhoneNumberExtensionTextField;

	@FindBys(@FindBy(xpath = "//button[contains(@class,'edit')]//*[name()='svg']//*[name()='use']"))
	private List<WebElement> updateCustomPhoneButton;

	@FindBy(xpath = "//div[@class='editable ps-r d-ib']//input")
	private WebElement updateCustomPhoneTextField;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'contact-details-wrapper')]//label[text()='Email']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updateEmailTextField;

	@FindBys(@FindBy(xpath = "//label[text()='Web']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updateWebTextField;

	@FindBys(@FindBy(xpath = "//label[text()='Fax']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updateFaxTextField;

	@FindBys(@FindBy(xpath = "//label[text()='Text']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updateTextTextField;

	@FindBys(@FindBy(xpath = "//label[text()='Mail Text']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updageMailTextTextField;

	@FindBys(@FindBy(xpath = "//label[text()='Email Attachment']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updateEmailAttachmentTextField;

	@FindBys(@FindBy(xpath = "//label[text()='Email CSV']//following-sibling::input[@class='g-input']"))
	private List<WebElement> updateEmailCsvTextField;

	@FindBys(@FindBy(xpath = "//ul//div[@class='g-popover']//span[@class='popover-btn mr-1 tw-m']"))
	private List<WebElement> deleteExistingFieldDropDownButton;

	@FindBys(@FindBy(xpath = "//ul//div[@class='g-popover__content has-arrow']//ul//li[1]//div"))
	private List<WebElement> makeExistingFieldAsPrivateToggleButton;

	@FindBys(@FindBy(xpath = "//ul//div[@class='g-popover__content has-arrow']//ul//li[2]"))
	private List<WebElement> deleteExistingFieldButton;

	@FindBy(xpath = "//div[@class='input-taggle-wrapper mv-2']")
	private WebElement DeliveryGroupsToStaffBlock;

	@FindBys(@FindBy(xpath = "//div[@class='input-taggle-wrapper mv-2']//ul//li//a"))
	private List<WebElement> removeExistingDeliveryGroupFromStaffButton;

	@FindBys(@FindBy(xpath = "//div[@class='input-taggle-wrapper mt-2 mb-1']//ul//li//span"))
	private List<WebElement> removeExistingDeliveryGroupFromStaffText;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//h4[text()='Add Delivery Group']//ancestor::header//following-sibling::section//input")
	private WebElement newDeliveryGroupNameFromExistingStaffTextField;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//h4[text()='Add Delivery Group']//ancestor::header//following-sibling::footer//button[@class='g-btn-primary']")
	private WebElement addNewDeliveryGroupNameFromExistingStaffButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//h4[text()='Add Delivery Group']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelNewDeliveryGroupFromExistingStaffButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//h4[text()='Add Delivery Group']//ancestor::header//following-sibling::i[@class='g-modal-close']")
	private WebElement closeNewDeliveryGroupFromExistingStaffButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate ']")
	private WebElement addDeliveryMethodsBlock;

	@FindBys(@FindBy(xpath = "//h2[text()='Select Delivery methods']//ancestor::section//button[contains(@class,'g-checkbox mr-2 ')]"))
	private List<WebElement> staffFieldCheckboxes;

	@FindBys(@FindBy(xpath = "//h2[text()='Select Delivery methods']//following::button[contains(@class,'edit')]//*[name()='svg']//*[name()='use']"))
	private List<WebElement> addDeliveryGroupToExistingStaffCustomPhoneNameButton;

	@FindBy(xpath = "//h2[text()='Select Delivery methods']//following::div[@class='editable ps-r d-ib']//input")
	private WebElement addDeliveryGroupToExistingStaffCustomPhoneNameTextField;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'contact-details-wrapper')]//label[text()='Phone']//following-sibling::input"))
	private List<WebElement> invalidPhoneNumberTextField;

	// Add Delivery Group Section

	@FindBy(xpath = "//*[name()='svg' and @class='ic-contacts icon full--h']//*[name()='use']")
	private WebElement addDeliveryGroupButton;

	@FindBy(xpath = "//aside[@class='g-sidebar full--h bdr-r multiple-select-view']//button[contains(@class,'mr-2 va-m g-checkbox')]")
	private WebElement selectAllDeliveryGroupCheckBox;

	@FindBy(xpath = "//aside[@class='g-sidebar full--h bdr-r multiple-select-view']//i[@class='close va-m']")
	private WebElement cancelMutipleDeliveryGroupSelectMode;

	@FindBy(xpath = "//div[@class='options fx fx-a-c fx-j-sb mt-2 ps-a ps-btm']//*[name()='svg' and @class ='icon ml-4 va-m']")
	private WebElement deleteMultipleDeliveryGroupsButton;

	@FindBys(@FindBy(xpath = "//ul[@id='dg-sidebar']//li//h4"))
	private List<WebElement> listOfDeliveryGroupNames;

	@FindBys(@FindBy(xpath = "//ul[@id='dg-sidebar']//li//figure//figcaption[text()]"))
	private List<WebElement> multipleDeliveryGroupSelectButtons;

	@FindBys(@FindBy(xpath = "//ul[@id='dg-sidebar']//li//button"))
	private List<WebElement> multipleDeliveryGroupSelectCheckBoxes;

	@FindBy(xpath = "//*[name()='svg' and @class='ml-2 ic-edit']")
	private WebElement editDeliveryGroupNameButton;

	@FindBy(xpath = "//div[@class='input-toggle tw-sb ']//input[@class='g-input']")
	private WebElement editDeliveryGroupNameTextField;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate has-search add  mr-4 add-staff']//button")
	private WebElement addStaffToDeliveryGroupButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate has-search add mr-4 add-staff open']//div[@class='g-dropmenu has-arrow']//div[@class='g-searchbox']//input")
	private WebElement searchStaffToAddInDeliveryGroupTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate has-search add mr-4 add-staff open']//div[@class='g-dropmenu has-arrow']//ul//li"))
	private List<WebElement> staffToAddInDeliveryGroup;

	@FindBy(xpath = "//header[@class='header bdr-b']//div[@class='g-popover']//span[@class='popover-btn mr-1 tw-m']")
	private WebElement deleteDeliveryGroupDropDownButton;

	@FindBy(xpath = "//header[@class='header bdr-b']//div[@class='g-popover__content has-arrow']//div")
	private WebElement autoSendToggleButton;

	@FindBy(xpath = "//header[@class='header bdr-b']//div[@class='g-popover__content has-arrow']//li[@class='g-delete-field ps-r']")
	private WebElement deleteDeliveryGroupButton;

	@FindBys(@FindBy(xpath = "//*[name()='svg' and @class = 'ic-edit cur-ptr']"))
	private List<WebElement> editExistingStaffInDeliveryGroupButton;

	@FindBys(@FindBy(xpath = "//*[name()='svg' and @class = 'ic-remove cur-ptr']"))
	private List<WebElement> deleteExistingStaffInDeliveryGroupButton;

	@FindBys(@FindBy(xpath = "//table[@class='dg-table dg-table-home has-avatar']//tbody//tr//td[@class='name']"))
	private List<WebElement> existingStaffNameInDeliveryGroupText;

	// Create New Delivery Group Block

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//label[text()='Name of Delivery Group']//following-sibling::input")
	private WebElement enterDeliveryGroupNameTextField;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//div[@class='search-wrap ps-r']//input")
	private WebElement searchStaffTextField;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//tbody//tr//td[@class='has-icon phone']//p"))
	private List<WebElement> staffPhoneFieldSelectButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//tbody//tr//td[@class='has-icon']//p"))
	private List<WebElement> staffEmailFieldSelectButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//tbody//tr//td[3]//p"))
	private List<WebElement> staffTextFieldSelectButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//header[@class='g-modal-head']//h4[text()='Create Delivery Group']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelNewDeliveryGroupButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//header[@class='g-modal-head']//h4[text()='Create Delivery Group']//ancestor::header//following-sibling::footer//button[@class='g-btn-primary']")
	private WebElement saveNewDeliveryGroupButton;

	@FindBy(xpath = "//div[@class='g-modal-wrapper alternate']//h4[text()='Create Delivery Group']//ancestor::header//i[@class='g-modal-close']")
	private WebElement createNewDeliveryGroupBlockCloseButton;

	// Save And Cancel Changes

	@FindBy(xpath = "//div[@class='footer-overlay ps-f ps-btm ps-right main-view-width']//button[@class='g-btn-primary']")
	private WebElement saveStaffChanges;

	@FindBy(xpath = "//div[@class='footer-overlay ps-f ps-btm ps-right main-view-width']//button[@class='g-btn-negative']")
	private WebElement cancelStaffChanges;

	@FindBys(@FindBy(xpath = "//div[@class='g-popover__content has-arrow ']//ul//li[1]//div[@class='g-togg-btn g-togg--on']"))
	private List<WebElement> staffPrivateToggleButtonEnabled;

	@FindBys(@FindBy(xpath = "//ul[@class='contact-details fx fx-j-sb fx-wrap']//li//label"))
	private List<WebElement> listOfContactMethodNames;

	@FindBys(@FindBy(xpath = "//ul//div[@class='g-popover__content has-arrow']//ul//li[1]//div[@class='g-togg-btn ']"))
	private List<WebElement> contactMethodPrivateToggleButtonDisabled;

	@FindBys(@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Staff saved successfully']"))
	private List<WebElement> staffSavedNotifiction;

	@FindBys(@FindBy(xpath = "//ul[@class='contact-details fx fx-j-sb fx-wrap']//li//label[contains(text(),'Phone')]"))
	private List<WebElement> customPhoneField;

	// Upload And Download Csv

	@FindBy(xpath = "//h4[text()='Import Contacts']//ancestor::div[@class='g-modal-wrapper alternate']")
	private WebElement uploadCsvBlock;

	@FindBys(@FindBy(xpath = "//h4[text()='Import Contacts']//ancestor::header//following-sibling::section//div[@class='contact-field']//input[@placeholder='Full Name']"))
	private List<WebElement> staffFullNameInCsvTextField;

	@FindBys(@FindBy(xpath = "//h4[text()='Import Contacts']//ancestor::header//following-sibling::section//div[@class='contact-field']//input[@placeholder='example@example.com']"))
	private List<WebElement> staffEmailInCsvTextField;

	@FindBy(xpath = "//h4[text()='Import Contacts']//ancestor::header//following-sibling::footer//button[@class='g-btn-primary']")
	private WebElement csvUploadAddToStaffButton;

	@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Contacts have been added successfully']")
	private WebElement staffUploadedConfirmationNotification;

	@FindBys(@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Contacts have been added successfully']"))
	private List<WebElement> staffUploadedConfirmationNotificationInvisible;

	@FindBy(xpath = "//h4[text()='Import Contacts']//ancestor::header//following-sibling::section//th//button")
	private WebElement autoDeliveryGroupCheckBox;

	public StaffPage clickOnAddStaffButton() {
		wait.waitForElementToBeVisible(driver, addStaffButton, 30);
		addStaffButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterNewStaffNameInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, staffNameTextField, 30);
		newStaffName = staffData.randomData("Staff Name");
		multipleStaffName.add(newStaffName);
		staffNameTextField.sendKeys(newStaffName);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterNewStaffRoleInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, staffRoleTextField, 30);
		newStaffRole = staffData.randomData("Staff Role");
		staffRoleTextField.sendKeys(newStaffRole);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnAddFieldsInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, addFieldToNewStaffDropDownButton, 30);
		addFieldToNewStaffDropDownButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnAddFieldsOptionsInAddStaffBlock(int index) {
		wait.waitForElementToBeClickable(driver, addFieldToStaffOptionsBlock, 30);
		addFieldToStaffOptions.get(index).click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterPhoneNumberInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, phoneNumberTextField.get(0), 30);
		newStaffPhoneNumber = staffData.randomTenDigitData();
		newStaffCountryCode = phoneFieldCountryCodeDropDownButton.get(0).getText();
		phoneNumberTextField.get(0).sendKeys(newStaffPhoneNumber);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterEmailInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, emailTextField.get(0), 30);
		newStaffEmail = staffData.randomEmail("email");
		emailTextField.get(0).sendKeys(newStaffEmail);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterWebInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, webTextField.get(0), 30);
		newStaffWeb = staffData.randomWeb();
		webTextField.get(0).sendKeys(newStaffWeb);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterFaxInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, faxTextField.get(0), 30);
		newStaffFaxNumber = staffData.randomTenDigitData();
		faxTextField.get(0).sendKeys(newStaffFaxNumber);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterTextInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, textTextField.get(0), 30);
		newStaffText = staffData.randomData("Text");
		textTextField.get(0).sendKeys(newStaffText);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterMailTextInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, mailTextTextField.get(0), 30);
		scroll.scrollUpToWebElement(driver, mailTextTextField.get(0));
		newStaffMailText = staffData.randomEmail("MailText");
		mailTextTextField.get(0).sendKeys(newStaffMailText);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterEmailAttachmentInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, emailAttachmentTextField.get(0), 30);
		scroll.scrollUpToWebElement(driver, emailAttachmentTextField.get(0));
		newStaffEmailAttachment = staffData.randomEmail("EmailAttachment");
		emailAttachmentTextField.get(0).sendKeys(newStaffEmailAttachment);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterEmailCsvInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, emailCsvTextField.get(0), 30);
		scroll.scrollUpToWebElement(driver, emailCsvTextField.get(0));
		newStaffEmailCsv = staffData.randomEmail("EmailCsv");
		emailCsvTextField.get(0).sendKeys(newStaffEmailCsv);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public HomePage clickOnSaveStaffInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, saveAddStaffButton, 30);
		saveAddStaffButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public StaffPage clickOnCancelStaffInAddStaffBlock() {
		wait.waitForElementToBeClickable(driver, cancelAddStaffButton, 30);
		cancelAddStaffButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnNewlyAddedStaff() {
		wait.waitForElementToBeClickable(driver, addStaffButton, 30);
		clickOnSearchButton(1).searchData(1, newStaffName);
		listOfStaff.get(0).click();
		clearSearchData(1).cancelSearchData(1);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnNewlyAddedStaffIcon() {
		wait.waitForElementToBeVisible(driver, addStaffButton, 30);
		clickOnSearchButton(1).searchData(1, newStaffName);
		multipleStaffSelectButtons.get(0).click();
		clearSearchData(1).cancelSearchData(1);
		staffIsDeleted = false;
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public HomePage clickOnNewlyAddedStaffDeleteButton() {
		wait.waitForElementToBeClickable(driver, deleteMultipleStaffButton, 30);
		deleteMultipleStaffButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public String newStaffPhoneNumberBeforeSaving() {
		if (invalidPhoneNumberTextField.size() == 0) {
			return newStaffPhoneNumber;
		} else {
			int length = newStaffCountryCode.length();
			int index = newStaffCountryCode.indexOf("+");
			newStaffCountryCode = newStaffCountryCode.substring(index + 1, length - 1);
			return newStaffCountryCode + newStaffPhoneNumber;
		}

	}

	public StaffPage clickOnAddToGroupButton() {
		wait.waitForElementToBeClickable(driver, addToGroupDropDownButton, 30);
		addToGroupDropDownButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnAddNewDeliveryGroupFromAddToGroupOptions() {
		wait.waitForElementToBeClickable(driver, addNewDeliveryGroupFromAddToGroupOptions, 30);
		addNewDeliveryGroupFromAddToGroupOptions.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterNewDeliveryGroupNameFromExistingStaff() {
		wait.waitForElementToBeClickable(driver, newDeliveryGroupNameFromExistingStaffTextField, 30);
		newDeliveryGroupName = staffData.randomData("delivery group name");
		multipleNewDeliveryGroupName.add(newDeliveryGroupName);
		newDeliveryGroupNameFromExistingStaffTextField.sendKeys(newDeliveryGroupName);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnAddNewDeliveryGroupToStaff() {
		wait.waitForElementToBeClickable(driver, addNewDeliveryGroupNameFromExistingStaffButton, 30);
		addNewDeliveryGroupNameFromExistingStaffButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnSelectStaffFieldsToDeliveryGroup() {
		wait.waitForElementToBeVisible(driver, addDeliveryMethodsBlock, 30);
		int size = staffFieldCheckboxes.size();
		for (int i = 0; i < size; i++) {
			staffFieldCheckboxes.get(i).click();
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public HomePage clickOnRemoveDeliveryGroupFromStaff(int index) {
		wait.waitForElementToBeVisible(driver, DeliveryGroupsToStaffBlock, 30);
		removeExistingDeliveryGroupFromStaffButton.get(index).click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public StaffPage clickOnDeliveryGroupTab() {
		wait.waitForElementToBeClickable(driver, deliveryGroupButton, 30);
		deliveryGroupButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage clickOnStaffTab() {
		wait.waitForElementToBeClickable(driver, staffButton, 30);
		staffButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage clickOnNewlyAddedDeliveryGroupIcon() {
		wait.waitForElementToBeVisible(driver, addDeliveryGroupButton, 30);
		clickOnSearchButton(1).searchData(1, newDeliveryGroupName);
		multipleDeliveryGroupSelectButtons.get(0).click();
		clearSearchData(1).cancelSearchData(1);
		deliveryGroupIsDeleted = false;
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public HomePage clickOnNewlyAddedDeliveryGroupDeleteButton() {
		wait.waitForElementToBeVisible(driver, deleteMultipleDeliveryGroupsButton, 30);
		deleteMultipleDeliveryGroupsButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public String newDeliveryGroupNameBeforeSaving() {
		return newDeliveryGroupName;
	}

	public String newDeliveryGroupNameAfterSaving(int index) {
		return removeExistingDeliveryGroupFromStaffText.get(index).getText();
	}

	public String newStaffNameBeforeSaving() {
		return newStaffName;
	}

	public String newStaffRoleBeforeSaving() {
		return newStaffRole;
	}

	public String newStaffEmailBeforeSaving() {
		return newStaffEmail;
	}

	public String newStaffWebBeforeSaving() {
		return newStaffWeb;
	}

	public String newStaffFaxBeforeSaving() {
		return newStaffFaxNumber;
	}

	public String newStaffTextBeforeSaving() {
		return newStaffText;
	}

	public String newStaffMailTextBeforeSaving() {
		return newStaffMailText;
	}

	public String newStaffEmailCsvBeforeSaving() {
		return newStaffEmailCsv;
	}

	public String newStaffEmailAttachmentBeforeSaving() {
		return newStaffEmailAttachment;
	}

	public String newStaffNameAfterSaving() {
		return updateStaffNameTextField.getAttribute("value");
	}

	public String newStaffRoleAfterSaving() {
		return staffDesignationTextField.getAttribute("value");
	}

	public String newStaffPhoneNumberAfterSaving() {
		if (invalidPhoneNumberTextField.size() == 0)
			return listUpdatePhoneNumberTextField.get(0).getAttribute("value");
		else
			return invalidPhoneNumberTextField.get(0).getAttribute("value");
	}

	public String newStaffEmailAfterSaving() {
		return updateEmailTextField.get(0).getAttribute("value");
	}

	public String newStaffWebAfterSaving() {
		return updateWebTextField.get(0).getAttribute("value");
	}

	public String newStaffFaxAfterSaving() {
		return updateFaxTextField.get(0).getAttribute("value");
	}

	public String newStaffTextAfterSaving() {
		return updateTextTextField.get(0).getAttribute("value");
	}

	public String newStaffMailTextAfterSaving() {
		return updageMailTextTextField.get(0).getAttribute("value");
	}

	public String newStaffEmailAttachmentAfterSaving() {
		return updateEmailAttachmentTextField.get(0).getAttribute("value");
	}

	public String newStaffEmailCsvAfterSaving() {
		return updateEmailCsvTextField.get(0).getAttribute("value");
	}

	public boolean staffBeforeDeleting() {

		return staffIsDeleted;
	}

	public String customPhoneNameBeforeSaving() {
		return "Phone (" + customPhoneName + ")";
	}

	public String customPhoneNameAfterSaving() {
		return customPhoneField.get(0).getText();
	}

	public boolean staffAfterDeleting() {
		int size = listOfStaffNames.size();
		boolean isDeleted = true;
		for (int i = 0; i < size; i++) {
			if (listOfStaffNames.get(i).getText().equals(newStaffName)) {
				isDeleted = false;
				break;
			}
		}
		return isDeleted;
	}

	public boolean deliveryGroupBeforeDeleting() {
		return deliveryGroupIsDeleted;

	}

	public boolean deliveryGroupAfterDeleting() {
		wait.waitForElementToBeVisible(driver, addDeliveryGroupButton, 30);
		int size = listOfDeliveryGroupNames.size();
		boolean isDeleted = true;
		for (int i = 0; i < size; i++) {
			if (listOfDeliveryGroupNames.get(i).getText().equals(newDeliveryGroupName)) {
				isDeleted = false;
				break;
			}
		}
		return isDeleted;
	}

	public StaffPage multiSelectNewlyAddedDeliveryGroups() {
		wait.waitForElementToBeVisible(driver, addDeliveryGroupButton, 30);
		clickOnSearchButton(1);
		for (int i = 1; i < 3; i++) {
			searchData(1, multipleNewDeliveryGroupName.get(i));
			if (i == 1) {
				multipleDeliveryGroupSelectButtons.get(0).click();
				clearSearchData(1);
			} else {
				multipleDeliveryGroupSelectCheckBoxes.get(0).click();
				clearSearchData(1).cancelSearchData(1);
			}
			deliveryGroupIsDeleted = false;
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage clickOnMultipleDeliveryGroupsDeleteButton() {
		wait.waitForElementToBeVisible(driver, deleteMultipleDeliveryGroupsButton, 30);
		deleteMultipleDeliveryGroupsButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage multiSelectNewlyAddedStaffIcon() {
		wait.waitForElementToBeVisible(driver, addStaffButton, 30);
		clickOnSearchButton(1);
		for (int i = 1; i < 3; i++) {
			searchData(1, multipleStaffName.get(i));
			if (i == 1) {
				multipleStaffSelectButtons.get(0).click();
				clearSearchData(1);
			} else {
				multipleStaffSelectCheckBoxes.get(0).click();
				clearSearchData(1).cancelSearchData(1);
			}
			staffIsDeleted = false;
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnMultipleStaffDeleteButton() {
		wait.waitForElementToBeVisible(driver, deleteMultipleStaffButton, 30);
		deleteMultipleStaffButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage clickOnStaffThreeDotsIcon() {
		wait.waitForElementToBeVisible(driver, deleteStaffDropDownButton, 30);
		deleteStaffDropDownButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage clickOnMakeStaffAsPrivateToggleButton() {
		wait.waitForElementToBeClickable(driver, makeStaffAsPrivateToggleButton, 30);
		makeStaffAsPrivateToggleButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public boolean isStaffPrivate() {

		if (staffPrivateToggleButtonEnabled.size() == 1) {
			return true;
		}

		else {
			return false;
		}

	}

	public StaffPage clickOnSaveStaff() {
		wait.waitForElementToBeClickable(driver, saveStaffChanges, 30);
		saveStaffChanges.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnNewlyAddedMultipleStaff(int index) {
		wait.waitForElementToBeVisible(driver, addStaffButton, 30);
		clickOnSearchButton(1).searchData(1, multipleStaffName.get(index));
		listOfStaff.get(0).click();
		clearSearchData(1).cancelSearchData(1);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnNewlyAddedMultipleDeliveryGroup(int index) {
		wait.waitForElementToBeVisible(driver, addDeliveryGroupButton, 30);
		clickOnSearchButton(1).searchData(1, multipleNewDeliveryGroupName.get(index));
		listOfDeliveryGroupNames.get(0).click();
		clearSearchData(1).cancelSearchData(1);

		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public String multipleStaffNameBeforeSaving(int index) {
		return multipleStaffName.get(index);
	}

	public String multipleDeliveryGroupNameBeforeSaving(int index) {
		return multipleNewDeliveryGroupName.get(index);
	}

	public String multipleDeliveryGroupNameAfterSaving() {
		wait.waitForElementToBeVisible(driver, updateDeliveryGroupTextField, 30);
		return updateDeliveryGroupTextField.getText();
	}

	public StaffPage clickOnAnyofContactMethod() {
		wait.waitForElementToBeVisible(driver, updateStaffNameTextField, 30);
		selectContactMethodIndex = staffData.randomIntegerData(0, 6);
		listOfContactMethodNames.get(selectContactMethodIndex).click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnContactMethodThreeDots(int index) {
		wait.waitForElementToBeVisible(driver, deleteExistingFieldDropDownButton.get(index), 30);
		deleteExistingFieldDropDownButton.get(index).click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage deleteSelectedContactMethod() {
		int size = listOfContactMethodNames.size();
		if ((selectContactMethodIndex + 2) < size)
			scroll.scrollUpToWebElement(driver, listOfContactMethodNames.get((selectContactMethodIndex + 2)));
		else
			scroll.scrollUpToWebElement(driver, addFieldToExistingStaffDropDownButton);
		try {
			listOfContactMethodNames.get(selectContactMethodIndex).click();
		} catch (Exception customPhoneText) {
			updateCustomPhoneTextField.click();
		}
		clickOnContactMethodThreeDots(selectContactMethodIndex);
		wait.waitForElementToBeVisible(driver, deleteExistingFieldButton.get(selectContactMethodIndex), 30);
		deleteExistingFieldButton.get(selectContactMethodIndex).click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage makeAnyContactMethodAsPrivate() {
		wait.waitForElementToBeVisible(driver, updateStaffNameTextField, 30);
		scroll.scrollUpToWebElement(driver, updateStaffNameTextField);
		selectContactMethodIndex = staffData.randomIntegerData(0, 5);
		int size = listOfContactMethodNames.size();
		if (selectContactMethodIndex == (size - 1) || selectContactMethodIndex == (size - 2))
			scroll.scrollUpToWebElement(driver, addFieldToExistingStaffDropDownButton);
		else
			scroll.scrollUpToWebElement(driver, listOfContactMethodNames.get((selectContactMethodIndex)));
		listOfContactMethodNames.get(selectContactMethodIndex).click();
		clickOnContactMethodThreeDots(selectContactMethodIndex);
		wait.waitForElementToBeVisible(driver, makeExistingFieldAsPrivateToggleButton.get(selectContactMethodIndex),
				30);
		makeExistingFieldAsPrivateToggleButton.get(selectContactMethodIndex).click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public boolean isContactMethodPrivate() {

		if (contactMethodPrivateToggleButtonDisabled.size() == 1) {
			return true;
		}

		else {
			return false;
		}

	}

	public boolean isContactMethodDeleted() {
		if (listOfContactMethodNames.size() == 7) {
			return true;
		}

		else {
			return false;
		}
	}

	public StaffPage addPhoneFieldAndEnterPhoneNumber() {
		scroll.scrollUpToWebElement(driver, addFieldToExistingStaffDropDownButton);
		addFieldToExistingStaffDropDownButton.click();
		addFieldToExistingStaffOptions.get(0).click();
		newStaffPhoneNumber = staffData.randomTenDigitData();
		newStaffCountryCode = updatePhoneFieldCountryCodeDropDownButton.get(0).getText();
		wait.waitForElementToBeVisible(driver, updatePhoneNumberTextField, 30);
		listUpdatePhoneNumberTextField.get(0).sendKeys(newStaffPhoneNumber);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnCustomPhoneField() {
		wait.waitForElementToBeVisible(driver, updateStaffNameTextField, 30);
		try {
			wait.waitForElementToBeVisible(driver, customPhoneField.get(0), 5);
			scroll.scrollUpToWebElement(driver, customPhoneField.get(0));
			wait.waitForElementToBeClickable(driver, customPhoneField.get(0), 30);
			customPhoneField.get(0).click();
		} catch (Exception customPhoneFieldException) {
			addPhoneFieldAndEnterPhoneNumber().clickOnSaveStaff().waitForAccountLoadOrSave();
			wait.waitForElementToBeInvisible(driver, staffSavedNotifiction, 30);
			scroll.scrollUpToWebElement(driver, customPhoneField.get(0));
			wait.waitForElementToBeClickable(driver, customPhoneField.get(0), 30);
			customPhoneField.get(0).click();
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage EnterCustomPhoneName() {
		wait.waitForElementToBeVisible(driver, updateCustomPhoneTextField, 30);
		customPhoneName = staffData.randomData("Custom Phone");
		updateCustomPhoneTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		updateCustomPhoneTextField.sendKeys(customPhoneName);
		scroll.scrollUpToWebElement(driver, updateStaffNameTextField);
		updateStaffNameTextField.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnUploadStaffThreeDotsButton() {
		wait.waitForElementToBeVisible(driver, staffSortByDropDownButton, 30);
		staffSortByDropDownButton.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage uploadStaffCsvFile() {
		wait.waitForElementToBeVisible(driver, staffSortByDropDownBlock, 30);
		uploadCsvField.sendKeys(new File("./TestData/Staff.csv").getAbsolutePath());
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterNewStaffNameUploadCsvBlock(int index) {
		wait.waitForElementToBeVisible(driver, uploadCsvBlock, 30);
		newStaffName = staffData.randomData("Staff Name");
		multipleStaffName.add(newStaffName);
		staffFullNameInCsvTextField.get(index).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		staffFullNameInCsvTextField.get(index).sendKeys(newStaffName);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage enterEmailInUploadCsvBlock(int index) {
		wait.waitForElementToBeVisible(driver, uploadCsvBlock, 30);
		newStaffEmail = staffData.randomEmail("email");
		scroll.scrollUpToWebElement(driver, staffEmailInCsvTextField.get(index));
		staffEmailInCsvTextField.get(index).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		staffEmailInCsvTextField.get(index).sendKeys(newStaffEmail);
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnAutoDeliveryCheckBox() {
		wait.waitForElementToBeVisible(driver, uploadCsvBlock, 30);
		autoDeliveryGroupCheckBox.click();
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage clickOnAddtoStaffButtonInUploadCsvBlock() {
		wait.waitForElementToBeVisible(driver, uploadCsvBlock, 30);
		csvUploadAddToStaffButton.click();
		wait.waitForElementToBeVisible(driver, staffUploadedConfirmationNotification, 30);
		wait.waitForElementToBeInvisible(driver, staffUploadedConfirmationNotificationInvisible, 30);
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage multiSelectNewlyUploadedStaffIcon() {
		wait.waitForElementToBeVisible(driver, addStaffButton, 30);
		clickOnSearchButton(1);
		for (int i = 4; i < 6; i++) {
			searchData(1, multipleStaffName.get(i));
			if (i == 4) {
				multipleStaffSelectButtons.get(0).click();
				clearSearchData(1);
			} else {
				multipleStaffSelectCheckBoxes.get(0).click();
				clearSearchData(1).cancelSearchData(1);
			}
			staffIsDeleted = false;
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public StaffPage multiSelectNewlyAddedAutoDeliveryGroups() {
		wait.waitForElementToBeVisible(driver, addDeliveryGroupButton, 30);
		clickOnSearchButton(1);
		for (int i = 4; i < 6; i++) {
			searchData(1, multipleStaffName.get(i));
			if (i == 4) {
				multipleDeliveryGroupSelectButtons.get(0).click();
				clearSearchData(1);
			} else {
				multipleDeliveryGroupSelectCheckBoxes.get(0).click();
				clearSearchData(1).cancelSearchData(1);
			}
			deliveryGroupIsDeleted = false;
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

	public StaffPage clickOnAutoAddedMultipleDeliveryGroup(int index) {
		wait.waitForElementToBeVisible(driver, addDeliveryGroupButton, 30);
		clickOnSearchButton(1).searchData(1, multipleStaffName.get(index));
		listOfDeliveryGroupNames.get(0).click();
		clearSearchData(1).cancelSearchData(1);
		return PageFactory.initElements(getDriver(), StaffPage.class);

	}

}
