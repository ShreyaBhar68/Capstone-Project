Feature: City Search Module

  Background:
    Given User is on the city selection page

  @ValidCitySearch
  Scenario: Search for a valid city name
    When I search for city "Kolkata"
    Then I should see "Kolkata" in the search results
    And verify dropdown text should be "Kolkata"

  @InvalidCitySearch
  Scenario: Search for an invalid city name
    When I search for city "Atlantis"
    Then I should see an error message here "No results found."

  @CityIcons
  Scenario: Select city by choosing a city icon
    When I select the city icon "Chennai"
    Then verify dropdown text should be "Chennai"

  @ViewAllCities
  Scenario: View all cities and validate additional city names
    When I click on "View All Cities"
    Then I should see city names like "Nagpur" and "Bhubaneswar" 
    And verify "Nagpur" and "Bhubaneswar" should not be in popular cities