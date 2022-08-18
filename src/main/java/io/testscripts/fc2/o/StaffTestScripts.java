package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.StaffPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class StaffTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	public StaffPage findingAccount(String accountNumber) {
		StaffPage staffPage = PageFactory.initElements(getDriver(), StaffPage.class);
		staffPage.clickOnFindButton().enterAccountNumber(accountNumber).clickOnLoadButton().waitForAccountLoadOrSave();
		return staffPage;
	}

	public void addNewStaff(StaffPage staffPage) {
		staffPage.clickOnStaffButton().clickOnAddStaffButton().enterNewStaffNameInAddStaffBlock()
				.enterNewStaffRoleInAddStaffBlock().clickOnAddFieldsInAddStaffBlock()
				.clickOnAddFieldsOptionsInAddStaffBlock(0).enterPhoneNumberInAddStaffBlock()
				.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(1).enterEmailInAddStaffBlock()
				.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(2).enterWebInAddStaffBlock()
				.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(3).enterFaxInAddStaffBlock()
				.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(4).enterTextInAddStaffBlock()
				.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(5)
				.enterMailTextInAddStaffBlock().clickOnAddFieldsInAddStaffBlock()
				.clickOnAddFieldsOptionsInAddStaffBlock(6).enterEmailAttachmentInAddStaffBlock()
				.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(7)
				.enterEmailCsvInAddStaffBlock().clickOnSaveStaffInAddStaffBlock().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();
		staffPage.clickOnStaffThreeDotsIcon().clickOnMakeStaffAsPrivateToggleButton().clickOnSaveStaff()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void addNewDeliveryGroup(StaffPage staffPage) {
		staffPage.clickOnNewlyAddedStaff().clickOnAddToGroupButton().clickOnAddNewDeliveryGroupFromAddToGroupOptions()
				.enterNewDeliveryGroupNameFromExistingStaff().clickOnAddNewDeliveryGroupToStaff()
				.clickOnSelectStaffFieldsToDeliveryGroup().clickOnSaveStaffInAddStaffBlock().waitForAccountLoadOrSave()
				.waitForSaveOrPublishNotification();

	}

	public void refetchingStaff(StaffPage staffPage) {
		staffPage.clickOnBriefcaseButton().clickOnLoadButton().waitForAccountLoadOrSave().clickOnStaffButton();

	}

	public void validatingNewlyAddedStaffInFc(StaffPage staffPage) {
		staffPage.clickOnNewlyAddedStaff();
		Assert.assertEquals(staffPage.newStaffNameBeforeSaving(), staffPage.newStaffNameAfterSaving());
		Assert.assertEquals(staffPage.newStaffRoleBeforeSaving(), staffPage.newStaffRoleAfterSaving());
		Assert.assertEquals(staffPage.newStaffPhoneNumberBeforeSaving(), staffPage.newStaffPhoneNumberAfterSaving());
		Assert.assertEquals(staffPage.newStaffEmailBeforeSaving(), staffPage.newStaffEmailAfterSaving());
		Assert.assertEquals(staffPage.newStaffWebBeforeSaving(), staffPage.newStaffWebAfterSaving());
		Assert.assertEquals(staffPage.newStaffFaxBeforeSaving(), staffPage.newStaffFaxAfterSaving());
		Assert.assertEquals(staffPage.newStaffTextBeforeSaving(), staffPage.newStaffTextAfterSaving());
		Assert.assertEquals(staffPage.newStaffMailTextBeforeSaving(), staffPage.newStaffMailTextAfterSaving());
		Assert.assertEquals(staffPage.newStaffEmailAttachmentBeforeSaving(),
				staffPage.newStaffEmailAttachmentAfterSaving());
		Assert.assertEquals(staffPage.newStaffEmailCsvBeforeSaving(), staffPage.newStaffEmailCsvAfterSaving());
		getReport().log(Status.PASS, "Staff Saved Successfully In FC");
		Assert.assertTrue(staffPage.isStaffPrivate());
		getReport().log(Status.PASS, "Staff Privacy Saved Successfully In FC");

	}

	public void validatingNewlyAddedDeliveryGroupInFc(StaffPage staffPage) {
		Assert.assertEquals(staffPage.newDeliveryGroupNameBeforeSaving(), staffPage.newDeliveryGroupNameAfterSaving(0));
		getReport().log(Status.PASS, "Delivery Group Saved Successfully In FC");

	}

	public void RemovingNewlyAddedDeliveryGroupFromStaffAndDeleteDeliveryGroup(StaffPage staffPage) {
//		staffPage.clickOnRemoveDeliveryGroupFromStaff(0).clickOnRemoveStaffFromDeliveryGroupConfirmationPopUp()
//				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		staffPage.clickOnDeliveryGroupTab().clickOnNewlyAddedDeliveryGroupIcon()
				.clickOnNewlyAddedDeliveryGroupDeleteButton().waitForAccountLoadOrSave()
				.clickOnDeleteDeliveryGroupConfirmationPopUp().waitForAccountLoadOrSave();
	}

	public void validatingDeletedDeliveryGroupInFc(StaffPage staffPage) {
		staffPage.clickOnDeliveryGroupTab();
		Assert.assertNotEquals(staffPage.deliveryGroupBeforeDeleting(), staffPage.deliveryGroupAfterDeleting());
		getReport().log(Status.PASS, "Delivery Group Deleted Successfully In FC");
	}

	public void deleteNewlyAddedStaff(StaffPage staffPage) {
		staffPage.clickOnNewlyAddedStaffIcon().clickOnNewlyAddedStaffDeleteButton().waitForAccountLoadOrSave()
				.clickOnDeleteStaffInConfirmationPopUp().waitForAccountLoadOrSave();
	}

	public void validatingDeletedStaffInFc(StaffPage staffPage) {
		Assert.assertNotEquals(staffPage.staffBeforeDeleting(), staffPage.staffAfterDeleting());
		getReport().log(Status.PASS, "Staff Deleted Successfully In FC");
	}

	public void addMultipleNewStaff(StaffPage staffPage) {
		for (int i = 1; i < 3; i++) {
			staffPage.clickOnAddStaffButton().enterNewStaffNameInAddStaffBlock().enterNewStaffRoleInAddStaffBlock()
					.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(0)
					.enterPhoneNumberInAddStaffBlock().clickOnAddFieldsInAddStaffBlock()
					.clickOnAddFieldsOptionsInAddStaffBlock(1).enterEmailInAddStaffBlock()
					.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(2)
					.enterWebInAddStaffBlock().clickOnAddFieldsInAddStaffBlock()
					.clickOnAddFieldsOptionsInAddStaffBlock(3).enterFaxInAddStaffBlock()
					.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(4)
					.enterTextInAddStaffBlock().clickOnAddFieldsInAddStaffBlock()
					.clickOnAddFieldsOptionsInAddStaffBlock(5).enterMailTextInAddStaffBlock()
					.clickOnAddFieldsInAddStaffBlock().clickOnAddFieldsOptionsInAddStaffBlock(6)
					.enterEmailAttachmentInAddStaffBlock().clickOnAddFieldsInAddStaffBlock()
					.clickOnAddFieldsOptionsInAddStaffBlock(7).enterEmailCsvInAddStaffBlock()
					.clickOnSaveStaffInAddStaffBlock().waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
			staffPage.clickOnStaffThreeDotsIcon().clickOnMakeStaffAsPrivateToggleButton().clickOnSaveStaff()
					.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		}
	}

	public void addMultipleNewDeliveryGroups(StaffPage staffPage) {
		for (int i = 1; i < 3; i++) {
			staffPage.clickOnNewlyAddedStaff().clickOnAddToGroupButton()
					.clickOnAddNewDeliveryGroupFromAddToGroupOptions().enterNewDeliveryGroupNameFromExistingStaff()
					.clickOnAddNewDeliveryGroupToStaff().clickOnSelectStaffFieldsToDeliveryGroup()
					.clickOnSaveStaffInAddStaffBlock().waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		}
	}

	public void validatingNewlyAddedMultipleStaffInFc(StaffPage staffPage) {
		for (int i = 1; i < 3; i++) {
			staffPage.clickOnNewlyAddedMultipleStaff(i);
			Assert.assertEquals(staffPage.multipleStaffNameBeforeSaving(i), staffPage.newStaffNameAfterSaving());
		}
		getReport().log(Status.PASS, "Multiple Staff Saved Successfully In FC");
	}

	public void validatingNewlyAddedMultipleDeliveryGroupsInFc(StaffPage staffPage) {
		for (int i = 1; i < 3; i++) {
			staffPage.clickOnDeliveryGroupTab().clickOnNewlyAddedMultipleDeliveryGroup(i);
			Assert.assertEquals(staffPage.multipleDeliveryGroupNameBeforeSaving(i),
					staffPage.multipleDeliveryGroupNameAfterSaving());
		}
		getReport().log(Status.PASS, "Multiple Delivery Groups Saved Successfully In FC");
	}

	public void deleteMultipleNewlyAddedDeliveryGroups(StaffPage staffPage) {
//		staffPage.clickOnStaffTab().clickOnNewlyAddedMultipleStaff(2);
//		for (int i = 0; i < 2; i++) {
//			staffPage.clickOnRemoveDeliveryGroupFromStaff(0).clickOnRemoveStaffFromDeliveryGroupConfirmationPopUp()
//					.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
//		}
		staffPage.clickOnDeliveryGroupTab().multiSelectNewlyAddedDeliveryGroups()
				.clickOnMultipleDeliveryGroupsDeleteButton().waitForAccountLoadOrSave()
				.clickOnDeleteDeliveryGroupConfirmationPopUp().waitForAccountLoadOrSave();

	}

	public void deleteMultipleNewlyAddedStaff(StaffPage staffPage) {
		staffPage.multiSelectNewlyAddedStaffIcon().clickOnMultipleStaffDeleteButton().waitForAccountLoadOrSave()
				.clickOnDeleteStaffInConfirmationPopUp().waitForAccountLoadOrSave();

	}

	public void deleteContactMethodAndMakeContactMethodAsPrivate(StaffPage staffPage) {
		staffPage.clickOnNewlyAddedStaff().clickOnAnyofContactMethod().deleteSelectedContactMethod().clickOnSaveStaff()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
		staffPage.makeAnyContactMethodAsPrivate().clickOnCustomPhoneField().EnterCustomPhoneName().clickOnSaveStaff()
				.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
	}

	public void validateContactMethodChanges(StaffPage staffPage) {
		staffPage.clickOnNewlyAddedStaff();
		Assert.assertTrue(staffPage.isContactMethodDeleted());
		getReport().log(Status.PASS, "Contact Method Deleted Successfully");
		Assert.assertTrue(staffPage.isContactMethodPrivate());
		getReport().log(Status.PASS, "Contact Method Privacy Saved Successfully In FC");
		Assert.assertEquals(staffPage.customPhoneNameBeforeSaving(), staffPage.customPhoneNameAfterSaving());
		getReport().log(Status.PASS, "Custom Phone Field Saved Successfully In FC");
	}

	public void uploadStaffUsingCsv(StaffPage staffPage) {
		staffPage.clickOnUploadStaffThreeDotsButton().uploadStaffCsvFile();
		for (int i = 0; i < 2; i++) {
			staffPage.enterNewStaffNameUploadCsvBlock(i).enterEmailInUploadCsvBlock(i);
		}
		staffPage.clickOnAutoDeliveryCheckBox().clickOnAddtoStaffButtonInUploadCsvBlock();
	}

	public void validatingUploadedStaff(StaffPage staffPage) {
		for (int i = 4; i < 6; i++) {
			staffPage.clickOnNewlyAddedStaff();
			staffPage.clickOnNewlyAddedMultipleStaff(i);
			Assert.assertEquals(staffPage.multipleStaffNameBeforeSaving(i), staffPage.newStaffNameAfterSaving());
		}
		getReport().log(Status.PASS, "Staff Upload Successfully Through Csv In FC");
	}

	public void validatingUploadedDeliveryGroups(StaffPage staffPage) {
		for (int i = 4; i < 6; i++) {
			staffPage.clickOnDeliveryGroupTab().clickOnAutoAddedMultipleDeliveryGroup(i);
			Assert.assertTrue(staffPage.multipleDeliveryGroupNameAfterSaving()
					.contains(staffPage.multipleStaffNameBeforeSaving(i)));
		}
		getReport().log(Status.PASS, "Auto Delivery Group Upload Successfully Through Csv In FC");
	}

	public void deletingUploadedDeliveryGroups(StaffPage staffPage) {
//		staffPage.clickOnStaffTab();
//		for (int i = 4; i < 6; i++) {
//			staffPage.clickOnNewlyAddedMultipleStaff(i);
//			staffPage.clickOnRemoveDeliveryGroupFromStaff(0).clickOnRemoveStaffFromDeliveryGroupConfirmationPopUp()
//					.waitForAccountLoadOrSave().waitForSaveOrPublishNotification();
//		}
		staffPage.clickOnDeliveryGroupTab().multiSelectNewlyAddedAutoDeliveryGroups()
				.clickOnMultipleDeliveryGroupsDeleteButton().waitForAccountLoadOrSave()
				.clickOnDeleteDeliveryGroupConfirmationPopUp().waitForAccountLoadOrSave();
	}

	public void deleteMultipleNewlyUploadedStaff(StaffPage staffPage) {
		staffPage.clickOnStaffTab().multiSelectNewlyUploadedStaffIcon().clickOnMultipleStaffDeleteButton()
				.waitForAccountLoadOrSave().clickOnDeleteStaffInConfirmationPopUp().waitForAccountLoadOrSave();

	}

	public StaffPage verifySingleStaffAndDeliveryGroup(StaffPage staffPage) {
		addNewStaff(staffPage);
		addNewDeliveryGroup(staffPage);
		refetchingStaff(staffPage);
		validatingNewlyAddedStaffInFc(staffPage);
		validatingNewlyAddedDeliveryGroupInFc(staffPage);
		RemovingNewlyAddedDeliveryGroupFromStaffAndDeleteDeliveryGroup(staffPage);
		refetchingStaff(staffPage);
		refetchingStaff(staffPage);
		validatingDeletedDeliveryGroupInFc(staffPage);
		refetchingStaff(staffPage);
		deleteNewlyAddedStaff(staffPage);
		validatingDeletedStaffInFc(staffPage);
		return staffPage;
	}

	public void verifyMultipleSelectStaffAndDeliveryGroup(StaffPage staffPage) {
		addMultipleNewStaff(staffPage);
		addMultipleNewDeliveryGroups(staffPage);
		refetchingStaff(staffPage);
		validatingNewlyAddedMultipleStaffInFc(staffPage);
		validatingNewlyAddedMultipleDeliveryGroupsInFc(staffPage);
		deleteMultipleNewlyAddedDeliveryGroups(staffPage);
		refetchingStaff(staffPage);
		refetchingStaff(staffPage);
		validatingDeletedDeliveryGroupInFc(staffPage);
		refetchingStaff(staffPage);
		deleteMultipleNewlyAddedStaff(staffPage);
		validatingDeletedStaffInFc(staffPage);
	}

	public void verifyContactMethodChanges(StaffPage staffPage) {
		addNewStaff(staffPage);
		deleteContactMethodAndMakeContactMethodAsPrivate(staffPage);
		refetchingStaff(staffPage);
		validateContactMethodChanges(staffPage);
		deleteNewlyAddedStaff(staffPage);
	}

	public void verifyUploadCsvChanges(StaffPage staffPage) {
		uploadStaffUsingCsv(staffPage);
		refetchingStaff(staffPage);
		validatingUploadedStaff(staffPage);
		validatingUploadedDeliveryGroups(staffPage);
		deletingUploadedDeliveryGroups(staffPage);
		refetchingStaff(staffPage);
		validatingDeletedDeliveryGroupInFc(staffPage);
		refetchingStaff(staffPage);
		refetchingStaff(staffPage);
		deleteMultipleNewlyUploadedStaff(staffPage);
	}

	@Parameters({ "AccountNumber" })
	@Test
	public void verifyStaff(String accountNumber) {
		report = extent.createTest("Verify Staff");
		setReport(report);
		StaffPage staffPage = findingAccount(accountNumber);
		verifySingleStaffAndDeliveryGroup(staffPage);
		verifyMultipleSelectStaffAndDeliveryGroup(staffPage);
		verifyContactMethodChanges(staffPage);
		verifyUploadCsvChanges(staffPage);
	}

	@AfterMethod
	public void reload() {
		StaffPage staffPage = PageFactory.initElements(getDriver(), StaffPage.class);
		staffPage.clickOnReload();

	}

}
