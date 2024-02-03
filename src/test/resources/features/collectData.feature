#@googleFinance

Feature:  Collect data

  Scenario Outline:  Task from meeting
    Given User opens page Finance from configConstants
    When User verifies the page is loaded by asserting the page title 'finance'
    And Setup objects of stock symbols from UI list that positive and expected list '<expectedStockSymbols>'
    Then Print all stock symbols that are in actual list but not in expected list
    And Print all stock symbols that are in expected list but not in actual list
    And Compare interested in info list with list of expected stock symbols expected list
    Examples:
      | expectedStockSymbols           |
      | INDEX, NXST, D, TSLA, AA, MSFT |