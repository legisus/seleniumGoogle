@googleFinance

Feature:  As user I want to verify the stock symbols listed under the section “You may be interested in info”

  Scenario Outline: Verify the stock symbols listed under the section “You may be interested in info”
    Given User opens '<homePage>' page
    When User verifies the page is loaded by asserting the page title '<title>'
    And Setup objects of stock symbols from UI list and expected list '<expectedStockSymbols>'
    Then Print all stock symbols that are in actual list but not in expected list
    And Print all stock symbols that are in expected list but not in actual list
    And Compare interested in info list with list of expected stock symbols expected list
    Examples:
      | homePage                        | title   | expectedStockSymbols                 |
      | https://www.google.com/finance/ | finance | INDEX, NXST, D, TSLA, AA, MSFT       |
#      | https://www.google.com/finance/ | finance | AAL, AMZN, BABA, MSFT, F, AAPL       |
#      | https://www.google.com/finance/ | finance | AAL, AMZN, BABA, MSFT, F, BAC        |
#      | https://www.google.com/finance/ | finance | F, AMZN, BABA, MSFT, F, BAC          |
#      | https://www.google.com/finance/ | finance | F, AMZN, BABA, MSFT, F, BAC, MCD, AA |
#      | https://www.google.com/finance/ | finance | F                                    |