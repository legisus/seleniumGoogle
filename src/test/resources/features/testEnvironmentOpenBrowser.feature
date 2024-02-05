@googleFinance

Feature: Tests selenium environment

  Scenario: Open and close browser
    Given User opens page Finance from configConstants
    Then User verifies the page is loaded by asserting the page title 'finance'