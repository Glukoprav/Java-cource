package local.home.azav.java.hw6_reflection.gettercounter;

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
}
