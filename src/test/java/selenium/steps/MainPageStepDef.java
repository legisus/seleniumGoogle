package selenium.steps;

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
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
