package hr.cars.webshop.carswebshop.service;

import hr.cars.webshop.carswebshop.dto.CarDTO;
import hr.cars.webshop.carswebshop.model.CarSearchForm;

import java.util.List;
import java.util.Optional;

public interface CarsService {
    List<CarDTO> getAllCars();
    Optional<CarDTO> getOneCar(Integer id);
    CarDTO addNewCar(CarDTO newCar);
    List<CarDTO> filterCars(CarSearchForm carSearchForm);
}
