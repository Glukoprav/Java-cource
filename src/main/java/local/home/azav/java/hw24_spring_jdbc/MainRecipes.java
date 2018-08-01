package local.home.azav.java.hw24_spring_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/** Задание к лекции "24_SpringJDBC, Transactions":
 *  Разработать консольное приложение для хранения рецептов.
 *  Функциональность:
 *  - поиск рецепта по имени или части имени блюда;
 *  - добавление рецепта - рецепт состоит из множества ингридиентов
 *    и их количественного состава;
 *  - удаление блюда.
 * */

@Component
public class MainRecipes {
    private DataSource dataSource;
    @Autowired
    private Recipes recipes;
    @Autowired
    public MainRecipes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {

    }
}
