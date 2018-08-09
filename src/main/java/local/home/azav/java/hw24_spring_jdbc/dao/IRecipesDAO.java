package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Recipe;

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
    int insertIngredient(int intId, String newName, int intValue);

    /**
     * Удаление всех ингредиентов у блюда по иденту
     */
    int deleteRecipe(int intId);
}
