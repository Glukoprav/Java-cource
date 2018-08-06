package local.home.azav.java.hw24_spring_jdbc.model;

/**
 * Класс - блюдо
 * Список блюд в БД - таблица dishes
 */
public class Dish {
    private int dishesId;
    private String name;

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
