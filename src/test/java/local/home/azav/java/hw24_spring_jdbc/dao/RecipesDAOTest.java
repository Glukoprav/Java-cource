package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/configcontexttest.xml"})
public class RecipesDAOTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RecipesDAO recipesDAO;

    // Проверяем получение контекста
    @Test
    public void testDataSource() {
        assertNotNull(dataSource);
        assertNotNull(recipesDAO);
    }

    // Тест получения непустого списка всех ингредиентов
    @Test
    public void testGetAll() {
        List<Recipe> listRecipe = recipesDAO.getAll();
        assertNotNull("Список не пуст", listRecipe);
        for (Recipe recipe : listRecipe) {
            System.out.println(recipe);
        }
    }

    // Тест получения по иденту блюда списка ингредиентов (рецепт)
    // и проверка его количества
    @Test
    public void testgetByDishId() {
        assertEquals(3, recipesDAO.getById(2).size());
        assertEquals(3, recipesDAO.getById(1).size());
    }

    @Test
    public void testInsertIngredient() {
    }

    @Test
    public void testDeleteRecipe() {
    }
}