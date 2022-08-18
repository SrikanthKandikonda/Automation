package io.pagelibrary.fc2.o;

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

public class ScenariosPage extends HomePage {

	Wait wait = new Wait();
	RandomData scenariosPageData = new RandomData();
	Scroll scroll = new Scroll();
	WebDriver driver;

	public ScenariosPage(WebDriver driver) {
		this.driver = driver;
	}

	private static String yourRole;
	private static String customRole;
	private static String accountGoal;
	private static String AfterHourRole;
	private static String scenarioName;
	private static List<String> scenarioStep = new ArrayList<>();

	@FindBy(xpath = "//section[@id='scenarios']//div[@class='body']//ul//li[1]")
	private WebElement yourRoleButton;

	@FindBys(@FindBy(xpath = "//section[@id='scenarios']//div[@class='body']//ul//li[not(contains(@class,'your-role-static'))]"))
	private List<WebElement> listOfScenarios;

	@FindBy(xpath = "//section[@id='scenarios']//div[@class='body']//div[@class='add-step ph-4 pv-2']//p")
	private WebElement addScenarioButton;

	@FindBy(xpath = "//div[@class='role']//button")
	private WebElement chooseYourRoleDropDown;

	@FindBy(xpath = "//div[@class='your-role g-dropdown-wrap dropdown--a open']")
	private WebElement chooseYourRoleOptionsBlock;

	@FindBys(@FindBy(xpath = "//div[@class='your-role g-dropdown-wrap dropdown--a open']//div[@class='g-dropmenu']//ul//li//button"))
	private List<WebElement> chooseYourRoleOptions;

	@FindBys(@FindBy(xpath = "//div[@class='your-role g-dropdown-wrap dropdown--a open']//div[@class='g-dropmenu']//ul//li//span"))
	private List<WebElement> chooseYourRoleOptionsText;

	@FindBy(xpath = "//div[@class='your-role g-dropdown-wrap dropdown--a']//div[@class='g-dropmenu']//ul//li[@class='has-check selected']//span")
	private WebElement selectedYourRole;

	@FindBy(xpath = "//div[contains(@class,'custom-role g-input-wrapper show')]//input")
	private WebElement customRoleTextField;

	@FindBy(xpath = "//div[@class='textarea-wrapper mb-3 ps-r']//textarea")
	private WebElement accountGoalTextField;

	@FindBys(@FindBy(xpath = "//div[@class='add-after-hours']//button[@class='va-m g-checkbox g-checkbox-checked']"))
	private List<WebElement> selectedAddAfterHourRoleCheckBox;

	@FindBy(xpath = "//div[@class='add-after-hours']//button")
	private WebElement addAfterHourRoleCheckBox;

	@FindBy(xpath = "//h3[@class='sub-heading mb-1 mt-5']//ancestor::div[@style='display: block;']//following-sibling::div//button[@class='g-drop-btn']")
	private WebElement chooseAfterHourRoleDropDownButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--a open']")
	private WebElement chooseAfterHourRoleOptionsBlock;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--a open']//div[@class='g-dropmenu']//ul//li//button"))
	private List<WebElement> chooseAfterHourRoleOptions;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--a open']//div[@class='g-dropmenu']//ul//li//span"))
	private List<WebElement> chooseAfterHourRoleOptionsText;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--a']//div[@class='g-dropmenu']//ul//li[@class='has-check selected']//span")
	private WebElement selectedAfterHourRole;

	@FindBy(xpath = "//*[name()='svg' and @class  ='ml-1']")
	private WebElement editScenarioNameButton;

	@FindBy(xpath = "//p[contains(@class,'delete cur-ptr')]")
	private WebElement deleteScenarioButton;

	@FindBy(xpath = "//div[@class='input-toggle tw-b']//input[@class='g-input']")
	private WebElement scenarioNameTextField;

	@FindBy(xpath = "//div[@class='input-toggle tw-b']//span")
	private WebElement scenarioNameText;

