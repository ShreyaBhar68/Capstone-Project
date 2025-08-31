package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import utils.WaitUtils;

public class MoviePage {

    private WebDriver driver;

    // ===== Locators =====
    private By moviesTab = By.xpath("//a[contains(@href,'movies') and contains(text(),'Movies')]");
    private By exploreUpcomingMoviesLink = By.xpath("//img[@alt='Coming Soon']");
    private By inCinemasNearYouLink = By.xpath("//img[@alt='Now Showing']");
    
    // Recommended Movies section
    private By recommendedMovies = By.xpath("//div[contains(@class,'sc-133848s-3')]//a[@class='sc-133848s-11 sc-lnhrs7-5 ctsexn bHVBt']");

    // Poster & movie details locators
    private By movieName = By.xpath("//h1[@class='sc-qswwm9-6 ea-drWB']");
//    final
    private By moviePoster = By.xpath("//section[@class='sc-bsek5f-0 jNOshi']");
    private By bookingButton = By.xpath("//div[@class='sc-qswwm9-8 fNtHgG']//button//span[text()='Book tickets']");

    // ===== Constructor =====
    public MoviePage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Actions =====
    public void clickMoviesTab() {
        try {
            WebElement element = WaitUtils.waitForVisibility(driver, moviesTab);
            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Handle overlay issue (Firefox)
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sc-1j4wkcy-0")));

            // Click via JavaScript to avoid interception
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Movies tab click failed: " + e.getMessage());
            throw e;
        }
    }

    public void clickExploreUpcomingMovies() {
        WaitUtils.waitForClickability(driver, exploreUpcomingMoviesLink).click();
    }

    public boolean isInCinemasNearYouLinkDisplayed() {
        return WaitUtils.waitForVisibility(driver, inCinemasNearYouLink).isDisplayed();
    }

    public void selectRandomRecommendedMovie() {
        List<WebElement> movies = driver.findElements(recommendedMovies);
        if (movies.isEmpty()) {
            throw new RuntimeException("No recommended movies found!");
        }
        Random random = new Random();
        int index = random.nextInt(movies.size());
        WebElement randomMovie = movies.get(index);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomMovie);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(randomMovie))
                .click();
    }

    public boolean isPosterDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement poster = wait.until(ExpectedConditions.visibilityOfElementLocated(moviePoster));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", poster);

            return poster.isDisplayed();
        } catch (Exception e) {
            System.out.println("Poster not displayed due to: " + e.getMessage());
            return false;
        }
    }

    public boolean isMovieNameDisplayed() {
        try {
            return WaitUtils.waitForVisibility(driver, movieName).isDisplayed();
        } catch (Exception e) {
            System.out.println("Movie name element not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isBookingOptionAvailable() {
        try {
            return WaitUtils.waitForVisibility(driver, bookingButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
