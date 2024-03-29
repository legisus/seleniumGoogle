package selenium.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.constants.ConfigConstants;
import selenium.library.InterestStocksLib;
import selenium.manager.PageFactoryManager;
import selenium.pages.google.HomePage;
import selenium.util.ConfigUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome");
        log.info("options.setBinary path");

        log.info("Updated ChromeOptions for headless execution");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("start-maximized");
        options.addArguments("incognito");

        log.info("Ensure chromedriver is set up correctly");
        WebDriverManager.chromedriver().setup();

        log.info("Instantiate ChromeDriver with specified options");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        log.info("Initialize other components");
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = pageFactoryManager.getHomePage();
        library = InterestStocksLib.getInstance();
    }

    @Given("User opens {string} page")
    public void openPage(String url) {
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @Given("User opens page Finance from configConstants")
    public void openPageFromConfig() {
        String url = ConfigUtil.getString(ConfigConstants.URL_MAIN_PAGE_GOOGLE_FINANCE);
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @When("User verifies the page is loaded by asserting the page title {string}")
    public void userClickOnButtonConnectOnMainPage(String pageTitle) {
        String pageTitleUi = homePage.getHeaderPageTitleText();
        String pageTitleUiAllTextContext = homePage.getHeaderAllTextContext();

        try{
            assertEquals(pageTitle.toLowerCase(), pageTitleUi.toLowerCase().trim());
        }catch (Exception e) {
            try {
                assertTrue(pageTitleUiAllTextContext.toLowerCase().contains(pageTitle));
            }catch (Exception e2) {
                throw new RuntimeException("Title is not correct");
            }
        }

    }

    @And("Setup objects of stock symbols from UI list and expected list {string}")
    public void setupObjectsOfStockSymbolsFromUiListAndExpectedList(String string) {
        library.setExpectedListOfStocksObject(List.of(string.split(",")));
        library.setActualListOfStocksObject(homePage.getStocks());
    }

    @And("Setup objects of stock symbols from UI list that positive and expected list {string}")
    public void setupObjectsOfStockSymbolsFromUiListOfPositiveStocksAndExpectedList(String string) {
        library.setExpectedListOfStocksObject(List.of(string.split(",")));
        library.setActualListOfStocksObject(homePage.getStocksAllInfo());
        homePage.getStocksAllInfo().forEach(log::info);

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
