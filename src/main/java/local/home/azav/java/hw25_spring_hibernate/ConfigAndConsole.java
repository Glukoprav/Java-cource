package local.home.azav.java.hw25_spring_hibernate;

import local.home.azav.java.hw25_spring_hibernate.dao.DishesDAO;
import local.home.azav.java.hw25_spring_hibernate.dao.RecipesDAO;
import local.home.azav.java.hw25_spring_hibernate.model.Dish;
import local.home.azav.java.hw25_spring_hibernate.model.Recipe;

import java.util.List;
import java.util.Scanner;

//@Configu1ration
public class ConfigAndConsole {
    private RecipesDAO recipesDAO;
    private DishesDAO dishesDAO;
    private Scanner scanner = new Scanner(System.in);

    public ConfigAndConsole(RecipesDAO recipesDAO, DishesDAO dishesDAO) {
        this.recipesDAO = recipesDAO;
        this.dishesDAO = dishesDAO;
    }

    // Метод обработки консольного меню
    void makeConsole() {
        printAllDishes();
        while (true) {
            menuConsole();
            int intInput = 0;
            try {
                intInput = Integer.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите цифру от 1 до 7");
                continue;
            }
            switch (intInput) {
                case 1:
                    printAllDishes();
                    break;
                case 2:
                    searchDishes();
                    break;
                case 3:
                    printRecipeId();
                    break;
                case 4:
                    addDish();
                    break;
                case 5:
                    addIngredient();
                    break;
                case 6:
                    deleteDish();
                    break;
                case 7:
                    System.out.println("Выход!");
                    return;
                default:
                    System.out.println("Введите цифру от 1 до 7");
            }
        }
    }

    private void menuConsole() {
        System.out.println("--------Меню---------\n" +
                "1. Список блюд.\n" +
                "2. Поиск по наименованию блюда.\n" +
                "3. Показать ингредиенты по номеру блюда.\n" +
                "4. Добавление блюда.\n" +
                "5. Добавление ингредиента к блюду.\n" +
                "6. Удаление блюда.\n" +
                "7. Выход из программы.\n" +
                "\nВведите пункт меню: ");
    }

    private void printAllDishes() {
        System.out.println("------- Список блюд -------");
        printList(dishesDAO.getAll());
        System.out.println("---------------------------");
    }

    <T> int printList(List<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
        return list.size();
    }

    private void searchDishes() {
        System.out.print("Введите название блюда для поиска: ");
        String searchName = scanner.nextLine();
        List<Dish> dishesList = dishesDAO.getByName(searchName);
        System.out.println("Найдено " + dishesList.size() + " блюд:");
        printList(dishesList);
    }

    private void printRecipeId() {
        System.out.print("Введите идентификатор блюда для печати ингредиентов: ");
        int intInput = Integer.parseInt(scanner.nextLine());
        System.out.println("Блюдо: " + dishesDAO.getById(intInput).toString());
        List<Recipe> recipeList = recipesDAO.getById(intInput);
        printList(recipeList);
    }

    private void addDish() {
        System.out.print("Введите название нового блюда: ");
        String newName = scanner.nextLine();
        dishesDAO.insertDish(newName);
    }

    private void addIngredient() {
        System.out.print("Введите идентификатор блюда, которому добавляем ингредиент: ");
        int intInput = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите название ингредиента: ");
        String newName = scanner.nextLine();
        System.out.print("Введите количество ингредиента, в граммах: ");
        int intValue = Integer.parseInt(scanner.nextLine());
        recipesDAO.insertIngredient(intInput, newName, intValue);
    }

    private void deleteDish() {
        System.out.print("Введите идентификатор блюда для удаления: ");
        int intInput = Integer.parseInt(scanner.nextLine());
        recipesDAO.deleteRecipe(intInput);
        dishesDAO.deleteDish(intInput);
    }


}
