package io.testscripts.fc2.o;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.pagelibrary.fc2.o.FormCreatorLaunchPage;
import io.testlibrary.fc2.o.LaunchBrowser;

@Listeners(io.utils.fc2.o.CustomListener.class)
public class GoogleSignInTestScripts extends LaunchBrowser {
	private static ExtentTest report;

	@Parameters({ "Email", "Password", "SessionId" })
	@Test(priority = 0)
	public void verifyGoogleSignIn(String email, String password, int sessionId) {
		if (sessionId == 1) {
			report = extent.createTest("Verify Google SignIn");
			setReport(report);
			FormCreatorLaunchPage formCreatorLaunchPage = PageFactory.initElements(getDriver(),
					FormCreatorLaunchPage.class);
			String title = formCreatorLaunchPage.clickOnGoogleSignInButton().googleSignIn(email, password)
					.getHomePageTitle();
			Assert.assertTrue(title.equals("FormCreator"));
			getReport().log(Status.PASS, "Google Sign In Successful");
		} else {
			FormCreatorLaunchPage formCreatorLaunchPage = PageFactory.initElements(getDriver(),
					FormCreatorLaunchPage.class);
			formCreatorLaunchPage.clickOnGoogleSignInButton().googleSignIn(email, password).getHomePageTitle();
		}

	}

}
