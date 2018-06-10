package local.home.azav.java.streams;

import local.home.azav.java.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // создаем тестовую коллекцию
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(true,"Вася", 30));
        personList.add(new Person(true,"Миша", 40));
        personList.add(new Person(true,"Петя", 20));
        // проверяем работу и преобразование
        Map<String, Person> personMap = Streams.of(personList)
                .filter(p -> p.getAge() < 25)
                .transform(p -> new Person(p.(), p.getAge() + 15))
                .toMap(p -> p.getName(), p -> p);

        System.out.println(personList);
        System.out.println(personMap);
    }
}
