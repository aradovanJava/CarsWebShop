package hr.cars.webshop.carswebshop.repository;

import hr.cars.webshop.carswebshop.model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcCarsRepositoryImplementation implements CarsRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcCarsInsert;

    public JdbcCarsRepositoryImplementation(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcCarsInsert = new SimpleJdbcInsert(dataSource).withTableName("CARS")
                                                    .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<Car> getAllCars() {
        return jdbcTemplate.query("SELECT * FROM CARS",
                new BeanPropertyRowMapper<Car>(Car.class));
    }

    @Override
    public Car getOneCar(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM CARS WHERE ID = ?", new CarRowMapper(), id);
    }

    @Override
    public Car addNewCar(Car newCar) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("MANUFACTURER", newCar.getManufacturer());
        parameters.put("TYPE", newCar.getType());
        parameters.put("ENGINETYPE", newCar.getEngineType().getName());
        parameters.put("COLOR", newCar.getColor());
        parameters.put("PRODUCTIONYEAR", newCar.getProductionYear());
        parameters.put("MILAGE", newCar.getMilage());
        parameters.put("PRICE", newCar.getPrice());

        Integer id = simpleJdbcCarsInsert.execute(parameters);
        newCar.setId(id);

        return newCar;
    }

    @Override
    public List<Car> filterCars(CarSearchForm carSearchForm) {

        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM Cars where 1=1 ");
        List<Object> queryArgs = new ArrayList<>();

        if(!carSearchForm.getManufacturer().isEmpty()){
            sqlQuery.append("AND manufacturer LIKE ('%'||?||'%') ");
            queryArgs.add(carSearchForm.getManufacturer());
        }

        if(!carSearchForm.getType().isEmpty()){
            sqlQuery.append("AND type LIKE ('%'||?||'%') ");
            queryArgs.add(carSearchForm.getType());
        }

        if(!carSearchForm.getEngineType().isEmpty()
                && !"None".equals(carSearchForm.getEngineType())){
            sqlQuery.append("AND enginetype = ? ");
            queryArgs.add(carSearchForm.getEngineType());
        }

        if(!carSearchForm.getColor().isEmpty()
                && !"None".equals(carSearchForm.getColor())){
            sqlQuery.append("AND color = ? ");
            queryArgs.add(carSearchForm.getColor());
        }

        if(Optional.ofNullable(carSearchForm.getProductionYearFrom()).isPresent()){
            sqlQuery.append("AND productionyear >= ? ");
            queryArgs.add(carSearchForm.getProductionYearFrom());
        }

        if(Optional.ofNullable(carSearchForm.getProductionYearTo()).isPresent()){
            sqlQuery.append("AND productionyear <= ? ");
            queryArgs.add(carSearchForm.getProductionYearTo());
        }

        if(Optional.ofNullable(carSearchForm.getMilageFrom()).isPresent()){
            sqlQuery.append("AND milage >= ? ");
            queryArgs.add(carSearchForm.getMilageFrom());
        }

        if(Optional.ofNullable(carSearchForm.getMilageTo()).isPresent()){
            sqlQuery.append("AND milage <= ? ");
            queryArgs.add(carSearchForm.getMilageTo());
        }

        if(Optional.ofNullable(carSearchForm.getPriceFrom()).isPresent()){
            sqlQuery.append("AND price >= ? ");
            queryArgs.add(carSearchForm.getPriceFrom());
        }

        if(Optional.ofNullable(carSearchForm.getPriceTo()).isPresent()){
            sqlQuery.append("AND price <= ? ");
            queryArgs.add(carSearchForm.getPriceTo());
        }

        Object[] preparedStatementArgs = new Object[queryArgs.size()];
        for(int i = 0; i < preparedStatementArgs.length; i++){
            preparedStatementArgs[i] = queryArgs.get(i);
        }

        return this.jdbcTemplate.query(sqlQuery.toString(), new CarRowMapper(), preparedStatementArgs);
    }

    private static class CarRowMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car newCar = new Car();
            newCar.setId(rs.getInt("ID"));
            newCar.setManufacturer(rs.getString("MANUFACTURER"));
            newCar.setType(rs.getString("TYPE"));

            EngineType engineType = new EngineType();
            engineType.setName(rs.getString("ENGINETYPE"));

            Color color = new Color();
            color.setName(rs.getString("COLOR"));

            newCar.setEngineType(engineType);
            newCar.setColor(color);
            newCar.setProductionYear(rs.getInt("PRODUCTIONYEAR"));
            newCar.setMilage(rs.getInt("MILAGE"));
            newCar.setPrice(rs.getBigDecimal("PRICE"));
            return newCar;
        }
    }
}
