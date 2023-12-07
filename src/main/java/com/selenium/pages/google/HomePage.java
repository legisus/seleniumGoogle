package com.selenium.pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.selenium.pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    @FindBy(xpath = "//div[@class='gb_od gb_id gb_ud']//span[@class='gb_ld gb_cd']")
    private WebElement headerPageTitle;

    @FindBy(xpath = "//ul[@class='sbnBtf']/li//div[contains(@class, 'COaKTb')]")
    private List<WebElement> stocksTicketsList;

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
}
