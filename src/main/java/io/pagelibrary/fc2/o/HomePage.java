package io.pagelibrary.fc2.o;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.testlibrary.fc2.o.DriverAndReportClass;
import io.utils.fc2.o.Wait;

public class HomePage extends DriverAndReportClass {
	Wait wait = new Wait();
	WebDriver driver = getDriver();

	@FindBy(xpath = "//button[@class='g-btn-primary fetch']")
	private WebElement findAccountButton;

	@FindBy(xpath = "//div[@id='fetch-modal']//div[@class='g-modal-wrapper alternate']")
	private WebElement findAccountBlock;

	@FindBy(xpath = "//div[@id='fetch-modal']//div[@class='g-modal-wrapper alternate']//header//i")
	private WebElement closeFindAccountBlock;

	@FindBy(xpath = "//div[@id='fetch-modal']//div[@class='g-modal-wrapper alternate']//section//div//input[@id='fetch-acc-num']")
	private WebElement findAccountUsingAccountNumber;

	@FindBy(xpath = "//a[@type='uniquePin']")
	private WebElement uniquePinFind;

	@FindBy(xpath = "//input[@id='unique-pin-id']")
	private WebElement findAccountUsingUniquePin;

	@FindBy(xpath = "//div[@id='fetch-modal']//div[@class='g-modal-wrapper alternate']//footer//button")
	private WebElement loadAccountButton;

	@FindBys(@FindBy(xpath = "//div[@id='fetch-modal']//div[@class='g-modal-wrapper alternate']//footer//button[@class='g-btn-primary g-btn-processing ']"))
	private List<WebElement> loadingAccountButton;

	@FindBy(xpath = "//*[name()='svg']//*[name()='circle'and @class='path']")
	private WebElement accountLoading;

	@FindBy(xpath = "//*[name()='svg']//*[name()='circle'and @class='path']")
	private List<WebElement> accountLoaded;

	@FindBy(xpath = "//nav[@class='primary-nav bc-color-black-400 full--h fx fx-col']//ul//li[@data-corres-sec='scripts']")
	private WebElement scriptsButton;

	@FindBy(xpath = "//nav[@class='primary-nav bc-color-black-400 full--h fx fx-col']//ul//li[@data-corres-sec='scenarios']")
	private WebElement scenariosButton;

	@FindBy(xpath = "//nav[@class='primary-nav bc-color-black-400 full--h fx fx-col']//ul//li[@data-corres-sec='staffs']")
	private WebElement staffButton;

	@FindBy(xpath = "//nav[@class='primary-nav bc-color-black-400 full--h fx fx-col']//ul//li[@data-corres-sec='settings']")
	private WebElement settingsButton;

	@FindBy(xpath = "//div[@class='mb-7 ta-c']//button")
	private WebElement briefcaseButton;

	@FindBy(xpath = "//div[contains(@class,'profile g-dropdown-wrap ph-3 right-pos mb-4')]//div//figure")
	private WebElement profileButton;

	@FindBy(xpath = "//div[@class='profile g-dropdown-wrap ph-3 right-pos mb-4 open']//div[@class='g-dropmenu has-arrow']")
	private WebElement playgroundAndLogoutBlock;

	@FindBy(xpath = "//div[contains(@class,'profile g-dropdown-wrap ph-3 right-pos mb-4')]//div[@class='g-dropmenu has-arrow']//ul//li[1]")
	private WebElement backToPlayGroundButton;

	@FindBy(xpath = "//div[contains(@class,'profile g-dropdown-wrap ph-3 right-pos mb-4')]//div[@class='g-dropmenu has-arrow']//ul//li[2]")
	private WebElement logoutButton;

	@FindBy(xpath = "//aside[@class='g-sidebar bdr-r ']")
	private WebElement searchBlock;

	@FindBys(@FindBy(xpath = "//div//a[@class='ic-blue ps-a ps-top ps-right']"))
	private List<WebElement> searchButton;

	@FindBys(@FindBy(xpath = "//div[@class='search-bar fx fx-a-c ps-r ph-3']//input"))
	private List<WebElement> searchTextField;

