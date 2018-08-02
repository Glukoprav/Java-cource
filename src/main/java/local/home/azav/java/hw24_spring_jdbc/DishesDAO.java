package local.home.azav.java.hw24_spring_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Класс работы с наименованиями блюд
 */
@Component
public class DishesDAO {
    private DataSource dataSource;

    @Autowired
    public DishesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
