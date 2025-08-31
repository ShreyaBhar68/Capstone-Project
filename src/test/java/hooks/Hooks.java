package hooks;

import base.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtils;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Hooks {
	private static WebDriver driver;

	@Before 
	public void setUp() {
		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
		driver = DriverSetup.getDriver(browser);
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// Call ScreenshotUtils
			byte[] screenshot = ScreenshotUtils.captureScreenshot(driver, scenario.getName());
			System.out.println("Hello"+screenshot);
			if (screenshot != null) {
				scenario.attach(screenshot, "image/png", scenario.getName());
			}
		}
		DriverSetup.quitDriver();
	}

	public static WebDriver getDriver() {
		return driver;
	}
}