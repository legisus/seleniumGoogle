package com.selenium.library;

import com.selenium.dto.ListOfStocksObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class InterestStocksLib {

    ListOfStocksObject expectedListOfStocksObject = new ListOfStocksObject();
    ListOfStocksObject actualListOfStocksObject = new ListOfStocksObject();

    public void setExpectedListOfStocksObject(List<String> expectedStockSymbols) {
        List<String> trimmedStockSymbols = expectedStockSymbols.stream()
                .map(String::trim)
                .toList();
        expectedListOfStocksObject.setStocks(trimmedStockSymbols);
    }

    public void setActualListOfStocksObject(List<String> actualStockSymbols) {
        actualListOfStocksObject.setStocks(actualStockSymbols);
    }

    public boolean compareStocksObjects() {
        return expectedListOfStocksObject.equalsStocksIfNotTrowExceptionWithDescription(actualListOfStocksObject.getStocks());
    }

    public void printStockSymbolsThatAreInActualListButNotInExpectedList() {
        List<String> stockSymbolsThatAreInActualListButNotInExpectedList = actualListOfStocksObject.getStocks().stream()
                .filter(stockSymbol -> !expectedListOfStocksObject.getStocks().contains(stockSymbol))
                .toList();
        log.info("Stock symbols that are in actual list but not in expected list: " + stockSymbolsThatAreInActualListButNotInExpectedList);
    }

    public void printStockSymbolsThatAreInExpectedListButNotInActualList() {
        List<String> stockSymbolsThatAreInExpectedListButNotInActualList = expectedListOfStocksObject.getStocks().stream()
                .filter(stockSymbol -> !actualListOfStocksObject.getStocks().contains(stockSymbol))
                .toList();
        log.info("Stock symbols that are in expected list but not in actual list: " + stockSymbolsThatAreInExpectedListButNotInActualList);
    }
}
