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
@ContextConfiguration(locations = {"classpath:/springhibernatetest.xml"})
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
        assertEquals(4, recipesDAO.getById(3).size());
    }

    @Test
    public void insertIngredient() {
        recipesDAO.insertIngredient(5, "Козел прыгучий", 40000);
        recipesDAO.insertIngredient(5, "Вода горная", 20000);
        recipesDAO.insertIngredient(5, "Костер на 8 часов", 50000);
        recipesDAO.insertIngredient(5, "Соль", 50);
        assertNotNull(recipesDAO.getById(5));
    }

    @Test
    public void deleteRecipe() {
        recipesDAO.deleteRecipe(5);
        assertEquals(0, recipesDAO.getById(5).size());
    }
}
