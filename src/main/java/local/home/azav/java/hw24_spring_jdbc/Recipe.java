package local.home.azav.java.hw24_spring_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Класс - рецепт из ингредиентов
 * Список рецептов блюд в БД - таблица recipes
 */

public class Recipe {
    private int dishes_id;
    private String ingredient;
    private int value;

    public Recipe(int dishes_id, String ingredient, int value) {
        this.dishes_id = dishes_id;
        this.ingredient = ingredient;
        this.value = value;
    }

    public int getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(int dishes_id) {
        this.dishes_id = dishes_id;
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
        return "Recipes{" +
                "dishes_id=" + dishes_id +
                ", ingredient='" + ingredient + '\'' +
                ", value=" + value +
                '}';
    }
}
