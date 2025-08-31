package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            if (browser == null || browser.isEmpty()) {
                browser = ConfigLoader.getProperty("browser");
            }
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
    public static void quitDriver() {
      if (driver != null) {
          driver.quit();
          driver = null;
      }
   }
}
