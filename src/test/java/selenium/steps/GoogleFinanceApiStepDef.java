package selenium.steps;

import selenium.library.InterestStocksLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
public class GoogleFinanceApiStepDef {

    InterestStocksLib lib = InterestStocksLib.getInstance();
    ValidatableResponse validatableResponse;

    @Given("Send serpApi GET request and storage into java object")
    public void serpApiGetRequestWithStorageIntoJavaObject() {

        validatableResponse = lib.sendGetRequestForGoogleFinance();

        assertEquals(200, validatableResponse.extract().statusCode());

        JSONArray jsonArray = new JSONArray(validatableResponse.extract().body().jsonPath().getList("discover_more[0].items"));

        lib.mapJsonToJavaObject(jsonArray.toString());

        lib.setListOfStocksObjectFromApi();
    }

    @Then("Verify the stock symbols listed under the section “You may be interested in info” ... with the {string}")
    public void verifyTheStockSymbolsListedUnderTheSectionYouMayBeInterestedInInfoWithThe(String stockSymbols) {
        lib.setExpectedListOfStocksObject(List.of(stockSymbols.split(",")));
        lib.setActualListOfStocksObject(lib.getListOfStocksObjectFromApi());

        assertEquals(true, lib.compareStocksObjects());
    }
}