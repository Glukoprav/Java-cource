package local.home.azav.java.hw24_spring_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Задание к лекции "24_SpringJDBC, Transactions":
 * Разработать консольное приложение для хранения рецептов.
 * Функциональность:
 * - поиск рецепта по имени или части имени блюда;
 * - добавление рецепта - рецепт состоит из множества ингридиентов
 * и их количественного состава;
 * - удаление блюда.
 */

@Configuration
public class MainRecipes {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Recipes recipes;

   // @Autowired
    public MainRecipes(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MainRecipes.class);

    }

    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:~/test", "sa", "");
    }

    private void createTable() throws SQLException {
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