	@FindBys(@FindBy(xpath = "//div[@class='search-bar fx fx-a-c ps-r ph-3']//i[@class='close']"))
	private List<WebElement> clearSearchData;

	@FindBy(xpath = "//div[@class='search-bar fx fx-a-c ps-r ph-3']//i[@class='g-drop-arrow']")
	private WebElement searchTypeDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='search-bar fx fx-a-c ps-r ph-3']//div[@class='g-dropmenu has-arrow']//ul/li"))
	private List<WebElement> searchTypes;

	@FindBys(@FindBy(xpath = "//*[name()='svg' and @class='arrow-left ps-a cur-ptr']"))
	private List<WebElement> cancelSearchButton;

	// Notification Popup's

	@FindBy(xpath = "//h4[text()='Delete Staffs']//following-sibling::i")
	private WebElement closeDeleteStaffNotificationButton;

	@FindBy(xpath = "//h4[text()='Delete Staffs']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelDeleteStaffButton;

	@FindBy(xpath = "//h4[text()='Delete Staffs']//ancestor::header//following-sibling::footer//button[@class='g-btn-danger']")
	private WebElement deleteStaffButton;

	@FindBy(xpath = "//h4[contains(text(),'Remove')]//ancestor::header//following-sibling::footer//button[@class='g-btn-danger']")
	private WebElement removeStaffFromDeliveryGroupButton;

	@FindBy(xpath = "//h4[contains(text(),'Remove')]//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelRemoveStaffFromDeliveryGroupButton;

	@FindBy(xpath = "//h4[contains(text(),'Remove')]//following-sibling::i")
	private WebElement closeRemoveStaffFromDeliveryGroupNotificationButton;

	@FindBy(xpath = "//h4[text()='Delete delivery groups']//following-sibling::i")
	private WebElement closeDeleteDeliveryGroupNotificationButton;

	@FindBy(xpath = "//h4[text()='Delete delivery groups']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelDeleteDeliveryGroupButton;

	@FindBy(xpath = "//h4[text()='Delete delivery groups']//ancestor::header//following-sibling::footer//button[@class='g-btn-danger']")
	private WebElement deleteDeliveryGroupButton;

	@FindBy(xpath = "//h4[text()='Confirm']//ancestor::header//following-sibling::footer//button[@class='g-btn-primary']")
	private WebElement confirmClearCacheNotificationButton;

	@FindBy(xpath = "//h4[text()='Confirm']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelClearCacheNotificationButton;

	@FindBy(xpath = "//h4[text()='Confirm']//following-sibling::i")
	private WebElement closeClearCacheNotificationButton;

	@FindBy(xpath = "//h4[text()='Delete Scenario']//ancestor::header//following-sibling::footer//button[@class='g-btn-danger']")
	private WebElement confirmDeleteScenarioNotificationButton;

	@FindBy(xpath = "//h4[text()='Delete Scenario']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelDeleteScenarioNotificationButton;

	@FindBy(xpath = "//h4[text()='Delete Scenario']//ancestor::header//following-sibling::i")
	private WebElement closeDeleteScenarioNotificationButton;

	@FindBy(xpath = "//h4[contains(text(),'Confirm')]//ancestor::header//following-sibling::footer//button[@class='g-btn-primary']")
	private WebElement confirmOutputOrdreNotificationButton;

	@FindBy(xpath = "//h4[text()='Confirm']//ancestor::header//following-sibling::footer//button[@class='g-btn-negative']")
	private WebElement cancelOutputOrderNotificationButton;

	@FindBy(xpath = "//h4[text()='Confirm']//following-sibling::i")
	private WebElement closeOutputOrderNotificationButton;

	@FindBy(xpath = "//div[@class='g-notification main-view-width']")
	private WebElement savedOrPublishedNotification;

	@FindBys(@FindBy(xpath = "//div[@class='g-notification main-view-width']"))
	private List<WebElement> savedOrPublishedNotificationInvisible;

	@FindBy(xpath = "//i[@class='g-modal-image delete']//following::footer//button")
	private WebElement bilingiualScirptAlertOkayButton;

	@FindBy(xpath = "//button[@class='g-drop-btn']/span[contains(text(),'View in')]")
	private WebElement viewInDropdown;

	@FindBy(xpath = "//li/a[contains(text(),'Form Versions')]")
	private WebElement formVersionInDropDown;

	@FindBy(xpath = "//h4[contains(text(),'Alert')]//ancestor::header//following-sibling::footer//button[@class='g-btn-danger']")
	private WebElement confirmMessageAnnotationsAlertNotification;

	@FindBy(xpath = "//h4[text()]//ancestor::header//following-sibling::footer//button[@class='g-btn-primary']")
	private WebElement confirmAddDeliveryGroupButton;

	public String getHomePageTitle() {
		wait.waitForWebPageLoad(driver, 30);
		wait.waitForElementToBeClickable(driver, findAccountButton, 30);
		String title = driver.getTitle();
		return title;
	}

	public HomePage clickOnFindButton() {
		wait.waitForElementToBeClickable(driver, findAccountButton, 30);
		findAccountButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage enterAccountNumber(String accountNumber) {
		wait.waitForElementToBeVisible(driver, findAccountBlock, 30);
		findAccountUsingAccountNumber.sendKeys(accountNumber);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public HomePage clickOnLoadButton() {
		wait.waitForElementToBeClickable(driver, loadAccountButton, 30);
		loadAccountButton.click();
		wait.waitForElementToBeInvisible(driver, loadingAccountButton, 30);
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage waitForAccountLoadOrSave() {
		try {
			wait.waitForElementToBeVisible(driver, accountLoading, 4);
		} catch (Exception accountLoaded) {
		}
		wait.waitForElementToBeInvisible(driver, accountLoaded, 120);
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public ScriptPage clickOnScriptButton() {
		scriptsButton.click();
		try {
			wait.waitForElementToBeVisible(driver, accountLoading, 2);
			wait.waitForElementToBeInvisible(driver, accountLoaded, 30);
		} catch (Exception scriptLoaded) {
		}
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScenariosPage clickOnScenariosButton() {
		wait.waitForElementToBeClickable(driver, scenariosButton, 30);
		scenariosButton.click();
		try {
			wait.waitForElementToBeVisible(driver, accountLoading, 2);
			wait.waitForElementToBeInvisible(driver, accountLoaded, 30);
		} catch (Exception scenariosLoaded) {
		}
		return PageFactory.initElements(getDriver(), ScenariosPage.class);

	}

	public StaffPage clickOnStaffButton() {
		staffButton.click();
		try {
			wait.waitForElementToBeVisible(driver, accountLoading, 2);
			wait.waitForElementToBeInvisible(driver, accountLoaded, 30);
		} catch (Exception staffLoaded) {
		}
		return PageFactory.initElements(getDriver(), StaffPage.class);
	}

	public SettingsPage clickOnSettingsButton() {
		settingsButton.click();
		try {
			wait.waitForElementToBeVisible(driver, accountLoading, 2);
			wait.waitForElementToBeInvisible(driver, accountLoaded, 30);
		} catch (Exception settingsLoaded) {
		}
		return PageFactory.initElements(getDriver(), SettingsPage.class);
	}

	public PlayGroundPage clickOnPlayGroundButton() {
		wait.waitForElementToBeClickable(driver, backToPlayGroundButton, 30);
		backToPlayGroundButton.click();
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), PlayGroundPage.class);
	}

	public FormCreatorLaunchPage clickOnLogoutdButton() {
		profileButton.click();
		wait.waitForElementToBeClickable(driver, logoutButton, 30);
		logoutButton.click();
		return PageFactory.initElements(getDriver(), FormCreatorLaunchPage.class);

	}

	public HomePage clickOnBriefcaseButton() {
		briefcaseButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnSearchButton(int index) {
		searchButton.get(index).click();
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public HomePage searchData(int index, String searchData) {
		wait.waitForElementToBeVisible(driver, searchTextField.get(index), 30);
		searchTextField.get(index).sendKeys(searchData);
		searchTextField.get(index).sendKeys(Keys.ENTER);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public HomePage clearSearchData(int index) {
		clearSearchData.get(index).click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage cancelSearchData(int index) {
		cancelSearchButton.get(index).click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnSearchTypeDropDown() {
		searchTypeDropDownButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public void selectSearchType(int searchTypeIndexNumber) {
		wait.waitForElementToBeClickable(driver, searchTypes.get(searchTypeIndexNumber), 30);
		searchTypes.get(searchTypeIndexNumber).click();
	}

	public HomePage clickOnDeleteStaffInConfirmationPopUp() {
		wait.waitForElementToBeClickable(driver, deleteStaffButton, 30);
		deleteStaffButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnRemoveStaffFromDeliveryGroupConfirmationPopUp() {
		wait.waitForElementToBeClickable(driver, removeStaffFromDeliveryGroupButton, 30);
		removeStaffFromDeliveryGroupButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnDeleteDeliveryGroupConfirmationPopUp() {
		wait.waitForElementToBeClickable(driver, deleteDeliveryGroupButton, 30);
		deleteDeliveryGroupButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnConfirmNotificationPopUp() {
		wait.waitForElementToBeClickable(driver, confirmClearCacheNotificationButton, 30);
		confirmClearCacheNotificationButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnDeleteScenarioConfirmationPopUp() {
		wait.waitForElementToBeClickable(driver, confirmDeleteScenarioNotificationButton, 30);
		confirmDeleteScenarioNotificationButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnCancelScenarioConfirmationPopUp() {
		wait.waitForElementToBeClickable(driver, cancelDeleteScenarioNotificationButton, 30);
		cancelDeleteScenarioNotificationButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnCloseScenarioConfirmationPopUp() {
		wait.waitForElementToBeClickable(driver, closeDeleteScenarioNotificationButton, 30);
		closeDeleteScenarioNotificationButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickOnOutputOrderNotificationPopUp() {
		wait.waitForElementToBeClickable(driver, confirmOutputOrdreNotificationButton, 30);
		confirmOutputOrdreNotificationButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public void clickOnReload() {
		driver.navigate().refresh();
		wait.waitForWebPageLoad(driver, 30);
	}

	public HomePage clickOnUniquePinFind() {
		wait.waitForElementToBeClickable(driver, uniquePinFind, 30);
		uniquePinFind.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage enterUniquePin(String uniquePin) {
		wait.waitForElementToBeClickable(driver, findAccountUsingUniquePin, 20);
		findAccountUsingUniquePin.sendKeys(uniquePin);
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage waitForSaveOrPublishNotification() {
		wait.waitForElementToBeVisible(driver, savedOrPublishedNotification, 30);
		wait.waitForElementToBeInvisible(driver, savedOrPublishedNotificationInvisible, 30);
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage confirmOnBilingiualScriptNotification() {
		wait.waitForElementToBeVisible(driver, bilingiualScirptAlertOkayButton, 2);
		bilingiualScirptAlertOkayButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickViewInDropDown() {
		wait.waitForElementToBeClickable(driver, viewInDropdown, 30);
		viewInDropdown.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage clickFormVersions() {
		wait.waitForElementToBeClickable(driver, formVersionInDropDown, 20);
		formVersionInDropDown.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public HomePage confirmAddDeliveryGroupNotification() {
		wait.waitForElementToBeVisible(driver, confirmAddDeliveryGroupButton, 30);
		confirmAddDeliveryGroupButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public boolean isSpanishScriptingNotificationDisplayed() {
		try {
			wait.waitForElementToBeVisible(driver, bilingiualScirptAlertOkayButton, 3);
			return true;
		} catch (Exception spanishScripting) {
			return false;
		}

	}

	public HomePage confirmMessageAnnotationAlertNotification() {
		try {
			wait.waitForElementToBeClickable(driver, confirmMessageAnnotationsAlertNotification, 5);
			confirmMessageAnnotationsAlertNotification.click();
		} catch (Exception messageAnnotation) {

		}
		return PageFactory.initElements(getDriver(), HomePage.class);
	}

	public boolean isLockedAlertDisplayed() {
		try {
			wait.waitForElementToBeVisible(driver, confirmMessageAnnotationsAlertNotification, 3);
			return true;
		} catch (Exception alert) {
			return false;
		}

	}

	public HomePage clickOnProfileButton() {
		wait.waitForElementToBeVisible(driver, profileButton, 30);
		profileButton.click();
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

}
