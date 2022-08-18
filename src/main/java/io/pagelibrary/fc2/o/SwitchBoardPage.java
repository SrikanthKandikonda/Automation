package io.pagelibrary.fc2.o;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.testlibrary.fc2.o.DriverAndReportClass;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;
import io.utils.fc2.o.WindowHandles;

public class SwitchBoardPage extends DriverAndReportClass {
	Wait wait = new Wait();
	WindowHandles handle = new WindowHandles();
	Scroll scroll = new Scroll();
	String yourRoleSb;

	private WebDriver driver;

	public SwitchBoardPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[contains(@class,'greeting-wrap open-modal')]//h3[@id='answer-phrase-value']")
	private WebElement answerPhrase;

	@FindBy(xpath = ".//div[@class='greeting-modal-footer']/h4/b")
	private WebElement yourRole;

	@FindBy(xpath = "//div[contains(@class,'greeting-wrap open-modal')]//div[@class='greeting']//i")
	private WebElement answerPhraseCloseButton;

	@FindBy(xpath = "//div[@id = 'Details-Tab-Content']//child::a[@id='hours_tab']")
	private WebElement hoursTab;

	@FindBy(xpath = "//div[@id = 'Details-Tab-Content']//child::a[@id='hours_tab']//cite")
	private WebElement companyWorkingStatus;

	@FindBy(xpath = "//div[@id = 'Details-Tab-Content']//child::a[text()='DIRECTORY']")
	private WebElement directoryTab;

	@FindBy(xpath = "//div[@id = 'Details-Tab-Content']//child::a[@id='messagehistory']")
	private WebElement messageHistoryTab;

	@FindBy(xpath = "//div[@id='hours_note']//ul/li")
	private WebElement notes;

	@FindBy(xpath = "//img[@alt='logo']")
	private WebElement accountInfoButton;

	@FindBy(xpath = "//div[@class='acct-info-cont']/ul/li//b[text()='Who We Are']//following-sibling::p")
	private WebElement accountGoalInSb;

	@FindBys(@FindBy(xpath = "//div[@class='acct-info-cont']//child::li//p"))
	private List<WebElement> accountInformation;

	@FindBy(xpath = "//div[@class='acct-info-cont']//child::li//b[text()='Account Name']//following-sibling::p")
	private WebElement accountName;

	@FindBy(xpath = "//div[@class='acct-info-cont']//child::li//b[text()='Answer Phrase Instruction']//following-sibling::p")
	private WebElement answerPhraseInstruction;

	@FindBy(xpath = "//div [@class='greeting-wrap']")
	private WebElement greetingWrap;

	@FindBy(xpath = "//p[@class='ap-instruction']//child::span")
	private WebElement answerPhraseInstructiontext;

	@FindBy(xpath = "//div[@id = 'Details-Tab-Content']//child::a[@id='hours_tab']//child::span")
	private WebElement countryTime;

	@FindBy(xpath = "//li[@id ='hours_roundclock']")
	private WebElement allhoursText;

	@FindBys(@FindBy(xpath = "//ul[@id ='hours_root']//child::li//child::span[2][contains(text(),'-')]//ancestor::li//span[1]"))
	private WebElement customDayText;

	@FindBys(@FindBy(xpath = "//ul[@id ='hours_root']//child::li//child::span[2][contains(text(),'-')]"))
	private WebElement customHourText;

	@FindBy(xpath = "//article[@id='hours' and contains(@style, 'display: block;')]")
	private WebElement hoursBlock;

	@FindBy(xpath = "//div[@class='component-field']//label//span[text()='Decision Tree']")
	private WebElement contentLoad;

	@FindBy(xpath = "//div[@id='scriptContainer' and @style='']")
	private WebElement formLoaded;

	@FindBys(@FindBy(xpath = "//span[@class = 'hint--top']//i"))
	private List<WebElement> appointment;

	@FindBys(@FindBy(xpath = "//div[@id = 'closures_info']//ul//li"))
	private List<WebElement> closuresAndHolidays;

