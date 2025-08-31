package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.Hooks;
import pages.MoviePage;

public class MovieSteps {

    WebDriver driver = Hooks.getDriver();
    MoviePage moviePage = new MoviePage(driver);

    @When("I click on the Movies tab")
    public void i_click_on_the_movies_tab() {
        moviePage.clickMoviesTab();
    }

    @When("I click on the {string} link")
    public void i_click_on_the_link(String string) {
        moviePage.clickExploreUpcomingMovies();
    }

    @Then("I should see the {string} link displayed")
    public void i_should_see_the_link_displayed(String string) {
        Assert.assertTrue(moviePage.isInCinemasNearYouLinkDisplayed(), "In Cinemas Near You link is not visible!");
    }

    // ====================================================================
    @When("I select a random movie from the Recommended Movies section")
    public void i_select_a_random_movie_from_the_recommended_movies_section() {
        moviePage.selectRandomRecommendedMovie();
    }

    @Then("I should see the movie details page")
    public void i_should_see_the_movie_details_page() {
        Assert.assertTrue(
            moviePage.isPosterDisplayed(),
            "Movie poster was not displayed on the details page"
        );
        System.out.println("✅ Movie details page loaded with poster visible");
    }



    @Then("the movie name should be displayed")
    public void the_movie_name_should_be_displayed() {
        Assert.assertTrue(moviePage.isMovieNameDisplayed(), "Movie name is not displayed!");
    }

    @Then("the movie poster should be visible")
    public void the_movie_poster_should_be_visible() {
        Assert.assertTrue(moviePage.isPosterDisplayed(), "Movie poster is not visible!");
    }

    @Then("the booking option should be available")
    public void the_booking_option_should_be_available() {
        Assert.assertTrue(moviePage.isBookingOptionAvailable(), "Booking option is not available!");
    }
}
