package local.home.azav.java.hw24_spring_jdbc;

import local.home.azav.java.hw24_spring_jdbc.dao.RecipesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
//@Configuration
public class MainRecipes {
    //private DataSource dataSource;
    //private JdbcTemplate jdbcTemplate;
    //@Autowired
    //private Recipe recipes;

    //@Autowired
//    public MainRecipes(DataSource dataSource) {
//        this.dataSource = dataSource;
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    public static void main(String[] args) {
        System.out.println("Create context.");
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(ConfigAndConsole.class);  //MainRecipes.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("configcontext.xml");
        System.out.println("Make getBean.");
        ConfigAndConsole console = context.getBean(ConfigAndConsole.class);
        console.MakeConsole();
    }



}
