package hr.cars.webshop.carswebshop.service;

import hr.cars.webshop.carswebshop.dto.CarDTO;
import hr.cars.webshop.carswebshop.model.*;
import hr.cars.webshop.carswebshop.repository.CarsRepository;
import hr.cars.webshop.carswebshop.repository.SpringDataJpaCarsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarsServiceImpl implements CarsService {

    //private CarsRepository carsRepository;
    private SpringDataJpaCarsRepository carsRepository;

    @Override
    public List<CarDTO> getAllCars() {
        return carsRepository.findAll().stream()
                .map(this::convertCarToCarDTO)
                .toList();
    }

    @Override
    public Optional<CarDTO> getOneCar(Integer id) {
        Optional<Car> carOptional = carsRepository.findById(id);
        return carOptional.map(this::convertCarToCarDTO);
    }

    @Override
    public CarDTO addNewCar(CarDTO newCar) {
        return convertCarToCarDTO(carsRepository.save(convertCarDtoToCar(newCar)));
    }

    @Override
    public List<CarDTO> filterCars(CarSearchForm carSearchForm) {
        Car filterCar = new Car();
        filterCar.setManufacturer(carSearchForm.getManufacturer());
        Example<Car> carExample = Example.of(filterCar);
        return carsRepository.findAll(carExample)
                .stream()
                .map(this::convertCarToCarDTO)
                .toList();

        /*
        return carsRepository.filterCars(carSearchForm)
                .stream()
                .map(this::convertCarToCarDTO)
                .toList();

         */
    }

    private Car convertCarDtoToCar(CarDTO carDTO) {

        EngineType engineType = new EngineType();
        engineType.setName(carDTO.getEngineType().name());

        Color color = new Color();
        color.setName(carDTO.getColor().name());

        return new Car(
                null,
                carDTO.getManufacturer(),
                carDTO.getType(),
                engineType,
                color,
                carDTO.getProductionYear(),
                carDTO.getMilage(),
                carDTO.getPrice()
        );
    }

    private CarDTO convertCarToCarDTO(Car car) {
        return new CarDTO(
                car.getManufacturer(),
                car.getType(),
                EngineTypeEnum.valueOf(car.getEngineType().getName()),
                ColorEnum.valueOf(car.getColor().getName()),
                car.getProductionYear(),
                car.getMilage(),
                car.getPrice()
        );
    }
 }
