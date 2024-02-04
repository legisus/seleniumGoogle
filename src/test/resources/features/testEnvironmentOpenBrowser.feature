@googleFinance

Feature: Tests selenium environment

  Scenario: Open and close browser
    Given User opens 'https://www.google.com/finance' page
    Then User verifies the page is loaded by asserting the page title 'finance'