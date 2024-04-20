package hr.cars.webshop.carswebshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {

    @Serial
    private static final long serialVersionUID = -3387516993124229948L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String manufacturer;
    private String type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enginetypeid")
    private EngineType engineType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "colorid")
    private Color color;
    @Column(name = "PRODUCTIONYEAR")
    private Integer productionYear;
    private Integer milage;
    private BigDecimal price;
}
