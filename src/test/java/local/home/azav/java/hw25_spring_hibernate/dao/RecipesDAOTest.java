package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Recipe;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springhibernatetest.xml" })
public class RecipesDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RecipesDAO recipesDAO;

    @Test
    public void getAll() {
        List<Recipe> listRecipe = recipesDAO.getAll();
        assertNotNull("Список не пуст", listRecipe);
        for (Recipe recipe : listRecipe) {
            System.out.println(recipe);
        }
    }

    @Test
    public void getById() {
        assertEquals(3, recipesDAO.getById(2).size());
        assertEquals(3, recipesDAO.getById(1).size());
        assertEquals(3, recipesDAO.getById(5).size());
    }

    @Test
    public void insertIngredient() {
    }

    @Test
    public void deleteRecipe() {
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