	@FindBy(xpath = "//div[@id = 'closures_info']//ul//li[contains(text(),'Custom')]")
	private WebElement customHolidayText;

	@FindBy(xpath = "//figcaption[@id = 'figcaption_id']//p//b")
	private WebElement roleType;

	@FindBy(xpath = "//figcaption[@id = 'figcaption_id']//p[@style]")
	private WebElement role;

	@FindBy(xpath = "//div[@class = 'scenarios-list-holder']//ul//li")
	private WebElement scenarios;

	@FindBys(@FindBy(xpath = "//div[@class = 'scenarios-list-holder']//ul//li//p[@id]"))
	private List<WebElement> listOfScenarioNames;

	@FindBys(@FindBy(xpath = "//div[@class = 'scenarios-list-holder']//ul//li[@class='active']//div//p"))
	private List<WebElement> listSelectedScenarioSteps;

	@FindBy(xpath = "//a[@id = 'scenarios_tab']")
	private WebElement scenariosTab;

	@FindBys(@FindBy(xpath = "//div[@class = 'scenarios-list-holder']//ul//li//div//p"))
	private WebElement listOfScenarioSteps;

	@FindBy(xpath = "//div[@id = 'Details-Tab-Content']//child::a[@id='hours_tab']//cite")
	private WebElement workingStatus;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div//label//span[text() = 'Name']"))
	private List<WebElement> nameField;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div//label//span[text() = 'Phone']"))
	private List<WebElement> phoneField;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div//label//span[text() = 'Email']"))
	private List<WebElement> emailField;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div//label//span[text() = 'Paragraph Text']"))
	private List<WebElement> paragraphTextField;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div//p[@lang='en']"))
	private List<WebElement> scriptingField;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div//p//b[text() = 'Instruction: ']"))
	private List<WebElement> instructionField;

	@FindBys(@FindBy(xpath = "//div[@class='option-field']//b[contains(@class,'toggle-option toggledown t')]//following-sibling::ol//li//label//span"))
	private List<WebElement> decisionTreeOptions;

	@FindBys(@FindBy(xpath = "//div[@class='option-field']//b[@class='toggle-option toggledown']"))
	private List<WebElement> decisionTreeListButton;

	@FindBys(@FindBy(xpath = "//label[contains(text(),'FAQ ') or contains(text(),'All Other ')]//span"))
	private List<WebElement> faqButton;

	@FindBys(@FindBy(xpath = "//span[text()='Ready to Place Order?']//following::b//following-sibling::ol//label[text()='Yes']"))
	private List<WebElement> yesButton;

	@FindBys(@FindBy(xpath = "//div[@id='message-form-holder']//div[@class]//p"))
	private List<WebElement> fieldScriptsInSB;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//div[@class]//label//span"))
	private List<WebElement> fieldNamesInSB;

	@FindBy(xpath = "//div[@id = 'closures_info']//ul//li[@class='current']")
	private WebElement currentHolidayText;

	@FindBy(xpath = "//div[@class='option-field' and @style ='display: block;']//b[contains(@class,'toggle-option toggledown')]//following-sibling::ol//li//label//span")
	private List<WebElement> selectORDeselectOptionsInDIInsideADI;

	@FindBy(xpath = "//div[@class='option-field' and @style = 'display: block;']//b[contains(@class,'toggle-option toggledown')]//following-sibling::div[@class= 'inst-instruction' and @style = 'display: block;']//following-sibling::a[text()]")
	private WebElement callTransferTextInSB;

	@FindBy(xpath = "//div[@class='input-field']//label//span[text()='Address 2']")
	private WebElement address2;

	@FindBys(@FindBy(xpath = "//div[@id = 'scenarios-list-holder']//ul//li//p[@id]"))
	private List<WebElement> scenarioTitleText;

	@FindBy(xpath = "//div[@class='lang-switch-wrp' and @style ='display: block;']//cite[@id='forms-lang-toggle']")
	private WebElement spanishToggleButton;

