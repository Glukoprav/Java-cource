package local.home.azav.java.hw25_spring_hibernate;

import local.home.azav.java.hw25_spring_hibernate.dao.DishesDAO;
import local.home.azav.java.hw25_spring_hibernate.dao.RecipesDAO;
import local.home.azav.java.hw25_spring_hibernate.model.Dish;
import local.home.azav.java.hw25_spring_hibernate.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springhibernatetest.xml" })
public class ConfigAndConsoleTest {

    @Autowired
    private RecipesDAO recipesDAO;
    @Autowired
    private DishesDAO dishesDAO;
    @Autowired
    private ConfigAndConsole configAndConsole;

    @Test
    public void testgetByListDish() {
        List<Dish> dishList = dishesDAO.getAll();
        assertEquals(4,configAndConsole.printList(dishList));
    }

    @Test
    public void testgetByListRecip() {
        List<Recipe> recipeList = recipesDAO.getAll();
        assertEquals(13,configAndConsole.printList(recipeList));
    }

    @Test
    public void menuConsole() {
        configAndConsole.menuConsole();
    }

    @Test
    public void printAllDishes() {
        configAndConsole.printAllDishes();
    }
}

//    @Mock
//    Service service;
//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//
//
//    @Test
//    public void testGetUserNames() {
//
//        List<String> userNames = new ArrayList<String>();
//        userNames.add("bob");
//
//        when(service.getJdbcTemplate()).thenReturn(jdbcTemplate);
//        when(jdbcTemplate.query(anyString(), anyObject()).thenReturn(userNames);
//
//        String retVal = Class.getUserNames("test");
//        assertEquals("bob", retVal);
//    }