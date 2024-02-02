package selenium.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import selenium.constants.ConfigConstants;
import selenium.dto.ListOfStocksObject;
import selenium.dto.stocksWithParams.StockDiscovered;
import selenium.util.ConfigUtil;
import selenium.util.RestRequests;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class InterestStocksLib {

    private static InterestStocksLib instance = null;

    private InterestStocksLib() {
    }

    public static InterestStocksLib getInstance() {
        if (instance == null) {
            synchronized (InterestStocksLib.class) {
                if (instance == null) {
                    instance = new InterestStocksLib();
                }
            }
        }
        return instance;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    RestRequests restRequests = new RestRequests();
    private ValidatableResponse validatableResponse;
    private List<StockDiscovered> stockDiscoveredList;

    private ListOfStocksObject expectedListOfStocksObject = new ListOfStocksObject();
    private ListOfStocksObject actualListOfStocksObject = new ListOfStocksObject();
    private ListOfStocksObject listOfStocksObjectFromApi = new ListOfStocksObject();

    public ListOfStocksObject getListOfStocksObjectFromApi() {
        return listOfStocksObjectFromApi;
    }

    public void setExpectedListOfStocksObject(List<String> expectedStockSymbols) {
        List<String> trimmedStockSymbols = expectedStockSymbols.stream()
                .map(String::trim)
                .toList();
        expectedListOfStocksObject.setStocks(trimmedStockSymbols);
    }

    public void setActualListOfStocksObject(List<String> actualStockSymbols) {
        actualListOfStocksObject.setStocks(actualStockSymbols);
    }

    public void setActualListOfStocksObject(ListOfStocksObject newValues) {
        actualListOfStocksObject = newValues;
    }

    public boolean compareStocksObjects() {
        return expectedListOfStocksObject.equalsStocksIfNotTrowExceptionWithDescription(actualListOfStocksObject.getStocks());
    }

    public void printStockSymbolsThatAreInActualListButNotInExpectedList() {
        List<String> stockSymbolsThatAreInActualListButNotInExpectedList = actualListOfStocksObject.getStocks().stream()
                .filter(stockSymbol -> !expectedListOfStocksObject.getStocks().contains(stockSymbol))
                .toList();
        log.info("Stock symbols that are in actual list but not in expected list: " + stockSymbolsThatAreInActualListButNotInExpectedList);

        for (String s : actualListOfStocksObject.getStocks()) {
            if (!expectedListOfStocksObject.getStocks().contains(s)) {
                log.info("Stock symbols that are in actual list but not in expected list by forEach: " + s);
            }
        }
    }

    public void printStockSymbolsThatAreInExpectedListButNotInActualList() {
        List<String> stockSymbolsThatAreInExpectedListButNotInActualList = expectedListOfStocksObject.getStocks().stream()
                .filter(stockSymbol -> !actualListOfStocksObject.getStocks().contains(stockSymbol))
                .toList();
        log.info("Stock symbols that are in expected list but not in actual list: " + stockSymbolsThatAreInExpectedListButNotInActualList);
    }

    public ValidatableResponse sendGetRequestForGoogleFinance() {
        String baseUri = ConfigUtil.getString(ConfigConstants.SERP_API_BASE_URL);
        String pathUri = ConfigUtil.getString(ConfigConstants.SERP_API_SEARCH_PATH);
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("engine", "google_finance_markets");
        parameters.put("trend", "indexes");
        parameters.put("api_key", ConfigUtil.getString(ConfigConstants.SERP_API_KEY));

        return restRequests.getBaseRequest(baseUri, pathUri, parameters, new HashMap<>());

    }

    public void mapJsonToJavaObject(String json) {
        try {
            stockDiscoveredList = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error while mapping json to java object" + e);
        }
    }

    public void setListOfStocksObjectFromApi() {
        List<String> stockTickets = stockDiscoveredList.stream()
                .map(StockDiscovered::getStock)
                .map(stock -> {
                    String[] split = stock.split(":");
                    return split[0];
                })
                .map(stock -> stock.replace(".", ""))
                .toList();
        listOfStocksObjectFromApi.setStocks(stockTickets);
    }
}



















































