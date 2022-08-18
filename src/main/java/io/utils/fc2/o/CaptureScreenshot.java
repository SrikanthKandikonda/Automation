package io.utils.fc2.o;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {

	public static String takeScreenshot(String screenshotName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String source = ts.getScreenshotAs(OutputType.BASE64);
		File file = OutputType.FILE.convertFromBase64Png(source);

		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			FileUtils.copyFile(file, new File("." + "/ScreenShots/" + screenshotName + dateName + ".png"), true);
			return source;
		} catch (Exception rv) {
			return "Error Occured In Taking Screenshot";

		}

	}

}
