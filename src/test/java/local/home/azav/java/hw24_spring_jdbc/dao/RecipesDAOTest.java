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
        recipesDAO.insertIngredient(4, "Козел прыгучий", 40000);
        recipesDAO.insertIngredient(4, "Вода горная", 20000);
        recipesDAO.insertIngredient(4, "Костер на 8 часов", 50000);
        recipesDAO.insertIngredient(4, "Соль", 50);
        assertNotNull(recipesDAO.getById(4));
    }

    @Test
    public void testDeleteRecipe() {
        recipesDAO.deleteRecipe(4);
        assertEquals(0, recipesDAO.getById(4).size());
    }
}


//    @Mock
//    DataSource dataSource;
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
//        when(dataSource.getJdbcTemplate()).thenReturn(jdbcTemplate);
//        when(jdbcTemplate.query(anyString(), anyObject()).thenReturn(userNames);
//
//        String retVal = Class.getUserNames("test");
//        assertEquals("bob", retVal);
//    }