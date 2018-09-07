package local.home.azav.java.hw24_spring_jdbc;

import local.home.azav.java.hw24_spring_jdbc.dao.DishesDAO;
import local.home.azav.java.hw24_spring_jdbc.dao.RecipesDAO;
import local.home.azav.java.hw24_spring_jdbc.model.Dish;
import local.home.azav.java.hw24_spring_jdbc.model.Recipe;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@Configu1ration
public class ConfigAndConsole {
    private static final Logger LOG = Logger.getLogger(ConfigAndConsole.class.getName());
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
                LOG.log(Level.INFO,"Введите цифру от 1 до 7");
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
                    LOG.log(Level.INFO,"Выход!");
                    return;
                default:
                    LOG.log(Level.INFO,"Введите цифру от 1 до 7");
            }
        }
    }

    void menuConsole() {
        LOG.log(Level.INFO,"--------Меню---------\n" +
                "1. Список блюд.\n" +
                "2. Поиск по наименованию блюда.\n" +
                "3. Показать ингредиенты по номеру блюда.\n" +
                "4. Добавление блюда.\n" +
                "5. Добавление ингредиента к блюду.\n" +
                "6. Удаление блюда.\n" +
                "7. Выход из программы.\n" +
                "\nВведите пункт меню: ");
    }

    void printAllDishes() {
        LOG.log(Level.INFO,"------- Список блюд -------");
        printList(dishesDAO.getAll());
        LOG.log(Level.INFO,"---------------------------");
    }

    <T> int printList(List<T> list) {
        for (T t : list) {
            LOG.log(Level.INFO,"> {0}",t);
        }
        return list.size();
    }

    private void searchDishes() {
        LOG.log(Level.INFO,"Введите название блюда для поиска: ");
        String searchName = scanner.nextLine();
        List<Dish> dishesList = dishesDAO.getByName(searchName);
        LOG.log(Level.INFO,"Найдено {0} блюд:", dishesList.size());
        printList(dishesList);
    }

    private void printRecipeId() {
        LOG.log(Level.INFO,"Введите идентификатор блюда для печати ингредиентов: ");
        int intInput = Integer.parseInt(scanner.nextLine());
        LOG.log(Level.INFO,"Блюдо: {0}", dishesDAO.getById(intInput));
        List<Recipe> recipeList = recipesDAO.getById(intInput);
        printList(recipeList);
    }

    private void addDish() {
        LOG.log(Level.INFO,"Введите название нового блюда: ");
        String newName = scanner.nextLine();
        int result = dishesDAO.insertDish(newName);
        if (result == 1) {
            LOG.log(Level.INFO,"Добавлено {0} блюдо: {1}", new Object[] {result, newName});
        } else {
            LOG.log(Level.INFO,"Блюдо {0} не добавилось!", newName);
        }
    }

    private void addIngredient() {
        LOG.log(Level.INFO,"Введите идентификатор блюда, которому добавляем ингредиент: ");
        int intInput = Integer.parseInt(scanner.nextLine());
        LOG.log(Level.INFO,"Введите название ингредиента: ");
        String newName = scanner.nextLine();
        LOG.log(Level.INFO,"Введите количество ингредиента, в граммах: ");
        int intValue = Integer.parseInt(scanner.nextLine());
        int result = recipesDAO.insertIngredient(intInput, newName, intValue);
        if (result == 1) {
            LOG.log(Level.INFO,"Добавлен {0} ингредиент: {1} к блюду {2}", new Object[] {result,newName,intInput});
        } else {
            LOG.log(Level.INFO,"Ингредиент {0} не добавился!", newName);
        }
    }

    private void deleteDish() {
        LOG.log(Level.INFO,"Введите идентификатор блюда для удаления: ");
        int intInput = Integer.parseInt(scanner.nextLine());
        int result = recipesDAO.deleteRecipe(intInput);
        if (result > 0) {
            LOG.log(Level.INFO,"Удалено {0} ингредиентов у блюда {1}", new Object[] {result,intInput});
        } else {
            LOG.log(Level.INFO,"Ингредиентов не удалено!");
        }
        result = dishesDAO.deleteDish(intInput);
        if (result == 1) {
            LOG.log(Level.INFO,"Удалено {0} блюдо {1}", new Object[] {result,intInput});
        } else {
            LOG.log(Level.INFO,"Блюдо не удалено!");
        }
    }
}
