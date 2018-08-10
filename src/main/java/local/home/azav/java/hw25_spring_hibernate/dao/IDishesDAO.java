package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Dish;

import java.util.List;

public interface IDishesDAO {

    /**
     * Получение списка всех блюд
     */
    List<Dish> getAll();

    /**
     * Получение блюда по имени или части имени
     */
    List<Dish> getByName(String name);

    /**
     * Получение блюда по идентификатору
     */
    Dish getById(int dishesId);

    /**
     * Добавление нового блюда
     */
    void insertDish(String newName);

    /**
     * Удаление блюда со всеми ингредиентами
     */
    void deleteDish(int dishesId);
}
