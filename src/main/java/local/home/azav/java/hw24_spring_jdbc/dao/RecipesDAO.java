package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс работы с рецептами
 */
public class RecipesDAO implements IRecipesDAO {
    private DataSource dataSource;

    public RecipesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Взять все строки ингредиентов
     */
    @Override
    public List<Recipe> getAll() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("Select * from recipes", new RecipeRowMapper());
    }

    /**
     * Взять рецепт по иденту блюда
     */
    @Override
    public List<Recipe> getById(int dishesid) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("Select * from recipes where dishesid=?", new RecipeRowMapper(), dishesid);
    }

    /**
     * Добавление ингредиента к блюду по иденту
     */
    @Override
    public int insertIngredient(int intId, String newName, int intValue) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update("INSERT INTO recipes (dishesid, ingredient, value) VALUES(?,?,?)", intId, newName, intValue);
    }

    /**
     * Удаление всех ингредиентов у блюда по иденту
     */
    @Override
    public int deleteRecipe(int intId) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update("delete from recipes where dishesid = ?", intId);
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {
        public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Recipe(resultSet.getInt("dishesid"),
                    resultSet.getString("ingredient"),
                    resultSet.getInt("value"));
        }
    }
}
