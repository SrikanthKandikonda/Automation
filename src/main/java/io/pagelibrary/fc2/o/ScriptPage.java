package io.pagelibrary.fc2.o;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.utils.fc2.o.DragAndDrop;
import io.utils.fc2.o.RandomData;
import io.utils.fc2.o.Scroll;
import io.utils.fc2.o.Wait;

public class ScriptPage extends HomePage {

	Wait wait = new Wait();
	DragAndDrop dragAndDrop = new DragAndDrop();
	Scroll scroll = new Scroll();
	RandomData scriptData = new RandomData();
	WebDriver driver;
	private static int fieldCountOfOriginalForm;
	private static int fieldCountOfCopiedForm;
	private static String cacheStatus;
	private static String url;
	private static List<String> englishScriptingField = new ArrayList<String>();
	private static List<String> spanishScriptingField = new ArrayList<String>();
	private static String newFormName;
	private static String selectedCallTransferContact;
	private static String selectedTransferType;
	private static String selectedCallFlowType;
	private static String selectedCallFlowContact;

	public ScriptPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='scripts-wrapper ps-r full--h pb-14']//ul[@class='form overflow-a ps-r']")
	private WebElement scriptContent;

	@FindBy(xpath = "//div[@class='scripts-wrapper ps-r full--h pb-14']//ul[@class='form overflow-a ps-r']//li[@class='field-placeholder drag']")
	private WebElement fieldsDrop;

	@FindBy(xpath = "//li[contains(@class,'group col-2 g-dropdown-wrap bdr-b ignore-click')][1]//button")
	private WebElement group1DropDownButton;

	@FindBys(@FindBy(xpath = "//li[contains(@class,'group col-2 g-dropdown-wrap bdr-b ignore-click')][1]//ul/li"))
	private List<WebElement> group1Fields;

	@FindBy(xpath = "//li[contains(@class,'group col-2 g-dropdown-wrap bdr-b ignore-click')][2]//button")
	private WebElement group2DropDownButton;

	@FindBys(@FindBy(xpath = "//li[contains(@class,'group col-2 g-dropdown-wrap bdr-b ignore-click')][2]//ul/li"))
	private List<WebElement> group2Fields;

	@FindBy(xpath = "//li[contains(@class,'group col-1 g-dropdown-wrap bdr-b ignore-click')][1]//button")
	private WebElement userTemplateDropDownButton;

	@FindBys(@FindBy(xpath = "//li[contains(@class,'group col-1 g-dropdown-wrap bdr-b ignore-click')][1]//div//ul//li"))
	private List<WebElement> userTemplateFields;

	@FindBy(xpath = "//li[contains(@class,'group col-1 g-dropdown-wrap bdr-b ignore-click')][2]//button[@class='g-drop-btn fx fx-j-sb fx-a-c ph-1']")
	private WebElement importScriptTemplateDropDownButton;

	@FindBy(xpath = "//li[@class='group col-1 g-dropdown-wrap bdr-b ignore-click open']//div//child::button[@class='g-btn-primary g-btn-sm ml-4 mt-2 mb-4']//span")
	private WebElement importScriptButton;

	@FindBy(xpath = "//div[@class='modal-window import-script-modal']//div[@class='g-modal-wrapper alternate']//i[@class='g-modal-close']")
	private WebElement closeImportScriptTemplateBlock;

	@FindBy(xpath = "//div[@class='modal-window import-script-modal']//div[@class='g-modal-wrapper alternate']//input")
	private WebElement enterAccountNumberField;

	@FindBy(xpath = "//div[@class='modal-window import-script-modal']//div[@class='g-modal-wrapper alternate']//a")
	private WebElement findImportTemplateButton;

	@FindBy(xpath = "//div[@class='modal-window import-script-modal']//div[@class='g-modal-wrapper alternate']//button[contains(@class,'g-btn-primary')]")
	private WebElement importButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap mt-6']//button[@class='g-drop-btn']")
	private WebElement chooseFormDropDown;

	@FindBy(xpath = "//div[@class='g-dropmenu' and @style = 'display: block;']/ul/li[1]")
	private WebElement firstOptionInChooseFromDropDown;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropmenu' and @style = 'display: block;']/ul/li"))
	private List<WebElement> optionsInChooseFromDropDown;

	@FindBys(@FindBy(xpath = "//li[@class='group col-1 g-dropdown-wrap bdr-b ignore-click open']/div/ul/li"))
	private List<WebElement> importTemplateFields;

	@FindBy(xpath = "//div[@class='modal-window import-script-modal']//div[@class='g-modal-wrapper alternate']//button[@class='g-btn-negative']")
	private WebElement cancelImportButton;

	@FindBy(xpath = "//li[contains(@class,'group col-1 dg g-dropdown-wrap bdr-b ignore-click')]//button[@class='g-drop-btn fx fx-j-sb fx-a-c ph-1']")
	private WebElement deliveryGroupDropDownButton;

	@FindBys(@FindBy(xpath = "//li[@class='group col-1 dg g-dropdown-wrap bdr-b ignore-click open']//div//ul//li"))
	private List<WebElement> deliveryGroups;

	@FindBys(@FindBy(xpath = "//li[@class='group col-1 dg g-dropdown-wrap bdr-b ignore-click open']//div//ul//li//button"))
	private List<WebElement> addDeliveryGroupButton;

	@FindBy(xpath = "//li[@class='group col-1 dg g-dropdown-wrap bdr-b ignore-click open']")
	private WebElement deliveryGroupsBlock;

	@FindBy(xpath = "//li[contains(@class,'group col-1 dg g-dropdown-wrap bdr-b ignore-click')]//div[@class='searchbox']//input")
	private WebElement deliveryGroupSearchField;

	@FindBy(xpath = "//section//div//input[@class='g-input' and @type='number']")
	private WebElement numberOfChoicesInDecisionTreeTextField;

	@FindBy(xpath = "//section//footer//button[@class='g-btn-primary']")
	private WebElement submitButtonForNumberOfChoicesInDecisionTreeAndSelectOnCallCalendar;

	@FindBy(xpath = "//section//footer//button[@class='g-btn-negative' and text()='Cancel']")
	private WebElement cancelButtonForNumberOfChoicesInDecisionTree;

	@FindBy(xpath = "//div[@class='modal-window decision-tree-modal']//i[@class='g-modal-close']")
	private WebElement closeButtonForNumberOfChoicesInDecisionTree;

	@FindBy(xpath = "//div[@class='forms-nav-wrapper ps-r fx']//button[@class='icon-add']")
	private WebElement addNewFormButton;

	@FindBy(xpath = "//div[contains(@class,'view-options g-dropdown-wrap ml-5 dropdown--manipulate popover-style va-m')]//button[@class='g-drop-btn']")
	private WebElement viewInDropDownButton;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'view-options g-dropdown-wrap ml-5 dropdown--manipulate popover-style va-m open')]//ul//li"))
	private List<WebElement> viewInDropDownMenu;

	@FindBy(xpath = "//div[contains(@class,'view-options g-dropdown-wrap ml-5 dropdown--manipulate popover-style va-m open')]//ul//li[1]")
	private WebElement switchBoardButtonInViewInMenu;

	@FindBy(xpath = "//div[contains(@class,'more-options g-dropdown-wrap ml-5 dropdown--manipulate popover-style va-m')]//button[@class='g-drop-btn']")
	private WebElement threeDotsButton;

	@FindBy(xpath = "//div[contains(@class,'more-options g-dropdown-wrap ml-5 dropdown--manipulate popover-style va-m open')]")
	private WebElement threeDotsMenuBlock;

	@FindBys(@FindBy(xpath = "//div[contains(@class,'more-options g-dropdown-wrap ml-5 dropdown--manipulate popover-style va-m open')]//div[@class='g-dropmenu has-arrow pv-3 tw-m']//ul//li"))
	private List<WebElement> threeDotsMenu;

	@FindBy(xpath = "//div[@class='fx fx-a-c tc-black-400']//button[@class='remove-fields bdr-rad-circle ps-r']")
	private WebElement collapseScriptButton;

	@FindBy(xpath = "//div[@class='fx fx-a-c tc-black-400']//button[@class='add-fields bdr-rad-circle ps-r']")
	private WebElement expandScriptButton;

	@FindBy(xpath = "//div[@class='col-right fx fx-a-c']//button[@class='delete bdr-rad-circle']")
	private WebElement deleteScriptButton;

	@FindBy(xpath = "//div[@class='col-right fx fx-a-c']//button[@id='save-form']")
	private WebElement saveScriptButton;

	@FindBy(xpath = "//div[@class='col-right fx fx-a-c']//button//span[text()='Publish']")
	private WebElement publishScriptButton;

	@FindBy(xpath = "//div[@class='multiselect fx fx-j-sb fx-a-c pl-6 pr-5 ']//button[@class='multiselect-btn text--12']")
	private WebElement multiSelectButton;

	@FindBy(xpath = "//div[@class='multiselect fx fx-j-sb fx-a-c pl-6 pr-5 ']//button[@id='undoInFormsButton']")
	private WebElement undoButton;

	@FindBy(xpath = "//div[@class='multiselect fx fx-j-sb fx-a-c pl-6 pr-5 ']//button[@id='redoInFormsButton']")
	private WebElement redoButton;

	@FindBy(xpath = "//button[@class='arrow-btn ps-a bdr-rad-circle']")
	private WebElement settingsViewButton;

	@FindBy(xpath = "//div[@class='full--h  pv-3']//a[@data-attr='field']")
	private WebElement fieldSettingButton;

	@FindBy(xpath = "//div[@class='full--h  pv-3']//a[@data-attr='form']")
	private WebElement formSettingsButton;

	@FindBy(xpath = "//div[@id='form-settings']//ul/li[1]//input")
	private WebElement formNameTextField;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='fx fx-j-sb fx-a-c']//span[text()='Lock message form']//following-sibling::div")
	private WebElement lockMessageFormToggleButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='fx fx-j-sb fx-a-c']//span[text()='Holiday Script']//following-sibling::div")
	private WebElement holidayScriptingToggleButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='fx fx-j-sb fx-a-c']//span[text()='Spanish Script']//following-sibling::div")
	private WebElement spanishScriptingToggleButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='set-output-order i-fx fx-col']//button[text()='Integrations/CSV']")
	private WebElement autoSetOutputOrderForIntegrationsOrCSVButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='set-output-order i-fx fx-col']//button[text()='Messages']")
	private WebElement autoSetOutputOrderForMessageFormButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='g-dropdown-wrap']//div//button")
	private WebElement copyOutputOrderFromScriptButton;

	@FindBys(@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='g-dropdown-wrap open']//div[@class='g-dropmenu']//ul/li"))
	private List<WebElement> scriptsInCopyOutputOrderFromScript;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//button[@class='export-btn g-btn-primary line']")
	private WebElement formExportButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='email-config text--sm']//ul//li//button[@data-type='static']")
	private WebElement staticEmailSubjectRadioButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='email-config text--sm']//ul//li//button[@data-type='custom']")
	private WebElement configurableEmailSubjectRadioButton;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='email-config text--sm']//ul//li//textarea")
	private WebElement staticEmailSubjectTextField;

	@FindBy(xpath = "//div[@class='form-settings ']//ul/li//div[@class='email-config text--sm']//ul//li//input")
	private WebElement configurableEmailSubjectTextField;

	@FindBy(xpath = "//div[@class='forms-nav-wrapper ps-r fx']//a")
	private List<WebElement> scriptsButton;

	@FindBy(xpath = "//span[@class='spinner spinner-text ps-r mr-6']")
	private WebElement clearCacheLoaderIcon;

	@FindBy(xpath = "//span[@class='spinner spinner-text ps-r mr-6 ']")
	private WebElement scriptLoaderIcon;

	@FindBys(@FindBy(xpath = "//span[@class='spinner spinner-text ps-r mr-6 ']"))
	private List<WebElement> scriptLoadedIcon;

	@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Clear cache process initiated']")
	private WebElement clearCacheIntiatedNotification;

	@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Cache got cleared successfully']")
	private WebElement cacheClearedNotification;

	@FindBys(@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='Cache got cleared successfully']"))
	private List<WebElement> cacheClearedNotificationInvisible;

	@FindBy(xpath = "//h4[text()='Copy Account Template']//ancestor::div[@class='g-modal-wrapper alternate']//input[@data-attr='from']")
	private WebElement copyAccountTemplateFromTextField;

	@FindBy(xpath = "//h4[text()='Copy Account Template']//ancestor::div[@class='g-modal-wrapper alternate']//input[@data-attr='to']")
	private WebElement copyAccountTemplateToTextField;

	@FindBys(@FindBy(xpath = "//h4[text()='Copy Account Template']//ancestor::div[@class='g-modal-wrapper alternate']//ul//li//button"))
	private List<WebElement> chooseTemplateMethodsCheckBoxes;

	@FindBy(xpath = "//h4[text()='Copy Account Template']//ancestor::div[@class='g-modal-wrapper alternate']//footer//button[@class='g-btn-negative']")
	private WebElement cancelCopyAccountTemplateButton;

	@FindBy(xpath = "//h4[text()='Copy Account Template']//ancestor::div[@class='g-modal-wrapper alternate']//footer//button[@class='g-btn-primary ']")
	private WebElement copyAccountTemplateButton;

	@FindBy(xpath = "//h4[text()='Copy Account Template']//ancestor::div[@class='g-modal-wrapper alternate']//i[@class='g-modal-close']")
	private WebElement closeCopyAccountTemplateButton;

	@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='We are processing your copy request. You will receive an email notification shortly']")
	private WebElement copyAccountTemplateProcessIntiatedNotification;

	@FindBys(@FindBy(xpath = "//div[@class='g-notification main-view-width']//p[text()='We are processing your copy request. You will receive an email notification shortly']"))
	private List<WebElement> copyAccountTemplateProcessIntiatedNotificationList;

	@FindBy(xpath = "//span[@class='text--sm']//span")
	private WebElement fieldCountText;

	@FindBys(@FindBy(xpath = "//div[@class='forms-nav-wrapper ps-r fx']//nav//a"))
	private List<WebElement> listOfFormButtons;

	@FindBys(@FindBy(xpath = "//div[@class='forms-nav-wrapper ps-r fx']//nav//a[not(i)]"))
	private List<WebElement> listOfUnpulishedForms;

	@FindBy(xpath = "//*[name()='svg']//*[name()='circle'and @class='path']")
	private WebElement accountLoading;

	@FindBy(xpath = "//*[name()='svg']//*[name()='circle'and @class='path']")
	private List<WebElement> accountLoaded;

	@FindBy(xpath = "//h4[text()='Confirm']//ancestor::div[@class='g-modal-wrapper alternate']//i")
	private WebElement closeConfirmDeleteOrPublishFormNotificationPopUp;

	@FindBy(xpath = "//h4[text()='Confirm']//ancestor::div[@class='g-modal-wrapper alternate']//footer//button[1]")
	private WebElement cancelDeleteOrPublishButton;

	@FindBy(xpath = "//h4[text()='Confirm']//ancestor::div[@class='g-modal-wrapper alternate']//footer//button[2]")
	private WebElement confirmDeleteOrPublishButton;

	@FindBy(xpath = "//h4[text()='Delete Fields']//ancestor::div[@class='g-modal-wrapper alternate']//i")
	private WebElement closeConfirmDeleteFieldsButton;

	@FindBy(xpath = "//h4[text()='Delete Fields']//ancestor::div[@class='g-modal-wrapper alternate']//footer//button[1]")
	private WebElement cancelDeleteFieldsButton;

	@FindBy(xpath = "//h4[text()='Delete Fields']//ancestor::div[@class='g-modal-wrapper alternate']//footer//button[2]")
	private WebElement confirmDeleteFieldsButton;

	@FindBy(xpath = "//div[@class='forms-nav-wrapper ps-r fx']")
	private WebElement formsBlock;

	@FindBy(xpath = "//span[@class='published text--lg tw-']//span[@class='italic va-m']")
	private WebElement formStatusText;

	// Script Fields

	@FindBy(xpath = "//li[@class='decision-tree form-field']//ul[@class='form-field formFieldMain']//div[@class='parent-wrapper decisionTree ']")
	private WebElement decisionTree;

	@FindBy(xpath = "//li[@class='decision-tree form-field']//ul[@class='form-field formFieldMain']//button[@class='collapse-toggle']")
	private WebElement decisionTreeExpandOrCollapseButton;

	@FindBys(@FindBy(xpath = "//li[@class='decision-tree form-field']//ul[@class='form-field formFieldMain']//div[@class='parent-wrapper decisionTree ']//following-sibling::ul//li//div[@class='parent-wrapper']//h3"))
	private List<WebElement> decisionTreeOptions;

	@FindBy(xpath = "//li[@class='decision-tree form-field']//ul[@class='form-field formFieldMain']//div[@class='parent-wrapper decisionTree ']//following-sibling::ul//li//div[@class='parent-wrapper']//following-sibling::ul//li[@id='placeholder']")
	private WebElement dragInsideDecisionTree;

	@FindBys(@FindBy(xpath = "//li[@class='decision-tree form-field']//ul[@class='form-field formFieldMain']//div[@class='parent-wrapper decisionTree ']//following-sibling::ul//li//button"))
	private List<WebElement> decisionTreeOptionExpandOrCollapseButton;

	@FindBys(@FindBy(xpath = "//ul[@class='call-flow generic-flow']//li//button[@class='g-radio-button mr-2 va-m ']"))
	private List<WebElement> callTransferContactsRadioButtons;

	@FindBys(@FindBy(xpath = "//ul[@class='call-flow generic-flow']//li//span[@class='text--xs tw-m va-m']"))
	private List<WebElement> callTransferContactsText;

	@FindBy(xpath = "//ul[@class='call-flow generic-flow']//li//button[@class='g-radio-button mr-2 va-m g-radio-selected']//following-sibling::span[@class='text--xs tw-m va-m']")
	private WebElement selectedContactForCallTranfer;

	@FindBy(xpath = "//ul[@class='call-flow generic-flow']")
	private WebElement callTranserContactsBlock;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap']//label//following::div[1]//button")
	private WebElement transferTypeDropDownButton;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap open']//label//following::div[1]//div//ul//li"))
	private List<WebElement> transferTypeOptions;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap open']//label//following::div[1]//div//ul//li//span"))
	private List<WebElement> transferTypeOptionsText;

	@FindBys(@FindBy(xpath = "//span[@class='repeateValue']"))
	private List<WebElement> setRepeatField;

	@FindBy(xpath = "//ul[@class='set-repeat text--xs']")
	private WebElement setReaptFieldSettingsBlock;

	@FindBys(@FindBy(xpath = "//ul[@class='set-repeat text--xs']//li//button"))
	private List<WebElement> setReaptRadioButtons;

	@FindBys(@FindBy(xpath = "//label[text()='URL Name']"))
	private List<WebElement> websiteField;

	@FindBy(xpath = "//div[contains(@class,'form-field cust-status formFieldMain')]")
	private WebElement customerStatusField;

	@FindBy(xpath = "//label[text()='URL']//ancestor::div[@class='g-input-wrapper ']")
	private WebElement urlBlock;

	@FindBy(xpath = "//label[text()='URL']//following-sibling::textarea")
	private WebElement urltextField;

	@FindBy(xpath = "//label[text()='Client Status']//following-sibling::textarea")
	private WebElement customerStatusTextField;

	@FindBy(xpath = "//button[@class='g-drop-btn flat-date-button']")
	private WebElement customerStatusCalendarButton;

	@FindBys(@FindBy(xpath = "//ul[@class='form overflow-a ps-r']//li[@draggable]"))
	private List<WebElement> listOfFieldsInScript;

	@FindBys(@FindBy(xpath = "//ul[@class='form overflow-a ps-r']//li[@draggable]//label"))
	private List<WebElement> listOfFieldInScriptLabels;

	@FindBy(xpath = "//div[@class='default-view']//button[@class='multiselect-btn text--12']")
	private WebElement multiSelectModeButton;

	@FindBy(xpath = "//div[@class='clipboard-options text--0 hide']//button[@class='clear-selection text--sm va-btm']")
	private WebElement clearSelectionButton;

	@FindBy(xpath = "//span[@class='d-ib va-m ']//button[@data-tips='Cut selected fields']")
	private WebElement cutMultipleFieldsButton;

	@FindBy(xpath = "//div[@class='default-view']//button[@class='paste-btn ml-4  g-data-tips bottom-tip ']")
	private WebElement pastIntoFormButton;

	@FindBy(xpath = "//span[@class='d-ib va-m ']//button[@data-tips='Copy selected fields']")
	private WebElement copyMultipleFieldsButton;

	@FindBy(xpath = "//span[@class='d-ib va-m ']//button[@data-tips='Delete selected fields']")
	private WebElement deleteMultipleFieldsButton;

	@FindBys(@FindBy(xpath = "//ul[@class='choice-fields-list']//li//button[@class='bdr-rad-circle']"))
	private List<WebElement> addChoiceFields;

	@FindBy(xpath = "//li[@style='display: inline-block;']//ul[@class='choice-fields-list']")
	private WebElement addChoiceFieldsBlock;

	@FindBy(xpath = "//label[@class='g-drop-label']//following-sibling::div//button//span[text()='Default']")
	private WebElement defaultCalendarDropDownButton;

	@FindBys(@FindBy(xpath = "//i[@class='indicator indicator--orange d-ib bdr-rad-circle mr-1']"))
	private List<WebElement> holidayScriptEnabledStatus;

	@FindBy(xpath = "//div[contains(@class,'g-input-wrapper ')]//label[text()='Field Script (English)']//following-sibling::textarea")
	private WebElement fieldScriptEnglishTextField;

	@FindBy(xpath = "//div[contains(@class,'g-input-wrapper ')]//label[text()='Field Script (Spanish)']//following-sibling::textarea")
	private WebElement fieldScriptSpanishTextField;

	@FindBys(@FindBy(xpath = "//div[@class='forms-nav-wrapper ps-r fx']//nav//a//span"))
	private List<WebElement> listOfFormNames;

	@FindBy(xpath = "//h3[@class='fieldLabel ' and text()='Decision Tree']//following-sibling::div[@class='options ps-a']//i[@type='cut']")
	private WebElement cutDecisionTreeButton;

	@FindBy(xpath = "//h3[@class='fieldLabel ' and text()='Decision Tree']//following-sibling::div[@class='options ps-a']//i[@type='copy']")
	private WebElement copyDecisionTreeButton;

	@FindBy(xpath = "//h3[@class='fieldLabel ' and text()='Decision Tree']//following-sibling::div[@class='options ps-a']//i[@data-tips='Delete']")
	private WebElement deleteDecisionTreeButton;

	@FindBy(xpath = "//div[@class='flatpickr-calendar hasTime animate static open arrowTop']")
	private WebElement customerStatusCalendarBlock;

	@FindBy(xpath = "//span[contains(@class,'today')]//following-sibling::span[1]")
	private WebElement customStatusTomorrow;

	@FindBy(xpath = "//div[@class='flatpickr-time']//span[@class='flatpickr-am-pm']")
	private WebElement customStatusExpiredDateAmOrPM;

	@FindBy(xpath = "//span[contains(@class,'flatpickr-next-month')]")
	private WebElement calendarNextMonthButton;

	@FindBys(@FindBy(xpath = "//label[contains(@class,'fieldLabel') and contains(text(),'Updated In Cwa')]"))
	private List<WebElement> updatedScriptFieldsText;

	@FindBy(xpath = "//div[contains(@class,'form-field call-transfer formFieldMain')]")
	private WebElement callTransferField;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap']//label//following::div[1]//button//span")
	private WebElement callTransferTypeDropDownText;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap']//label//following::div[1]//button")
	private WebElement specificPeopleCallDropDownButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap']//label//following::div[1]//button//span")
	private WebElement specifcPeopleCallTypeDropDownText;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap open']//label//following::div[1]//div//ul//li"))
	private List<WebElement> specificPeopleCallOptions;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap open']//label//following::div[1]//div//ul//li//span"))
	private List<WebElement> specificPeopleCallOptionsText;

	@FindBys(@FindBy(xpath = "//ul[contains(@class,'call-flow')]//li//button"))
	private List<WebElement> specificPeopleCallFlowContactCheckBox;

	@FindBys(@FindBy(xpath = "//ul[contains(@class,'call-flow')]//li//button//following-sibling::span"))
	private List<WebElement> specificPeopleCallFlowContactCheckBoxText;

	@FindBy(xpath = "//ul[contains(@class,'call-flow')]//li//button[contains(@class,'checked')]//following-sibling::span")
	private WebElement specificPeopleCallFlowContactText;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap']//button[@class='g-drop-btn']//span[contains(text(),'/')]")
	private WebElement dateDropDownField;

	@FindBys(@FindBy(xpath = "//div[@class='g-dropdown-wrap open']//div[@class='g-dropmenu']//ul//li"))
	private List<WebElement> dateDropDownOptions;

	@FindBy(xpath = "//div[@class='fx fx-j-sb fx-a-c']//span[text()='Time Based Call Flow']//following-sibling::div")
	private WebElement timeBasedCallFlowToggleButton;

	@FindBys(@FindBy(xpath = "//h5[text()='Call flow Time']//ancestor::div[@class='g-business-hrs']//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul[@class='g-custom-hrs ']//child::li//button[contains(@class,'g-checkbox')]"))
	private List<WebElement> timeBasedCallFlowDayCheckBoxes;

	@FindBys(@FindBy(xpath = "//h5[text()='Call flow Time']//ancestor::div[@class='g-business-hrs']//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul[@class='g-custom-hrs ']//child::li//button[@class='g-checkbox g-checkbox-checked']"))
	private List<WebElement> selectedTimeBasedCallFlowDays;

	@FindBys(@FindBy(xpath = "//h5[text()='Call flow Time']//ancestor::div[@class='g-business-hrs']//div[@class='g-custom-hrs-wrap g-multiple-timerange']//ul//li//div[contains(@class,'g-drop-btn ')]"))
	private List<WebElement> timeBasedCallFlowFormTimeAndToTimeButtons;

	@FindBy(xpath = "//div[@class='g-business-hrs']//button[text()='Use Business Hours']")
	private WebElement useBusinessHoursButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate popover-style dropdownDiv']//button")
	private WebElement timeBasedCallFlowThreeDotsButton;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap dropdown--manipulate popover-style dropdownDiv open']//div[@class='g-dropmenu has-arrow']//ul//li[1]")
	private WebElement applyAllButton;

	@FindBy(xpath = "//h1[text()='Script']//following::div[@class='acc-info'][1]//button//span")
	private WebElement accountNumberText;

	@FindBy(xpath = "//div[@class='form-field italic']//p[text()='Set Appointment Provider']")
	private WebElement appointmentField;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap']//label[text()='Appointment Provider']//following-sibling::div//button")
	private WebElement appointmentProviderDropDown;

	@FindBy(xpath = "//div[@class='g-dropdown-wrap open']//label[text()='Appointment Provider']//following-sibling::div//ul//li")
	private List<WebElement> appointmentProviderOptions;

	public ScriptPage clickOnGroup1Button() {
		wait.waitForElementToBeClickable(driver, group1DropDownButton, 30);
		group1DropDownButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnGroup2Button() {
		wait.waitForElementToBeClickable(driver, group2DropDownButton, 30);
		group2DropDownButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnUserTemplatesButton() {
		wait.waitForElementToBeClickable(driver, userTemplateDropDownButton, 30);
		userTemplateDropDownButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnImportScriptDropDown() {
		wait.waitForElementToBeClickable(driver, importScriptTemplateDropDownButton, 30);
		importScriptTemplateDropDownButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnDeliveryGroupButton() {
		wait.waitForElementToBeClickable(driver, deliveryGroupDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, deliveryGroupDropDownButton);
		deliveryGroupDropDownButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnAddDeliveryGroupButton() {
		wait.waitForElementToBeVisible(driver, deliveryGroupsBlock, 30);
		wait.waitForElementToBeClickable(driver, deliveryGroups.get(0), 30);
		scroll.scrollUpToWebElement(driver, deliveryGroups.get(0));
		deliveryGroups.get(0).click();
		wait.waitForElementToBeClickable(driver, addDeliveryGroupButton.get(0), 30);
		addDeliveryGroupButton.get(0).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage dragAndDropGroup1Field(int fieldNumber) {
		if (fieldNumber == 4) {
			dragAndDropDecisionTree();
		} else if (fieldNumber == 21) {
			dragAndDropSetRepeat();
		} else if (fieldNumber == 23) {
			dragAndDropOncallSchedule();
		} else if (fieldNumber == 24) {
			dragAndDropCallTransfer();
		} else if (fieldNumber == 25) {
			dragAndDropAppointmentField();
		} else {
			dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(fieldNumber - 1), scriptContent, driver);
		}
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage dragAndDropGroup2Field(int fieldNumber) {
		dragAndDrop.dragAndDropUsingJavaScript(group2Fields.get(fieldNumber - 1), scriptContent, driver);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public void dragAndDropDecisionTree() {
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(3), scriptContent, driver);
		wait.waitForElementToBeVisible(driver, numberOfChoicesInDecisionTreeTextField, 30);
		submitButtonForNumberOfChoicesInDecisionTreeAndSelectOnCallCalendar.click();
		wait.waitForElementToBeClickable(driver, scriptContent, 30);
	}

	public void dragAndDropSetRepeat() {
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(0));
		dragAndDrop.dragUsingJavaScript(group1Fields.get(20), decisionTreeOptions.get(0), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(20), dragInsideDecisionTree, driver);
		setRepeatField.get(0).click();
		wait.waitForElementToBeVisible(driver, setReaptFieldSettingsBlock, 30);
		setReaptRadioButtons.get(0).click();
	}

	public void dragAndDropOncallSchedule() {
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(1));
		dragAndDrop.dragUsingJavaScript(group1Fields.get(22), decisionTreeOptions.get(1), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(22), dragInsideDecisionTree, driver);
		wait.waitForElementToBeVisible(driver, accountLoading, 5);
		wait.waitForElementToBeInvisible(driver, accountLoaded, 30);
		wait.waitForElementToBeVisible(driver, defaultCalendarDropDownButton, 30);
		submitButtonForNumberOfChoicesInDecisionTreeAndSelectOnCallCalendar.click();
	}

	public void dragAndDropCallTransfer() {
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(0));
		dragAndDrop.dragUsingJavaScript(group1Fields.get(23), decisionTreeOptions.get(0), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(23), dragInsideDecisionTree, driver);
		wait.waitForElementToBeClickable(driver, callTranserContactsBlock, 30);
		callTransferContactsRadioButtons.get(0).click();

	}

	public ScriptPage dragAndDropCallTransferField() {
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(0));
		dragAndDrop.dragUsingJavaScript(group1Fields.get(23), decisionTreeOptions.get(0), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(23), dragInsideDecisionTree, driver);
		wait.waitForElementToBeClickable(driver, callTranserContactsBlock, 30);
		int size = callTransferContactsRadioButtons.size();
		int index = scriptData.randomIntegerData(0, size - 1);
		selectedCallTransferContact = callTransferContactsText.get(index).getText();
		scroll.scrollUpToWebElement(driver, callTransferContactsRadioButtons.get(index));
		callTransferContactsRadioButtons.get(index).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage selectCallTransferType() {
		scroll.scrollUpToWebElement(driver, transferTypeDropDownButton);
		transferTypeDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, transferTypeOptions, 30);
		int index = scriptData.randomIntegerData(0, 2);
		scroll.scrollUpToWebElement(driver, transferTypeOptions.get(index));
		selectedTransferType = transferTypeOptionsText.get(index).getText();
		transferTypeOptions.get(index).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage selectSpecificPeopleCallType() {
		wait.waitForElementToBeVisible(driver, specificPeopleCallDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, specificPeopleCallDropDownButton);
		specificPeopleCallDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, specificPeopleCallOptions, 30);
		int index = scriptData.randomIntegerData(1, 2);
		scroll.scrollUpToWebElement(driver, specificPeopleCallOptions.get(index));
		selectedCallFlowType = specificPeopleCallOptionsText.get(index).getText();
		transferTypeOptions.get(index).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage selectCommonCallType() {
		wait.waitForElementToBeVisible(driver, specificPeopleCallDropDownButton, 30);
		scroll.scrollUpToWebElement(driver, specificPeopleCallDropDownButton);
		specificPeopleCallDropDownButton.click();
		wait.waitForAllElementsToBeVisible(driver, specificPeopleCallOptions, 30);
		selectedCallFlowType = specificPeopleCallOptionsText.get(2).getText();
		transferTypeOptions.get(2).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage selectContactForCallFlow() {
		wait.waitForAllElementsToBeVisible(driver, specificPeopleCallFlowContactCheckBox, 30);
		int size = specificPeopleCallFlowContactCheckBox.size();
		int index = scriptData.randomIntegerData(0, size - 2);
		selectedCallFlowContact = specificPeopleCallFlowContactCheckBoxText.get(index).getText();
		scroll.scrollUpToWebElement(driver, specificPeopleCallFlowContactCheckBox.get(index));
		specificPeopleCallFlowContactCheckBox.get(index).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnSelectedCallTranfer() {
		wait.waitForElementToBeVisible(driver, expandScriptButton, 30);
		expandScriptButton.click();
		wait.waitForElementToBeVisible(driver, callTransferField, 30);
		scroll.scrollUpToWebElement(driver, callTransferField);
		callTransferField.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public void dragAndDropAppointmentField() {
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(0));
		dragAndDrop.dragUsingJavaScript(group1Fields.get(24), decisionTreeOptions.get(0), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(24), dragInsideDecisionTree, driver);
		wait.waitForElementToBeClickable(driver, appointmentField, 30);
		appointmentField.click();
		wait.waitForElementToBeVisible(driver, appointmentProviderDropDown, 30);
		appointmentProviderDropDown.click();
		wait.waitForAllElementsToBeVisible(driver, appointmentProviderOptions, 30);
		int option = scriptData.randomIntegerData(0, 1);
		appointmentProviderOptions.get(option).click();
		settingsViewButton.click();
	}

	public ScriptPage clickOnViewInDropDownButton() {
		wait.waitForElementToBeClickable(driver, viewInDropDownButton, 30);
		viewInDropDownButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage clickOnWebsiteField(int index) {
		scroll.scrollUpToWebElement(driver, websiteField.get(index));
		websiteField.get(index).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnCustomerStatusField() {
		scroll.scrollUpToWebElement(driver, customerStatusField);
		customerStatusField.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterUrl() {
		wait.waitForElementToBeVisible(driver, urlBlock, 30);
		url = scriptData.randomWeb();
		urltextField.sendKeys(url);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterCustomerStatus() {
		wait.waitForElementToBeVisible(driver, customerStatusTextField, 30);
		String customerStatus = scriptData.randomData("CustomerStatus");
		customerStatusTextField.sendKeys(customerStatus);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage selectCustomerStatusExpireDate() {
		wait.waitForElementToBeVisible(driver, customerStatusCalendarButton, 30);
		scroll.scrollUpToWebElement(driver, customerStatusCalendarButton);
		customerStatusCalendarButton.click();
		try {
			wait.waitForElementToBeVisible(driver, customStatusTomorrow, 2);
		} catch (Exception customDate) {
			calendarNextMonthButton.click();
		}
		scroll.scrollUpToWebElement(driver, customStatusTomorrow);
		wait.waitForElementToBeClickable(driver, customStatusTomorrow, 30);
		customStatusTomorrow.click();
		wait.waitForElementToBeVisible(driver, customStatusExpiredDateAmOrPM, 30);
		scroll.scrollUpToWebElement(driver, customStatusTomorrow);
		customStatusExpiredDateAmOrPM.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public void clickOnViewInMenuOptions(int optionNumber) {
		wait.waitForElementToBeClickable(driver, switchBoardButtonInViewInMenu, 30);
		switch (optionNumber) {
		case 1:
			viewInDropDownMenu.get(0).click();
			break;
		case 2:
			viewInDropDownMenu.get(1).click();
			break;
		case 3:
			viewInDropDownMenu.get(2).click();
			break;
		case 4:
			viewInDropDownMenu.get(3).click();
			break;
		case 5:
			viewInDropDownMenu.get(4).click();
			break;
		case 6:
			viewInDropDownMenu.get(5).click();
			break;
		case 7:
			viewInDropDownMenu.get(6).click();
			break;
		}

	}

	public ScriptPage clickOnThreeDotsButton() {
		wait.waitForElementToBeClickable(driver, threeDotsButton, 30);
		threeDotsButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public void clickOnThreeDotsOptions(int optionNumber) {
		wait.waitForElementToBeClickable(driver, threeDotsMenuBlock, 30);
		threeDotsMenu.get(optionNumber - 1).click();
	}

	public ScriptPage waitUntilScriptFieldsLoaded() {
		wait.waitForElementToBeInvisible(driver, scriptLoadedIcon, 30);
		String fieldCountTextOfOriginalForm = fieldCountText.getText();
		fieldCountOfOriginalForm = Integer.parseInt(fieldCountTextOfOriginalForm);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage waitUntilCacheGotCleared() {
		wait.waitForElementToBeVisible(driver, clearCacheLoaderIcon, 30);
		wait.waitForElementToBeVisible(driver, cacheClearedNotification, 300);
		cacheStatus = cacheClearedNotification.getText();
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public boolean isCacheCleared() {

		if (cacheStatus.equals("Cache got cleared successfully"))
			return true;
		else
			return false;

	}

	public ScriptPage enterAccountNumberInCopyAccountFromTextField(String accountNumber) {
		wait.waitForElementToBeVisible(driver, copyAccountTemplateFromTextField, 30);
		copyAccountTemplateFromTextField.sendKeys(accountNumber);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterAccountNumberInCopyAccountToTextField(String accountNumber) {
		wait.waitForElementToBeVisible(driver, copyAccountTemplateToTextField, 30);
		copyAccountTemplateToTextField.sendKeys(Keys.COMMAND, "a", Keys.DELETE);
		copyAccountTemplateToTextField.sendKeys(accountNumber);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnCopyAccountTemplateButton() {
		chooseTemplateMethodsCheckBoxes.get(1).click();
		wait.waitForElementToBeVisible(driver, copyAccountTemplateButton, 30);
		copyAccountTemplateButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage waitUntilAccountTemplateGetsCopied() {
		wait.waitForElementToBeVisible(driver, copyAccountTemplateProcessIntiatedNotification, 30);
		wait.waitForElementToBeInvisible(driver, copyAccountTemplateProcessIntiatedNotificationList, 30);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public boolean isCopiedAccountTemplateLoaded() {
		listOfFormButtons.get(1).click();
		waitUntilScriptFieldsLoaded();
		if (fieldCountText.getText().equals("0"))
			return false;
		else
			wait.waitForElementToBeInvisible(driver, scriptLoadedIcon, 30);
		String fieldCountTextOfCopeidForm = fieldCountText.getText();
		fieldCountOfCopiedForm = Integer.parseInt(fieldCountTextOfCopeidForm);
		return true;
	}

	public ScriptPage waitForScriptLoad() {
		try {
			wait.waitForElementToBeVisible(driver, accountLoading, 2);
		} catch (Exception accountLoaded) {
		}
		wait.waitForElementToBeInvisible(driver, accountLoaded, 30);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public boolean isAccountTemplateCopied() {
		if (fieldCountOfCopiedForm <= fieldCountOfOriginalForm)
			return true;

		else
			return false;

	}

	public ScriptPage clickOnFormbutton(int index) {
		wait.waitForElementToBeVisible(driver, formsBlock, 30);
		listOfFormButtons.get(index).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnDeleteButton() {
		wait.waitForElementToBeClickable(driver, deleteScriptButton, 30);
		deleteScriptButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnPublishFormButton() {
		wait.waitForElementToBeClickable(driver, publishScriptButton, 30);
		publishScriptButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnConfirmDeleteOrPublishFormButton() {
		wait.waitForElementToBeClickable(driver, confirmDeleteOrPublishButton, 30);
		confirmDeleteOrPublishButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public int getTotalNumberOfUnpublishedForms() {
		wait.waitForElementToBeVisible(driver, formsBlock, 30);
		int size = listOfUnpulishedForms.size();
		return size;
	}

	public int getTotalNumberOfForms() {
		wait.waitForElementToBeVisible(driver, formsBlock, 30);
		int size = listOfFormButtons.size();
		return size;
	}

	public String getFormStatus() {
		wait.waitForElementToBeVisible(driver, formStatusText, 30);
		String formStatus = formStatusText.getText();
		return formStatus;
	}

	public ScriptPage clickOnAddNewFormButton() {
		wait.waitForElementToBeClickable(driver, addNewFormButton, 30);
		addNewFormButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public int getFieldCount() {
		String fieldCountTextOfOriginalForm = fieldCountText.getText();
		int fieldCount = Integer.parseInt(fieldCountTextOfOriginalForm);
		return fieldCount;
	}

	public ScriptPage clickOnSaveScriptButton() {
		wait.waitForElementToBeClickable(driver, saveScriptButton, 30);
		saveScriptButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage clickOnMultiSelectButton() {
		wait.waitForElementToBeClickable(driver, multiSelectButton, 30);
		multiSelectButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage clickOnAnyFieldInScript(int fieldNumber) {
		listOfFieldsInScript.get(fieldNumber - 1).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnCutMultipleFields() {
		wait.waitForElementToBeClickable(driver, cutMultipleFieldsButton, 30);
		cutMultipleFieldsButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnCopyMultipleFields() {
		wait.waitForElementToBeClickable(driver, copyMultipleFieldsButton, 30);
		copyMultipleFieldsButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnPasteMultipleFields() {
		wait.waitForElementToBeClickable(driver, pastIntoFormButton, 30);
		pastIntoFormButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnDeleteMultipleFields() {
		wait.waitForElementToBeClickable(driver, deleteMultipleFieldsButton, 30);
		deleteMultipleFieldsButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnConfirmDeleteFields() {
		wait.waitForElementToBeClickable(driver, confirmDeleteFieldsButton, 30);
		confirmDeleteFieldsButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public int getFieldsAddedInScript() {
		int fields = listOfFieldsInScript.size();
		return fields;
	}

	public ScriptPage addOptionToDecisionTree() {
		scroll.scrollUpToWebElement(driver, decisionTree);
		decisionTree.click();
		wait.waitForElementToBeClickable(driver, addChoiceFieldsBlock, 30);
		int size = addChoiceFields.size();
		scroll.scrollUpToWebElement(driver, addChoiceFields.get(size - 1));
		addChoiceFields.get(size - 1).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage dragAndDropUserTemplates(int userTemplateNumber) {
		dragAndDropDecisionTree();
		addOptionToDecisionTree();
		int size = decisionTreeOptions.size();
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(size - 1));
		dragAndDrop.dragUsingJavaScript(userTemplateFields.get(userTemplateNumber - 1),
				decisionTreeOptions.get(size - 1), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(userTemplateFields.get(userTemplateNumber - 1),
				decisionTreeOptions.get(size - 1), driver);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage addingFieldsInsideOption(int fieldNumber, int optionNumber) {
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(optionNumber - 1));
		dragAndDrop.dragUsingJavaScript(group1Fields.get(fieldNumber - 1), decisionTreeOptions.get(optionNumber - 1),
				driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(group1Fields.get(fieldNumber - 1),
				decisionTreeOptions.get(optionNumber - 1), driver);
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public int getGroup1FieldsCount() {

		return group1Fields.size();

	}

	public int getGroup2FieldsCount() {

		return group2Fields.size();

	}

	public ScriptPage clickOnImportScriptButton() {
		wait.waitForElementToBeVisible(driver, importScriptButton, 30);
		scroll.scrollUpToWebElement(driver, importScriptButton);
		wait.waitForElementToBeClickable(driver, importScriptButton, 30);
		scroll.scrollUpToWebElement(driver, importScriptButton);
		try {
			importScriptButton.click();
		} catch (Exception ImportScript) {
			scroll.scrollUpToWebElement(driver, importScriptButton);
			importScriptButton.click();
		}
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterAccountNumberForImportScripting(String accountNumber) {
		wait.waitForElementToBeClickable(driver, enterAccountNumberField, 30);
		enterAccountNumberField.sendKeys(accountNumber + Keys.ENTER);
		waitForAccountLoadOrSave();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage selectFormForImportScripting(int index) {
		wait.waitForElementToBeClickable(driver, chooseFormDropDown, 30);
		chooseFormDropDown.click();
		wait.waitForElementToBeClickable(driver, firstOptionInChooseFromDropDown, 30);
		optionsInChooseFromDropDown.get(index).click();
		wait.waitForElementToBeClickable(driver, importButton, 30);
		importButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage dragAndDropImportScript(int importTemplates) {
		addOptionToDecisionTree();
		int size = decisionTreeOptions.size();
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(size - 1));
		dragAndDrop.dragUsingJavaScript(importTemplateFields.get(importTemplates - 1),
				decisionTreeOptions.get(size - 1), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(importTemplateFields.get(importTemplates - 1),
				decisionTreeOptions.get(size - 1), driver);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnSettingsViewButton() {
		wait.waitForElementToBeClickable(driver, settingsViewButton, 30);
		settingsViewButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnFormSettingsButton() {
		wait.waitForElementToBeClickable(driver, formSettingsButton, 30);
		formSettingsButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnFieldSettingsButton() {
		wait.waitForElementToBeClickable(driver, fieldSettingButton, 30);
		fieldSettingButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage EnableOrDisableHolidayScripting() {
		wait.waitForElementToBeClickable(driver, holidayScriptingToggleButton, 30);
		holidayScriptingToggleButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public boolean isHolidayScriptingEnabled() {
		if (holidayScriptEnabledStatus.size() == 1)
			return true;
		else
			return false;

	}

	public ScriptPage EnableOrDisableSpanishScripting() {
		wait.waitForElementToBeClickable(driver, spanishScriptingToggleButton, 30);
		spanishScriptingToggleButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterFieldScriptEnglish() {
		wait.waitForElementToBeClickable(driver, fieldScriptEnglishTextField, 30);
		String englishScript = scriptData.randomData("English Script");
		englishScriptingField.add(englishScript);
		fieldScriptEnglishTextField.sendKeys(englishScript);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterFieldScriptSpanish() {
		wait.waitForElementToBeClickable(driver, fieldScriptEnglishTextField, 30);
		String englishScript = scriptData.randomData("¿Le puedo ayudar en algo?");
		spanishScriptingField.add(englishScript);
		fieldScriptSpanishTextField.sendKeys(englishScript);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage enterNewFormName() {
		wait.waitForElementToBeClickable(driver, formNameTextField, 30);
		formNameTextField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
		String formName = scriptData.randomData("Form Name");
		newFormName = formName;
		formNameTextField.sendKeys(formName);
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage clickOnFormName() {
		int size = listOfFormButtons.size();
		for (int i = 0; i < size; i++) {
			if (listOfFormNames.get(i).getText().equals(newFormName))
				listOfFormNames.get(i).click();
		}
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage publishNewlyRevertedForm() {
		listOfFormNames.get(2).click();
		clickOnPublishFormButton();
		clickOnConfirmDeleteOrPublishFormButton();

		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnDecisionTree() {
		scroll.scrollUpToWebElement(driver, decisionTree);
		decisionTree.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);

	}

	public ScriptPage clickOnDecisionTreeOption(int optionNumber) {
		scroll.scrollUpToWebElement(driver, decisionTree);
		decisionTreeOptions.get(optionNumber - 1).click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage dragAndDropUserTemplate(int userTemplateNumber) {
		addOptionToDecisionTree();
		int size = decisionTreeOptions.size();
		scroll.scrollUpToWebElement(driver, decisionTreeOptions.get(size - 1));
		dragAndDrop.dragUsingJavaScript(userTemplateFields.get(userTemplateNumber - 1),
				decisionTreeOptions.get(size - 1), driver);
		wait.waitForElementToBeVisible(driver, dragInsideDecisionTree, 5);
		dragAndDrop.dragAndDropUsingJavaScript(userTemplateFields.get(userTemplateNumber - 1),
				decisionTreeOptions.get(size - 1), driver);
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnCutDecisionTree() {
		wait.waitForElementToBeClickable(driver, cutDecisionTreeButton, 30);
		cutDecisionTreeButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnCopyDecisionTree() {
		wait.waitForElementToBeClickable(driver, copyDecisionTreeButton, 30);
		copyDecisionTreeButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnDateDropdown() {
		wait.waitForElementToBeVisible(driver, dateDropDownField, 30);
		scroll.scrollUpToWebElement(driver, dateDropDownField);
		dateDropDownField.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnTimeBasedCallFlowToggleButton() {
		wait.waitForElementToBeVisible(driver, timeBasedCallFlowToggleButton, 30);
		timeBasedCallFlowToggleButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage clickOnUseBusinessHoursButton() {
		wait.waitForElementToBeVisible(driver, useBusinessHoursButton, 30);
		scroll.scrollUpToWebElement(driver, useBusinessHoursButton);
		useBusinessHoursButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public ScriptPage selectApplyAllForTimeBasedCallFlow() {
		wait.waitForElementToBeVisible(driver, timeBasedCallFlowThreeDotsButton, 30);
		scroll.scrollUpToWebElement(driver, timeBasedCallFlowThreeDotsButton);
		timeBasedCallFlowThreeDotsButton.click();
		wait.waitForElementToBeVisible(driver, applyAllButton, 30);
		applyAllButton.click();
		return PageFactory.initElements(getDriver(), ScriptPage.class);
	}

	public String timeBasedCallFlowTimingsAfterSaving(int index) {
		wait.waitForElementToBeVisible(driver, useBusinessHoursButton, 30);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		final String java_script = "return document.querySelectorAll('.g-timerange.enabled .flatpickr-wrapper >input')["
				+ index + "].value";
		String value = (String) executor.executeScript(java_script);
		return value;
	}

	public String englishFieldScriptBeforeSaving(int field) {
		return englishScriptingField.get(field - 1);
	}

	public String spanishFieldScriptBeforeSaving(int field) {
		return spanishScriptingField.get(field - 1);
	}

	public String englishFieldScriptAfterSaving() {
		return fieldScriptEnglishTextField.getText();
	}

	public String spanishFieldScriptAfterSaving() {
		return fieldScriptSpanishTextField.getText();
	}

	public String getFormName() {
		return newFormName;
	}

	public String updatedScriptField(int fieldNumber) {
		return updatedScriptFieldsText.get(fieldNumber - 1).getText();
	}

	public String getCustomerStatusText() {
		wait.waitForElementToBeVisible(driver, customerStatusTextField, 30);
		return customerStatusTextField.getText();
	}

	public String selectedCallTranferContactBeforeSaving() {
		return selectedCallTransferContact;
	}

	public String selectedTransferTypeBeforeSaving() {
		return selectedTransferType;
	}

	public String selectedCallTransferContactAfterSaving() {
		wait.waitForElementToBeVisible(driver, selectedContactForCallTranfer, 30);
		return selectedContactForCallTranfer.getText();
	}

	public String selectedTranferTypeAfterSaving() {
		wait.waitForElementToBeVisible(driver, callTransferTypeDropDownText, 30);
		return callTransferTypeDropDownText.getText();
	}

	public String selectedCallFlowContactBeforeSaving() {
		return selectedCallFlowContact;
	}

	public String selectedCallFlowTypeBeforeSaving() {
		return selectedCallFlowType;
	}

	public String selectedCallFlowTypeAfterSaving() {
		wait.waitForElementToBeVisible(driver, specifcPeopleCallTypeDropDownText, 30);
		return specifcPeopleCallTypeDropDownText.getText();
	}

	public String selectedCallFlowContactAfterSaving() {
		wait.waitForElementToBeVisible(driver, specificPeopleCallFlowContactText, 30);
		return specificPeopleCallFlowContactText.getText();
	}

	public String getAnyFieldLableInScript(int field) {
		return listOfFieldInScriptLabels.get(field - 1).getText();
	}

	public int getDateDrownOptions() {
		wait.waitForAllElementsToBeVisible(driver, dateDropDownOptions, 30);
		return dateDropDownOptions.size();
	}

	public int selectedTimeBasedCallFlowDays() {
		wait.waitForAllElementsToBeVisible(driver, selectedTimeBasedCallFlowDays, 30);
		return selectedTimeBasedCallFlowDays.size();
	}

	public String getAccountNumber() {
		wait.waitForElementToBeVisible(driver, accountNumberText, 30);
		return accountNumberText.getText();
	}

}
