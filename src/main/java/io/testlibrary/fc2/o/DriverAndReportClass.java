package io.testlibrary.fc2.o;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class DriverAndReportClass {
	private static WebDriver driver1;
	private static WebDriver driver2;
	private static WebDriver driver3;
	private static WebDriver driver4;
	private static WebDriver driver5;
	private static WebDriver driver6;
	private static ExtentTest report1;
	private static ExtentTest report2;
	private static ExtentTest report3;
	private static ExtentTest report4;
	private static ExtentTest report5;
	private static ExtentTest report6;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;

	public void setDriver(WebDriver driver) {
		String threadName = Thread.currentThread().getName();
		switch (threadName) {
		case "Thread 1":
			driver1 = driver;
			break;
		case "Thread 2":
			driver2 = driver;
			break;
		case "Thread 3":
			driver3 = driver;
			break;
		case "Thread 4":
			driver4 = driver;
			break;
		case "Thread 5":
			driver5 = driver;
			break;
		}
	}

	public WebDriver getDriver() {

		String threadName = Thread.currentThread().getName();
		switch (threadName) {
		case "Thread 1":
			return driver1;
		case "Thread 2":
			return driver2;
		case "Thread 3":
			return driver3;
		case "Thread 4":
			return driver4;
		case "Thread 5":
			return driver5;
		}
		return driver6;
	}

	public void setReport(ExtentTest report) {
		String threadName = Thread.currentThread().getName();
		switch (threadName) {
		case "Thread 1":
			report1 = report;
			break;
		case "Thread 2":
			report2 = report;
			break;
		case "Thread 3":
			report3 = report;
			break;
		case "Thread 4":
			report4 = report;
			break;
		case "Thread 5":
			report5 = report;
			break;
		}
	}

	public ExtentTest getReport() {
		String threadName = Thread.currentThread().getName();
		switch (threadName) {
		case "Thread 1":
			return report1;
		case "Thread 2":
			return report2;
		case "Thread 3":
			return report3;
		case "Thread 4":
			return report4;
		case "Thread 5":
			return report5;
		}
		return report6;

	}

}
