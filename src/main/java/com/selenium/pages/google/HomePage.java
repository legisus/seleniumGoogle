package com.selenium.pages.google;

import com.selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    @FindBy(xpath = "//*[@id='sdgBod']/span[contains(text(),'Finance')]")
    private WebElement headerPageTitle;

    @FindBy(xpath = "//*[@id='gb']")
    private WebElement header;

    @FindBy(xpath = "//ul[@class='sbnBtf']/li//div[contains(@class, 'COaKTb')]")
    private List<WebElement> stocksTicketsList;

    @FindBy(xpath = "//ul[@class='sbnBtf']/li//div[contains(@class, 'SEGxAb')]")
    private List<WebElement> stocksTicketsListPrices;

    public String getHeaderPageTitleText() {
        return headerPageTitle.getText();
    }

    public List<String> getStocks() {
        List<String> stocks = new ArrayList<>();
        for (WebElement stock : stocksTicketsList) {
            stocks.add(stock.getText());
        }
        return stocks;
    }

    public String getHeaderAllTextContext() {
        return header.getText();
    }


    public List<String> getStocksPositive() {
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        List<String> stocks = new ArrayList<>();
        for (WebElement stock : stocksTicketsList) {
            if(stock.getText().contains("+"))  stock.getText().substring(2).toString();
        }

        return stocks;
    }


    public List<String> getStocksIndexPositive() {
        List<String> stocks = new ArrayList<>();
        for (int i = 0; i < stocksTicketsList.size(); i++) {
            if(stocksTicketsListPrices.get(i).getText().contains("+"))
            stocks.add(stocksTicketsList.get(i).getText());
        }
        return stocks;
    }

    @FindBy(xpath = "//ul[@class='sbnBtf']/li")
    private List<WebElement> allInfoListStock;

    public List<String> getStocksAllInfo() {
        List<String> stocks = new ArrayList<>();
        List<String> index;

        for (WebElement stock : allInfoListStock) {
            if(stock.getText().contains("+")) {
                index = List.of(stock.getText().split("\n"));
                stocks.add(index.get(0));
            }
        }
        return stocks;
    }

}
