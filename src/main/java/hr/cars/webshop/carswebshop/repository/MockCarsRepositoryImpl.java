package hr.cars.webshop.carswebshop.repository;

import hr.cars.webshop.carswebshop.model.Car;
import hr.cars.webshop.carswebshop.model.CarSearchForm;
import hr.cars.webshop.carswebshop.model.ColorEnum;
import hr.cars.webshop.carswebshop.model.EngineTypeEnum;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MockCarsRepositoryImpl implements CarsRepository {
    private static final List<Car> carList;

    static {
        carList = new ArrayList<>();

        Car newCar1 = new Car();

        newCar1.setId(1);
        //newCar1.setColor(ColorEnum.BLACK);
        newCar1.setMilage(50000);
        newCar1.setManufacturer("Audi");
        newCar1.setType("eTron");
        newCar1.setPrice(new BigDecimal(80000));
        //newCar1.setEngineType(EngineTypeEnum.ELECTRIC);
        newCar1.setProductionYear(2021);

        Car newCar2 = new Car();

        newCar2.setId(2);
        //newCar2.setColor(ColorEnum.BLACK);
        newCar2.setMilage(1000000);
        newCar2.setManufacturer("Yugo");
        newCar2.setType("Koral 55");
        newCar2.setPrice(new BigDecimal(1));
        //newCar2.setEngineType(EngineTypeEnum.FUEL);
        newCar2.setProductionYear(1987);

        Car newCar3 = new Car();

        newCar3.setId(3);
        //newCar3.setColor(ColorEnum.BLUE);
        newCar3.setMilage(10000);
        newCar3.setManufacturer("Kia");
        newCar3.setType("Sportage");
        newCar3.setPrice(new BigDecimal(36000));
        //newCar3.setEngineType(EngineTypeEnum.HYBRID);
        newCar3.setProductionYear(2021);

        Car newCar4 = new Car();

        newCar4.setId(4);
        //newCar4.setColor(ColorEnum.PINK);
        newCar4.setMilage(1000);
        newCar4.setManufacturer("Volvo");
        newCar4.setType("X-60");
        newCar4.setPrice(new BigDecimal(46000));
        //newCar4.setEngineType(EngineTypeEnum.HYBRID);
        newCar4.setProductionYear(2024);

        carList.add(newCar1);
        carList.add(newCar2);
        carList.add(newCar3);
        carList.add(newCar4);
    }

    @Override
    public List<Car> getAllCars() {
        return carList;
    }

    @Override
    public Car getOneCar(Integer id) {
        return carList.stream()
                .filter(c -> c.getId().equals(id))
                .toList()
                .getFirst();
    }

    @Override
    public Car addNewCar(Car newCar) {
        newCar.setId(generateNewCarId());
        carList.add(newCar);
        return newCar;
    }

    @Override
    public List<Car> filterCars(CarSearchForm carSearchForm) {

        List<Car> cars = getAllCars();

        if(!carSearchForm.getManufacturer().isEmpty()) {
            cars = cars.stream()
                    .filter(c -> c.getManufacturer().contains(carSearchForm.getManufacturer()))
                    .toList();
        }

        if(!carSearchForm.getType().isEmpty()) {
            cars = cars.stream()
                    .filter(c -> c.getType().contains(carSearchForm.getType()))
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getEngineType()).isPresent()
            && !"None".equals(carSearchForm.getEngineType()))
        {

            EngineTypeEnum filterEngineType = EngineTypeEnum.valueOf(carSearchForm.getEngineType());

            cars = cars.stream()
                    .filter(c -> c.getEngineType().getName().equals(filterEngineType.name()))
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getColor()).isPresent()
                && !"None".equals(carSearchForm.getColor()))
        {

            ColorEnum filterColor = ColorEnum.valueOf(carSearchForm.getColor());

            cars = cars.stream()
                    .filter(c -> c.getColor().getName().equals(filterColor.name()))
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getProductionYearFrom()).isPresent()) {
            cars = cars.stream()
                    .filter(c -> c.getProductionYear().compareTo(carSearchForm.getProductionYearFrom()) >= 0)
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getProductionYearTo()).isPresent()) {
            cars = cars.stream()
                    .filter(c -> c.getProductionYear().compareTo(carSearchForm.getProductionYearTo()) <= 0)
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getMilageFrom()).isPresent()) {
            cars = cars.stream()
                    .filter(c -> c.getMilage().compareTo(carSearchForm.getMilageFrom()) >= 0)
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getMilageTo()).isPresent()) {
            cars = cars.stream()
                    .filter(c -> c.getMilage().compareTo(carSearchForm.getMilageTo()) <= 0)
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getPriceFrom()).isPresent()) {
            cars = cars.stream()
                    .filter(c -> c.getPrice().compareTo(carSearchForm.getPriceFrom()) >= 0)
                    .toList();
        }

        if(Optional.ofNullable(carSearchForm.getPriceTo()).isPresent()) {
            cars = cars.stream()
                    .filter(c -> c.getPrice().compareTo(carSearchForm.getPriceTo()) <= 0)
                    .toList();
        }

        return cars;
    }

    private Integer generateNewCarId() {
        Optional<Car> carWithTheMaxId = getAllCars().stream()
                .max((c1, c2) -> c1.getId().compareTo(c2.getId()));
        return carWithTheMaxId.map(car -> car.getId() + 1).orElse(1);
    }
}
