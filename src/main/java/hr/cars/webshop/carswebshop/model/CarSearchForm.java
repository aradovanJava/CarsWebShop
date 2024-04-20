package hr.cars.webshop.carswebshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarSearchForm {
    private String manufacturer;
    private String type;
    private String engineType;
    private String color;
    private Integer productionYearFrom;
    private Integer productionYearTo;
    private Integer milageFrom;
    private Integer milageTo;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
}
