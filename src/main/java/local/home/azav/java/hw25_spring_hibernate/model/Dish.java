package local.home.azav.java.hw25_spring_hibernate.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс - блюдо
 * Список блюд в БД - таблица dishes
 */
@Entity
@Table(name = "DISHES")
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "DISHESID" )
    private int dishesId;
    @Column( name = "NAME" )
    private String name;

//    @OneToMany(mappedBy = "DISHES", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Recipe> recipe;

    public Dish() {
    }

    public Dish(String name) {
        this.name = name;
    }

    public Dish(int dishesId, String name) {
        this.dishesId = dishesId;
        this.name = name;
//        recipe = new ArrayList<>();
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
