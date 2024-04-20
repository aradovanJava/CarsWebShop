package hr.cars.webshop.carswebshop.controller.rest;

import hr.cars.webshop.carswebshop.dto.CarDTO;
import hr.cars.webshop.carswebshop.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/carswebshop")
@AllArgsConstructor
public class RestCarsController {

    private CarsService carsService;

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carsService.getAllCars();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDTO> getOneCar(@PathVariable Integer carId) {
        Optional<CarDTO> carDTOOptional = carsService.getOneCar(carId);
        return carDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/car")
    public CarDTO addNewCar(@RequestBody CarDTO carDTO) {
        return carsService.addNewCar(carDTO);
    }
}
