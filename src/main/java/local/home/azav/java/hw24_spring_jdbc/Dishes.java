package local.home.azav.java.hw24_spring_jdbc;

/**
 * Класс - блюдо
 * Список блюд в БД - таблица dishes
 */
public class Dishes {
    private int dishes_id;
    private String name;

    public Dishes(int dishes_id, String name) {

        this.dishes_id = dishes_id;
        this.name = name;
    }
    public int getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(int dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
