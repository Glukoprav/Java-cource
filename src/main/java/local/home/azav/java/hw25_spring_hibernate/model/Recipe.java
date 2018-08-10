package local.home.azav.java.hw25_spring_hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс - рецепт из ингредиентов
 * Список рецептов блюд в БД - таблица recipes
 */
@Entity
@Table(name = "RECIPES")
public class Recipe implements Serializable {

    @Column( name = "DISHESID" )
    private int dishesId;
    @Column( name = "INGREDIENT" )
    private String ingredient;
    @Column( name = "VALUE" )
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
