package local.home.azav.java.hw24_spring_jdbc.dao;

import local.home.azav.java.hw24_spring_jdbc.model.Dish;

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
    int insertDish(String newName);

    /**
     * Удаление блюда со всеми ингредиентами
     */
    int deleteDish(int dishesId);
}
