package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Dish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс работы с наименованиями блюд
 */
public class DishesDAO {
    private DataSource dataSource;

    public DishesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dish> getAll() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final List<Dish> resultList;
        resultList = jdbcTemplate.query("Select * from dishes", new DishesDAO.DishesRowMapper());
        return resultList;
    }

    public List<Dish> getByName(String name) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("Select * from dishes where name like '%" + name + "%'", new DishesRowMapper());
    }

    public Dish getById(int dishesId) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("Select * from dishes where dishesid=?", new DishesRowMapper(),
                dishesId);
    }

    private class DishesRowMapper implements RowMapper<Dish> {
        public Dish mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Dish(resultSet.getInt("dishesid"),
                    resultSet.getString("name"));
        }
    }
}