	@FindBy(xpath = "//div[@class='component-field']//div//p[@lang='es']")
	private List<WebElement> spanishScriptingText;

	@FindBy(xpath = "//div[@class='tab-pane active']//menu//a//i[@class='fa fa-map-marker']")
	private WebElement locationTab;

	@FindBy(xpath = "//li[@class='current']//span[1]")
	private WebElement presentDayText;

	@FindBys(@FindBy(xpath = "//div[@class='option-field']//b[contains(@class,'toggle-option toggledown t')]//following-sibling::ol//li//label"))
	private List<WebElement> decisionTreeOptionsText;

	@FindBy(xpath = "//article[@id = 'directory' and  contains(@style,'display: block;')]")
	private WebElement staffListBlock;

	@FindBys(@FindBy(xpath = "//article[@id = 'directory' and  contains(@style,'display: block;')]//ul//li//b"))
	private List<WebElement> listOfStaff;

	@FindBys(@FindBy(xpath = "//span[@class='hint--top']"))
	private List<WebElement> BusinessDayAppointment;

	@FindBys(@FindBy(xpath = "//ul[@id='hours_root']//li/span[text()='Closed']"))
	private List<WebElement> closedBusinessDays;

	@FindBys(@FindBy(xpath = "//div[@class='component-field']//ol//child::li//label"))
	private List<WebElement> decisionTreeOption;

	@FindBy(xpath = "//p[contains(@class,'receivecalls')]//lable")
	private WebElement callTranferContactText;

	@FindBy(xpath = "//p[contains(@class,'receivecalls')]//b")
	private WebElement callTranserTypeText;

