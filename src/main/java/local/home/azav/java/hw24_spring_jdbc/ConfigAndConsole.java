package local.home.azav.java.hw24_spring_jdbc;

import local.home.azav.java.hw24_spring_jdbc.dao.DishesDAO;
import local.home.azav.java.hw24_spring_jdbc.dao.RecipesDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//@Configuration
public class ConfigAndConsole {
    private RecipesDAO recipesDAO;
    private DishesDAO dishesDAO;
    //private DataSource dataSource;

    public ConfigAndConsole(RecipesDAO recipesDAO, DishesDAO dishesDAO) {
        this.recipesDAO = recipesDAO;
        this.dishesDAO = dishesDAO;
    }

//    @Bean
//    public DriverManagerDataSource dataSource() {
//        System.out.println("Create dateSource!");
//        return new DriverManagerDataSource("jdbc:h2:~/test", "sa", "");
//    }
    protected void MakeConsole() {
        System.out.println("Begin MakeConsole");

    }


}
