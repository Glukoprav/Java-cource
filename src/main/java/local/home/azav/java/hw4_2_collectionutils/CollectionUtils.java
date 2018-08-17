package local.home.azav.java.hw4_2_collectionutils;

import java.util.*;

import static java.util.stream.Collectors.toList;

// Параметризовать методы, используя правило PECS, и реализовать их
public class CollectionUtils<T> implements Comparable<T> {
    private List<? extends T> listin;
    private T tin;

    public T getTin() {
        return tin;
    }

    public void setTin(T tin) {
        this.tin = tin;
    }

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    // Ограничил список заданным размером через Stream API
    public static <T> List limit(List<? extends T> source, int size) {
        return source.stream().limit(size).collect(toList());
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

    // Переопределенный метод от интерфейса Comparable   @NotNull
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
    // implements Comparable<T>
    public static <T extends Comparable<? super T>> List range(List<T> list, T min, T max) {
        List<T> listout;
        Collections.sort(list);
        int indMin = list.indexOf(min);
        int indMax = list.lastIndexOf(max);
        listout = list.subList(indMin, indMax + 1);
        return listout;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через переданный Comparator
    public static <T> List range(List<T> list, T min, T max, Comparator<T> comparator) {
        List<T> listout;
        list.sort(comparator);
        int indMin = list.indexOf(min);
        int indMax = list.lastIndexOf(max);
        listout = list.subList(indMin, indMax + 1);
        return listout;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Вариант со Stream API
    public static <T> List rangeStream(List<T> list, T min, T max) {
        return list.stream()
                .filter(o -> o.hashCode() >= min.hashCode() && o.hashCode() <= max.hashCode())
                .sorted()
                .collect(toList());
    }

    /**
     * Проверяем все методы range - без лямбд и с лямбдами.
     */
    public static void main(String[] args) {

        // 1. Тест с имплементированным компаратором
        System.out.println("С имплементом: " + range(Arrays.asList(8, 1, 3, 5, 2, 5, 1, 6, 5, 4, 3, 6, 4), 3, 6).toString());

        // 2. Тест с вызовом внешнего компаратора
        // Определяем компаратор в виде метода для теста
        Comparator<Integer> compar = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 - o2);
            }
        };
        System.out.println("Внешний метод: " + range(Arrays.asList(8, 1, 3, 5, 2, 5, 1, 6, 5, 4, 3, 6, 4), 3, 6, compar).toString());

        // 3. Тест с вызовом внешнего компаратора
        // Определяем компаратор в виде лямбды для теста
        Comparator<Integer> compar2 = (o1, o2) -> (o1 - o2);
        System.out.println("Внешняя ЛямбдА: " + range(Arrays.asList(8, 1, 3, 5, 2, 5, 1, 6, 5, 4, 3, 6, 4), 3, 6, compar2).toString());

        // 4. Тест с компаратором в виде анонимного метода
        System.out.println("Внутренний метод: " + range(Arrays.asList(8, 1, 3, 5, 2, 5, 6, 5, 4, 3, 6, 4), 3, 6,
                new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1.hashCode() > o2.hashCode()) {
                            return 1;
                        }
                        return (o1.hashCode() < o2.hashCode()) ? -1 : 0;
                    }
                }).toString());

        // 5. Тест с компаратором в виде анонимной лямбды
        System.out.println("Внутренняя ЛямбдА: " + range(Arrays.asList(8, 1, 3, 5, 2, 5, 1, 6, 5, 4, 3, 6, 4), 3, 6,
                (o1, o2) -> (o1 - o2)).toString());

        // 6. Тест с использованием Stream API
        System.out.println("Stream API: " + rangeStream(Arrays.asList(8, 1, 3, 5, 2, 5, 1, 6, 5, 4, 3, 6, 4), 3, 6).toString());
    }
}
