package local.home.azav.java.hw24_spring_jdbc.model;

/**
 * Класс - рецепт из ингредиентов
 * Список рецептов блюд в БД - таблица recipes
 */

public class Recipe {
    private int dishesId;
    private String ingredient;
    private int value;

    public Recipe(String ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe(int dishesId, String ingredient, int value) {
        this.dishesId = dishesId;
        this.ingredient = ingredient;
        this.value = value;
    }

    public int getDishesId() {
        return dishesId;
    }

    public void setDishesId(int dishesId) {
        this.dishesId = dishesId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Ингридиент: " + ingredient + ", количество: " + value + " гр.";
    }
}
