package local.home.azav.java.streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

// Задание:
// Реализовать класс похожий на java.util.stream.Stream
// Использование этого класса должно выглядеть примерно так:
// List<Person> someCollection = ...
//Map m = Streams.of(someCollection)
//         .filter(p -> p.getAge() > 20)
//         .transform( p -> new Person(p.geAge() + 30)))
//         .toMap(p -> p.geName(), p -> p);
// Streams.of() - статический метод, который принимает коллекцию и создает новый объект Streams
// filter() - оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде.
// transform() - преобразует элемент в другой.
// toMap - принимает 2 лямбды для создания мапы, в одной указывается, что использовать в качестве ключа, в другой, что в качестве значения.
//   После выполнения всех операций коллекция someCollection не должна поменяться.
//   Класс надо параметризовать используя правило PECS
public class Streams<T> {
    private final List<T> list;

    public Streams(List<? extends T> list) {
        this.list = new ArrayList<>(list);
    }

    /**
     * Статический метод, который принимает коллекцию и создает новый объект Streams.
     *
     * @param list входная коллеция.
     * @return объект Streams.
     */
    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    /**
     * Оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде.
     *
     * @param streamsPredicate условие отбора в виде лямбды.
     * @return объект Streams.
     */
    public Streams<T> filter(Predicate<? super T> streamsPredicate) {
        for (T t : list) {
            if (!streamsPredicate.test(t)) {
                list.remove(t);
            }
        }
        return this;
    }

    /**
     * Преобразует каждый элемент колллекции Streams в другой.
     *
     * @param tran условие преобразования в виде лямбды.
     * @return объект Streams.
     */
    public Streams<T> transform(Function<? super T, ? extends T> tran) {
        for (T t : list) {
            int index = list.indexOf(t);
            T transNew = (T) tran.apply(t);
            list.set(index, transNew);
        }
        return this;
    }

    /**
     * Принимает 2 лямбды для создания мапы, в одной указывается,
     * что использовать в качестве ключа, в другой, что в качестве значения.
     * @param keyMap условие задания ключа в виде лямбды.
     * @param keyValue условие задания значения в виде лямбды.
     * @return результат в виде Map.
     */
    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMap, Function<? super T, ? extends V> keyValue) {
        Map<K, V> map = new TreeMap<>();
        for (T t : list) {
            K keyNew = (K) keyMap.apply(t);
            V valueNew = (V) keyValue.apply(t);
            map.put(keyNew, valueNew);
        }
        return map;
    }
}
