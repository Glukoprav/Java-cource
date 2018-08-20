package local.home.azav.java.hw6_reflection.gettercounter;

import java.util.List;

/**
 * Интерфейс счетчика геттеров в классе
 */
public interface GetterCounter {
    /**
     * Возвращает колличество геттеров в переданном класс
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    int calcGetterCount(Class<?> clazz);

    /**
     * Возвращает список геттеров в переданном классе.
     *
     * @param clazz класс в котором необходимо прочитать геттеры.
     * @return возвращает список строк - имена найденных геттеров.
     */
    public List<String> arrayGetterCount(Class<?> clazz);
}
