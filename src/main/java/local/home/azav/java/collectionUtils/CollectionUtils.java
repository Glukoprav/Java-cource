package local.home.azav.java.collectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Параметризовать методы, используя правило PECS, и реализовать их
public class CollectionUtils<T> implements Comparable<T> {
    private List<? extends T> listin;
    private T tin;


    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static List<?> newArrayList() {
        List<?> list = new ArrayList<>();
        return list;
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    // Что надо сделать???
    public static <T> List limit(List<? extends T> source, int size) {

        return null;
    }

    public static <T> void add(List<T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? extends T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    // true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        boolean boo = false;
        for (T ter : c2) {
            if (c1.contains(ter)) {
                boo = true;
                break;
            }
        }
        return boo;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<? extends T> list, T min, T max) {
        List<T> listout = new ArrayList<>();
        CollectionUtils<T> colUtils = new CollectionUtils<>();
        colUtils.listin = list;
        for (T ter : colUtils.listin) {
            colUtils.tin = ter;
            if (colUtils.compareTo(min) >= 0 & colUtils.compareTo(max) <= 0) {
                listout.add(ter);
            }
        }
        return listout;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static List range(List list, Object min, Object max, Comparator comparator) {
        return null;
    }

    @Override
    public int compareTo(T t) {
        if (this.tin > t) {
            return 1;
        } else if (this.tin < t) {
            return -1;
        } else {
            return 0;
        }
    }
}
