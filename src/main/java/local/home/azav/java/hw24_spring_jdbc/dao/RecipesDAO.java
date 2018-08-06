package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс работы с рецептами
 */
//@Configuration
public class RecipesDAO {
    private DataSource dataSource;

    public RecipesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Взять все строки ингредиентов
     */
    public List<Recipe> getAll() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("Select * from recipes", new RecipeRowMapper());
    }

    /**
     * Взять рецепт по иденту блюда
     */
    public List<Recipe> getById(int dishesid) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("Select * from recipes where dishesid=?", new RecipeRowMapper(), dishesid);
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {
        public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Recipe(resultSet.getInt("dishesid"),
                    resultSet.getString("ingredient"),
                    resultSet.getInt("value"));
        }
    }
}
