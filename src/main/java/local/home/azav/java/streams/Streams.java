package local.home.azav.java.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Streams<T>  {

    // статический метод, который принимает коллекцию и создает новый объект Streams
    public static Streams of(List<?> list) {
        Stream<T> stream = new;
        return stream;
    }

    // оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде
    public Streams filter(........) {
           ...
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
