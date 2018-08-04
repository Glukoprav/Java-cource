package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Dish;
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
@Configuration
public class RecipesDAO {
    private DataSource dataSource;

    public RecipesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        System.out.println("Create dateSource!");
        return new DriverManagerDataSource("jdbc:h2:~/test", "sa", "");
    }

    /**
     * Взять все строки ингредиентов
     */
    @Bean
    public List<Recipe> getAll() {
        //System.out.println("---------------");
        //System.out.println(dataSource.toString());
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final List<Recipe> resultList;
        resultList = jdbcTemplate.query("Select * from recipes", new RecipeRowMapper());
        return resultList;
    }

    /**
     * Взять рецепт по иденту блюда
     */
    public List<Recipe> getById(int dishesId) {
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        SqlParameterSource paramsId = new BeanPropertySqlParameterSource(dishesId);
        return jdbcTemplate.query("Select * from recipes where dishes_id=:dishesId",
                paramsId, new RecipeRowMapper());
    }

    /**
     * Взять рецепт по наименованию блюда
     */
    @Bean
    public List<Recipe> getByName(String name) {
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        SqlParameterSource paramsName = new BeanPropertySqlParameterSource(name);
        final List<Dish> resultId
                = jdbcTemplate.query("Select * from dishes where name=:name",paramsName,new DishesRowMapper());
        final List<Recipe> resultList;
        if (resultId.isEmpty()) {
            return resultList = null;
        } else {
            SqlParameterSource paramsId = new BeanPropertySqlParameterSource(resultId.get(0).getDishes_id());
            resultList = jdbcTemplate.query("Select * from recipes where dishes_id=:dishes_id", paramsId, new RecipeRowMapper());
            return resultList;
        }
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {
        public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Recipe(resultSet.getInt("dishes_id"),
                    resultSet.getString("ingredient"),
                    resultSet.getInt("value"));
        }
    }

    private class DishesRowMapper implements RowMapper<Dish> {
        public Dish mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Dish(resultSet.getInt("dishes_id"),
                    resultSet.getString("name"));
        }
    }

    private void createTable(JdbcTemplate jdbcTemplate) throws SQLException {
        // RUNSCRIPT FROM fileNameString scriptCompressionEncryption
        jdbcTemplate.update("CREATE TABLE dishes(" +
                "dishes_id int," +
                "name varchar(80));");

        jdbcTemplate.update("CREATE TABLE recipes(" +
                "dishes_id int," +
                "ingredient varchar(80)," +
                "value int);");
    }
}
