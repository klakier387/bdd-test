Feature: Top Bar 
  
  Background: 
    Given I am logged in as "Sabina Subik" in platform
@stays
    Scenario: Stays Search Page
      Given I am on the "Top Bar" page
      When I click over "Top Bar Stays" control
      Then I am on the "Stays Search Bar" page
@flights
    Scenario: Flights Search Page
      Given I am on the "Top Bar" page
      When I click over "Top Bar Flights" control
      Then I am on the "Go to Gate Page" page
@carsRental
    Scenario: Cars Rental Search Page
      Given I am on the "Top Bar" screen
      When I click over "Top Bar Car Rentals" control
      Then I am on the "Cars Rental Search Page" screen
@attractions
    Scenario: Attractions Search Page
      Given I am on the "Top Bar" page
      When I click over "Top Bar Attractions" control
      Then I am on the "Attractions Search" screen
@airportsTaxi
    Scenario: Airports Taxi Search Page
      Given I am on the "Top Bar" page
      When I click over "Top Bar Airports Taxi" control
      Then I am on the "Airports Taxi Search" screen