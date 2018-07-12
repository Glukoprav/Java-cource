package local.home.azav.java.hw4_1_countmap;

import java.util.*;

public class CountMapIml<T> implements CountMap<T> {
    private List<T> list;

    /**
     * Конструктор списка
     */
    public CountMapIml() {
        list = new ArrayList<>();
    }

    /**
     * Добавляет элемент в этот контейнер.
     */
    @Override
    public void add(T t) {
        list.add(t);
    }

    /**
     * Возвращает количество добавлений данного элемента
     */
    @Override
    public int getCount(T t) {
        int count = 0;
        for (T ter : list) {
            if (ter.equals(t)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Удаляет элемент из контейнера и возвращает количество его добавлений(до удаления)
     */
    @Override
    public int remove(T t) {
        int count = getCount(t);
        list.remove(list.indexOf(t));
        return count;
    }

    /**
     * Количество разных элементов
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
     */
    @Override
    public void addAll(CountMap<? extends T> source) {
        list.addAll((Collection<? extends T>) source);
    }

    /**
     * Вернуть java.util.Map. Ключ - добавленный элемент, значение - количество его добавлений
     */
    @Override
    public Map toMap() {
        Map<T, Integer> hash = new HashMap<>();
        for (T ter : list) {
            if (!hash.containsKey(ter)) {
                hash.put(ter, getCount(ter));
            }
        }
        return hash;
    }

    /**
     * Тот же самый контракт как и toMap(), только всю информацию записать в destination
     */
    @Override
    public void toMap(Map destination) {
        for (T ter : list) {
            if (!destination.containsKey(ter)) {
                destination.put(ter, getCount(ter));
            }
        }
    }
}
