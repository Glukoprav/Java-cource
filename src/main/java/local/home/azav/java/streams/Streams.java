package local.home.azav.java.streams;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    List<T> list;

    public Streams(List list) {
        this.list = list;
    }

    // статический метод, который принимает коллекцию и создает новый объект Streams
    public static Streams of(List list) {
        Streams streams = new Streams(list);
        return streams;
    }

    // оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде
    public Streams filter(Predicate<T> r) {
           r.test();
        return this;
    }

    // преобразует элемент в другой
    public Streams transform(........) {
           ...
        return this;
    }

    // принимает 2 лямбды для создания мапы, в одной указывается,
    // что использовать в качестве ключа, в другой, что в качестве значения
    public Map toMap(........) {
       return ;
    }

}