	public SwitchBoardPage waitForSbLoad() {
		handle.parentToChildWindowControl(driver, 1);
		wait.waitForWebPageLoad(driver, 30);
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);

	}

	public SwitchBoardPage getYourRole() {
		wait.waitForElementToBeVisible(driver, yourRole, 30);
		yourRoleSb = yourRole.getText();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);

	}

	public SwitchBoardPage clickOnCloseAnswerPhraseButton() {
		wait.waitForElementToBeClickable(driver, answerPhraseCloseButton, 30);
		answerPhraseCloseButton.click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);
	}

	public SwitchBoardPage clickOnAccountInfo() {
		wait.waitForElementToBeClickable(driver, accountInfoButton, 30);
		accountInfoButton.click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);
	}

	public String getAccountGoal() {
		return accountGoalInSb.getText();
	}

	public SwitchBoardPage clickOnHours() {
		wait.waitForElementToBeClickable(driver, hoursTab, 30);
		hoursTab.click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);
	}

	public String getAnswerPhraseInSb() {
		wait.waitForElementToBeVisible(driver, answerPhrase, 30);
		return answerPhrase.getText();
	}

	public String getAccountNameInSb() {
		wait.waitForElementToBeVisible(driver, accountName, 30);
		return accountName.getText();
	}

	public String getAnswerPhraseInstructionInSb() {
		wait.waitForElementToBeVisible(driver, answerPhraseInstruction, 30);
		return " " + answerPhraseInstruction.getText();
	}

	public HomePage switchBoradPageClose() {
		handle.childWindowClose(driver, 1);
		handle.childToParentWindowControl(driver);
		return PageFactory.initElements(getDriver(), HomePage.class);

	}

	public String getBusinessHoursInSb() {
		wait.waitForElementToBeVisible(driver, hoursBlock, 30);
		String businessHours = customDayText.getText() + " " + customHourText.getText();
		return businessHours;
	}

	public String getCustomHolidayInSb() {
		wait.waitForElementToBeVisible(driver, hoursBlock, 30);
		wait.waitForElementToBeVisible(driver, customHolidayText, 30);
		int index = customHolidayText.getText().indexOf("-");
		String customHoliday = customHolidayText.getText().substring(0, index - 1);
		return customHoliday;
	}

	public SwitchBoardPage clickOnDirectory() {
		wait.waitForElementToBeClickable(driver, directoryTab, 30);
		directoryTab.click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);

	}

	public String getNewlyAddedStaffInSb() {
		wait.waitForElementToBeVisible(driver, staffListBlock, 30);
		int index = listOfStaff.size() - 1;
		scroll.scrollUpToWebElement(driver, listOfStaff.get(index));
		String staffName = listOfStaff.get(index).getText();
		return staffName;

	}

	public SwitchBoardPage waitUntilFormGotLoaded() {
		wait.waitForElementToBeVisible(driver, formLoaded, 30);
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);
	}

	public boolean isScriptLoaded() {
		if (formLoaded.isDisplayed())
			return true;
		else
			return false;
	}

	public SwitchBoardPage clickOnScenariosTab() {
		wait.waitForElementToBeClickable(driver, scenariosTab, 30);
		scenariosTab.click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);

	}

	public String getNewlyAddedScenario() {
		wait.waitForElementToBeClickable(driver, scenarios, 30);
		int size = listOfScenarioNames.size();
		return listOfScenarioNames.get(size - 1).getText();

	}

	public SwitchBoardPage clickOnNewlyAddedScenario() {
		wait.waitForElementToBeClickable(driver, scenarios, 30);
		int size = listOfScenarioNames.size();
		listOfScenarioNames.get(size - 1).click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);

	}

	public String getNewlyAddedScenarioSteps(int stepNumber) {
		return listSelectedScenarioSteps.get(stepNumber - 1).getText();
	}

	public String getRole() {
		wait.waitForElementToBeVisible(driver, role, 30);
		int length = roleType.getText().length();
		return role.getText().substring(length + 1);
	}

	public String getCompanyWorkingStatus() {
		wait.waitForElementToBeVisible(driver, companyWorkingStatus, 30);
		return companyWorkingStatus.getText();
	}

	public boolean isAppointmentSelectedInSb() {
		if (BusinessDayAppointment.size() == 1)
			return true;
		else
			return false;

	}

	public SwitchBoardPage enableOrDisableSpanishScripting() {
		wait.waitForElementToBeClickable(driver, spanishToggleButton, 30);
		spanishToggleButton.click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);

	}

	public SwitchBoardPage clickOnDecisionTreeOption(int optionNumber) {
		wait.waitForElementToBeVisible(driver, decisionTreeOption.get(optionNumber - 1), 30);
		decisionTreeOption.get(optionNumber - 1).click();
		return PageFactory.initElements(getDriver(), SwitchBoardPage.class);
	}

	public boolean isSpanishScriptingLoaded() {
		boolean enabled = false;
		wait.waitForElementToBeVisible(driver, spanishToggleButton, 30);
		enabled = true;
		return enabled;
	}

	public String getEnglishScriptingFieldsInSb(int field) {
		return scriptingField.get(field - 1).getText();
	}

	public String getSpanishScriptingFieldsInSb(int field) {
		return spanishScriptingText.get(field - 1).getText();
	}

	public int getBusinessHours() {
		wait.waitForAllElementsToBeVisible(driver, closedBusinessDays, 30);
		return closedBusinessDays.size();
	}

	public int getClosures() {
		wait.waitForAllElementsToBeVisible(driver, closuresAndHolidays, 30);
		return closuresAndHolidays.size();
	}

	public String getCallTranferContactInSb() {
		wait.waitForElementToBeVisible(driver, callTranferContactText, 30);
		return callTranferContactText.getText();
	}

	public String getCallTranferTypeInSb() {
		wait.waitForElementToBeVisible(driver, callTranserTypeText, 30);
		return callTranserTypeText.getText();
	}

	public String getFieldNameInSb(int fieldNumber) {
		return fieldNamesInSB.get(fieldNumber - 1).getText();
	}

	public int getNumberOfOptionsInDecisionTree() {
		return decisionTreeOption.size();
	}
}
