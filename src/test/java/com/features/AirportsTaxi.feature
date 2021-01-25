Feature: Airports Taxi

  Background:
    Given I am logged in as "Sabina Subik" in platform
    And I am on the "Top Bar" page
    And I click over "Top Bar Airports Taxi" control
    And I am on the "Airports Taxi Search" page

  Scenario: All necessary fields are filled
    When I enter "London Heathrow" auto complete into the "Pick Up Location Airports Taxi" field
  And I enter "London" text into text field
    And I enter "Hilton Garden Inn London" auto complete into the "Destination Location Airports Taxi" field
    And I enter "Hilton" text into text field
    And I choose "3" select list from the "Passengers Airports Taxi" field
    And I click over "Submit Search Airports Taxi" control
    Then I am on the "Airports Taxi Results" screen