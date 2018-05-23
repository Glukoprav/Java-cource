package local.home.azav.java.collectionUtils;

import javafx.print.Collation;

import java.util.*;

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
    // Для эксперимента создал новый список с заданным размером и передал ему ссылку
    public static <T> List limit(List<? extends T> source, int size) {
        List<?> list = new ArrayList<>(size);
        list = source;
        return list;
    }

    public static <T> void add(List<T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? extends T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    // true если первый лист содержит все элементы второго
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

    /** Переопределенный метод от интерфейса Comparable */
    @Override
    public int compareTo(T t) {
        if (this.tin.hashCode() > t.hashCode()) {
            return 1;
        } else if (this.tin.hashCode() < t.hashCode()) {
            return -1;
        } else {
            return 0;
        }
    }
    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
     //implements Comparable<T>
    public static <T extends Comparable<? super T>> List range(List<T> list, T min, T max) {
        List<T> listout;
        Collections.sort(list);
        int indMin = list.indexOf(min);
        int indMax = list.lastIndexOf(max);
        listout = list.subList(indMin,indMax+1);
        return listout;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через !!Comparable.  !?! Может Comparator???
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<T> list, T min, T max, Comparator comparator) {
        List<T> listout;
        Collections.sort(list, comparator);
        int indexMin = list.indexOf(min);
        int indexMax = list.lastIndexOf(max);
        listout = list.subList(indexMin,indexMax+1);
        return listout;
    }


    public static void main(String[] args) {
        System.out.println(range(Arrays.asList(8,1,3,5,4,3,6,4), 3, 6).toString());
    }
}