	@FindBys(@FindBy(xpath = "//div[@class='body ph-6 pt-5 pb-24']//ul//li//div[contains(@class,'input--editable')]"))
	private List<WebElement> scenarioStepsTextField;

	@FindBys(@FindBy(xpath = "//div[@class='body ph-6 pt-5 pb-24']//ul//li//div[contains(@class,'input--editable')]//p"))
	private List<WebElement> scenarioStepsText;

	@FindBys(@FindBy(xpath = "//div[@class='body ph-6 pt-5 pb-24']//ul//li//div[@class='fx fx-j-sb fx-a-c']//i"))
	private List<WebElement> scenarioStepsDeleteButton;

	@FindBy(xpath = "//div[@class='add-step']//p")
	private WebElement addScenarioStepButton;

	@FindBy(xpath = "//div[@class='footer-overlay ps-f ps-btm ps-right main-view-width']//div//button[@class='g-btn-negative']")
	private WebElement cancelScenarioChangesButton;

	@FindBy(xpath = "//div[@class='footer-overlay ps-f ps-btm ps-right main-view-width']//div//button[@class='g-btn-primary']")
	private WebElement saveScenarioChangesButton;

	@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Scenario was deleted successfully']")
	private WebElement scenarioDeletedNotification;

	public ScenariosPage clickOnYourRole() {
		wait.waitForElementToBeClickable(driver, yourRoleButton, 30);
		yourRoleButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnChooseYourRoleDropDown() {
		wait.waitForElementToBeClickable(driver, chooseYourRoleDropDown, 30);
		chooseYourRoleDropDown.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage selectAnyYourRole() {
		wait.waitForElementToBeClickable(driver, chooseYourRoleOptionsBlock, 30);
		int option = scenariosPageData.randomIntegerData(0, 8);
		scroll.scrollUpToWebElement(driver, chooseYourRoleOptions.get(option));
		yourRole = chooseYourRoleOptionsText.get(option).getText();
		chooseYourRoleOptions.get(option).click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage selectCustomYourRole() {
		wait.waitForElementToBeClickable(driver, chooseYourRoleOptionsBlock, 30);
		int size = chooseYourRoleOptions.size();
		scroll.scrollUpToWebElement(driver, chooseYourRoleOptions.get(size - 1));
		chooseYourRoleOptions.get(size - 1).click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage enterCustomRole() {
		wait.waitForElementToBeClickable(driver, customRoleTextField, 30);
		customRole = scenariosPageData.randomData("Custom Role");
		customRoleTextField.sendKeys(customRole);
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage enterAccountGoal() {
		wait.waitForElementToBeClickable(driver, accountGoalTextField, 30);
		scroll.scrollUpToWebElement(driver, accountGoalTextField);
		accountGoal = scenariosPageData.randomData("Account Goal");
		accountGoalTextField.clear();
		accountGoalTextField.sendKeys(accountGoal);
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnAddAfterHours() {
		wait.waitForElementToBeClickable(driver, addAfterHourRoleCheckBox, 30);
		scroll.scrollUpToWebElement(driver, addAfterHourRoleCheckBox);
		if (selectedAddAfterHourRoleCheckBox.size() == 0)
			addAfterHourRoleCheckBox.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnChooseAfterHourRoleDropDown() {
		wait.waitForElementToBeClickable(driver, chooseAfterHourRoleDropDownButton, 30);
		chooseAfterHourRoleDropDownButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage selectAnyAfterHourRole() {
		wait.waitForElementToBeClickable(driver, chooseAfterHourRoleOptionsBlock, 30);
		int option = scenariosPageData.randomIntegerData(0, 8);
		scroll.scrollUpToWebElement(driver, chooseAfterHourRoleOptions.get(option));
		AfterHourRole = chooseAfterHourRoleOptionsText.get(option).getText();
		chooseAfterHourRoleOptions.get(option).click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage selectCustomAfterHourRole() {
		wait.waitForElementToBeClickable(driver, chooseAfterHourRoleOptionsBlock, 30);
		scroll.scrollUpToWebElement(driver, chooseAfterHourRoleOptions.get(10));
		chooseAfterHourRoleOptions.get(10).click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnAddScenario() {
		wait.waitForElementToBeClickable(driver, addScenarioButton, 30);
		addScenarioButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnNewlyAddedScenario() {
		wait.waitForElementToBeClickable(driver, addScenarioButton, 30);
		int size = listOfScenarios.size();
		listOfScenarios.get(size - 1).click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnEditScenario() {
		wait.waitForElementToBeClickable(driver, editScenarioNameButton, 30);
		editScenarioNameButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage enterScenarioName() {
		wait.waitForElementToBeClickable(driver, scenarioNameTextField, 30);
		scenarioName = scenariosPageData.randomData("Scenario Name");
		scenarioNameTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		scenarioNameTextField.sendKeys(scenarioName);
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnDeleteScenario() {
		wait.waitForElementToBeClickable(driver, deleteScenarioButton, 30);
		deleteScenarioButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage enterScenrioStep(int stepNumber) {
		wait.waitForElementToBeClickable(driver, addScenarioStepButton, 30);
		String scenarioStepData = scenariosPageData.randomData("Scenario Step");
		scenarioStepsTextField.get(stepNumber - 1).click();
		scenarioStepsTextField.get(stepNumber - 1).clear();
		scenarioStepsTextField.get(stepNumber - 1).sendKeys(scenarioStepData);
		scenarioStep.add(scenarioStepData);
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnAddScenarioStep() {
		wait.waitForElementToBeClickable(driver, addScenarioStepButton, 30);
		addScenarioStepButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnDeleteScenarioStep(int stepNumber) {
		wait.waitForElementToBeClickable(driver, addScenarioStepButton, 30);
		scenarioStepsTextField.get(stepNumber - 1).click();
		wait.waitForElementToBeClickable(driver, scenarioStepsDeleteButton.get(stepNumber - 1), 30);
		scenarioStepsDeleteButton.get(stepNumber - 1).click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnSaveScenarioChanges() {
		wait.waitForElementToBeClickable(driver, saveScenarioChangesButton, 30);
		saveScenarioChangesButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public ScenariosPage clickOnCancelScenarioChanges() {
		wait.waitForElementToBeClickable(driver, cancelScenarioChangesButton, 30);
		cancelScenarioChangesButton.click();
		return PageFactory.initElements(getDriver(), ScenariosPage.class);
	}

	public String getYourRoleBeforeSaving() {
		return yourRole;
	}

	public String getCustomRoleBeforeSaving() {
		return customRole;
	}

	public String getAccountGoalBeforeSaving() {
		return accountGoal;
	}

	public String getAfterHourRoleBeforeSaving() {
		return AfterHourRole;
	}

	public String getScenariosNameBeforeSaving() {
		return scenarioName;
	}

	public String getScenarioStepBeforeSaving(int stepNumber) {
		return scenarioStep.get(stepNumber - 1);
	}

	public String getYourRoleAfterSaving() {
		return selectedYourRole.getText();
	}

	public String getAfterHourRoleAfterSaving() {
		return selectedAfterHourRole.getText();
	}

	public String getAccountGoalAfterSaving() {
		return accountGoalTextField.getText();
	}

	public String getScenarioNameAfterSaving() {
		return scenarioNameText.getText();
	}

	public String getScenarioStepAfterSaving(int stepNumber) {
		return scenarioStepsText.get(stepNumber - 1).getText();
	}

	public boolean isScenarioDeleted() {
		try {
			wait.waitForElementToBeVisible(driver, scenarioDeletedNotification, 30);
			return true;
		} catch (Exception scenarioDeletedNotification) {
			return false;
		}

	}

}
