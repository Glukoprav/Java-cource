package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Recipe;

import java.util.List;

public interface IRecipesDAO {

    /**
     * Взять все строки ингредиентов
     */
    List<Recipe> getAll();

    /**
     * Взять рецепт по иденту блюда
     */
    List<Recipe> getById(int dishesid);

    /**
     * Добавление ингредиента к блюду по иденту
     */
    void insertIngredient(int intId, String newName, int intValue);

    /**
     * Удаление всех ингредиентов у блюда по иденту
     */
    void deleteRecipe(int intId);
}
