package selenium.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListOfStocksObject {
    private List<String> stocks;

    public ListOfStocksObject(List<String> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "ListOfStocksObject{" +
                "stocks=" + stocks +
                '}';
    }

    public boolean equalsStocksIfNotTrowExceptionWithDescription(List<String> actualStocks) {
        if(stocks.equals(actualStocks)) {
            return true;
        }else {
           throw new IllegalArgumentException("Stocks are not equal: " + "\nExpected: " + stocks + "\nActual: " + actualStocks);
        }
    }
}
