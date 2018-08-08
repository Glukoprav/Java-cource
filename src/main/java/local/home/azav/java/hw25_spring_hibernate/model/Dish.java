package local.home.azav.java.hw25_spring_hibernate.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Класс - блюдо
 * Список блюд в БД - таблица dishes
 */
@Entity
@Table(name = "DISHES")
public class Dish {
    @Id
    private int dishesId;

    private String name;

    public Dish(String name) {
        this.name = name;
    }

    public Dish(int dishesId, String name) {
        this.dishesId = dishesId;
        this.name = name;
    }

    public int getDishesId() {
        return dishesId;
    }

    public void setDishesId(int dishesId) {
        this.dishesId = dishesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Номер: " + dishesId + ", Наименование: " + name;
    }
}
