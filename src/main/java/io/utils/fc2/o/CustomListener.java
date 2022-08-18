package io.utils.fc2.o;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.testlibrary.fc2.o.DriverAndReportClass;

public class CustomListener extends DriverAndReportClass implements ITestListener {
	WindowHandles tabs = new WindowHandles();

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName() + " Test Case Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName() + " Test Case Is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println(result.getName() + " Is Failed");
		getReport().log(Status.FAIL, "Test Case Failed Due To " + result.getThrowable().getMessage());
		try {
			getReport().log(Status.FAIL, "Details", MediaEntityBuilder.createScreenCaptureFromBase64String(
					CaptureScreenshot.takeScreenshot(result.getName(), getDriver())).build());
		} catch (Exception screenshot) {
			getReport().log(Status.FAIL, "Unable Take ScreenShot");

		}
		int totalTabs = tabs.getNumberOfTabs(getDriver());
		if (totalTabs > 1) {
			tabs.childWindowClose(getDriver(), 1);
			tabs.childToParentWindowControl(getDriver());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		getReport().log(Status.SKIP, "Test Case Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
