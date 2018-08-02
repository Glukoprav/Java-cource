package local.home.azav.java.hw24_spring_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс работы с рецептами
 */
@Component
public class RecipesDAO {
    private DataSource dataSource;

    @Autowired
    public RecipesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Взять все строки ингредиентов
     */
    public List<Recipe> getAll() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final List<Recipe> resultList;
        resultList = jdbcTemplate.query("Select * from recipes", new RecipeRowMapper());
        return resultList;
    }

    /**
     * Взять рецепт по иденту блюда
     */
    public List<Recipe> getById(int dishes_id) {
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        final List<Recipe> resultList;
        resultList = jdbcTemplate.query("Select * from recipes where dishes_id=:dishes_id", new RecipeRowMapper());
        return resultList;
    }

    /**
     * Взять рецепт по наименованию блюда
     */
    public List<Recipe> getByName(String name) {
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        final List<Recipe> resultList;
        resultList = jdbcTemplate.query("Select * from recipes where dishes_id=:dishes_id", new RecipeRowMapper());
        return resultList;
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {
        public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
            Recipe recipe = new Recipe(resultSet.getInt("dishes_id"),
                    resultSet.getString("ingredient"),
                    resultSet.getInt("value"));
            return recipe;
        }
    }
}
