package io.testlibrary.fc2.o;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser extends DriverAndReportClass {
	private ExtentHtmlReporter htmlReporter;

	@BeforeSuite
	public void setupExtentReport() {
		htmlReporter = new ExtentHtmlReporter("." + "/test-output/AutoMationReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("User", "Pavithran");

	}

	@Parameters({ "URL", "SessionId" })
	@BeforeTest
	public void BrowserLaunch(String url, String sessionId) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--disable-extensions");
		Thread.currentThread().setName("Thread " + sessionId);
		WebDriver chromeDriver = new ChromeDriver(chromeOptions);
		chromeDriver.get(url);
		chromeDriver.manage().window().maximize();
		setDriver(chromeDriver);

	}

	@AfterTest
	public void browserQuit() {
		getDriver().quit();
	}

	@AfterSuite
	public void extentReport() {
		extent.flush();
		// EmailableReport.emailReport(htmlReporter);

	}

}