@login
Feature: Login to the page

  Scenario: Login
    Given I am logged in as "Sabina Subik" in platform
    Then I should see the "Home Top Bar" page
    And I should see the "Sabina Subik" label is shown

    Scenario: Invalid password
      Given I try to log in as "sabina.subik@gmail.com" and password "12345678910" in platform

      Scenario: Invalid username
        Given I try to log in as "sab.subik@gmail.com" and password "12345678910" in platform

