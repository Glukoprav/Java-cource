package local.home.azav.java.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
//
// Streams.of() - статический метод, который принимает коллекцию и создает новый объект Streams
// filter() - оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде.
// transform() - преобразует элемент в другой.
// toMap - принимает 2 лямбды для создания мапы, в одной указывается, что использовать в качестве ключа, в другой, что в качестве значения.
//   После выполнения всех операций коллекция someCollection не должна поменяться.
//   Класс надо параметризовать используя правило PECS
public class Streams<T> {
    private List<T> list;

    public Streams(List<T> list) {
        this.list = list.subList(0, list.size());
    }

    // статический метод, который принимает коллекцию и создает новый объект Streams
    public static <T> Streams of(List<T> list) {
        Streams streams = new Streams(list);
        return streams;
    }

    // оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде
    public Streams<T> filter(Predicate<T> streamsPredicate) {
        List<T> listout = new ArrayList<>();
        for (T t : list) {
            if (streamsPredicate.test(t)) {
                listout.add(t);
            }
        }
        list = listout;
        return this;
    }

    // преобразует элемент в другой
    public Streams transform(Function tran) {
        List<T> listout = new ArrayList<>();
        for (T t : list) {
            T transNew = (T) tran.apply(t);
            listout.add(transNew);
        }
        list = listout;
        return this;
    }

    // принимает 2 лямбды для создания мапы, в одной указывается,
    // что использовать в качестве ключа, в другой, что в качестве значения
    public <K, V> Map<K, V> toMap(Function keyMap, Function keyValue) {
        Map<K, V> map = new TreeMap<>();
        for (T t : list) {
            K keyNew = (K) keyMap.apply(t);
            V valueNew = (V) keyValue.apply(t);
            map.put(keyNew, valueNew);
        }
        return map;
    }
}
