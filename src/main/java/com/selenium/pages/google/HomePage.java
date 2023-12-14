package com.selenium.pages.google;

import com.selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    @FindBy(xpath = "//div[@class='gb_kd gb_ed gb_qd']//span[contains(text(),'Finance')]")
    private WebElement headerPageTitle;

    @FindBy(xpath = "//header[@class='gb_Ja gb_ab gb_Nd gb_ld gb_gd gb_Tc']")
    private WebElement header;

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

    public String getHeaderAllTextContext() {
        return header.getText();
    }
}
