package local.home.azav.java.hw24_spring_jdbc;

import local.home.azav.java.hw24_spring_jdbc.dao.DishesDAO;
import local.home.azav.java.hw24_spring_jdbc.dao.RecipesDAO;
import local.home.azav.java.hw24_spring_jdbc.model.Dish;
import local.home.azav.java.hw24_spring_jdbc.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/configcontexttest.xml"})
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
        assertTrue(configAndConsole.printList(dishList) > 0);
    }

    @Test
    public void testgetByListRecip() {
        List<Recipe> recipeList = recipesDAO.getAll();
        assertEquals(10, configAndConsole.printList(recipeList));
    }

    @Test
    public void menuConsole() {
        configAndConsole.menuConsole();
    }

    @Test
    public void printAllDishes() {
        configAndConsole.printAllDishes();
    }

//    @Test
//    public void searchDishes() {
//        configAndConsole.searchDishes();
//    }
}