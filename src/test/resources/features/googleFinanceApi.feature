#@googleFinance

Feature:  “You may be interested in info” ... using API

  Scenario Outline: Verify the stock symbols listed under the section “You may be interested in info” ... using API
    Given Send serpApi GET request and storage into java object
    Then Verify the stock symbols listed under the section “You may be interested in info” ... with the '<expectedStockSymbols>'

    Examples:
      | expectedStockSymbols           |
      | INDEX, NXST, D, TSLA, AA, MSFT |
      | DJI, INX, TSLA, AAPL, AMZN, BABA, F, NXST, BA, IXIC, VIX, MSFT, T, CCL, DIS, SPY, WMT, NFLX |