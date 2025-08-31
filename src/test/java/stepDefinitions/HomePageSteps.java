package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import constants.FrameworkConstants;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePageSteps {
	
	WebDriver driver = Hooks.getDriver();
	HomePage citypage=new HomePage(driver);

    @Given("User is on the city selection page")
    public void user_is_on_the_city_selection_page() {
    	driver.get(FrameworkConstants.BASE_URL);
    }

    // ---------- Valid & Invalid City Search ----------
    @When("I search for city {string}")
    public void i_search_for_city(String cityName) throws InterruptedException {
    	citypage.selectCity(cityName);
    }

    @Then("I should see {string} in the search results")
    public void i_should_see_in_the_search_results(String expectedCity) throws InterruptedException {
    	Assert.assertTrue(citypage.isCityPresentInResults(expectedCity));
    	Thread.sleep(1000);
    	citypage.clickOnFirstResult();
    }

    @Then("I should see an error message here {string}")
    public void i_should_see_an_error_message_here(String expectedError) {
    	String actualErrorMessage=citypage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedError);
    }

    @Then("verify dropdown text should be {string}")
    public void verify_dropdown_text_should_be(String exceptedCity) {
        String actualText=citypage.getTextFromCityDropdown();
        System.out.println(actualText);
        Assert.assertTrue(actualText.contains(exceptedCity));
    }

    // ---------- City Icon Selection ----------
    @When("I select the city icon {string}")
    public void i_select_the_city_icon(String cityIcon) throws InterruptedException {
        citypage.selectCityByIcon(cityIcon);
        Thread.sleep(3000);
    }

    // ---------- View All Cities ----------

    @When("I click on {string}")
    public void i_click_on(String optionName) {
       citypage.openAllCitiesList();
    }

    @Then("I should see city names like {string} and {string}")
    public void i_should_see_city_names_like_and(String city1, String city2) {
        Assert.assertTrue(citypage.isCityPresentInAllCities(city2));
        Assert.assertTrue(citypage.isCityPresentInAllCities(city1));
    }

    @Then("verify {string} and {string} should not be in popular cities")
    public void verify_cities_not_in_popular_cities(String city1, String city2) {
        Assert.assertFalse(citypage.isCityPresentInPopularCities(city2));
        Assert.assertFalse(citypage.isCityPresentInPopularCities(city1));
    }
}