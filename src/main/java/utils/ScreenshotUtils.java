package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

	public static byte[] captureScreenshot(WebDriver driver, String scenarioName) {
	    try {
	        // Take screenshot in bytes (for report attachment)
	        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	        // Also save screenshot as PNG file
	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

	        // Sanitize scenario name to avoid illegal chars in filename
	        String safeName = scenarioName.replaceAll("[^a-zA-Z0-9-_\\. ]", "_");

	        File destFile = new File("screenshots/" + safeName + "_" + timestamp + ".png");

	        // Create folder if not exists
	        destFile.getParentFile().mkdirs();
	        Files.copy(srcFile.toPath(), destFile.toPath());

	        return screenshotBytes;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}