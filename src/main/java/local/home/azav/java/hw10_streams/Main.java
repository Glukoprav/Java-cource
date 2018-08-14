package local.home.azav.java.hw10_streams;

import local.home.azav.java.hw2_person.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // создаем тестовую коллекцию
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(true,"Саша", 30));
        personList.add(new Person(true,"Женя", 40));
        personList.add(new Person(true,"Федя", 20));
        personList.add(new Person(false,"Марфа Потаповна", 30));

        // проверяем работу и преобразование
        Map<String, Person> personMap = Streams.of(personList)
                .filter( p -> p.isMan() && p.getAge() > 25)                                                            // отберем мужиков старше 25
                .transform( p -> new Person(false, p.getName(), p.getAge() + 5))                              // сделаем вид, что они - дамы
                .toMap( p -> p.getName(), p -> p);                                                                     // и отправим в женский монастырь.
        System.out.println(personList.toString());
        System.out.println(personMap);
    }
}
