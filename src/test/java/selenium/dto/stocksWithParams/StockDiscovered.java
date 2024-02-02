package selenium.dto.stocksWithParams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonPropertyOrder({"price", "link", "name", "currency", "price_movement", "stock", "serpapi_link", "extracted_price"})
public class StockDiscovered {
    @JsonProperty("price")
    private String price;
    @JsonProperty("link")
    private String link;
    @JsonProperty("name")
    private String name;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("price_movement")
    private PriceMovement priceMovement;
    @JsonProperty("stock")
    private String stock;
    @JsonProperty("serpapi_link")
    private String serpapiLink;
    @JsonProperty("extracted_price")
    private Double extractedPrice;
}