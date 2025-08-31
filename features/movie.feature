Feature: Movie Search Modulein BookMyShow

  Background:
    Given User is on home page and select city "Kolkata"

  @CurrentMovies
  Scenario: Select any movie from recommended movies and validate movie details
    When I select a random movie from the Recommended Movies section
    Then I should see the movie details page
    And the movie name should be displayed
    And the movie poster should be visible
    And the booking option should be available

  @UpcomingMovies
  Scenario: Navigate to upcoming movies and validate "In Cinemas Near You" link
    When I click on the Movies tab
    And I click on the "Explore Upcoming Movies" link
    Then I should see the "In Cinemas Near You" link displayed
