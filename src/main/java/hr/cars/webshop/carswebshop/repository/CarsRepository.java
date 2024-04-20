package hr.cars.webshop.carswebshop.repository;

import hr.cars.webshop.carswebshop.model.Car;
import hr.cars.webshop.carswebshop.model.CarSearchForm;

import java.util.List;

public interface CarsRepository {
    List<Car> getAllCars();
    Car getOneCar(Integer id);
    Car addNewCar(Car newCar);
    List<Car> filterCars(CarSearchForm carSearchForm);
}
