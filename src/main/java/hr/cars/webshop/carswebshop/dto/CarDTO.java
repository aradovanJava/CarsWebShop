package hr.cars.webshop.carswebshop.dto;

import hr.cars.webshop.carswebshop.model.ColorEnum;
import hr.cars.webshop.carswebshop.model.EngineTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private String manufacturer;
    private String type;
    private EngineTypeEnum engineType;
    private ColorEnum color;
    private Integer productionYear;
    private Integer milage;
    private BigDecimal price;
}
