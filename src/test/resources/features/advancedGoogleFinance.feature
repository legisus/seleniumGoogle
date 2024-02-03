@googleFinance

Feature:  As user I want to verify the stock symbols listed under the section “You may be interested in info” ... using properties file

  Scenario Outline: Verify the stock symbols listed under the section “You may be interested in info” ... using properties file
    Given User opens page Finance from configConstants
    When User verifies the page is loaded by asserting the page title 'finance'
    And Setup objects of stock symbols from UI list and expected list '<expectedStockSymbols>'
    Then Print all stock symbols that are in actual list but not in expected list
    And Print all stock symbols that are in expected list but not in actual list
    And Compare interested in info list with list of expected stock symbols expected list
    Examples:
      | expectedStockSymbols           |
      | AMZN, MSFT, AAL, INDEX, NXST, BABA |