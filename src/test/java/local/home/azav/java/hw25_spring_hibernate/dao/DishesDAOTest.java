package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Dish;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springhibernatetest.xml" })
public class DishesDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DishesDAO dishesDAO;
    private int dishesId;

    // Проверяем получение контекста
    @Test
    public void testDataSource() {
        assertNotNull(sessionFactory);
        assertNotNull(dishesDAO);
    }

    // Тест получения непустого списка всех блюд
    @Test
    public void testGetAll() {
        List<Dish> listDish = dishesDAO.getAll();
        assertNotNull("Список не пуст", listDish);
        for (Dish dish : listDish) {
            System.out.println(dish);
        }
    }

    // Тест не пустого списка по части имени блюда
    @Test
    public void testGetByNameNotNull() {
        assertNotNull("Список не пуст", dishesDAO.getByName("орн"));
    }

    // Тест соответствия размера списка
    @Test
    public void testGetByNameSize() {
        assertEquals(3, dishesDAO.getByName("орн").size());
    }

    // Тест получения по иденту
    @Test
    public void testGetById() {
        assertEquals("Горное мороженое", dishesDAO.getById(2).getName());
    }

    @Test
    public void insertDish() {
        dishesDAO.insertDish("Хрючило N 5");
        List<Dish> listDish = dishesDAO.getByName("Хрючило N 5");
        if (listDish.size() < 1) { fail("No select string!"); }
        assertEquals("Хрючило N 5",listDish.get(0).getName());
    }

    @Test
    public void deleteDish() {
        List<Dish> listDish = dishesDAO.getByName("Хрючило N 5");
        if (listDish.size() < 1) {
            fail("No select string!");
        }
        dishesId = listDish.get(0).getDishesId();
        dishesDAO.deleteDish(dishesId);
        List<Dish> listDishAfterDelete = dishesDAO.getByName("Хрючило N 5");
        assertTrue(listDish.size() > listDishAfterDelete.size());
    }
}