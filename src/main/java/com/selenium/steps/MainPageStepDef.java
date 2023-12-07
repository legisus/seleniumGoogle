package com.selenium.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.selenium.library.InterestStocksLib;
import com.selenium.manager.PageFactoryManager;
import com.selenium.pages.google.HomePage;

import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@Slf4j
public class MainPageStepDef {

    private static final long DEFAULT_TIMEOUT = 5;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    InterestStocksLib library;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        library = new InterestStocksLib();
    }

    @Given("User opens {string} page")
    public void openPage(String url) {
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @When("User verifies the page is loaded by asserting the page title {string}")
    public void userClickOnButtonConnectOnMainPage(String pageTitle) {
        String pageTitleUi = homePage.getHeaderPageTitleText();
        assertEquals(pageTitle.toLowerCase(), pageTitleUi.toLowerCase().trim());
    }

    @And("Setup objects of stock symbols from UI list and expected list {string}")
    public void setupObjectsOfStockSymbolsFromUiListAndExpectedList(String string) {
        library.setExpectedListOfStocksObject(List.of(string.split(",")));
        library.setActualListOfStocksObject(homePage.getStocks());
    }

    @Then("Print all stock symbols that are in actual list but not in expected list")
    public void printAllStockSymbolsThatAreInActualStockSymbolsButNotInExpected() {
        library.printStockSymbolsThatAreInActualListButNotInExpectedList();
    }

    @Then("Print all stock symbols that are in expected list but not in actual list")
    public void printAllStockSymbolsThatAreInExpectedStockSymbolsButNotInActualList() {
        library.printStockSymbolsThatAreInExpectedListButNotInActualList();
    }


    @And("Compare interested in info list with list of expected stock symbols expected list")
    public void compareInterestedInInfoListWithListOfExpectedStockSymbols() {
        assertTrue(library.compareStocksObjects());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
