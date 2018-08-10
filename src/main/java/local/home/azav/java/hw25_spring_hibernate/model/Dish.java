package local.home.azav.java.hw25_spring_hibernate.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Класс - блюдо
 * Список блюд в БД - таблица dishes
 */
@Entity
@Table(name = "DISHES")
public class Dish implements Serializable {
    @Id
    @Column( name = "DISHESID" )
    private int dishesId;
    @Column( name = "NAME" )
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
