package selenium.dto.stocksWithParams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonPropertyOrder({"percentage", "movement"})
public class PriceMovement {
    @JsonProperty("percentage")
    private String percentage;
    @JsonProperty("movement")
    private String movement;
}
