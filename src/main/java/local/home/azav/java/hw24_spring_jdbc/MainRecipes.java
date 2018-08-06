package local.home.azav.java.hw24_spring_jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Задание к лекции "24_SpringJDBC, Transactions":
 * Разработать консольное приложение для хранения рецептов.
 * Функциональность:
 * - поиск рецепта по имени или части имени блюда;
 * - добавление рецепта - рецепт состоит из множества ингридиентов
 * и их количественного состава;
 * - удаление блюда.
 */
//@Configuration
public class MainRecipes {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("configcontext.xml");
        ConfigAndConsole console = context.getBean(ConfigAndConsole.class);
        console.makeConsole();
    }
}
