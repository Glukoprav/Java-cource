package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Dish;
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
public class DishesDAOTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DishesDAO dishesDAO;

    // Проверяем получение контекста
    @Test
    public void testDataSource() {
        assertNotNull(dataSource);
        assertNotNull(dishesDAO);
    }

    // Тест получения непустого списка всех блюд
    @Test
    public void testGetAll() {
        List<Dish> listDish = dishesDAO.getAll();
        assertNotNull("Список не пуст", listDish);
//        for (Dish dish : listDish) {
//            System.out.println(dish);
//        }
    }

    // Тест не пустого списка по части имени блюда
    @Test
    public void testGetByName() {
        List<Dish> listDish = dishesDAO.getByName("орн");
        assertNotNull("Список не пуст", listDish);
        for (Dish dish : listDish) {
            System.out.println(dish);
        }
    }

    // Тест соответствия размера списка
    @Test
    public void testGetByNameSize() {
        assertEquals(2, dishesDAO.getByName("орн").size());
    }

    // Тест получения по иденту
    @Test
    public void testGetById() {
        assertEquals("Горное мороженое", dishesDAO.getById(2).getName());
    }

    @Test
    public void testInsertDish() {
    }

    @Test
    public void testDeleteDish() {
    }
}