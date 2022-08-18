package io.pagelibrary.fc2.o;

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
import io.utils.fc2.o.WindowHandles;

public class FormVersionsPage extends HomePage {

	Wait wait = new Wait();
	Scroll scroll = new Scroll();
	WindowHandles handle = new WindowHandles();
	WebDriver driver;
	RandomData formVersionsData = new RandomData();
	private static String editableFormVersionsName;

	public FormVersionsPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//span[@id='accountNumberFormVersions']")
	private WebElement accountNumberTextField;

	@FindBy(xpath = "//input[@id='accountNumberSearch']")
	private WebElement searchAccountNumberTextField;

	@FindBy(xpath = "//button[text()='Search']")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@class='form-versions-view']")
	private WebElement viewDropDownButton;

	@FindBys(@FindBy(xpath = "//ul[@style='display: block;']//li"))
	private List<WebElement> optionsInViewDropDownButton;

	@FindBy(xpath = "//h5[contains(@class,'underlineTh') and text() = 'Last Modified']")
	private WebElement lastModifiedButton;

	@FindBy(xpath = "//h5[contains(@class,'underlineTh') and text() = 'Script Name']")
	private WebElement scriptNameButton;

	@FindBys(@FindBy(xpath = "//tbody//tr//td//span"))
	private List<WebElement> starAndUnstarFormButton;

	@FindBys(@FindBy(xpath = "//tbody//tr//td[contains(text(),'@anywhere.co')]"))
	private List<WebElement> modifiedByText;

	@FindBys(@FindBy(xpath = "//tbody//tr//td[contains(text(),'GMT+0530 (India Standard Time)')]"))
	private List<WebElement> lastModifiedTimeText;

	@FindBys(@FindBy(xpath = "//tbody//tr//td[@data]"))
	private List<WebElement> scriptNameText;

	@FindBys(@FindBy(xpath = "//tbody//tr//td//a[@class='editScriptName']"))
	private List<WebElement> editScriptNameButton;

	@FindBy(xpath = "//td[@class='editable']/p")
	private WebElement editScriptNameTextBox;

	@FindBys(@FindBy(xpath = "//tbody//tr//td//a[@class='viewScript']"))
	private List<WebElement> viewScriptButton;

	@FindBy(xpath = "//i[@class='fa fa-chevron-left']")
	private WebElement navigatorLeftButton;

	@FindBy(xpath = "//i[@class='fa fa-chevron-right']")
	private WebElement navigatorRightButton;

	@FindBy(xpath = "//tbody//tr//td[@class='editable']")
	private WebElement formNameEditTextField;

	@FindBy(xpath = "//button[@id = 'revertMessageFormButton']")
	private WebElement revertToThisVersionButton;

	@FindBy(xpath = "//button[@id = 'expand_form']")
	private WebElement formVerionsExpandFormButton;

	@FindBy(xpath = "//button[@id = 'collapseMessageForm']")
	private WebElement formVerionscollapseFormButton;

	@FindBy(xpath = "//button[@id = 'close_form']")
	private WebElement formVersionsCloseFormButton;

	@FindBy(xpath = "//button[@class = 'close-popup formClosePopup']")
	private WebElement formVersionClosePopUpButton;

	@FindBys(@FindBy(xpath = "//div[@id='notification']"))
	private List<WebElement> formVersionNotification;

	@FindBy(xpath = "//div[@id='notification']")
	private WebElement formVersionNotificationLoading;

	@FindBy(xpath = "//div[@id='notification'][contains(text(),'Loading Form Version Entries')]")
	private WebElement loadingFormVersionEntries;

	@FindBy(xpath = "//div[@id='notification'][contains(text(),'be notified with a mail when the request is completed')]")
	private WebElement formRevertingNotification;

	@FindBys(@FindBy(xpath = "//ul[@class='created_field_list']//li[@id]"))
	private List<WebElement> formVersionsAllFields;

	@FindBy(xpath = "//td[@class='editable']/p")
	private WebElement editableFormName;

	@FindBy(xpath = "//div[@class='form-version-footer-pagination']/ul/li[2]/a")
	private WebElement pageNumber;

	@FindBys(@FindBy(xpath = "//td[1]"))
	private List<WebElement> starIcon;

	@FindBys(@FindBy(xpath = "//tr[1]/td/span[@class='mark-star starred']/i"))
	private List<WebElement> unstarIcon;

	@FindBys(@FindBy(xpath = "//tr/td/span[@class='mark-star starred']"))
	private List<WebElement> starredForm;

	@FindBy(xpath = "//button[@class='ds-feedback-app-feedback-button']")
	private WebElement feedBackButtonInFormVersions;

	@FindBys(@FindBy(xpath = "//p[@class='scriptPara']"))
	private List<WebElement> formNameText;

	public FormVersionsPage switchToFormVersionsTab() {
		handle.parentToChildWindowControl(getDriver(), 1);
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage closeFormVersions() {
		handle.childWindowClose(driver, 1);
		handle.childToParentWindowControl(driver);
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage clickViewScriptButton(int formNumber) {
		wait.waitForAllElementsToBeVisible(driver, viewScriptButton, 30);
		wait.waitForElementToBeClickable(driver, viewScriptButton.get(formNumber - 1), 30);
		viewScriptButton.get(formNumber - 1).click();
		wait.waitForElementToBeClickable(driver, formVersionClosePopUpButton, 20);
		formVersionClosePopUpButton.click();
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage editFormName() {
		editableFormVersionsName = formVersionsData.randomData("FormVersion edition ");
		editScriptNameButton.get(0).click();
		editScriptNameTextBox.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		editScriptNameTextBox.sendKeys(editableFormVersionsName + Keys.ENTER);
		wait.waitForElementToBeVisible(driver, formVersionNotificationLoading, 30);
		wait.waitForElementToBeInvisible(driver, formVersionNotification, 30);
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public String EditedFormName() {

		return editableFormVersionsName;
	}

	public FormVersionsPage navigateToNextPage() {
		scroll.scrollUpToWebElement(driver, navigatorRightButton);
		navigatorRightButton.click();
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage navigateToPreviousPage() {
		scroll.scrollUpToWebElement(driver, navigatorLeftButton);
		navigatorLeftButton.click();
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage clickOnStarIcon(int formNumber) {
		wait.waitForAllElementsToBeVisible(driver, starIcon, 30);
		scroll.scrollUpToWebElement(driver, starIcon.get(formNumber - 1));
		starIcon.get(formNumber - 1).click();
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage clickToUnstarIcon() {
		unstarIcon.get(1).click();
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage clickViewDropDown() {
		viewDropDownButton.click();
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage clickstarredOptionFromDropDown() {
		optionsInViewDropDownButton.get(0).click();
		try {
			wait.waitForElementToBeVisible(driver, formVersionNotificationLoading, 2);
		} catch (Exception notification) {
		}
		wait.waitForElementToBeInvisible(driver, formVersionNotification, 20);
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage clickAllOptionsFromDropDown() {
		optionsInViewDropDownButton.get(1).click();
		try {
			wait.waitForElementToBeVisible(driver, formVersionNotificationLoading, 2);
		} catch (Exception notification) {
		}
		wait.waitForElementToBeInvisible(driver, formVersionNotification, 20);
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public boolean isStarredFormListed() {
		wait.waitForElementToBeInvisible(driver, formVersionNotification, 30);
		if (starredForm.size() > 0)
			return true;
		else
			return false;
	}

	public FormVersionsPage unstarAllForms() {
		int size = starredForm.size();
		for (int i = 0; i < size; i++) {
			int attempt = 0;
			while (attempt < 3) {
				try {
					starredForm.get(0).click();
					break;
				} catch (Exception staleElement) {
					attempt++;
				}
			}
		}
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public FormVersionsPage revertAForm() {
		wait.waitForElementToBeClickable(driver, viewScriptButton.get(0), 20);
		viewScriptButton.get(0).click();
		wait.waitForElementToBeClickable(driver, formVersionClosePopUpButton, 20);
		revertToThisVersionButton.click();
		wait.waitForElementToBeInvisible(driver, formVersionNotification, 30);
		return PageFactory.initElements(getDriver(), FormVersionsPage.class);
	}

	public String getFormName(int fromNumber) {
		return formNameText.get(fromNumber - 1).getText();
	}

}
