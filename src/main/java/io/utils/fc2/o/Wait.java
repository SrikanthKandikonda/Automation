package io.utils.fc2.o;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	public void waitForElementToBeClickable(WebDriver driver, WebElement webElement, int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitForElementToBeInvisible(WebDriver driver, List<WebElement> webElement, int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.invisibilityOfAllElements(webElement));

	}

	public void waitForElementToBeVisible(WebDriver driver, WebElement webElement, int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForAllElementsToBeVisible(WebDriver driver, List<WebElement> webElement, int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
	}

	public void waitForWebPageLoad(WebDriver driver, int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void waitForWebPageRefresh(WebDriver driver, int timeInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.visibilityState").equals("visible"));
	}

	public void waitForToBeAlertLoaded(WebDriver driver, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.alertIsPresent());
	}

}
