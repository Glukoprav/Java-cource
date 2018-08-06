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
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        SqlParameterSource paramsName = new BeanPropertySqlParameterSource(name);
        final List<Dish> resultId
                = jdbcTemplate.query("Select * from dishes where name like '%:name%'", paramsName, new DishesRowMapper());
        return resultId;
//        if (resultId.isEmpty()) {
//            return resultList = null;
//        } else {
//            SqlParameterSource paramsId = new BeanPropertySqlParameterSource(resultId.get(0).getDishes_id());
//            resultList = jdbcTemplate.query("Select * from recipes where dishes_id=:dishes_id", paramsId, new RecipeRowMapper());
//            return resultList;
//        }
    }

    private class DishesRowMapper implements RowMapper<Dish> {
        public Dish mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Dish(resultSet.getInt("dishes_id"),
                    resultSet.getString("name"));
        }
    }
}
