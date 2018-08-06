package local.home.azav.java.hw24_spring_jdbc;

import local.home.azav.java.hw24_spring_jdbc.dao.DishesDAO;
import local.home.azav.java.hw24_spring_jdbc.dao.RecipesDAO;
import local.home.azav.java.hw24_spring_jdbc.model.Dish;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

//@Configuration
public class ConfigAndConsole {
    private RecipesDAO recipesDAO;
    private DishesDAO dishesDAO;
    private DataSource dataSource;
    private Scanner scanner = new Scanner(System.in);

    public ConfigAndConsole(RecipesDAO recipesDAO, DishesDAO dishesDAO, DataSource dataSource) {
        this.recipesDAO = recipesDAO;
        this.dishesDAO = dishesDAO;
        this.dataSource = dataSource;
    }

    // Метод консольного меню
    protected void MakeConsole() {
        printAllDishes();
        while (true) {
            menuConsole();
            int userInput = 0;
            try {
                userInput = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите цифру от 1 до 5");
                continue;
            }
            switch (userInput) {
                case 1:
                    printAllDishes();
                    break;
                case 2:
                    searchDishes();
                    break;
                case 3:
                    //addDish();
                    break;
                case 4:
                    //deleteDish();
                    break;
                case 5:
                    System.out.println("Выход!");
                    return;
                default:
                    System.out.println("Введите цифру 1-4");
            }
        }
    }

    private void menuConsole() {
        System.out.println("1. Список блюд.\n" +
                "2. Поиск по наименованию.\n" +
                "3. Добавление блюда.\n" +
                "4. Удаление блюда.\n" +
                "5. Выход из программы.\n" +
                "\nВведите пункт меню: ");
    }

    private void printAllDishes() {
        System.out.println("------- Список блюд -------");
        for (Dish dish : dishesDAO.getAll()) {
            System.out.println(dish.toString());
        }
        System.out.println("---------------------------");
    }

    private void printDishesList(List<Dish> list) {
        System.out.println("------- Список найденных блюд -------");
        for (Dish dish : list) {
            System.out.println(dish.toString());
        }
    }

    private void searchDishes() {
        System.out.print("Введите название блюда для поиска: ");
        String searchName = scanner.nextLine();
        List<Dish> dishesList = dishesDAO.getByName(searchName);
        System.out.println("Найдено " + dishesList.size() + " блюд");
        printDishesList(dishesList);
    }
}
