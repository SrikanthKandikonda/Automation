package io.utils.fc2.o;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandles {

	public void parentToChildWindowControl(WebDriver driver, int windowNumber) {
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(windows);
		driver.switchTo().window(tabs.get(windowNumber));
	}

	public void childToParentWindowControl(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(windows);
		driver.switchTo().window(tabs.get(0));

	}

	public void childWindowClose(WebDriver driver, int windowNumber) {
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(windows);
		driver.switchTo().window(tabs.get(windowNumber)).close();
	}

	public int getNumberOfTabs(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(windows);
		return tabs.size();
	}

}
