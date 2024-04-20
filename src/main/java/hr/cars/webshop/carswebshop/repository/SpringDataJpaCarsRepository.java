package hr.cars.webshop.carswebshop.repository;

import hr.cars.webshop.carswebshop.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCarsRepository extends JpaRepository<Car, Integer> {
}
